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

function addPlugin() {
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
        addPlugin()
      })
    })
  })
}

exports.generate = function () {
  getName('1.0.0', chalk.green('input your project version :')).then(res => {
    bundleJson.version = res.name
    addPlugin()
  })
}