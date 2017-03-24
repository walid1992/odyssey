/**
 * @Author   : walid
 * @Data     : 2017-03-15  22:31
 * @Describe : express 请求
 */

let bodyParser = require('body-parser')
let express = require('express')
let app = express()
let fs = require("fs");
let path = require("path")
let toonpack = require('../build/toonpack')

let newestStep = '正在打包操作中~'
let downUrl

// 增加body编解码
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended: false}))

//  读取日志文件最后一行
app.get('/v1/get-status', (req, res) => {
  if (downUrl) {
    res.send({
      data: {
        url: downUrl
      },
      code: 0,
      msg: newestStep
    })
  }
  res.send({
    data: {},
    code: 0,
    msg: newestStep
  })
})

// POST 请求提交表单
app.post('/v1/submit-form', (req, res) => {
  console.log('收到post请求实体：\n', req.body)
  res.send({
    data: {},
    code: 0,
    msg: '数据合法，即将开始打包操作~'
  })
  newestStep = ''
  downUrl = ''
  let child = require('child_process').fork(`src/build/toonpack.js`)
  child.on('message', (m) => {
    console.log(m)
    if (m.action === 'notice') {
      newestStep = m.content
    } else if (m.action === 'success') {
      downUrl = m.content
      newestStep = '生成apk成功~'
    }
  })
  child.send({
    action: 'submitForm',
    body: req.body
  })
})

let server = app.listen(3000, () => {
  let host = server.address().address
  let port = server.address().port
  console.log("服务已启动： http://%s:%s", host, port)
})