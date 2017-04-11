## small frame for android

> 基于small插件化方案实现的定制化APP组装、打包框架~

## 原理介绍

> small 插件化方案android端分为两个步骤

1. gradle 打包插件机制
2. 运行期加载机制

### 打包插件机制

[官方说明](https://github.com/wequick/Small/tree/master/Android/DevSample/buildSrc)

> 将多个app与lib工程编译成so文件

### 运行期加载机制

#### Dynamic load classes

[官方说明](https://github.com/wequick/Small/wiki/Android-dynamic-load-classes)

> DexClassLoader不支持".so"后缀，为了让应用启动时能自动复制插件包到应用存储目录，需要支持".so"后缀。做法就是模拟 压缩包加载代码块，创建一个dex元素，再反射添加到宿主class loader里的dexPathList。

#### Dynamic load resources

[官方说明](https://github.com/wequick/Small/wiki/Android-dynamic-load-resources)

#### Dynamic register activities

##### activity 启动过程：

> 注： Android activities受Instrumentation监控

1. 由Activity的startActivityForResult方法启动，通过instrumentation的execStartActivity方法激活生命周期。
2. 在ActivityThread的performLaunchActivity方法中通过instrumentation的newActivity方法实例化。

##### small 实现方案：

**1. 首先在宿主manifest中注册一个命名特殊的占坑activity**

```
<!-- Stub Activities -->
<activity android:name=".A.0" android:launchMode="standard"/>
```

**2. 封装一个instrumentation，替换掉宿主的**

（1）、欺骗startActivityForResult(启动过程1)以获得生命周期
（2）、欺骗performLaunchActivity(启动过程2)来创建插件activity实例

```
ActivityThread thread = currentActivityThread();
Instrumentation base = thread.@mInstrumentation;
Instrumentation wrapper = new InstrumentationWrapper(base);
thread.@mInstrumentation = wrapper;

class InstrumentationWrapper extends Instrumentation {
    // 欺骗startActivityForResult获得生命周期
    public ActivityResult execStartActivity(..., Intent intent, ...) {
        fakeToStub(intent);
        base.execStartActivity(args);
    }

    // 欺骗performLaunchActivity创建实例
    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) {
        className = restoreToReal(intent, className);
        return base.newActivity(cl, className, intent);
    }
} 
```


## 项目结构

```
small-frame
├── app (宿主app)
│      ├── LaunchActivity  
│      │  
│      └── SmallApp│
│
├── app+event (全局event宿主分身) 
│
├── app+storage (全局storage宿主分身)
│      
├── app+stub (转场动画宿主分身)
│
├── app.top (topbar框架APP)
│      │ 
│      └── MainActivity  
│              
├── app.bottom (bottombar框架app)
│      │
│      └── MainActivity  
│
├── app.home （首页模块app）
│      └── MainFragment
│
├── app.weex （weex模块app）
│      │ 
│      ├── MainActivity  
│      │
│      └── MainFragment
│         
├── app.detail （详情模块app）
│      ├── MainActivity
│      │   
│      └── SubActivity
│
├── lib.weex （weex lib 库）
│
├── lib.martian （公共工具库）
│           
└── lib.style （公共样式库）
       └── res
           ├── colors.xml
           ├── dimens.xml
           └── styles.xml
```

## config驱动文件打包APK流程


### 打包配置说明 

```
{
  // app基本信息配置
  baseInfo: {
    applicationId: 'com.syswin.toon.bottom',
    versionCode: 2,
    appIcon: 'bottom',
    appName: 'BOTTOM框架',
    versionName: '1.0.1'
  },
  // 框架末班配置
  frame: {
    // 框架模块uri
    uri: 'bottom',
    // 可选
    tags: []
  },
  // 配置small打包配置
  modules: {
    // small 版本
    version: '1.0.0',
    // 需要打包的app及lib
    bundles: [
      {
        uri: 'bottom',
        pkg: 'com.osmartian.small.app.bottom'
      },
      ... 
      {
        uri: 'lib.style',
        pkg: 'com.osmartian.small.lib.style'
      }
    ]
  }
}
```

### APK生成过程

1. 动态修改APP基本信息

采用动态修改gradle配置方式，修改AppID、appName、appIcon...

在项目根build.gradle中有如下配置，只需动态修改此配置即可：

```
ext {
    compileSdkVersion = 25
    buildToolsVersion = '25.0.2'
    applicationId = "com.syswin.toon.walid"
    appName = "Walid APK"
    appIcon = "top"
    minSdkVersion = 15
    targetSdkVersion = 25
    versionCode = 10
    versionName = "1.0.1"
}

```

2. 设置框架frame模块

动态设置框架模块uri，用于宿主模块调起

见宿主app模块下com.osmartian.small中的config.java文件：

```
package com.osmartian.small;

/**
 * @Author : walid
 * @Data : 2017-03-14  22:36
 * @Describe : INDEX URL配置
 */
public class Config {
    public static final String INDEX_URI = "bottom?tags=%5B%7B%22name%22%3A%22%E9%A6%96%E9%A1%B5%22%2C%22uri%22%3A%22home%22%7D%2C%7B%22name%22%3A%22%E6%88%91%E7%9A%84%22%2C%22uri%22%3A%22weex%3Furl%3Dhttp%253A%252F%252F172.31.243.44%253A12580%252Fdist%252Fweex%252Fviews%252Fmine%252Fapp.js%22%7D%2C%7B%22name%22%3A%22%E4%B8%AA%E4%BA%BA%E8%B5%84%E6%96%99%22%2C%22uri%22%3A%22weex%3Furl%3Dhttp%253A%252F%252F172.31.243.44%253A12580%252Fdist%252Fweex%252Fviews%252Fuserinfo%252Fapp.js%22%7D%5D";
}

```

3. 生成bundle.json small打包需要文件

动态生成bundle.json small打包文件，且copy到android、ios项目

(1)、生成bundle.json文件

```
const bundlePath = path.join(__dirname, '../../build/output', 'bundle.json')

// 框架模块安装
function packModules(modules) {
  console.log(modules)
  return new Promise((resolve, reject) => {
    fs.writeFile(bundlePath, JSON.stringify(modules), (err) => {
      err ? reject(err) : resolve()
    })
  })
}
```

(2)、copy 至 android 、 ios项目

```
// cp -vf build/output/bundle.json android/app/src/main/assets/bundle.json
npm run copy:bundle
```

4. 执行small打包 -> so 文件

将需要打包的模块打包成so文件

```
npm run build:small
```

5. 编译生成apk文件

执行npm指令进行apk生成

```
npm run dev:android 
// 或 
npm run build:android
```

## small 模块跳转操作

1、 跳转h5

```
  Small.openUri("https://github.com/osmartian/small-frame", getContext());
```

2、 跳转app module 传值

```
  Small.openUri("detail?params=我是参数，从首页传送过来的~", getContext());
```

3、 跳转app module 二级界面

```
  Small.openUri("detail/sub", getContext());
```

## 项目打包APK示例

1. [top框架](../doc/android/topbar示例.md)
2. [bottom框架](../doc/android/bottombar示例.md)