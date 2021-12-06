const fs = require('fs');
const util = require('util');
const path = require('path');
const readFile = util.promisify(fs.readFile);
const CopyWebpackPlugin = require('./plugins/CopyWebpackPlugin');

const Plugin2 = require('./plugins/Plugin2');

module.exports = {
    mode:'development',
    entry:'/src/index.js',
    plugins:[
        new CopyWebpackPlugin({
            from:'public',
            ignore:['index.html']
        })
    ]
}