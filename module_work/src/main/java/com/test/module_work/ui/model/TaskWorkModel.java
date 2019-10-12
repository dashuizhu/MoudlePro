package com.test.module_work.ui.model;

import com.test.basemoudle.bean.NetworkResult;
import com.test.basemoudle.exception.CustomException;
import com.test.basemoudle.network.RetrofitManager;
import com.test.module_work.bean.TaskWorkPublishResult;
import com.test.module_work.network.WorkApi;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by zhuj on 2017/7/20 下午3:08.
 */
public class TaskWorkModel {

    private final String TAG = TaskWorkModel.class.getSimpleName();

    /**
     * 获得作业类型列表
     */
    public Observable<TaskWorkPublishResult> getWorkList(boolean isHistory, int page, int limit,
            boolean isCache) {
        WorkApi httpApi = RetrofitManager.getInstance().getApi(WorkApi.class);
        String cacheString = RetrofitManager.getCacheControlString(isCache);
        int type = isHistory ? 1 : 0;
        return httpApi.getTaskPublishList(cacheString, type, page, limit)
                .doOnNext(new Consumer<TaskWorkPublishResult>() {
                    @Override
                    public void accept(TaskWorkPublishResult networkResult) throws Exception {
                        if (!networkResult.isNetworkSuccess()) {
                            throw new CustomException(networkResult);
                        }
                    }
                });
    }

    /**
     * 获得作业类型列表
     */
    //public Observable<TaskWorkPublishDetailResult> getWorkPublishComple(int publishId) {
    //    IHttpApi httpApi = AppApplication.getIHttpApi();
    //    return httpApi.getTaskPublishComple(publishId)
    //            .doOnNext(new Consumer<TaskWorkPublishDetailResult>() {
    //                @Override
    //                public void accept(TaskWorkPublishDetailResult networkResult) throws Exception {
    //                    if (!networkResult.isNetworkSuccess()) {
    //                        throw new CustomException(networkResult);
    //                    }
    //                }
    //            });
    //}

    /**
     * 获得作业类型列表
     */
    public Observable<NetworkResult> setWorkStudentPrompt(int publishId) {
        WorkApi httpApi = RetrofitManager.getInstance().getApi(WorkApi.class);
        return httpApi.setTaskPrompt(publishId).doOnNext(new Consumer<NetworkResult>() {
            @Override
            public void accept(NetworkResult networkResult) throws Exception {
                if (!networkResult.isNetworkSuccess()) {
                    throw new CustomException(networkResult);
                }
            }
        });
    }
}
