const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {

    //entry: ['babel-polyfill','./src/js/index.js'],
    entry: ['./src/js/index.js'],
    output: {
        path: path.resolve(__dirname,'dist'),
        filename: 'js/bundle.js'
    },
    devServer: {
        static: {
            directory: path.resolve(__dirname, "dist"),
           
        },
    },
    plugins:[
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: './src/index.html'
        })
    ],
    // module: {
    //     rules:[
    //         {
    //             test : /\.js$/,
    //             exclude: /node_modules/,
    //             use: {
    //                 loader: 'babel-loader'
    //             }
    //         }
    //     ]
    // }

};