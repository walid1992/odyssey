package com.osmartian.small.app.home.network.api.task;

import com.osmartian.small.app.home.bean.server.task.TaskInfoVo;
import com.osmartian.small.app.home.bean.server.task.UserTaskItemModel;
import com.osmartian.small.app.home.network.HttpResult;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author   : walid
 * Data     : 2016-09-10  01:50
 * Describe :
 */

interface ITaskApi {

    // 获取任务详情
    @GET("/v1/task/get")
    Observable<HttpResult<TaskInfoVo>> getDetail(@Query("taskId") int taskId);

    // 获取任务列表
    @GET("/v1/task/list")
    Observable<HttpResult<List<TaskInfoVo>>> taskList(@Query("start") int start, @Query("size") int size);

    // 获取用户任务列表
    @GET("/v1/task/user/list")
    Observable<HttpResult<List<UserTaskItemModel>>> userList(@Query("status") int status, @Query("start") int start, @Query("size") int size);

    // 领取任务
    @FormUrlEncoded
    @POST("/v1/task/userreceivetask")
    Observable<HttpResult<Integer>> userreceivetask(@Field("taskId") int taskId);

    // 提交任务
    @Multipart
    @POST("/v1/task/post")
    Observable<HttpResult<Boolean>> post(@Part List<MultipartBody.Part> params);

}

