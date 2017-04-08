package com.osmartian.small.appstub.event.promise;

import com.osmartian.small.appstub.event.promise.interfaces.IFunc;
import com.osmartian.small.appstub.event.promise.interfaces.IPromiseInitializer;
import com.osmartian.small.appstub.event.promise.interfaces.IReject;
import com.osmartian.small.appstub.event.promise.interfaces.IResolve;

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
