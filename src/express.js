/**
 * @Author   : walid
 * @Data     : 2017-03-15  22:31
 * @Describe : express 请求
 */

let bodyParser = require('body-parser')
let express = require('express')
let app = express()
let exec = require('child_process').exec

// 增加body编解码
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended: false}))

//  GET 请求
app.get('/get/walid', (req, res) => {
  res.send({
    data: 'get walid success',
    code: 0,
    message: '请求成功'
  })
})

app.get('*', (req, res) => {
  res.send({
    data: 'get success',
    code: 0,
    message: '请求成功'
  })
})

// POST 请求提交表单
app.post('/v1/submit-form', (req, res) => {
  console.log('收到post请求实体：\n', req.body)
  res.send({
    data: 'post walid success',
    code: 0,
    message: '请求成功'
  })
  // require('../build/generator').generate()
})

app.post('*', (req, res) => {
  res.send({
    data: 'post success',
    code: 0,
    message: '请求成功'
  })
})

let server = app.listen(3000, () => {
  let host = server.address().address
  let port = server.address().port
  console.log("服务已启动： http://%s:%s", host, port)
})
