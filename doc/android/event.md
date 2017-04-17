# global event 使用方式

## 原理介绍

> 维护单利 event map 进行注册、注销、分发处理，加入自定义Java Promise机制~

## [源码示例](../../android/app+event)

* GLobalEvent 工具类

```
/**
 * Author   : walid
 * Data     : 2017-04-08  17:38
 * Describe : GlobalEvent事件中心
 */
 
public class GlobalEvent {

    private Map<Object, SparseArray<List<Promise<GlobalBean, String>>>> listenerMap = new HashMap<>();

    private GlobalEvent() {
    }

    private static class SingletonHolder {
        static GlobalEvent instance = new GlobalEvent();
    }

    public static Promise<GlobalBean, String> register(Object o, @Key int key) {
        Map<Object, SparseArray<List<Promise<GlobalBean, String>>>> listenerMap = SingletonHolder.instance.listenerMap;
        SparseArray<List<Promise<GlobalBean, String>>> sparseArray = listenerMap.get(o) != null ? listenerMap.get(o) : new SparseArray<>();
        List<Promise<GlobalBean, String>> promises = sparseArray.get(key) != null ? sparseArray.get(key) : new ArrayList<>();
        Promise<GlobalBean, String> promise = new Promise<>((resolve, reject) -> {
        });
        promises.add(promise);
        sparseArray.put(key, promises);
        listenerMap.put(o, sparseArray);
        return promise;
    }

    public static void unRegister(Object o) {
        SingletonHolder.instance.listenerMap.remove(o);
    }

    public static void post(GlobalBean globalBean) {
        GlobalEvent globalEvent = SingletonHolder.instance;
        Set<Map.Entry<Object, SparseArray<List<Promise<GlobalBean, String>>>>> entrySet = globalEvent.listenerMap.entrySet();
        for (Map.Entry<Object, SparseArray<List<Promise<GlobalBean, String>>>> entry : entrySet) {
            List<Promise<GlobalBean, String>> promiseList = entry.getValue().get(globalBean.getKey());
            if (promiseList != null) {
                for (Promise<GlobalBean, String> item : promiseList) {
                    item.resolve.run(globalBean);
                }
            }
        }
    }

}
```

* Promise.class

```
/**
 * Author   : walid
 * Data     : 2017-04-07  18:53
 * Describe : Android Promise
 */
public class Promise<TResolveData, TRejectData> {

    // promise 链表
    private Promise<Object, TRejectData> next;
    @State
    public int state;
    private IResolve<TResolveData> success = null;
    private IReject<TRejectData> fail = null;
    public IPromiseInitializer<TResolveData, TRejectData> init;

    private TResolveData resolveResult;
    private TRejectData rejectError;

    public Promise(IPromiseInitializer<TResolveData, TRejectData> init) {
        this.init = init;
        state = State.PENDING;
        this.go();
    }

    public Promise<TResolveData, TRejectData> go() {
        init.run(this.resolve, this.reject);
        return this;
    }

    public IResolve<TResolveData> resolve = (TResolveData res) -> {
        state = State.RESOLVE;
        resolveResult = res;
        next();
    };

    public IReject<TRejectData> reject = (TRejectData err) -> {
        state = State.REJECT;
        rejectError = err;
        next();
    };

    private void next() {
        switch (state) {
            case State.RESOLVE:
                if (this.success != null) {
                    this.success.run(resolveResult);
                }
                break;
            case State.REJECT:
                System.out.println("fail");
                if (this.fail != null) {
                    System.out.println("fail != null");
                    this.fail.run(rejectError);
                } else {
                    Promise<Object, TRejectData> next = this.next;
                    while (next != null) {
                        if (next.fail != null) {
                            next.fail.run(rejectError);
                            return;
                        }
                        next = next.next;
                    }
                }
                break;
            case State.PENDING:
                break;
            default:
                break;
        }
    }

    public Promise<TResolveData, TRejectData> then(IResolve<TResolveData> pSuccess) {
        this.success = pSuccess;
        if (state == State.RESOLVE) {
            pSuccess.run(resolveResult);
        }
        return this;
    }

    public <TNewResData> Promise<TNewResData, TRejectData> then(IFunc<TResolveData, TNewResData> func) {
        Promise<TNewResData, TRejectData> promise = new Promise<>((res, rej) -> {
            if (state == State.RESOLVE) {
                res.run(func.run(resolveResult));
            } else if (state == State.REJECT) {
                this.fail.run(rejectError);
            } else {
                this.success = t -> res.run(func.run(resolveResult));
            }
        });
        this.next = (Promise<Object, TRejectData>) promise;
        return promise;
    }

    public Promise<TResolveData, TRejectData> fail(IReject<TRejectData> fail) {
        this.fail = fail;
        if (state == State.REJECT) {
            this.fail.run(rejectError);
        }
        return this;
    }

}
```

## 使用方式

### android

* register

```
GlobalEvent.register(this, "LOGIN_SUCCESS").then(res -> {
   Log.d("TAG", String.valueOf(res));
});
```

* post

```
GlobalEvent.post(new GlobalBean("LOGIN_SUCCESS", new Value(0, "success", "")));
```

* unRegister

```
GlobalEvent.unRegister(this);
```

### weex

* register

```
const sysEvent = weex.requireModule('sysEvent')
sysEvent.register(data => {
  console.log('sysEvent', data)
})
```

* post

```
const sysEvent = weex.requireModule('sysEvent')
sysEvent.post()
```

* unRegister

```
const sysEvent = weex.requireModule('sysEvent')
sysEvent.unRegister()
```