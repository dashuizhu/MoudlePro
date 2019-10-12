package com.test.moudle;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.person.commonlib.utils.AppUtils;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.test.basemoudle.BaseActivity;
import com.test.basemoudle.provider.IMyProvider;
import com.test.basemoudle.provider.ITaskProvider;
import com.test.basemoudle.provider.IWorkProvider;
import com.test.basemoudle.utils.ActivityUtils;
import com.test.basemoudle.utils.sharedPresenter.SharedPreApp;
import com.test.basemoudle.utils.sharedPresenter.SharedPreUser;
import com.test.moudle.bean.GradeResult;
import com.test.moudle.bean.VersionUpdateResult;
import com.test.moudle.view.UpdateDialog;
import java.io.File;
import rx.functions.Action1;

/**
 * @author zhuj 2018/9/4 下午4:53
 */
@Route(path = "/app/mainActivity")
public class MainActivity extends BaseActivity {

    private final String TAG_CIRCLE = "tag_circle";
    private final String TAG_WORK   = "tag_work";
    private final String TAG_COURSE = "tag_course";
    private final String TAG_MY     = "tag_my";

    @BindView(R.id.ll_container)     LinearLayout   mLlContainer;
    @BindView(R.id.tv_course)        TextView       mTvCourse;
    @BindView(R.id.tv_work)          TextView       mTvWork;
    @BindView(R.id.tv_education)     TextView       mTvEducation;
    @BindView(R.id.tv_my)            TextView       mTvMy;
    @BindView(R.id.tv_message_count) TextView       mTvMessageCount;
    @BindView(R.id.layout_bottom)    LinearLayout   mLayoutBottom;
    @BindView(R.id.rl_message)       RelativeLayout mRlMessage;

    //private AppPresenter mAppPresenter;

    private Fragment mCircleFragment, mWorkFragment, mCourseFragment, mMyFragment;
    private int mLastClickId;

    /** 版本升级 **/
    //private CheckVersionDialog mAlertDialog;
    //private MyAlertDialog      pp;

    //没有班级的时候，点击课程、作业， 要请求查询班级，有了班级，就要跳转刚点的
    private int mSkipClickId;

    @Autowired(name = "/my/myProvider")     IMyProvider   mMeProvider;
    @Autowired(name = "/task/taskProvider") ITaskProvider mTaskProvider;
    @Autowired(name = "/work/workProvider") IWorkProvider mWorkProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        transitionSystemBar();
        //AndroidBug5497Workaround.assistActivity(this);
        //injector();
        initNotch();
        if (savedInstanceState != null) {
            //这时候不为空  需要恢复FragmentManager 里保存的 fragment
            mCourseFragment = getSupportFragmentManager().findFragmentByTag(TAG_COURSE);
            mWorkFragment = getSupportFragmentManager().findFragmentByTag(TAG_WORK);
            mCircleFragment = getSupportFragmentManager().findFragmentByTag(TAG_CIRCLE);
            mMyFragment = getSupportFragmentManager().findFragmentByTag(TAG_MY);
        }

        int gradeRank = SharedPreUser.getInstance().getKeyGradeRank(-1);
        if (gradeRank == -1) {
            onViewClicked(mTvEducation);
            showToast(R.string.toast_no_class);
        } else {
            onViewClicked(mTvCourse);
        }

