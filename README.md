# small frame

> 基于small插件化方案实现的动态插件组合、自动打包框架~ 

# 官方文档

[官方文档](http://code.wequick.net/Small/cn/home)

# 架构

![架构](https://camo.githubusercontent.com/798b51f0fb90a0ece76381cb807e19fafe930bd6/687474703a2f2f636f64652e7765717569636b2e6e65742f6173736574732f696d616765732f736d616c6c2d6172636869746563747572652e706e67)

# 模块打包

## 打包配置说明 

[插件配置文件示例](/src/build/config.js)


```
{
  baseInfo: {
    applicationId: 'com.syswin.toon',
    versionCode: 2,
    appIcon: 'toon',
    appName: 'toon通平台',
    versionName: '1.0.1',
  },
  frame: {
    uri: 'top',
    tags: [
      {
        name: '首页',
        uri: 'home'
      },
      {
        name: '动态',
        uri: `weex?url=${encodeURIComponent(`http://${ipAddress}:12580/dist/weex/views/tweet/app.js`)}`
      },
      {
        name: '我的',
        uri: `weex?url=${encodeURIComponent(`http://${ipAddress}:12580/dist/weex/views/mine/app.js`)}`
      }
    ]
  },
  modules: {
    version: '1.0.0',
    bundles: [
      {
        uri: 'bottom',
        pkg: 'com.osmartian.small.app.bottom'
      },
      {
        uri: 'top',
        pkg: 'com.osmartian.small.app.top'
      },
      {
        uri: 'home',
        pkg: 'com.osmartian.small.app.home'
      },
      {
        uri: 'weex',
        pkg: 'com.osmartian.small.app.weex'
      },
      {
        uri: 'detail',
        pkg: 'com.osmartian.small.app.detail',
        rules: {
          sub: 'Sub'
        }
      },
      {
        uri: 'lib.weex',
        pkg: 'com.osmartian.small.lib.weex'
      },
      {
        uri: 'lib.martian',
        pkg: 'com.osmartian.small.lib.martian'
      },
      {
        uri: 'lib.style',
        pkg: 'com.osmartian.small.lib.style'
      }
    ]
  }
}
```

## APK生成过程

* 初始化配置app基本信息

> 采用动态修改gradle配置方式

* 生成bundle.json small打包需要文件

> 将bundle.json写入android目录下

* 执行small打包命令

## 指令介绍

* 项目启动debug

```
// android
npm run dev:android
// ios 暂不支持 （预留指令）
npm run dev:android
```

* 项目构建release

```
// android ios平台
npm run build
// android
npm run build:android
// ios 暂不支持 （预留指令）
npm run build:ios
```

# 框架项目

* [Android](/android)
* [iOS](/ios)