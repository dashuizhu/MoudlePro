package com.test.basemoudle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhuj 2019/4/17 上午10:49.
 */
public interface IPresenter extends LifecycleObserver {

    //@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    //void onCreate(@NotNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(@NotNull LifecycleOwner owner);

}