        //检查通知栏
        //if (!NotifyUtils.isNotificationEnable(this)) {
        //
        //    Observable.timer(1, TimeUnit.SECONDS)
        //            .observeOn(AndroidSchedulers.mainThread())
        //            .subscribe(new Consumer<Long>() {
        //                @Override
        //                public void accept(Long aLong) throws Exception {
        //                    showNotifyPop();
        //                }
        //            });
        //}
    }

    private void initNotch() {
        int top = (int) SharedPreApp.getInstance().get(this, SharedPreApp.NOTCH_TOP, 0);
        if (top > 0) {
            FrameLayout.LayoutParams layoutParams =
                    (FrameLayout.LayoutParams) mRlMessage.getLayoutParams();
            layoutParams.setMargins(0, top, 0, 0);
            mRlMessage.setLayoutParams(layoutParams);
        }
    }

    //private void showNotifyPop() {
    //    pp = new MyAlertDialog(this, "打开通知", getString(R.string.label_notice_open_hint));
    //    pp.setClickCallBack(new MyAlertDialog.ClickCallBack() {
    //        @Override
    //        public void onConfrim() {
    //            NotifyUtils.gotoSet(MainActivity.this);
    //        }
    //    });
    //    pp.show();
    //}
    //
    //private void injector() {
    //    AppPresnterComponent comment = DaggerAppPresnterComponent.builder()
    //            .appModelModule(new AppModelModule(this))
    //            .build();
    //    mAppPresenter = comment.getAppPresenter();
    //}

    private void showFragment(int itemId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        hideFragments(fragmentTransaction);

        int gradeRank = SharedPreUser.getInstance().getKeyGradeRank(-1);

        switch (itemId) {
            case R.id.tv_course:
                //if (gradeRank == -1) {
                //    //mAppPresenter.getGradeInfo();
                //    mSkipClickId = R.id.tv_course;
                //    return;
                //}

                if (mCourseFragment == null) {
                    mCourseFragment = mTaskProvider.getMainTaskFragment();
                    fragmentTransaction.add(R.id.ll_container, mCourseFragment, TAG_COURSE);
                } else {
                    fragmentTransaction.show(mCourseFragment);
                }
                //mTvCourse.setSelected(true);

                break;
            case R.id.tv_work:
                //if (gradeRank == -1) {
                //    //mAppPresenter.getGradeInfo();
                //    mSkipClickId = R.id.tv_work;
                //    return;
                //}
                if (mWorkFragment == null) {
                    mWorkFragment = mWorkProvider.getMainWorkFragment();
                    fragmentTransaction.add(R.id.ll_container, mWorkFragment, TAG_WORK);
                } else {
                    fragmentTransaction.show(mWorkFragment);
                }
                //mTvWork.setSelected(true);

                break;
            case R.id.tv_education:
                //if (mCircleFragment == null) {
                //    mCircleFragment = new ResourceFragment();
                //    fragmentTransaction.add(R.id.ll_container, mCircleFragment, TAG_CIRCLE);
                //} else {
                //    fragmentTransaction.show(mCircleFragment);
                //}
                //mTvEducation.setSelected(true);
                break;
            case R.id.tv_my:
                //ActivityUtils.startCharacter(this, 1);
                if (mMyFragment == null) {
                    mMyFragment = mMeProvider.getMainMyFragment();
                    fragmentTransaction.add(R.id.ll_container, mMyFragment, TAG_MY);
                } else {
                    fragmentTransaction.show(mMyFragment);
                }
                //mTvMy.setSelected(true);
                break;
            default:
        }
        mLastClickId = itemId;
        mTvCourse.setSelected(mLastClickId == R.id.tv_course);
        mTvWork.setSelected(mLastClickId == R.id.tv_work);
        mTvEducation.setSelected(mLastClickId == R.id.tv_education);
        mTvMy.setSelected(mLastClickId == R.id.tv_my);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction ft) {
        if (mCourseFragment != null && !mCourseFragment.isHidden()) {
            ft.hide(mCourseFragment);
        }
        if (mWorkFragment != null && !mWorkFragment.isHidden()) {
            ft.hide(mWorkFragment);
        }
        if (mCircleFragment != null && !mCircleFragment.isHidden()) {
            ft.hide(mCircleFragment);
        }
        if (mMyFragment != null && !mMyFragment.isHidden()) {
            ft.hide(mMyFragment);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //if (mAppPresenter != null) {
        //    mAppPresenter.destroy();
        //}

    }

    @OnClick({ R.id.tv_course, R.id.tv_work, R.id.tv_education, R.id.tv_my })
    public void onViewClicked(View view) {
        TextView tv = (TextView) view;
        if (mLastClickId == view.getId() && view.isSelected()) {
            return;
        }
        showFragment(view.getId());
    }

    @Override
    public void onSuccess(Object successObj) {
        super.onSuccess(successObj);
        if (successObj instanceof VersionUpdateResult) {
            checkVerson(this, successObj, false);
        } else if (successObj instanceof GradeResult) {
            int rank = SharedPreUser.getInstance().getKeyGradeRank(-1);
            if (rank == -1) {
                showToast(R.string.toast_no_class);
            } else {

                if (mSkipClickId == R.id.tv_course) {
                    onViewClicked(mTvCourse);
                } else if (mSkipClickId == R.id.tv_work) {
                    onViewClicked(mTvWork);
                }
            }
        }
    }

    public void checkVerson(Context context, Object successObj, boolean isShow) {
        final VersionUpdateResult updateResult = (VersionUpdateResult) successObj;
        AppApplication.isCheckVersion = true;
        if (updateResult.getData().getUpgradeFlag() > VersionUpdateResult.STATUS_ALREAD_LAST) {

            boolean isForced = updateResult.isForced();

            //mAlertDialog = new CheckVersionDialog(context, updateResult.isForced(),
            //        updateResult.getData().getUpgradeContent());
            //mAlertDialog.setOnOkClickListener(new CheckVersionDialog.OnOkClickListener() {
            //
            //    @Override
            //    public void clickCancel() {
            //
            //    }
            //
            //    @Override
            //    public void clickConfirm() {
            //        downloadApk(updateResult.getData().getDownloadLink(), updateResult.isForced(),
            //                MainActivity.this);
            //    }
            //});
            //mAlertDialog.show();
        } else {
            if (isShow) {
                showToast("当前已经是最新版本");
            }
        }
    }

    /**
     * 下载应用
     *
     * @param url 下载地址
     * @param isForced 是否强制升级
     */
    private void downloadApk(final String url, final boolean isForced, final Activity activity) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        new RxPermissions(activity).request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    //String PACKAGE_URL = "https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk";
                    String PACKAGE_URL = url;
                    String fileName =
                            PACKAGE_URL.substring(PACKAGE_URL.lastIndexOf(File.separator) + 1);
                    boolean download = (boolean) SharedPreApp.getInstance()
                            .get(activity, AppConstants.KEY_UPDATE_DOWNLOAD_COMPLETE + fileName,
                                    false);
                    File file = new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS), fileName);
                    //检查文件夹
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    if (!download) {
                        UpdateDialog updateDialog =
                                new UpdateDialog(activity, PACKAGE_URL, isForced);
                        updateDialog.show();
                    } else {
                        if (file.exists() && file.length() > 0) {
                            //已经下载成功，就直接安装。
                            AppUtils.installApk(activity, file);
                            //SharedPreApp.getInstance().put(AppSettingActivity.this,
                            //                AppConstants.KEY_UPDATE_DOWNLOAD_COMPLETE + fileName, false);
                        } else {
                            //文件不存在 或者 下载数为0
                            SharedPreApp.getInstance()
                                    .put(activity,
                                            AppConstants.KEY_UPDATE_DOWNLOAD_COMPLETE + fileName,
                                            false);
                            SharedPreApp.getInstance()
                                    .put(activity,
                                            AppConstants.KEY_UPDATE_DOWNLOAD_BYTES + fileName, 0L);
                            UpdateDialog updateDialog =
                                    new UpdateDialog(activity, PACKAGE_URL, isForced);
                            updateDialog.show();
                        }
                    }
                } else {
                    showToast(R.string.toast_permission_sdcard);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                showToast(R.string.toast_download_fail);
                dialogDismiss();
            }
        });
    }

    private void dialogDismiss() {
        //if (mAlertDialog != null) {
        //    if (mAlertDialog.isShowing()) {
        //        mAlertDialog.dismiss();
        //    }
        //    mAlertDialog = null;
        //}
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if (NotifyUtils.isNotificationEnable(this)) {
        //    if (pp != null) {
        //        pp.dismiss();
        //    }
        //}
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!AppApplication.isCheckVersion) {
            //显示过引导页，才会检查更新
            //if (GuideViewUtils.isShowed("guide_publish")) {
            //    mAppPresenter.versionCheck();
            //}
        }
        registerRxBus();
        initRemind();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unRegisterRxBus();
    }

    //@Subscribe(thread = EventThread.MAIN_THREAD, tags = @Tag(AppConstants.RXBUS_PUSH_MESSAGE))
    //public void onPush(PushBean pushBean) {
    //    //这里不区分推送类型， 只要有推送就显示红点，
    //    initRemind();
    //}
    //
    //@Subscribe(thread = EventThread.MAIN_THREAD)
    //public void onString(String action) {
    //    if (AppConstants.RXBUS_GUIDE_PUBLISH_OVER.equals(action)) {
    //        mAppPresenter.versionCheck();
    //    }
    //}

    private void initRemind() {
        int total = SharedPreUser.getInstance().getRemindUnread()[0];
        if (total > 0) {
            mTvMessageCount.setVisibility(View.VISIBLE);
            if (total > 99) {
                mTvMessageCount.setText("99+");
            } else {
                mTvMessageCount.setText(String.valueOf(total));
            }
        } else {
            mTvMessageCount.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.iv_message)
    public void onMesageClick() {
        ActivityUtils.startMessage();
    }
}

