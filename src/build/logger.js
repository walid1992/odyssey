/**
 * @Author   : walid
 * @Data     : 2017-03-17  10:53
 * @Describe : logger 工具类
 */

const fs = require('fs-extra')
const path = require('path')
const lineReader = require('line-reader')

// pack log log
const packLog = path.join(__dirname, '../../build/output', 'log')

exports.clear = function () {
  fs.writeFileSync(packLog, '')
}

exports.writeLog = function (content) {
  return new Promise((resolve, reject) => {
    fs.readFile(packLog, (err, data) => {
      if (err) {
        reject(err)
        return
      }
      fs.writeFile(packLog, `${content}\n${data}`)
      resolve('write success !')
    })
  })
}

exports.readTopLine = function () {
  return new Promise((resolve, reject) => {
    lineReader.eachLine(packLog, (line, last) => {
      resolve(line)
      return false
    })
  })
}

