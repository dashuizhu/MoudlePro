package com.test.moudle.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.person.commonlib.utils.AppUtils;
import com.test.basemoudle.utils.sharedPresenter.SharedPreApp;
import com.test.moudle.AppConstants;
import com.test.moudle.R;
import com.test.moudle.service.ProgressDownloader;
import com.test.moudle.service.ProgressResponseBody;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * 升级版本dialog
 * 存放在 getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
 *
 * @author zhuj 2018/8/17 下午3:00
 */
public class UpdateDialog extends Dialog implements ProgressResponseBody.ProgressListener {

    @BindView(R.id.tv_percentage) TextView    mTvPercentage;
    @BindView(R.id.progressBar)   ProgressBar mProgressBar;
    @BindView(R.id.btn_cancel)    Button      mBtnCancel;

    /**
     * 下载链接
     */
    private String mUrl;
    /**
     * 上次断点位置
     */
    private long mBreakPoints;
    /**
     * 已经下载的大小
     */
    private long mDownloadBytes;
    /**
     * 文件总大小
     */
    private long mContentLength;
    /**
     * //进度条最大值
     */
    private int mMax;

    private long               lastPercentage = -1;
    private Context            mContext;
    private ProgressDownloader downloader;
    private File               mFile;
    private Disposable         mInstanlDispose;

    /**
     * 是否为强制升级
     */
    boolean mForced = false;

    boolean mPause;

    public UpdateDialog(@NonNull Context context, String url) {
        super(context, R.style.dialog);
        mUrl = url;
        mContext = context;
        setCancelable(false);
    }

    public UpdateDialog(@NonNull Context context, String url, boolean forced) {
        super(context, R.style.dialog);
        mUrl = url;
        mContext = context;
        setCancelable(false);
        mForced = forced;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update);
        ButterKnife.bind(this);
        String fileName = mUrl.substring(mUrl.lastIndexOf(File.separator));
        mFile = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                fileName);
        downloader = new ProgressDownloader(mUrl, mFile, this);
        mBreakPoints = (long) SharedPreApp.getInstance()
                .get(mContext, AppConstants.KEY_UPDATE_DOWNLOAD_BYTES + mFile.getName(), 0L);
        downloader.download(mBreakPoints);
    }

    @OnClick(R.id.btn_cancel)
    public void onViewClicked() {
        Log.e("test", " on click " + mDownloadBytes);
        if (mForced && mPause) {
            mBreakPoints = mDownloadBytes;
            downloader.download(mBreakPoints);
            mBtnCancel.setText(R.string.cancel);
            mPause = false;
            return;
        }
        downloader.pause();
        // 存储此时的totalBytes，即断点位置。
        SharedPreApp.getInstance()
                .put(mContext, AppConstants.KEY_UPDATE_DOWNLOAD_BYTES + mFile.getName(),
                        mDownloadBytes);
        if (mForced) {
            mPause = true;
            mBtnCancel.setText(R.string.label_pause);
        } else {
            dismiss();
        }
    }

    /**
     * @param leftLength 剩余文件的长度
     */
    @Override
    public void onPreExecute(long leftLength) {
        //下载地址可能是个跳转网页，如小米下载地址，重定向到文件，所以这里只设置一次，是错误的。
        //if (this.mContentLength == 0L) {
        this.mContentLength = leftLength + mBreakPoints;
        mMax = (int) (mContentLength / 1024);
        mProgressBar.setMax(mMax);
        //}
    }

    @Override
    public void update(long bytes, final boolean done) {
        // 注意加上断点的长度
        this.mDownloadBytes = bytes + mBreakPoints;
        Log.e("test", " on download " + mDownloadBytes);
        int progress = (int) mDownloadBytes / 1024;
        if (mMax != 0) {
            int percentage = progress * 100 / mMax;
            //只有在百分比变化的时候， 才更新
            if (percentage != lastPercentage) {
                lastPercentage = percentage;
                Disposable dis = Observable.just(percentage)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                mTvPercentage.setText(MessageFormat.format("{0}%", integer));
                            }
                        });
            }
        }
        if (done) {
            SharedPreApp.getInstance()
                    .put(mContext, AppConstants.KEY_UPDATE_DOWNLOAD_BYTES + mFile.getName(),
                            mDownloadBytes);
            SharedPreApp.getInstance()
                    .put(mContext, AppConstants.KEY_UPDATE_DOWNLOAD_COMPLETE + mFile.getName(),
                            true);
            // TODO: 2017/8/3 发现，在高版本6.0以上手机中，下载完毕的文件，无法直接安装，需要过了10秒左右，才能解析安装
            // TODO: 2018/4/24 之后又未再发现,暂时不知道什么原因，也许是特定机型的 检测机制
            if (Build.VERSION.SDK_INT >= 22) {
                mInstanlDispose = Observable.interval(1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                if (aLong == 0) {
                                    Log.e("test", " download over onnext");
                                    mTvPercentage.setText(R.string.label_parse);
                                } else if (aLong >= 10) {
                                    AppUtils.installApk(mContext, mFile);
                                    dismiss();
                                }
                            }
                        });
            } else {
                AppUtils.installApk(mContext, mFile);
                dismiss();
            }
        }
    }

    @Override
    public void onError(Throwable throwable) {
        if (!isShowing()) {
            return;
        }
        Disposable dis = Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mTvPercentage.setText(R.string.label_network_unavailable);
                    }
                });
    }

    @Override
    public void dismiss() {
        if (mInstanlDispose != null) {
            if (!mInstanlDispose.isDisposed()) {
                mInstanlDispose.dispose();
            }
            mInstanlDispose = null;
        }
        super.dismiss();
    }
}
