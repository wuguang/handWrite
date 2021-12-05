const Plugin2 = require('./plugins/Plugin2');

module.exports = {
    mode:'development',
    entry:'/src/index.js',
    plugins:[
        new Plugin2()
    ]
}