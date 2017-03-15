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
const bundlePath = path.join('./build/output', 'bundle.json')

let applicationId = 'com.osmartian.small'

// TODO 常用插件列表供开发者选择 后续会从网络上读取
let plugins = [
  {
    "uri": "main",
    "pkg": "app.main"
  },
  {
    "uri": "main2",
    "pkg": "app.main2"
  },
  {
    "uri": "home",
    "pkg": "app.home"
  },
  {
    "uri": "mine",
    "pkg": "app.mine"
  },
  {
    "uri": "detail",
    "pkg": "app.detail",
    "rules": {
      "sub": "Sub"
    }
  },
  {
    "uri": "lib.weex",
    "pkg": "lib.weex"
  },
  {
    "uri": "lib.style",
    "pkg": "lib.style"
  }
]

let bundleJson = {
  version: '1.0.0',
  bundles: []
}

/**
 * 获取输入内容
 * @param name
 * @param message
 */
function getName(name, message) {
  return new Promise((resolve, reject) => {
    let schema = {
      properties: {
        name: {
          default: name,
          message: message
        }
      }
    }
    prompt.start()
    prompt.get(schema, (err, result) => {
      err ? reject(err) : resolve(result)
    })
  })
}

let index = 0

function addDefaultPlugins() {
  if (!plugins || plugins.length <= index) {
    getName('Y', chalk.green(`Don't more defaul plugins，do your want add custom plugin ?(Y/n)`)).then(res => {
      if (res.name.toLowerCase() === 'n') {
        fs.writeFileSync(bundlePath, JSON.stringify(bundleJson))
        chooseHome()
        return
      }
      addCustomPlugin()
    })
    return
  }
  getName('Y', chalk.green(`Are your sure add ${applicationId}.${plugins[index].uri} plugin ?(Y/n)`)).then(res => {
    if (res.name.toLowerCase() === 'y') {
      plugins[index].pkg = `${applicationId}.${plugins[index].pkg}`
      bundleJson.bundles.push(plugins[index])
    }
    index++
    addDefaultPlugins()
  })
}

function addCustomPlugin() {
  let uri = 'main'
  let pkg = 'com.osmartian.small.app.main'
  getName('main', chalk.green('please input your plugin uri')).then(res => {
    uri = res.name
    getName('com.osmartian.small.app.main', chalk.green('please input your plugin pkg')).then(res => {
      pkg = res.name
      bundleJson.bundles.push({uri, pkg})
      console.log(bundleJson)
      getName('Y', chalk.green('Continue to add the plugin ?(Y/n)')).then(res => {
        if (res.name.toLowerCase() === 'n') {
          fs.writeFileSync(bundlePath, JSON.stringify(bundleJson))
          chooseHome()
          return
        }
        addCustomPlugin()
      })
    })
  })
}

function chooseHome() {
  getName('main', chalk.green('please choose your home uri')).then(res => {
    if (res.name) {
      // android 入口配置
      let androidConfigFile = path.join(__dirname, '../android/app/src/main/java/com/osmartian/small/Config.java')
      fs.readFile(androidConfigFile, (err, data) => {
        if (err) {
          throw err
        }
        data = data.toString().replace(/INDEX_URI(.*)"/, `INDEX_URI = "${res.name}"`)
        fs.writeFile(androidConfigFile, data, function (err) {
          if (err) {
            throw err
          }
          console.log(data)
        })
      })
    }
  })
}

exports.generate = function () {
  getName(applicationId, chalk.green('please choose your applicationId')).then(res => {
    if (res.name) {
      // applicationId = res.name
      // android 入口配置
      let androidConfigFile = path.join(__dirname, '../android/build.gradle')
      fs.readFile(androidConfigFile, (err, data) => {
        if (err) {
          throw err
        }
        data = data.toString().replace(/applicationId(.*)"/, `applicationId = "${res.name}"`)
        fs.writeFile(androidConfigFile, data, (err) => {
          if (err) {
            throw err
          }
          console.log(data)
          getName('1.0.0', chalk.green('input your project version')).then(res => {
            bundleJson.version = res.name
            addDefaultPlugins()
          })
        })
      })
    }
  })
}