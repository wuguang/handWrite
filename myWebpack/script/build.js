const myWebpack = require('../lib/selfWebpack');
const config = require('../config/webpack.config');
const compiler = myWebpack(config);

compiler.run();

