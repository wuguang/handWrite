//import globby from 'globby';
const {validate} = require('schema-utils');
const schema = require('./schema.json');
const path = require('path');
import { globby } from 'globby';

class CopyWebpackPlugin{

    constructor(options = {}){
        //校验options 规范
        validate(schema,options,{
            name:"CopyWebpackPlugin"
        });
        this.options = options;
    }

    apply(compiler){
        compiler.hooks.thisCompilation.tap('CopyWebpacPlugin',(compilation)=>{
            compilation.hooks.additionalAssets.tapAsync('CopyWebpackPlugin',async (cb)=>{
                let {from,ignore,to}  = this.options;
                to = to||'.';
                //context的webpack默认值
                const context= compiler.options.context;
                console.log(context);

                const absoluteFrom = path.isAbsolute(from)?from:path.resolve(context,from);
                const paths =  globby(from,{ignore});
                console.log(`paths = ${paths}`);

                
                /*
                let globbyObj = import('globby');
                globbyObj.then(obj=>{
                    console.log(obj);;
                    let globby = {obj};
                    console.log(`----globby=${globby}`);
                    
                    const paths =  globby(from,{ignore});
                    console.log(`paths = ${paths}`);
                    
                }).catch((e)=>{
                    console.log(`e  = ${e}`);
                })
                */
                

            })
        })
    }
}

module.exports = CopyWebpackPlugin;