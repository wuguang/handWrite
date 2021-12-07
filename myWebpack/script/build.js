const myWebpack = require('../lib/myWebpack');
const config = require('../config/webpack.config');
const compiler = myWebpack(config);

compiler.run();

