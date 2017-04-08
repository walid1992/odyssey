package com.osmartian.small.appstub.event.promise.interfaces;

/**
 * Author   : walid
 * Data     : 2017-04-07  18:53
 * Describe :
 */
public interface IPromiseInitializer<ResolveData, RejectData> {
    public void run(IResolve<ResolveData> resolve, IReject<RejectData> reject);
}
