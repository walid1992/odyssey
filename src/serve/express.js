/**
 * @Author   : walid
 * @Data     : 2017-03-15  22:31
 * @Describe : express 请求
 */

let bodyParser = require('body-parser')
let express = require('express')
let app = express()
let childProcess = require('child_process')
let fs = require("fs");
let path = require("path")

// 增加body编解码
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended: false}))

//  读取日志文件最后一行
app.get('/v1/get-status', (req, res) => {
    fs.readFile(path.join('d:', 'test_log.txt'), function (err, data) {
        if (err) {
            res.send(err);
        }
        res.send({
            data: {},
            code: 0,
            message: data.indexOf("\n") < 0 ? data.toString() : data.toString().substring(data.lastIndexOf("\n") + 1)
        })
    });
})

app.get('*', (req, res) => {
  res.send({
    data: {},
    code: 0,
    message: '请求成功'
  })
})

// POST 请求提交表单
app.post('/v1/submit-form', (req, res) => {
  console.log('收到post请求实体：\n', req.body)
  childProcess.exec('./build.sh', function (err, stdout, stderr) {
    if (err) {
      throw err
    }
    res.send({
      data: 'post walid success',
      code: 0,
      message: '请求成功'
    })
    console.log('stdout', stdout)
  })
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
