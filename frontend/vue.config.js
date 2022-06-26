
module.exports = {
  devServer: {
    proxy: {
      '^/api': {
        target: 'server.it-humke.de:9002',
        ws: true,
        changeOrigin: true,
        pathRewrite: {'^/api':'/api'},
      }
    }
  }
}