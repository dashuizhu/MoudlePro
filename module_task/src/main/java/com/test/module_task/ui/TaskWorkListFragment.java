package com.test.module_task.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.person.commonlib.qmui.QMUINotchHelper;
import com.person.commonlib.utils.DensityUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.test.basemoudle.BaseFragment;
import com.test.basemoudle.bean.NetworkResult;
import com.test.basemoudle.exception.CustomException;
import com.test.basemoudle.utils.sharedPresenter.SharedPreApp;
import com.test.module_task.bean.TaskWorkPublishBean;
import com.test.module_task.bean.TaskWorkPublishResult;
import com.vlonjatg.progressactivity.ProgressLinearLayout;
import java.util.List;

/**
 * 作业列表fragment
 *
 * @author zhuj 2018/8/9 上午11:54
 */
public class TaskWorkListFragment extends BaseFragment implements OnRefreshLoadmoreListener {

    public final static int TYPE_HOMEWORK = 0;
    public final static int TYPE_HABIT    = 1;
    public final static int TYPE_ACTIVITY = 2;
    public final static int TYPE_TASK     = 3;

    RecyclerView            mRecyclerView;
    SmartRefreshLayout      mRefreshLayout;
    TextView                mTvTitle;
    TextView                mTvTaskToday;
    AppBarLayout            mAppBar;
    Toolbar                 mToolbar;
    ProgressLinearLayout    mProgressLayout;
    ImageView               mIvHistory;
    CollapsingToolbarLayout mToolbarLayout;

    //private TaskWorkListAdapter mAdapter;
    //private TaskWorkPresenter   mPresenter;

    private List<TaskWorkPublishBean> mBeanList;

    private int mNowPage;

    private int mRefreshPosition = -1;

    private String mTitle;

    public TaskWorkListFragment() {
    }

    private float mFraction;


