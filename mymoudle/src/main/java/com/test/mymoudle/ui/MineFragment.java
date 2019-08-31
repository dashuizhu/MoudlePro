package com.test.mymoudle.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.person.commonlib.utils.DensityUtil;
import com.test.basemoudle.BaseFragment;
import com.test.basemoudle.utils.sharedPresenter.SharedPreApp;
import com.test.mymoudle.R;
import java.io.File;
import java.util.List;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Route(path = "/my/myFragment")
public class MineFragment extends BaseFragment {
    public static final int PHOTO_REQUEST_USERINFO = 0x1;
    public static final int PHOTO_REQUEST_CAMERA   = 0x2;
    public static final int PHOTO_REQUEST_CROP     = 0x3;

    //@BindView(R.id.item_cache)           MyItemView       itemCache;
    //@BindView(R.id.tv_user_name)         TextView         tvUserName;
    //@BindView(R.id.tv_child_school_name) TextView         tvChildSchoolName;
    //@BindView(R.id.draweeView)           SimpleDraweeView draweeView;
    //@BindView(R.id.item_update)          MyItemView       itemUpdate;
    //@BindView(R.id.item_my_class)        MyItemView       itemClass;
    //@BindView(R.id.tv_header)            TextView         mTvHeader;
    //@BindView(R.id.tv_hor)               TextView         mTvHor;
    //@BindView(R.id.iv_top_bg)            ImageView        mIvTopBg;
    //@BindView(R.id.ll_head)              LinearLayout     mLlHeader;
    //@BindView(R.id.scrollView)           NestedScrollView scrollView;
    //
    //private AppPresenter            mAppPresenter;
    //private UserPresenter           mUserPresenter;
    //private String                  mHeaderPath;
    //private String                  pictureFile;
    //private SelectBottomPopupWindow mHeadPopupWindow;
    //private DeviceTipsDialog        dialog;
    //
    //private int notchTop = 0;

