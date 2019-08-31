package com.test.basemoudle.exception;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * retry条件
 * 配合Rx接口， 针对Connect Socket Timeout三种exception，会进行失败重试
 * 重试时间为，失败的时间点 + delayTime
 *
 * @author WZG on 2016/10/17.
 */
public class RetryWhenNetworkException
        implements Function<Observable<? extends Throwable>, Observable<?>> {
    /**
     * retry次数
     */
    private int count = 3;

    /**
     * 延迟时间， 单位秒
     */
    private long delayTime = 10;

    /**
     * 当前重复次数
     */
    private int nowCount;

    public RetryWhenNetworkException() {

    }

    /**
     *
     * @param count 重复次数，默认3次
     * @param delayTime 重复间隔时间，默认10秒，单位秒
     */
    public RetryWhenNetworkException(int count, long delayTime) {
        this.count = count;
        this.delayTime = delayTime;
    }

    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) {
        return observable.flatMap(new Function<Throwable, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(Throwable throwable) throws Exception {
                boolean isNet = (throwable instanceof ConnectException
                        || throwable instanceof SocketTimeoutException
                        || throwable instanceof TimeoutException);
                nowCount++;
                //只针对网络超时、连接异常发起重启
                if (isNet && nowCount <= count) {
                    //没有达到最大重试次数，就重新请求
                    return Observable.timer(delayTime, TimeUnit.SECONDS);
                } else {
                    return Observable.error(throwable);
                }
            }
        });
    }
}
