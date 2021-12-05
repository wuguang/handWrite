const { Compilation } = require("webpack");

const fs = require('fs');
const util = require('util');
const path = require('path');
const readFile = util.promisify(fs.readFile);

const webpack = require('webpack');
const {RawSource} = webpack.sources;

class Plugin2{
    apply(complier){
        complier.hooks.thisCompilation.tap('Plugin2',(compilation)=>{
            //debugger;
            compilation.hooks.additionalAssets.tapAsync('Plugin2',async (cb)=>{
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

                const data = await readFile(path.resolve(__dirname,'b.txt'));
                compilation.assets['b.txt'] = new RawSource(data);

                compilation.emitAsset('c.txt',new RawSource(data));

                cb();

            })
        })
    }
}

module.exports = Plugin2;