    //public static TaskWorkListFragment newInstance(int type) {
    //    TaskWorkListFragment fragment = new TaskWorkListFragment();
    //    Bundle args = new Bundle();
    //    args.putInt(ARG_TYPE, type);
    //    fragment.setArguments(args);
    //    return fragment;
    //}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_work_list, container, false);
        //
        //mRecyclerView = view.findViewById(R.id.recyclerView);
        //mRefreshLayout = view.findViewById(R.id.refreshLayout);
        //mTvTitle = view.findViewById(R.id.tv_title);
        //mTvTaskToday = view.findViewById(R.id.tv_task_today);
        //mAppBar = view.findViewById(R.id.app_bar);
        //mToolbar = view.findViewById(R.id.toolbar);
        //mProgressLayout = view.findViewById(R.id.progressLayout);
        //mIvHistory = view.findViewById(R.id.iv_history);
        //mToolbarLayout = view.findViewById(R.id.toolbar_layout);
        //
        //injectorPresenter();
        //initNotch();
        //initViews();
        return null;
    }

    private void initNotch() {

        int notchTop =
                (int) SharedPreApp.getInstance().get(getContext(), SharedPreApp.NOTCH_TOP, 0);
        if (notchTop > 0) {
            //刘海屏
            int hei = DensityUtil.dip2px(getContext(), 48);
            CollapsingToolbarLayout.LayoutParams params =
                    (CollapsingToolbarLayout.LayoutParams) mIvHistory.getLayoutParams();
            params.height = hei + notchTop;
            mIvHistory.setLayoutParams(params);
            mIvHistory.setPadding(mIvHistory.getPaddingLeft(), notchTop,
                    mIvHistory.getPaddingRight(), 0);

            params = (CollapsingToolbarLayout.LayoutParams) mTvTitle.getLayoutParams();
            params.height = hei + notchTop;
            mTvTitle.setLayoutParams(params);
            mTvTitle.setPadding(0, notchTop, 0, 0);

            params = (CollapsingToolbarLayout.LayoutParams) mToolbar.getLayoutParams();
            params.height = hei + notchTop;
            mToolbar.setLayoutParams(params);
            mToolbar.setPadding(0, notchTop, 0, 0);
        }

        //int top = (int) SharedPreApp.getInstance().get(getContext(), SharedPreApp.NOTCH_TOP, 0);
        //if (top>0) {
        //    mRlHead.setPadding(0, top, 0, 0);
        //}
    }

    @Override
    public void onLoadError(Throwable e) {

        //if (e instanceof CustomException) {
        //    int code = ((CustomException) e).getCode();
        //    if (code == NetworkResult.CODE_NO_GRADE) {
        //        mProgressLayout.showEmpty(R.mipmap.list_work_empty_img,
        //                getString(R.string.label_no_grade), null);
        //        return;
        //    }
        //}
        //
        //if (mAdapter.getData() == null || mAdapter.getData().size() == 0) {
        //    mProgressLayout.showError(R.mipmap.img_progress_error,
        //            getString(R.string.label_load_error), getString(R.string.label_check_network),
        //            getString(R.string.label_reload), new View.OnClickListener() {
        //                @Override
        //                public void onClick(View v) {
        //                    mProgressLayout.showContent();
        //                    mRefreshLayout.autoRefresh();
        //                }
        //            });
        //}
        super.onError(e);
    }

    @Override
    public void onSuccess(Object successObj) {
        //if (successObj instanceof TaskWorkPublishResult) {
        //    List<TaskWorkPublishBean> list =
        //            ((TaskWorkPublishResult) successObj).getData().getContList();
        //    mNowPage = ((TaskWorkPublishResult) successObj).getData().getCurrPage();
        //    if (list == null || list.size() == 0) {
        //        if (((TaskWorkPublishResult) successObj).getTag() == NetworkResult.TAG_REFRESH) {
        //            //只有刷新才显示空页面
        //            mProgressLayout.showEmpty(R.mipmap.list_work_empty_img,
        //                    getString(R.string.label_work_list_empty), null);
        //        }
        //        return;
        //    }
        //    mProgressLayout.showContent();
        //    if (list.size() == WorkPresenter.PAGE_SIZE) {
        //        mRefreshLayout.setLoadmoreFinished(false);
        //    } else {
        //        //不够一页了， 直接加载结束
        //        mRefreshLayout.setLoadmoreFinished(true);
        //    }
        //    if (((TaskWorkPublishResult) successObj).getTag() == NetworkResult.TAG_REFRESH) {
        //        //刷新列表
        //        mBeanList = list;
        //        mAdapter.setData(mBeanList);
        //    } else {
        //        //加载更多列表
        //        mAdapter.addMoreData(list);
        //    }
        //} else if (successObj instanceof NetworkResult) {
        //    int position = ((NetworkResult) successObj).getPosition();
        //    mAdapter.notifyItemChanged(position);
        //}
        super.onSuccess(successObj);
    }

    //@OnClick(R.id.iv_history)
    //public void onHistory() {
    //    ActivityUtils.startTaskWorkHistory(getContext());
    //}
    //
    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        //mPresenter.loadmoreList(false, mNowPage + 1);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        //mPresenter.refreshList(false);
    }
    //
    //@Override
    //public void hideProgress() {
    //    super.hideProgress();
    //    if (mRefreshLayout != null) {
    //        mRefreshLayout.finishRefresh();
    //        mRefreshLayout.finishLoadmore();
    //    }
    //}
    //
    //@Override
    //public void onDestroyView() {
    //    super.onDestroyView();
    //}
    //
    //private void initViews() {
    //
    //    mToolbarLayout.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
    //    mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
    //        @Override
    //        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
    //            int scrollRange = appBarLayout.getTotalScrollRange();
    //            float fraction = 1f * (scrollRange + verticalOffset) / scrollRange;
    //            if (mFraction == fraction) {
    //                return;
    //            }
    //            mFraction = fraction;
    //            Log.w(TAG, " fra " + fraction);
    //            //滑动比例，达到比例，就收起 或隐藏
    //            //增大系数， 在0 - 0。6的时候， 就显示隐藏 header，  0.4- 1 隐藏显示top
    //            mTvTitle.setAlpha(Math.abs(1 - fraction * 2));
    //            if (fraction < 0.5) {
    //                mTvTitle.setText(R.string.title_task_today);
    //                mTvTitle.setTextSize(22);
    //            } else {
    //                mTvTitle.setText(mTitle);
    //                mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
    //                        DensityUtil.sp2px(getContext(), 18));
    //            }
    //            mTvTaskToday.setAlpha(fraction);
    //            //mBlurview.setAlpha(1-fraction);
    //            //mIv.setAlpha(fraction);
    //        }
    //    });
    //    boolean isNotch = QMUINotchHelper.hasNotch(getActivity());
    //    if (isNotch) {
    //        CoordinatorLayout.LayoutParams layoutParams =
    //                (CoordinatorLayout.LayoutParams) mAppBar.getLayoutParams();
    //        int height = DensityUtil.dip2px(getContext(), 20);
    //        layoutParams.height = layoutParams.height + height;
    //    }
    //
    //    //mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
    //    //    @Override
    //    //    public void onScrollChange(NestedScrollView nestedScrollView, int x, int y, int oldX,
    //    //            int oldY) {
    //    //        //mScrollY += y;
    //    //
    //    //        int scrollRange = mIvTopBg.getHeight();
    //    //        float fraction = 1f * (y) / scrollRange;
    //    //        Log.w(TAG, y + " - > " + fraction);
    //    //        //滑动比例，达到比例，就收起 或隐藏
    //    //        //增大系数， 在0 - 0。6的时候， 就显示隐藏 header，  0.4- 1 隐藏显示top
    //    //        //mTvTitle.setAlpha((fraction - 0.4f) * 2);
    //    //        //mTvWork.setAlpha((fraction - 0.4f) * 2);
    //    //
    //    //        mTvTitle.setAlpha(Math.abs(1-fraction * 2));
    //    //        if (fraction < 0.5) {
    //    //            mTvTitle.setText(mTitle);
    //    //        } else {
    //    //            mTvTitle.setText(R.string.title_task_today);
    //    //        }
    //    //        mTvWork.setAlpha(1-fraction);
    //    //
    //    //        mBlurview.setAlpha(fraction);
    //    //        mIvLine.setAlpha(fraction);
    //    //    }
    //    //});
    //
    //    StringBuilder stringBuilder = new StringBuilder();
    //    String gradeName = SharedPreUser.getInstance().getKeyGradeName() + "-";
    //    int gradeRank = SharedPreUser.getInstance().getKeyGradeRank(-1);
    //    switch (gradeRank) {
    //        case 0:
    //            stringBuilder.append(gradeName);
    //            stringBuilder.append("小班");
    //            break;
    //        case 1:
    //            stringBuilder.append(gradeName);
    //            stringBuilder.append("中班");
    //            break;
    //        case 2:
    //            stringBuilder.append(gradeName);
    //            stringBuilder.append("大班");
    //            break;
    //        default:
    //            stringBuilder.append(getString(R.string.label_no_grade));
    //            break;
    //    }
    //    mTitle = stringBuilder.toString();
    //    mTvTitle.setText(stringBuilder.toString());
    //
    //    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    //    mRecyclerView.setLayoutManager(layoutManager);
    //    mAdapter = new TaskWorkListAdapter(mRecyclerView);
    //    mRecyclerView.setAdapter(mAdapter);
    //    //mScrollView.setNestedScrollingEnabled(false);
    //    ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    //    mRefreshLayout.setOnRefreshLoadmoreListener(this);
    //
    //    mAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
    //        @Override
    //        public void onRVItemClick(ViewGroup parent, View itemView, int position) {
    //            TaskWorkPublishBean publishBean = mAdapter.getData().get(position);
    //            ActivityUtils.startWorkDetail(TaskWorkListFragment.this, publishBean);
    //            if (!publishBean.isCheckState() && AppUtils.isNetworkAvailable(getContext())) {
    //                mRefreshPosition = position;
    //                mAdapter.getData().get(position).setCheckState(true);
    //                //返回的时候，再刷新UI
    //            }
    //        }
    //    });
    //
    //    //开始刷新
    //    mRefreshLayout.autoRefresh();
    //}
    //
    //private void injectorPresenter() {
    //    AppPresnterComponent component = DaggerAppPresnterComponent.builder()
    //            .appModelModule(new AppModelModule(this))
    //            .build();
    //    mPresenter = component.getTaskWorkPresenter();
    //    getLifecycle().addObserver(mPresenter);
    //}
    //
    //@Override
    //public void onActivityResult(int requestCode, int resultCode, Intent data) {
    //    super.onActivityResult(requestCode, resultCode, data);
    //
    //    if (mRefreshPosition >= 0 && mRefreshPosition < mAdapter.getData().size()) {
    //        mAdapter.notifyItemChanged(mRefreshPosition);
    //        mRefreshPosition = -1;
    //    }
    //
    //    if (resultCode != Activity.RESULT_OK) {
    //        return;
    //    }
    //
    //    if (data != null) {
    //        int publishId = data.getIntExtra(AppString.KEY_ID, 0);
    //        TaskWorkPublishBean bean;
    //        //找到原来的任务，更新催交字段
    //        for (int i = 0; i < mAdapter.getData().size(); i++) {
    //            bean = mAdapter.getData().get(i);
    //            if (bean.getId() == publishId) {
    //                bean.setTodayCall(true);
    //                mAdapter.notifyItemChanged(i);
    //                break;
    //            }
    //        }
    //    }
    //}
    //
    //@Override
    //public void onHiddenChanged(boolean hidden) {
    //    super.onHiddenChanged(hidden);
    //    if (!hidden) {
    //        if (mProgressLayout.isEmpty()) {
    //            mRefreshLayout.autoRefresh();
    //        }
    //    }
    //}
}
