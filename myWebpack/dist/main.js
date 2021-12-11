
(function (depsGraph) {
    function require(module) {
        function localRequire(relativePath) {
            return require(depsGraph[module].deps[relativePath]);
        }
        
        var exports = {};
        (function (require, exports, code) {
            eval(code)
        })(localRequire, exports, depsGraph[module].code)

        return exports;
        
        //eval( depsGraph[module].code);
    }

    require('./src/index.js');

})({ "./src/index.js": { "code": "\"use strict\";\n\nvar _add = _interopRequireDefault(require(\"./add.js\"));\n\nvar _count = _interopRequireDefault(require(\"./count.js\"));\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { \"default\": obj }; }\n\nconsole.log((0, _add[\"default\"])(1, 2));\nconsole.log((0, _count[\"default\"])(3, 1));\nsetTimeout(function () {\n  console.log((0, _add[\"default\"])(3, 4));\n  console.log((0, _count[\"default\"])(5, 6));\n}, 1000);", "deps": { "./add.js": "D:\\work\\github\\handWrite\\myWebpack\\src\\add.js", "./count.js": "D:\\work\\github\\handWrite\\myWebpack\\src\\count.js" } }, "D:\\work\\github\\handWrite\\myWebpack\\src\\add.js": { "code": "\"use strict\";\n\nObject.defineProperty(exports, \"__esModule\", {\n  value: true\n});\nexports[\"default\"] = void 0;\n\nfunction add(x, y) {\n  return x + y;\n}\n\nvar _default = add;\nexports[\"default\"] = _default;", "deps": {} }, "D:\\work\\github\\handWrite\\myWebpack\\src\\count.js": { "code": "\"use strict\";\n\nObject.defineProperty(exports, \"__esModule\", {\n  value: true\n});\nexports[\"default\"] = void 0;\n\nfunction count(x, y) {\n  return x - y;\n}\n\nvar _default = count;\nexports[\"default\"] = _default;", "deps": {} } });




let codeObj = {
	"./src/index.js": {
		"code": "\"use strict\";\n\nvar _add = _interopRequireDefault(require(\"./add.js\"));\n\nvar _count = _interopRequireDefault(require(\"./count.js\"));\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { \"default\": obj }; }\n\nconsole.log((0, _add[\"default\"])(1, 2));\nconsole.log((0, _count[\"default\"])(3, 1));\nsetTimeout(function () {\n  console.log((0, _add[\"default\"])(3, 4));\n  console.log((0, _count[\"default\"])(5, 6));\n}, 1000);",
		"deps": {
			"./add.js": "D:\\work\\github\\handWrite\\myWebpack\\src\\add.js",
			"./count.js": "D:\\work\\github\\handWrite\\myWebpack\\src\\count.js"
		}
	},
	"D:\\work\\github\\handWrite\\myWebpack\\src\\add.js": {
		"code": "\"use strict\";\n\nObject.defineProperty(exports, \"__esModule\", {\n  value: true\n});\nexports[\"default\"] = void 0;\n\nfunction add(x, y) {\n  return x + y;\n}\n\nvar _default = add;\nexports[\"default\"] = _default;",
		"deps": {}
	},
	"D:\\work\\github\\handWrite\\myWebpack\\src\\count.js": {
		"code": "\"use strict\";\n\nObject.defineProperty(exports, \"__esModule\", {\n  value: true\n});\nexports[\"default\"] = void 0;\n\nfunction count(x, y) {\n  return x - y;\n}\n\nvar _default = count;\nexports[\"default\"] = _default;",
		"deps": {}
	}
}








