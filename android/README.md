## small frame for android

> 基于small插件化方案实现的动态插件组合、自动打包框架~ 

## 项目结构

```
small-frame
├── app (宿主app)
│      ├── LaunchActivity  
│      │  
│      └── SmallApp
│
├── app.main （宿主app选择加载的主模块）
│      └── MainActivity
│
│
├── app.home （首页模块app）
│      └── MainFragment
│
│
├── app.mine （我的模块app）
│      └── MainFragment
│      
│          
├── app.detail （详情模块app）
│      ├── MainActivity
│      │   
│      └── SubActivity
│           
└── lib.style （公共样式库）
       └── res
           ├── colors.xml
           ├── dimens.xml
           └── styles.xml
```

## 路由管理

1、 跳转h5

```
  Small.openUri("https://github.com/OsMartian/small-frame", getContext());
```

2、 跳转app module 传值

```
  Small.openUri("detail?params=我是参数，从首页传送过来的~", getContext());
```

3、 跳转app module 二级界面

```
  Small.openUri("detail/sub", getContext());
```

## 示例图片

<img src="./screenshot/Screenshot_20170310-023438.jpg" width = "300" align=center />
<img src="./screenshot/Screenshot_20170310-023440.jpg" width = "300" align=center />
<img src="./screenshot/Screenshot_20170310-023445.jpg" width = "300" align=center />
<img src="./screenshot/Screenshot_20170310-023450.jpg" width = "300" align=center />
