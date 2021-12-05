const { Compilation } = require("webpack");

class Plugin2{
    apply(complier){
        complier.hooks.thisCompilation.tap('Plugin2',(compilation)=>{
            //debugger;

            compilation.hooks.additionalAssets.tapAsync('Plugin2',(cb)=>{
                //debugger;

                const content = 'hello plugin~~';

                compilation.assets['config.js'] = {
                    size(){
                        return content.length;
                    },
                    source(){
                        return content;
                    }
                }
                cb();
            })
        })

        
    }
}

module.exports = Plugin2;