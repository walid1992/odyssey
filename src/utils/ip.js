/**
 * @author walid
 * @date 2017/03/02
 * @description IP地址写入config
 */

let fs = require('fs')
let path = require('path')
let ip = require('ip').address()

fs.writeFileSync(path.resolve('config.js'), `export default \'${ip}\'`)