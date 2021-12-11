const fs = require('fs');
const path = require('path');
const babelParse = require('@babel/parser');
const traverse = require('@babel/traverse').default;
const {transformFromAst}  = require('@babel/core');


function myWebpack(config){
    return new Compiler(config);
}

class Compiler{
    constructor(options = {}){
        this.options = options;
    }

    run(){
        const filePath = this.options.entry;
        const file = fs.readFileSync(filePath,'utf-8');

        const ast = babelParse.parse(file,{
            sourceType:'module',

        });

        console.log(ast);
        const dirname = path.dirname(filePath);
        //
        const deps = {};

        traverse(ast,{
            ImportDeclaration({node}){
                debugger
                console.log(node);
                const relativePath = node.source.value;
                //
                const absolutePath = path.resolve(dirname,relativePath);
                deps[relativePath] = absolutePath;

            }
        })

        console.log(deps);

        const {code} = transformFromAst(ast,null,{
            presets:['@babel/preset-env']
        });

        console.log(code);

    }
}

module.exports = myWebpack;


