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

// TODO 常用插件列表供开发者选择 后续会从网络上读取
let plugins = [
  {
    "uri": "main",
    "pkg": "com.osmartian.small.app.main"
  },
  {
    "uri": "home",
    "pkg": "com.osmartian.small.app.home"
  },
  {
    "uri": "mine",
    "pkg": "com.osmartian.small.app.mine"
  },
  {
    "uri": "lib.style",
    "pkg": "com.osmartian.small.lib.style"
  },
  {
    "uri": "detail",
    "pkg": "com.osmartian.small.app.detail",
    "rules": {
      "sub": "Sub"
    }
  }
]

let bundleJson = {
  version: '',
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
          message: message,
          default: name
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
        return
      }
      addCustomPlugin()
    })
    return
  }
  getName('Y', chalk.green(`Are your sure add ${plugins[index].uri} plugin ?(Y/n)`)).then(res => {
    if (res.name.toLowerCase() === 'y') {
      bundleJson.bundles.push(plugins[index])
    }
    index++
    addDefaultPlugins()
  })
}

function addCustomPlugin() {
  let uri = 'main'
  let pkg = 'com.osmartian.small.app.main'
  getName('main', chalk.green('please input your plugin uri :')).then(res => {
    uri = res.name
    getName('com.osmartian.small.app.main', chalk.green('please input your plugin pkg :')).then(res => {
      pkg = res.name
      bundleJson.bundles.push({uri, pkg})
      console.log(bundleJson)
      getName('Y', chalk.green('Continue to add the plugin ?(Y/n)')).then(res => {
        if (res.name.toLowerCase() === 'n') {
          fs.writeFileSync(bundlePath, JSON.stringify(bundleJson))
          return
        }
        addCustomPlugin()
      })
    })
  })
}

exports.generate = function () {
  getName('1.0.0', chalk.green('input your project version :')).then(res => {
    bundleJson.version = res.name
    addDefaultPlugins()
  })
}