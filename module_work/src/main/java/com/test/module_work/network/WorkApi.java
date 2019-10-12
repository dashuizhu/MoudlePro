package com.test.module_work.network;

import com.test.basemoudle.bean.NetworkResult;
import com.test.module_work.bean.TaskWorkPublishResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author zhuj 2019-09-04 16:16.
 */
public interface WorkApi {

    /**
     * 获取发布的课程详list表
     */
    @GET("teachapi/v1/task/publish")
    Observable<TaskWorkPublishResult> getTaskPublishList(@Header("Cache-control") String cacheControl,
            @Query("type") int type,
            @Query("page") int page,
            @Query("limit") int limit);

    ///**
    // * 获取性格塑造 学生完成情况
    // */
    //@GET("teachapi/v1/task/{publishId}/comple")
    //Observable<TaskWorkPublishDetailResult> getTaskPublishComple(@Path("publishId") int publishId);

    /**
     * 一键催交作业
     */
    @POST("teachapi/v1/task/{publishId}/call")
    Observable<NetworkResult> setTaskPrompt(@Path("publishId") int publishId);
}
