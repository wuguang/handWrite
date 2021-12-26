1.2 优化 resolve 配置


externals
externals 配置选项提供了「从输出的 bundle 中排除依赖」的方法。此功能通常对 library 开发人员来说是最有用的，然而也会有各种各样的应用程序用到它。

如，从 CDN 引入 jQuery，而不是把它打包：

引入链接

<script
  src="https://code.jquery.com/jquery-3.1.0.js"
  integrity="sha256-slogkvB1K3VOkzAI8QITxV3VzpOnkeNVsKvtkYLMjfk="
  crossorigin="anonymous"
></script>
复制代码

配置 externals

const config = {
  //...
  externals: {
    jquery: 'jQuery',
  },
};
复制代码

使用 jQuery

import $ from 'jquery';

$('.my-element').animate(/* ... */);
复制代码
我们可以用这样的方法来剥离不需要改动的一些依赖，大大节省打包构建的时间。




1.2.3 modules
告诉 webpack 解析模块时应该搜索的目录，常见配置如下
const path = require('path');

// 路径处理方法
function resolve(dir){
  return path.join(__dirname, dir);
}

const config = {
  //...
  resolve: {
     modules: [resolve('src'), 'node_modules'],
  },
};
复制代码
告诉 webpack 优先 src 目录下查找需要解析的文件，会大大节省查找时间



1.2.4 resolveLoader
resolveLoader 与上面的 resolve 对象的属性集合相同， 但仅用于解析 webpack 的 loader 包。
一般情况下保持默认配置就可以了，但如果你有自定义的 Loader 就需要配置一下，不配可能会因为找不到 loader 报错

例如：我们在 loader 文件夹下面，放着我们自己写的 loader

我们就可以怎么配置
const path = require('path');

// 路径处理方法
function resolve(dir){
  return path.join(__dirname, dir);
}

const config = {
  //...
  resolveLoader: {
    modules: ['node_modules',resolve('loader')]
  },
};



1.3 noParse  ??

不需要解析依赖的第三方大型类库等，可以通过这个字段进行配置，以提高构建速度
使用 noParse 进行忽略的模块文件中不会解析 import、require 等语法

const config = {
  //...
  module: { 
    noParse: /jquery|lodash/,
    rules:[...]
  }

};


1.4 IgnorePlugin
防止在 import 或 require 调用时，生成以下正则表达式匹配的模块：

requestRegExp 匹配(test)资源请求路径的正则表达式。
contextRegExp 匹配(test)资源上下文（目录）的正则表达式。

new webpack.IgnorePlugin({ resourceRegExp, contextRegExp });
复制代码
以下示例演示了此插件的几种用法。

安装 moment 插件（时间处理库）

$ npm i -S moment
复制代码

配置 IgnorePlugin

// 引入 webpack
const webpack = require('webpack')

const config = {
  ...
  plugins:[ // 配置插件
    ...
    new webpack.IgnorePlugin({
      resourceRegExp: /^\.\/locale$/,
      contextRegExp: /moment$/,
    }),
  ]  
};
复制代码
目的是将插件中的非中文语音排除掉，这样就可以大大节省打包的体积了



1.5.1 thread-loader
配置在 thread-loader 之后的 loader 都会在一个单独的 worker 池（worker pool）中运行

安装

$ npm i -D  thread-loader
复制代码

配置

const path = require('path');

// 路径处理方法
function resolve(dir){
  return path.join(__dirname, dir);
}

const config = {
  //...
  module: { 
    noParse: /jquery|lodash/,
    rules: [
      {
        test: /\.js$/i,
        include: resolve('src'),
        exclude: /node_modules/,
        use: [
          {
            loader: 'thread-loader', // 开启多进程打包
            options: {
              worker: 3,
            }
          },
          'babel-loader',
        ]
      },
      // ...
    ]
  }
};


1.6 利用缓存
利用缓存可以大幅提升重复构建的速度
1.6.1 babel-loader 开启缓存

babel 在转译 js 过程中时间开销比价大，将 babel-loader 的执行结果缓存起来，重新打包的时候，直接读取缓存
缓存位置： node_modules/.cache/babel-loader

具体配置如下：
const config = {
 module: { 
    noParse: /jquery|lodash/,
    rules: [
      {
        test: /\.js$/i,
        include: resolve('src'),
        exclude: /node_modules/,
        use: [
          // ...
          {
            loader: 'babel-loader',
            options: {
              cacheDirectory: true // 启用缓存
            }
          },
        ]
      },
      // ...
    ]
  }
}


那其他的 loader 如何将结果缓存呢？
cache-loader 就可以帮我们完成这件事情
1.6.2 cache-loader

缓存一些性能开销比较大的 loader 的处理结果
缓存位置：node_modules/.cache/cache-loader


安装

$ npm i -D cache-loader
复制代码

配置 cache-loader

const config = {
 module: { 
    // ...
    rules: [
      {
        test: /\.(s[ac]|c)ss$/i, //匹配所有的 sass/scss/css 文件
        use: [
          // 'style-loader',
          MiniCssExtractPlugin.loader,
          'cache-loader', // 获取前面 loader 转换的结果
          'css-loader',
          'postcss-loader',
          'sass-loader', 
        ]
      }, 
      // ...
    ]
  }
}
复制代码
1.6.3 hard-source-webpack-plugin

hard-source-webpack-plugin 为模块提供了中间缓存，重复构建时间大约可以减少 80%，但是在 webpack5 中已经内置了模块缓存，不需要再使用此插件

