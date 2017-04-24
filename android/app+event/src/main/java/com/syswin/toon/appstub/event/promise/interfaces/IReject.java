package com.syswin.toon.appstub.event.promise.interfaces;

/**
 * Author   : walid
 * Data     : 2017-04-07  18:53
 * Describe :
 */
public interface IReject<T> {
    public void run(T err);
}