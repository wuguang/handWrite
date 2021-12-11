const fs = require('fs');
const path = require('path');
const {getAst,getDeps,getCode} = require('./parser');


class Compiler{
    constructor(options = {}){
        //wepback
        this.options = options;
        this.modules = [];
    }

    run(){

        const filePath = this.options.entry;

        const fileInfo = this.build(filePath);
        this.modules.push(fileInfo);
        this.modules.forEach((fileInfo)=>{
            const deps = fileInfo.deps;
            //map 对象
            for(const relativePath in deps){
                const absolutePath = deps[relativePath];
                const fileInfo = this.build(absolutePath);
                this.modules.push(fileInfo);
            }
        });

        console.log(this.modules);

        const depsGraph = this.modules.reduce((graph,module)=>{
            return {
                ...graph,
                [module.filePath]:{
                    code:module.code,
                    deps:module.deps
                }
            }
        },{});

        console.log(depsGraph);

        this.generate(depsGraph);
    
    }

    build(filePath){
        const ast = getAst(filePath);
        const deps = getDeps(ast,filePath);
        const code = getCode(ast);
        return{
            filePath,
            deps,
            code
        }
    }

    generate(depsGraph){
        const bundle = `
            (function(depsGraph){
                function require(module){
                    function localRequire(relativePath){
                        return require(depsGraph[module].deps[relativePath]);
                    }

                    var exports = {};


                    (function(require,exports,code){
                        eval(code)
                    })(localRequire,exports,depsGraph[module].code)
                    
                    return exports;   
                }

                require('${this.options.entry}');

            })(${JSON.stringify(depsGraph)});
        `;

        const filePath = path.resolve(this.options.output.path,this.options.output.filename);
        fs.writeFileSync(filePath,bundle,'utf-8');
    }
}

module.exports = Compiler;