1.6.5 cache 持久化缓存

通过配置 cache 缓存生成的 webpack 模块和 chunk，来改善构建速度。

const config = {
  cache: {
    type: 'filesystem',
  },
};



2. 优化构建结果
2.1 构建结果分析
借助插件 webpack-bundle-analyzer 我们可以直观的看到打包结果中，文件的体积大小、各模块依赖关系、文件是够重复等问题，极大的方便我们在进行项目优化的时候，进行问题诊断。

安装

$ npm i -D webpack-bundle-analyzer
复制代码

配置插件

// 引入插件
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin


const config = {
  // ...
  plugins:[ 
    // ...
    // 配置插件 
    new BundleAnalyzerPlugin({
      // analyzerMode: 'disabled',  // 不启动展示打包报告的http服务器
      // generateStatsFile: true, // 是否生成stats.json文件
    })
  ],
};
复制代码

修改启动命令

 "scripts": {
    // ...
    "analyzer": "cross-env NODE_ENV=prod webpack --progress --mode production"
  },
复制代码

执行编译命令 npm run analyzer

打包结束后，会自行启动地址为 http://127.0.0.1:8888 的 web 服务，访问地址就可以看到

如果，我们只想保留数据不想启动 web 服务，这个时候，我们可以加上两个配置
new BundleAnalyzerPlugin({
   analyzerMode: 'disabled',  // 不启动展示打包报告的http服务器
   generateStatsFile: true, // 是否生成stats.json文件
})
复制代码
这样再次执行打包的时候就只会产生 state.json 的文件了
2.2 压缩 CSS

安装 optimize-css-assets-webpack-plugin

$ npm install -D optimize-css-assets-webpack-plugin 
复制代码

修改 webapck.config.js 配置

// ...
// 压缩css
const OptimizeCssAssetsPlugin = require('optimize-css-assets-webpack-plugin')
// ...

const config = {
  // ...
  optimization: {
    minimize: true,
    minimizer: [
      // 添加 css 压缩配置
      new OptimizeCssAssetsPlugin({}),
    ]
  },
 // ...
}

// ...



2.4 清除无用的 CSS
purgecss-webpack-plugin 会单独提取 CSS 并清除用不到的 CSS

安装插件

$ npm i -D purgecss-webpack-plugin
复制代码

添加配置

// ...
const PurgecssWebpackPlugin = require('purgecss-webpack-plugin')
const glob = require('glob'); // 文件匹配模式
// ...

function resolve(dir){
  return path.join(__dirname, dir);
}

const PATHS = {
  src: resolve('src')
}

const config = {
  plugins:[ // 配置插件
    // ...
    new PurgecssPlugin({
      paths: glob.sync(`${PATHS.src}/**/*`, {nodir: true})
    }),
  ]
}


2.5 Tree-shaking
Tree-shaking 作用是剔除没有使用的代码，以降低包的体积

webpack 默认支持，需要在 .bablerc 里面设置 model：false，即可在生产环境下默认开启

了解更多 Tree-shaking 知识，推荐阅读 👉🏻 从过去到现在，聊聊 Tree-shaking
module.exports = {
  presets: [
    [
      "@babel/preset-env",
      {
        module: false,
        useBuiltIns: "entry",
        corejs: "3.9.1",
        targets: {
          chrome: "58",
          ie: "11",
        },
      },
    ],
  ],
  plugins: [    
    ["@babel/plugin-proposal-decorators", { legacy: true }],
    ["@babel/plugin-proposal-class-properties", { loose: true }],
  ]
};



3.3 代码懒加载
针对首屏加载不太需要的一些资源，我们可以通过懒加载的方式去实现，下面看一个小🌰

需求：点击图片给图片加一个描述

1. 新建图片描述信息
desc.js
const ele = document.createElement('div')
ele.innerHTML = '我是图片描述'
module.exports = ele
复制代码
2. 点击图片引入描述
index.js
import './main.css';
import './sass.scss'
import logo from '../public/avatar.png'

import '@/fonts/iconfont.css'

const a = 'Hello ITEM'
console.log(a)

const img = new Image()
img.src = logo

document.getElementById('imgBox').appendChild(img)

// 按需加载
img.addEventListener('click', () => {
  import('./desc').then(({ default: element }) => {
    console.log(element)
    document.body.appendChild(element)
  })
})
复制代码
3. 查看效果

点击前




点击后



3.4 prefetch 与 preload
上面我们使用异步加载的方式引入图片的描述，但是如果需要异步加载的文件比较大时，在点击的时候去加载也会影响到我们的体验，这个时候我们就可以考虑使用 prefetch 来进行预拉取
3.4.1 prefetch


prefetch (预获取)：浏览器空闲的时候进行资源的拉取


改造一下上面的代码
// 按需加载
img.addEventListener('click', () => {
  import( /* webpackPrefetch: true */ './desc').then(({ default: element }) => {
    console.log(element)
    document.body.appendChild(element)
  })
})


//剔除console.log

//vue.config.js
const TerserPlugin = require('terser-webpack-plugin')
configureWebpack: {
    optimization: {
      minimizer: [
        new TerserPlugin({
          terserOptions: {
            ecma: undefined,
            warnings: false,
            parse: {},
            compress: {
              drop_console: true,
              drop_debugger: false,
              pure_funcs: ['console.log'] // 移除console
            }
          },
        }),
      ]
    }
  },




https://www.webpackjs.com/contribute/writing-a-plugin/



/*
342421   19621008   7222

342421   19620321   721x
*/