const fs = require('fs');
const util = require('util');
const {resolve} = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');


module.exports = {
    entry:['/src/js/index.js'],
    output:{
        filename:'js/build.js',
        path:resolve(__dirname,'build')
    },
    module:{
        rules:[
            {
                test:/\.less$/,
                use:['style-loader','css-loader','less-loader']
            },
            {
                test:/\.css$/,
                use:['style-loader','css-loader']
            },
            {
                loader:'file-loader',
                options:{
                     name:'[hash:10].[ext]',
                    // outputPath:'media'
                }
             },
            {
                test:/\.(jpg|png|gif)$/,
                loader:'url-loader',
                options:{
                    limit:50*1024,
                    name:'[hash:10].[ext]',
                   // esModule:false,
                   // outputPath:'imgs'
                }
            },{
                test:/\.html$/,
                loader:'html-loader'
            }
        ]
    },
    plugins:[
        new HtmlWebpackPlugin({
            template:'./src/index.html'
        })
    ],
    mode:'development',
    //mode:'production',
    devServer:{
        //contentBase:resolve(__dirname,'build'),
        compress:true,
        port:3000,
        open:true,
        hot:true
    }
}