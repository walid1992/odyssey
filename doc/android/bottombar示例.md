# 打包bottom框架说明

* config 文件配置

```
{
  baseInfo: {
    applicationId: 'com.syswin.toon.bottom',
    versionCode: 2,
    appIcon: 'bottom',
    appName: 'BOTTOM框架',
    versionName: '1.0.1'
  },
  frame: {
    uri: 'bottom',
    tags: [
      {
        name: 'Weex首页',
        uri: `weex?url=${encodeURIComponent(`http://${ipAddress}:12580/dist/weex/views/home/app.js`)}`
      },
      {
        name: '原生首页',
        uri: `home`
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

### 示例图片

<img src="../android/screenshot/bottom-native-home.jpg" width = "300" align=center />
<img src="../android/screenshot/bottom-weex-home.jpg" width = "300" align=center />
<img src="../android/screenshot/bottom-mine.jpg" width = "300" align=center />
