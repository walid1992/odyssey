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
const logger = require('./logger')
const childProcess = require('child_process')
const ipAddress = require('ip').address()

function sendNotice(content) {
  process.send({
    action: 'notice',
    content: content
  })
}

function sendSuccess(content) {
  process.send({
    action: 'success',
    content: content
  })
}

// 打包基本信息
function packBase(baseInfo) {
  sendNotice('打包基本信息中~')
  return new Promise((resolve, reject) => {
    if (!baseInfo.applicationId) {
      logger.writeLog('请设置applicationId')
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
        sendNotice('打包基本信息完毕~')
        err ? reject(err) : resolve()
      })
    })
  })
}

// 框架模块配置
function packFrame(frame) {
  sendNotice('框架模块配置中~')
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
        sendNotice('框架模块配置完毕~')
        err ? reject(err) : resolve()
      })
    })
  })
}

// 框架模块安装
function packModules(modules) {
  console.log(modules)
  sendNotice('模块配置中~')
  return new Promise((resolve, reject) => {
    fs.writeFile(bundlePath, JSON.stringify(modules), (err) => {
      sendNotice('模块配置完毕~')
      err ? reject(err) : resolve()
    })
  })
}

process.on('message', (m) => {
  if (m.action === 'submitForm') {
    this.generate(m.body)
  }
})

exports.generate = (config) => {
  logger.clear()
  return packBase(config.baseInfo)
    .then(res => {
      return packFrame(config.frame)
    })
    .then(res => {
      return packModules(config.modules)
    })
    .then(res => {
      sendNotice('打包APK中~')
      childProcess.exec(`${__dirname}/build.sh`, (err, stdout, stderr) => {
        if (err) {
          throw err
        }
        console.log(stdout)
        sendSuccess(`http://${ipAddress}:8888/android/app/build/outputs/apk/app-syswin-release.apk`)
      })
    })
}
