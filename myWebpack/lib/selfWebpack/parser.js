const fs = require('fs');
const path = require('path');
const babelParse = require('@babel/parser');
const traverse = require('@babel/traverse').default;
const {transformFromAst}  = require('@babel/core');


const parser = {
    getAst(filePath){
        //const filePath = this.options.entry;
        const file = fs.readFileSync(filePath,'utf-8');

        const ast = babelParse.parse(file,{
            sourceType:'module',
        });
        return ast;
    },
    getDeps(ast,filePath){
        const dirname = path.dirname(filePath);
        //
        const deps = {};
        traverse(ast,{
            ImportDeclaration({node}){
                console.log(node);
                const relativePath = node.source.value;
                //
                const absolutePath = path.resolve(dirname,relativePath);
                deps[relativePath] = absolutePath;

            }
        })

        return deps
    },
    getCode(ast){
        const {code} = transformFromAst(ast,null,{
            presets:['@babel/preset-env']
        });

        return code;
    }
}

module.exports = parser;