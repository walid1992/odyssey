# small frame

> 基于small插件化方案实现的定制化APP组装、打包框架~

# 官方文档

[官方文档](http://code.wequick.net/Small/cn/home)

# 架构

![架构](https://camo.githubusercontent.com/798b51f0fb90a0ece76381cb807e19fafe930bd6/687474703a2f2f636f64652e7765717569636b2e6e65742f6173736574732f696d616765732f736d616c6c2d6172636869746563747572652e706e67)

# 框架解决问题

1. 由于公司业务的发展，导致更多的超级app诞生于世，导致app 项目太大、耦合严重，且相关开发人员相互耦合，导致效率低下
2. app 热修复问题在很多场景是急需的
3. 对于开发效率方面，采用了weex模块进行快速迭代

# 框架获取

1. git clone https://github.com/osmartian/odyssey.git
2. cd odyssey
3. npm install

## 指令介绍

* android相关

```
// android debug 调试
npm run dev:android
// android 打包渠道包
npm run build:android
```

* ios相关

```
ios 暂不支持
```

# weex模块集成

关于weex模块集成详见项目 [https://github.com/osmartian/weex-frame.git](https://github.com/osmartian/weex-frame.git)

# 框架项目

* [Android](/android)
* [iOS](/ios)