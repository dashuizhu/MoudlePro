package com.test.basemoudle;

import android.app.Application;
import android.content.Context;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.person.commonlib.utils.DensityHelp;
import com.test.basemoudle.network.RetrofitManager;

/**
 * @author zhuj 2019-08-28 21:14.
 */
public class BaseApplication extends Application {

    private static BaseApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        DensityHelp.setDensity(this);
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
        RetrofitManager.getInstance().init(this);
        initFresco();
        sApplication = this;
    }

    public static Context getContext() {
        return sApplication.getApplicationContext();
    }


    private void initFresco() {
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                //.setBaseDirectoryPath(new File(AppSettings.AppFilePathDir + "/caches"))
                .setBaseDirectoryName("rsSystemPicCache").setMaxCacheSize(150 * ByteConstants.MB)
                .setMaxCacheSizeOnLowDiskSpace(100 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(50 * ByteConstants.MB)
                .setMaxCacheSize(80 * ByteConstants.MB).build();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .setDownsampleEnabled(true)
                .build();

        Fresco.initialize(this, config);
    }
}
