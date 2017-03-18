/**
 * @author walid
 * @date 2017/3/10
 * @description 构建插件
 */

// 获取输入参数
// const yargs = require('yargs')
// const argv = yargs.argv
// require('./gen').generate(argv._[0])

let config = {
  baseInfo: {
    applicationId: 'com.syswin.toon.bottom',
    versionCode: 2,
    appIcon: 'toon',
    appName: 'toon通',
    versionName: '1.0.1',
  },
  frame: {
    uri: 'bottom',
    tags: [
      {
        name: '我的',
        uri: 'mine'
      },
      {
        name: '首页',
        uri: 'home'
      },
      {
        name: '发现',
        uri: 'mine'
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
        uri: 'home',
        pkg: 'com.osmartian.small.app.home'
      },
      {
        uri: 'mine',
        pkg: 'com.osmartian.small.app.mine'
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
