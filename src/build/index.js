/**
 * @author walid
 * @date 2017/3/10
 * @description 构建插件
 */

const fs = require('fs-extra')
const path = require('path')
// 安卓路径
const androidPath = path.join(__dirname, '../../android')
// bundle json
const bundlePath = path.join(__dirname, '../../build/output', 'bundle.json')
const ipAddress = require('ip').address()

let config = {
  baseInfo: {
    applicationId: 'com.syswin.toon.bottom',
    versionCode: 2,
    appIcon: 'toon',
    appName: 'toon通平台',
    versionName: '1.0.1',
  },
  frame: {
    uri: 'bottom',
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

// 打包基本信息
function packBase(baseInfo) {
  return new Promise((resolve, reject) => {
    if (!baseInfo.applicationId) {
      reject({
        msg: '请设置applicationId'
      })
      return
    }
    // 初始化applicationId
    let androidGradle = path.join(androidPath, 'build.gradle')
    fs.readFile(androidGradle, (err, data) => {
      if (err) {
        reject(err)
        return
      }
      data = data.toString()
      data = data.replace(/applicationId(.*)\n/, `applicationId = "${baseInfo.applicationId}"\n`)
      data = data.replace(/appName(.*)\n/, `appName = "${baseInfo.appName || 'toon'}"\n`)
      data = data.replace(/appIcon(.*)\n/, `appIcon = "${baseInfo.appIcon || 'toon'}"\n`)
      data = data.replace(/versionCode(.*)\n/, `versionCode = ${baseInfo.versionCode || 1}\n`)
      data = data.replace(/versionName(.*)\n/, `versionName = "${baseInfo.versionName || '1.0.0'}"\n`)
      fs.writeFile(androidGradle, data, (err) => {
        console.log(data)
        console.log('打包基本信息完毕~')
        err ? reject(err) : resolve()
      })
    })
  })
}

// 框架模块配置
function packFrame(frame) {
  return new Promise((resolve, reject) => {
    let androidConfigFile = path.join(androidPath, 'app/src/main/java/com/osmartian/small/Config.java')
    fs.readFile(androidConfigFile, (err, data) => {
      if (err) {
        reject(err)
        return
      }
      data = data.toString().replace(/INDEX_URI(.*)\n/, `INDEX_URI = "${frame.uri}?tags=${encodeURIComponent(JSON.stringify(frame.tags))}";\n`)
      fs.writeFile(androidConfigFile, data, (err) => {
        console.log(data)
        err ? reject(err) : resolve()
      })
    })
  })
}

// 框架模块安装
function packModules(modules) {
  console.log(modules)
  return new Promise((resolve, reject) => {
    fs.writeFile(bundlePath, JSON.stringify(modules), (err) => {
      err ? reject(err) : resolve()
    })
  })
}

packBase(config.baseInfo)
  .then(res => {
    return packFrame(config.frame)
  })
  .then(res => {
    return packModules(config.modules)
  })
  .then(res => {
    console.log('打包APK中~')
  })
