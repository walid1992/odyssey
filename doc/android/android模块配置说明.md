## android 模块配置说明

* 设置模块uri、pkg

包名配置说明：

> 注： 前缀必须是com.syswin.toon

```
com.syswin.toon.app.xxx
或者
com.syswin.toon.lib.xxx
```

```
    {
      "uri": "bottom",
      "pkg": "com.osmartian.small.app.bottom"
    },
```
   
* 设置入口activity
   
```
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity
            android:name=".MainActivity"
            android:launchMode="singleInstance"
            android:windowSoftInputMode="adjustResize">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <activity
            android:name=".SubActivity"
            android:theme="@style/Dialog" />

    </application>
```

* 跳转模块方法

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

* 模块取值操作

```
        Uri uri = Small.getUri(this);
        if (uri != null) {
            String params = uri.getQueryParameter("params");
            if (params != null) {
                TextView tvFrom = (TextView) findViewById(R.id.tvFrom);
                tvFrom.setText("我收到的参数是：" + params);
            }
        }
```