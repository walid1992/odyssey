/**
 * @author walid
 * @date 2017/3/10
 * @description
 */
"use strict"

const prompt = require('prompt')
const fs = require('fs-extra')
const path = require('path')
const chalk = require('chalk')
// 安卓路径
const androidPath = path.join(__dirname, '../../android')
// bundle json
const bundlePath = path.join(__dirname, '../../build/output', 'bundle.json')
import packLog from './pack-log.js'

// 打包基本信息
function packBase(baseInfo) {
  return new Promise((resolve, reject) => {
    if (!baseInfo.applicationId) {
      fs.writeFile(packLog, '请设置applicationId')
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
      data = data.replace(/versionCode(.*)\n/, `versionCode = ${baseInfo.versionCode || 1}\n`)
      data = data.replace(/versionName(.*)\n/, `versionName = "${baseInfo.versionName || '1.0.0'}"\n`)
      fs.writeFile(androidGradle, data, (err) => {
        console.log(data)
        fs.writeFile(packLog, '写入applicationID成功~')
        err ? reject(err) : resolve()
      })
    })
  })
}

// 框架模块配置
function packIndex(homeUri) {
  return new Promise((resolve, reject) => {
    let androidConfigFile = path.join(androidPath, 'app/src/main/java/com/osmartian/small/Config.java')
    fs.readFile(androidConfigFile, (err, data) => {
      if (err) {
        reject(err)
        return
      }
      data = data.toString().replace(/INDEX_URI(.*)\n/, `INDEX_URI = "${homeUri}";\n`)
      fs.writeFile(androidConfigFile, data, (err) => {
        console.log(data)
        err ? reject(err) : resolve()
      })
    })
  })
}

// 框架模块标签配置
function packTags(tagList) {
  return new Promise((resolve, reject) => {
    resolve()
    // let androidConfigFile = path.join(androidPath, 'app/src/main/java/com/osmartian/small/Config.java')
    // fs.readFile(androidConfigFile, (err, data) => {
    //   if (err) {
    //     reject(err)
    //     return
    //   }
    //   data = data.toString().replace(/INDEX_URI(.*)\n/, `INDEX_URI = "${baseInfo.homeUri}"\n`)
    //   fs.writeFile(androidConfigFile, data, (err) => {
    //     err ? reject(err) : resolve()
    //   })
    // })
  })
}

// 框架模块标签配置
function packPlugins(bundleJson) {
  return new Promise((resolve, reject) => {
    fs.writeFile(bundlePath, JSON.stringify(bundleJson), (err) => {
      console.log(bundleJson)
      err ? reject(err) : resolve()
    })
  })
}

exports.generate = function (config) {
  return packBase(config.baseInfo)
    .then(res => {
      return packIndex(config.moduleConfig.indexUri)
    })
    .then(res => {
      return packTags(config.moduleConfig.tagList)
    })
    .then(res => {
      return packPlugins(config.bundleJson)
    })
}
