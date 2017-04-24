package com.syswin.toon.app.home.network.api.task;

import com.syswin.toon.app.home.bean.server.task.TaskInfoVo;
import com.syswin.toon.app.home.bean.server.task.UserTaskItemModel;
import com.syswin.toon.app.home.network.SeaApiUtils;
import com.walid.rxretrofit.HttpManager;
import com.walid.rxretrofit.interfaces.IHttpCallback;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * Author   : walid
 * Data     : 2016-08-23  10:23
 * Describe :
 */

public class TaskApiService {

    private static final ITaskApi TASK_API = HttpManager.getInstance().getApiService(ITaskApi.class);

    public static void getDetail(int taskId, IHttpCallback<TaskInfoVo> listener) {
        SeaApiUtils.toSubscribe(TASK_API.getDetail(taskId), listener);
    }

    public static void taskList(int start, int size, IHttpCallback<List<TaskInfoVo>> listener) {
        SeaApiUtils.toSubscribe(TASK_API.taskList(start, size), listener);
    }

    public static void userList(int taskType, int start, int size, IHttpCallback<List<UserTaskItemModel>> listener) {
        SeaApiUtils.toSubscribe(TASK_API.userList(taskType, start, size), listener);
    }

    public static void userreceivetask(int taskId, IHttpCallback<Integer> listener) {
        SeaApiUtils.toSubscribe(TASK_API.userreceivetask(taskId), listener);
    }

    public static void post(List<MultipartBody.Part> params, IHttpCallback<Boolean> listener) {
        SeaApiUtils.toSubscribe(TASK_API.post(params), listener, false);
    }

}
