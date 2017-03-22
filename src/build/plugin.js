/**
 * @author walid
 * @date 2017/3/10
 * @description 构建插件
 */

// 获取输入参数
// const yargs = require('yargs')
// const argv = yargs.argv
// require('./gen').generate(argv._[0])

const ipAddress = require('ip').address()

let config = {
  baseInfo: {
    applicationId: 'com.syswin.toon.top',
    versionCode: 2,
    appIcon: 'toon',
    appName: 'toon通平台',
    versionName: '1.0.1',
  },
  frame: {
    uri: 'top',
    tags: [
      {
        name: '首页',
        uri: 'home'
      },
      {
        name: '动态',
        uri: `weex?url=${encodeURIComponent(`http://${ipAddress}:12580/dist/weex/views/tweet/app.js`)}`
      },
      {
        name: '我的',
        uri: `weex?url=${encodeURIComponent(`http://${ipAddress}:12580/dist/weex/views/mine/app.js`)}`
      }
    ]
  },
  modules: {
    version: '1.0.0',
    bundles: [
      {
        uri: 'bottom',
        pkg: 'com.osmartian.small.app.bottom'
      },
      {
        uri: 'top',
        pkg: 'com.osmartian.small.app.top'
      },
      {
        uri: 'home',
        pkg: 'com.osmartian.small.app.home'
      },
      {
        uri: 'weex',
        pkg: 'com.osmartian.small.app.weex'
      },
      {
        uri: 'detail',
        pkg: 'com.osmartian.small.app.detail',
        rules: {
          sub: 'Sub'
        }
      },
      {
        uri: 'lib.weex',
        pkg: 'com.osmartian.small.lib.weex'
      },
      {
        uri: 'lib.martian',
        pkg: 'com.osmartian.small.lib.martian'
      },
      {
        uri: 'lib.style',
        pkg: 'com.osmartian.small.lib.style'
      }
    ]
  }
}

// 进入命令行模式
// require('./generator').generate()

// 直接打包apk
require('./toonpack').generate(config)
  .then(res => {
    console.log('res' + res)
  })
  .catch(err => {
    console.log('err' + err)
  })
