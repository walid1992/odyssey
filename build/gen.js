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
// const bundleJson = path.relative(path.join('./android/app/src/main/assets'), 'bundle.json')
const bundlePath = path.relative(path.join('./'), 'bundle.json')
let bundleJson = {
  'version': '',
  'bundles': []
}

/**
 * 获取输入内容
 * @param name
 * @param message
 */
function getName (name, message) {
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
      if (err) {
        reject(err)
      } else {
        resolve(result)
      }
    })
  })
}

function initPlugin () {
  getName('main', chalk.green('please input your plugin uri :')).then(res => {
    bundleJson.version = res.name
    getName('main', chalk.green('please input your plugin pkg :')).then(res => {
      bundleJson.version = res.name
    })
  })
}

exports.generate = function () {
  getName('v1.0.0', chalk.green('input your project version :')).then(res => {
    bundleJson.version = res.name
    // let projectName = result.name
    // const dirpath = path.join(process.cwd(), projectName)
    // createProject(projectName, dirpath)
    // let content = fs.readFileSync(filePath, {
    //   encoding: 'utf-8'
    // })
    // content = content.replace(/{{\s*(.+)\s*}}/ig, function (defaultName) {
    //   return name || defaultName
    // })
    // fs.writeFileSync(filePath, content)
  })
}