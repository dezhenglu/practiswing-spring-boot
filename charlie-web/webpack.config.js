var path = require('path');

module.exports = {
  module: {
    loaders: [
      { test: /\.css$/, loader: 'style-loader!css-loader' },
      { test: /\.woff2?$/, loader: 'url-loader' },
      { test: /\.(ttf|eot|jpg|svg)$/, loader: 'file-loader' }
    ]
  },
  entry: './src/main/js/index.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'build/resources/main/static/js')
  }
};