    public MineFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }

    //private void injector() {
    //    AppPresnterComponent comment = DaggerAppPresnterComponent.builder()
    //            .appModelModule(new AppModelModule(this))
    //            .build();
    //    mAppPresenter = comment.getAppPresenter();
    //    mUserPresenter = comment.getUserPresenter();
    //}
    //
    //private void initNotch() {
    //    notchTop = (int) SharedPreApp.getInstance().get(getContext(), SharedPreApp.NOTCH_TOP, 0);
    //    if (notchTop > 0) {
    //        int hei = DensityUtil.dip2px(getContext(), 48);
    //        //LinearLayout.LayoutParams params =
    //        //        (LinearLayout.LayoutParams) mTvHeader.getLayoutParams();
    //        //params.height = hei + notchTop;
    //        //mTvHeader.setLayoutParams(params);
    //        //mTvHeader.setPadding(0, notchTop, 0, 0);
    //
    //        FrameLayout.LayoutParams params2 =
    //                (FrameLayout.LayoutParams) mLlHeader.getLayoutParams();
    //        params2.height = hei + notchTop;
    //        mLlHeader.setLayoutParams(params2);
    //        mLlHeader.setPadding(0, notchTop, 0, 0);
    //
    //        //
    //        params2 = (FrameLayout.LayoutParams) scrollView.getLayoutParams();
    //        params2.setMargins(0, notchTop, 0, 0);
    //        scrollView.setLayoutParams(params2);
    //    }
    //}
    //
    //protected int[] getScreenLocation(View view) {
    //    int[] location = new int[2];
    //    view.getLocationInWindow(location);
    //    //int x = location[0];
    //    //int y = location[1];
    //    return location;
    //}
    //
    //@Override
    //public void onDestroyView() {
    //    super.onDestroyView();
    //    if (mAppPresenter != null) {
    //        mAppPresenter.destroy();
    //    }
    //    if (mUserPresenter != null) {
    //        mUserPresenter.destroy();
    //    }
    //}
    //
    //@OnClick({
    //        R.id.item_my_class, R.id.item_update_password, R.id.item_feed_back, R.id.item_about_us,
    //        R.id.item_update, R.id.item_cache, R.id.tv_user_info, R.id.tv_exist,
    //        R.id.iv_update_head, R.id.draweeView
    //})
    //public void onViewClicked(View view) {
    //    switch (view.getId()) {
    //        case R.id.item_my_class:
    //            int gradeRank = SharedPreUser.getInstance().getKeyGradeRank(-1);
    //            if (gradeRank == -1) {
    //                showToast(getString(R.string.label_no_grade));
    //            } else {
    //                ActivityUtils.startMyClass(getActivity());
    //            }
    //            break;
    //        case R.id.item_update_password:
    //
    //            ActivityUtils.startSettingPassword(getActivity());
    //            break;
    //        case R.id.item_feed_back:
    //            ActivityUtils.startFeedback(getActivity());
    //            break;
    //        case R.id.item_about_us:
    //            ActivityUtils.startAbout(getActivity());
    //            break;
    //        case R.id.item_update:
    //            mAppPresenter.versionCheck();
    //            break;
    //        case R.id.item_cache:
    //            clearCache();
    //            break;
    //        case R.id.tv_user_info:
    //            ActivityUtils.startUserInfo(this, PHOTO_REQUEST_USERINFO);
    //            break;
    //        case R.id.tv_exist:
    //            if (AudioPlayer.get().isPlaying()) {
    //                AudioPlayer.get().stopPlayer();
    //            }
    //            Notifier.get().cancelAll();
    //            ActivityUtils.exit2Login(getActivity());
    //            getActivity().finish();
    //            break;
    //        case R.id.iv_update_head:
    //            showHeadWindow();
    //            break;
    //        case R.id.draweeView:
    //            ActivityUtils.startUserInfo(this, PHOTO_REQUEST_USERINFO);
    //            break;
    //        default:
    //            break;
    //    }
    //}
    //
    //@Override
    //public void onSuccess(Object successObj) {
    //    super.onSuccess(successObj);
    //    //if (successObj instanceof VersionUpdateResult) {
    //    //    MainActivity mainActivity = (MainActivity) getActivity();
    //    //    if (mainActivity != null) {
    //    //        mainActivity.checkVerson(getActivity(), successObj, true);
    //    //    }
    //    //}
    //}
    //
    //@Override
    //public void onStart() {
    //    super.onStart();
    //    if (!isHidden()) {
    //        initView();
    //    }
    //}
    //
    //@Override
    //public void onHiddenChanged(boolean hidden) {
    //    super.onHiddenChanged(hidden);
    //    if (!hidden) {
    //        initView();
    //    }
    //}
    //
    //private int mScrollY = 0;
    //
    //private void initView() {
    //    String name = SharedPreUser.getInstance().getUserNick();
    //    tvUserName.setText(name);
    //
    //    String schoolName = SharedPreUser.getInstance().getSchoolName();
    //    tvChildSchoolName.setText(schoolName);
    //
    //    String head = SharedPreUser.getInstance().getUserAvator();
    //    draweeView.setImageURI(head);
    //
    //    String myClassName = SharedPreUser.getInstance().getKeyGradeName();
    //    int myClassNum = SharedPreUser.getInstance().getKeyGradeNum();
    //    int myRank = SharedPreUser.getInstance().getKeyGradeRank(-1);
    //    String classStr;
    //    if (myRank >= 0) {
    //        classStr = myClassName + getClassNo() + "（" + myClassNum + "）";
    //        itemClass.setTextContent(classStr);
    //    } else {
    //        classStr = myClassName + getClassNo();
    //        itemClass.setTextContent(classStr);
    //    }
    //
    //    String gander = SharedPreUser.getInstance().getKeyGnder();
    //    if (TextUtils.isEmpty(gander) || "2".equals(gander)) {
    //        //男
    //        initGander(R.mipmap.my_boy);
    //    } else {
    //        initGander(R.mipmap.my_girl);
    //    }
    //
    //    boolean hasNewVersion = (boolean) SharedPreApp.getInstance()
    //            .get(getActivity(), SharedPreApp.KEY_REMIND_VERSION, false);
    //    itemUpdate.setRedTipVisibility(hasNewVersion ? View.VISIBLE : View.GONE);
    //    scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
    //        @Override
    //        public void onScrollChange(NestedScrollView nestedScrollView, int x, int y, int oldX,
    //                int oldY) {
    //            if (mScrollY == 0) {
    //                int hei = getScreenLocation(draweeView)[1];
    //                //int startY = getScreenLocation(mLlHeader)[1];
    //                int lh = mLlHeader.getHeight();
    //                //27是高度差
    //                mScrollY = hei + DensityUtil.dip2px(getContext(), 27) - lh;
    //            }
    //            if (y >= mScrollY) {
    //                mTvHeader.setVisibility(View.VISIBLE);
    //            } else {
    //                mTvHeader.setVisibility(View.INVISIBLE);
    //            }
    //        }
    //    });
    //}
    //
    //private String getClassNo() {
    //    String rankName = "";
    //    int gradeRank = SharedPreUser.getInstance().getKeyGradeRank(-1);
    //    switch (gradeRank) {
    //        case 0:
    //            rankName = "小班";
    //            break;
    //        case 1:
    //            rankName = "中班";
    //            break;
    //        case 2:
    //            rankName = "大班";
    //            break;
    //        default:
    //            rankName = getString(R.string.label_no_grade);
    //            break;
    //    }
    //    return rankName;
    //}
    //
    //private void initGander(int resId) {
    //    Drawable drawableRight = getResources().getDrawable(resId);
    //    tvUserName.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableRight, null);
    //}
    //
    //private void initCacheSize() {
    //
    //    Observable.just(1)
    //            .observeOn(Schedulers.newThread())
    //            .flatMap(new Func1<Integer, Observable<String>>() {
    //                @Override
    //                public Observable<String> call(Integer integer) {
    //                    String size = getCacheFileSize();
    //                    return Observable.just(size);
    //                }
    //            })
    //            .observeOn(AndroidSchedulers.mainThread())
    //            .subscribe(new Action1<String>() {
    //                @Override
    //                public void call(String size) {
    //                    itemCache.setTextContent(size);
    //                }
    //            });
    //}
    //
    //private void clearCache() {
    //    if (dialog == null) {
    //        String title = getResources().getString(R.string.label_clean_cache);
    //        String content = getResources().getString(R.string.label_clean_cache_info);
    //        dialog = new DeviceTipsDialog(getActivity(), title, content);
    //        dialog.setOnOkClickListener(new DeviceTipsDialog.OnOkClickListener() {
    //            @Override
    //            public void clickCancel() {
    //            }
    //
    //            @Override
    //            public void clickConfirm() {
    //                //必须在子线程
    //                Glide.get(getActivity()).clearMemory();
    //                Observable.just(1)
    //                        .observeOn(Schedulers.newThread())
    //                        .subscribe(new Action1<Integer>() {
    //                            @Override
    //                            public void call(Integer integer) {
    //                                //清除图片
    //                                Fresco.getImagePipeline().clearCaches();
    //                                //必须在子线程
    //                                Glide.get(getActivity()).clearDiskCache();
    //                                //清除sd 缓存文件夹
    //                                FileUtils.deleteCacheDir(getActivity());
    //                                //清楚 故事播放器缓存的图片
    //                                StoryDao.clearImageCache();
    //                            }
    //                        });
    //                itemCache.setTextContent("0.00KB");
    //            }
    //        });
    //    }
    //    dialog.showPopupWindow(0, 0);
    //}
    //
    ///**
    // * 获取缓存大小
    // *
    // * @return
    // * */
    //private String getCacheFileSize() {
    //    String totalSizeStr = "";
    //    try {
    //        Fresco.getImagePipelineFactory().getMainFileCache().trimToMinimum();
    //        long frescoSize = Fresco.getImagePipelineFactory().getMainFileCache().getSize();
    //        long glideSize = FileUtils.getFolderSize(
    //                new File(AppApplication.getInstance().getCacheDir() + "/cache"));
    //        long fileSize = FileUtils.getFolderSize(getActivity().getExternalFilesDir(null));
    //        long sdSzie = FileUtils.getFolderSize(FileUtils.getCacheDirFile());
    //        long totalSize = frescoSize + glideSize + fileSize + sdSzie;
    //        if (totalSize == 0) {
    //            return "0.00KB";
    //        }
    //        totalSizeStr = FileUtils.getFormatSize(totalSize);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    return totalSizeStr;
    //}
    //
    //@Override
    //public void onActivityResult(int requestCode, int resultCode, Intent data) {
    //    if (resultCode != Activity.RESULT_OK) {
    //        return;
    //    }
    //    switch (requestCode) {
    //        case PHOTO_REQUEST_USERINFO:
    //            initView();
    //            break;
    //        case PhotoPicker.REQUEST_CODE:
    //            List<String> photos = null;
    //            if (data != null) {
    //                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
    //            }
    //            if (photos != null && photos.size() > 0) {
    //                mHeaderPath = photos.get(0);
    //            }
    //            Uri uriStr = Uri.parse("file://" + mHeaderPath);
    //            gotoClipActivity(uriStr);
    //            break;
    //        //拍照图片
    //        case PHOTO_REQUEST_CAMERA:
    //            Uri uri = null;
    //            if (mHeadPopupWindow == null) {
    //                String picture = (String) SharedPreApp.getInstance()
    //                        .get(getActivity(), SharedPreApp.KEY_URI_PICTURE, "");
    //                uri = Uri.parse(picture);
    //            } else {
    //                uri = Uri.fromFile(new File(pictureFile));
    //            }
    //            gotoClipActivity(uri);
    //            break;
    //        //拍照剪裁
    //        case PHOTO_REQUEST_CROP:
    //            Uri uriCrop = data.getData();
    //            if (uriCrop != null) {
    //                mHeaderPath = FileUtils.getRealFilePathFromUri(getActivity(), uriCrop);
    //                draweeView.setImageURI(Uri.parse("file://" + mHeaderPath));
    //            }
    //            mUserPresenter.setUserHead(mHeaderPath);
    //            if (mHeadPopupWindow != null) {
    //                mHeadPopupWindow.dismiss();
    //            }
    //            break;
    //        default:
    //            break;
    //    }
    //    super.onActivityResult(requestCode, resultCode, data);
    //}
    //
    ///**
    // * 打开截图界面
    // */
    //private void gotoClipActivity(Uri uri) {
    //
    //    if (uri == null) {
    //        return;
    //    }
    //    Intent intent = new Intent();
    //    intent.setClass(getContext(), ClipImageActivity.class);
    //    intent.putExtra("type", 1);
    //    intent.setData(uri);
    //    startActivityForResult(intent, PHOTO_REQUEST_CROP);
    //}
    //
    //private void showHeadWindow() {
    //    if (mHeadPopupWindow == null) {
    //        mHeadPopupWindow = new SelectBottomPopupWindow(getActivity(), "从图库选取", "拍照");
    //        mHeadPopupWindow.setClickCallBack(new SelectBottomPopupWindow.ClickCallBack() {
    //            @Override
    //            public void onItemOne() {
    //
    //                new RxPermissions(getActivity()).request(
    //                        Manifest.permission.READ_EXTERNAL_STORAGE,
    //                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    //                        .subscribe(new Action1<Boolean>() {
    //                            @Override
    //                            public void call(Boolean aBoolean) {
    //                                if (aBoolean) {
    //                                    PhotoPicker.builder()
    //                                            .setPhotoCount(1)
    //                                            .setShowCamera(false)
    //                                            .setPreviewEnabled(false)
    //                                            .start(getContext(), MineFragment.this);
    //                                } else {
    //                                    ToastUtils.toast(R.string.toast_none_permission);
    //                                }
    //                            }
    //                        });
    //                mHeadPopupWindow.dismiss();
    //            }
    //
    //            @Override
    //            public void onItemTwo() {
    //                new RxPermissions(getActivity()).request(Manifest.permission.CAMERA,
    //                        Manifest.permission.READ_EXTERNAL_STORAGE,
    //                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    //                        .subscribe(new Action1<Boolean>() {
    //                            @Override
    //                            public void call(Boolean aBoolean) {
    //                                if (aBoolean) {
    //                                    Intent intent1 =
    //                                            new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    //                                    // 启动相机
    //                                    pictureFile = FileUtils.getPicCacheDir()
    //                                            + System.currentTimeMillis()
    //                                            + ".png";
    //                                    Uri uri = Uri.fromFile(new File(pictureFile));
    //                                    SharedPreApp.getInstance()
    //                                            .put(getActivity(), SharedPreApp.KEY_URI_PICTURE,
    //                                                    pictureFile);
    //                                    //为拍摄的图片指定一个存储的路径
    //                                    intent1.putExtra(MediaStore.EXTRA_OUTPUT, uri);
    //                                    startActivityForResult(intent1, PHOTO_REQUEST_CAMERA);
    //                                } else {
    //                                    ToastUtils.toast(R.string.toast_none_permission);
    //                                }
    //                            }
    //                        });
    //
    //                mHeadPopupWindow.dismiss();
    //            }
    //        });
    //    }
    //    mHeadPopupWindow.showPopupWindow(0, 0);
    //}
}
