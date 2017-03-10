## small frame

> 基于small插件化方案实现的动态插件组合、自动打包框架~ 

## 官方文档

[官方文档](http://code.wequick.net/Small/cn/home)

## 架构

![架构](https://camo.githubusercontent.com/798b51f0fb90a0ece76381cb807e19fafe930bd6/687474703a2f2f636f64652e7765717569636b2e6e65742f6173736574732f696d616765732f736d616c6c2d6172636869746563747572652e706e67)

## 插件管理

### 插件配置说明

```
{
  "version": "1.0.0",
  "bundles": [
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
}
```

### 插件构建脚本

1. 采用nodejs进行bundle.json配置文件生成
2. 执行buildSmall脚本文件编译lib库与app工程

#### 构建指令

```
npm run build:plugin
```

#### 打包机制

> 目前android支持两种格式打包

1、 打包x86格式so文件

```
cd android
./buildSmall x86
```

2、打包armeabi格式so文件

```
cd android
./buildSmall armeabi
```

构建日志如下,脚本构建分为default脚本（官方推荐插件）与 custom脚本（官方市场中插件）组成 :


```
➜  small-frame git:(dev) npm run build:plugin

> small-toolkit@1.0.0 build:plugin /Users/walid/Desktop/dev/android/small-frame
> node build/plugin && npm run copy:bundle && android/buildSmall

prompt: input your project version ::  (1.0.0)
prompt: Are your sure add main plugin ?(Y/n):  (Y)
prompt: Are your sure add home plugin ?(Y/n):  (Y)
prompt: Are your sure add mine plugin ?(Y/n):  (Y)
prompt: Are your sure add lib.style plugin ?(Y/n):  (Y)
prompt: Are your sure add detail plugin ?(Y/n):  (Y)
prompt: Don't more defaul plugins，do your want add custom plugin ?(Y/n):  (Y) y
prompt: please input your plugin uri ::  (main)
prompt: please input your plugin pkg ::  (com.osmartian.small.app.main)

...

[Small] building library 1 of 2 - app (0x7f)
[Small] building library 2 of 2 - lib.style (0x79)
        [lib.style] split library res files...                          [  OK  ]
        [lib.style] slice asset package and reset package id...         [  OK  ]
        [lib.style] split library R.java files...                       [  OK  ]
        [lib.style] split R.class...                                    [  OK  ]
   -- output: armeabi/libcom_osmartian_small_lib_style.so (4305 bytes = 4.2 KB)
编译公共库完成

[Small] building bundle 2 of 4 - app.home (0x70)
        [app.home] split library res files...                           [  OK  ]
        [app.home] slice asset package and reset package id...          [  OK  ]
        [app.home] split library R.java files...                        [  OK  ]
        [app.home] split R.class...                                     [  OK  ]
   -- output: armeabi/libcom_osmartian_small_app_home.so (49548 bytes = 48.4 KB)
[Small] building bundle 3 of 4 - app.main (0x77)
        [app.main] split library res files...                           [  OK  ]
        [app.main] slice asset package and reset package id...          [  OK  ]
        [app.main] split library R.java files...                        [  OK  ]
        [app.main] split R.class...                                     [  OK  ]
   -- output: armeabi/libcom_osmartian_small_app_main.so (52363 bytes = 51.1 KB)
[Small] building bundle 4 of 4 - app.mine (0x16)
        [app.mine] split library res files...                           [  OK  ]
        [app.mine] slice asset package and reset package id...          [  OK  ]
        [app.mine] split library R.java files...                        [  OK  ]
        [app.mine] split R.class...                                     [  OK  ]
   -- output: armeabi/libcom_osmartian_small_app_mine.so (47726 bytes = 46.6 KB)
编译业务单元完成

```


## 项目启动脚本

### debug

```
// android
npm run dev:android
// ios 暂不支持 （预留指令）
npm run dev:android
```


### release

> 暂不支持 （预留指令）

```
// android
npm run build:android
// ios
npm run build:ios
```

## 框架项目

* [Android](https://github.com/OsMartian/small-frame/tree/dev/android)
* [iOS](https://github.com/OsMartian/small-frame/tree/dev/ios)