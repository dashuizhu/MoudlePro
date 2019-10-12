package com.test.module_work.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGARecyclerViewHolder;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;
import com.person.commonlib.utils.DateUtils;
import com.person.commonlib.utils.DensityUtil;
import com.test.module_work.R;
import com.test.module_work.bean.TaskWorkPublishBean;

/**
 * @author zhuj
 * @date 2018/8/13 下午2:27.
 */
public class TaskWorkListAdapter extends BGARecyclerViewAdapter<TaskWorkPublishBean> {

    private boolean mHistroy;

    /**
     * 用来存储课程id ，对应的名称内容
     */
    //private Map<Integer, String> mCourseMap = new HashMap<Integer, String>();
    public TaskWorkListAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    public TaskWorkListAdapter(RecyclerView recyclerView, boolean isHistroy) {
        super(recyclerView);
        mHistroy = isHistroy;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.work_recycler_item_work;
    }

    @Override
    public BGARecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    //@Override
    //protected void setItemChildListener(BGAViewHolderHelper helper, int viewType) {
    //    helper.setItemChildClickListener(R.id.tv_check);
    //}

    @Override
    protected void fillData(final BGAViewHolderHelper bgaViewHolderHelper, int i,
            TaskWorkPublishBean bean) {
        bgaViewHolderHelper.setText(R.id.tv_title, bean.getTask().getTitle())
                .setText(R.id.tv_subtitle, bean.getTask().getSubTitle())
                .setText(R.id.tv_count_finish, String.valueOf(bean.getSubmitNum()))
                .setText(R.id.tv_count_all, String.format("/%1$s人", bean.getStudentNum()))
                .setText(R.id.tv_check,
                        bean.isCheckState() ? R.string.work_label_checked : R.string.work_label_uncheck);

        FrameLayout.LayoutParams params =
                (FrameLayout.LayoutParams) bgaViewHolderHelper.getView(R.id.cl).getLayoutParams();
        int dp10 = DensityUtil.dip2px(bgaViewHolderHelper.getConvertView().getContext(), 10);
        //16 或者 10
        params.topMargin = i == 0 ? (int) (dp10 * 1.6) : dp10;

        bgaViewHolderHelper.getTextView(R.id.tv_check).setEnabled(!bean.isCheckState());
        ProgressBar pb = bgaViewHolderHelper.getView(R.id.progressBar);
        pb.setMax(bean.getStudentNum());
        pb.setProgress(bean.getSubmitNum());

        //读取课程类型，保存在map缓存中
        //String courseName;
        //if (mCourseMap.containsKey(bean.getCourseThemeId())) {
        //    courseName = mCourseMap.get(bean.getCourseThemeId());
        //} else {
        //    courseName = (String) SharedPreApp.getInstance()
        //            .get(bgaViewHolderHelper.getConvertView().getContext(),
        //                    SharedPreApp.KEY_COURSE_NAME + bean.getCourseThemeId(), "");
        //    mCourseMap.put(bean.getCourseThemeId(), courseName);
        //}
        bgaViewHolderHelper.setText(R.id.tv_type, bean.getTaskCategory().getCategoryName());

        bgaViewHolderHelper.setText(R.id.tv_progress, getProgressString(bean));

        String publishTime =
                String.format("%1$s布置", DateUtils.getWorkTimeFormatNoToday(bean.getPublishTime()));
        bgaViewHolderHelper.setText(R.id.tv_publish_time, publishTime);
        if (bean.isState()) {
            String endTimeStr =
                    String.format("%1$s 截止", DateUtils.getWorkTimeFormat(bean.getEndTime()));
            bgaViewHolderHelper.setText(R.id.tv_end_time, endTimeStr);
        } else {
            bgaViewHolderHelper.setText(R.id.tv_end_time, R.string.work_label_deadline);
        }
    }

    /**
     * 是否为该分类下的第一个条目
     */
    public boolean isCategoryFistItem(int position) {
        // 第一条数据是该分类下的第一个条目
        if (position == 0) {
            return true;
        }

        long time1 = getItem(position - 1).getPublishTime();
        long time2 = getItem(position).getPublishTime();
        // 当前条目的分类和上一个条目的分类不相等时，当前条目为该分类下的第一个条目
        boolean isLike = DateUtils.isMonthInner(time1, time2);
        return !isLike;
    }

    /**
     * 返回进度所展示的内容
     */
    private String getProgressString(TaskWorkPublishBean bean) {
        StringBuffer stringBuffer = new StringBuffer();
        //switch (bean.getCourseThemeId()) {
        //    case AppType.COURSE_CAPACITY:
        stringBuffer.append("(");
        stringBuffer.append(bean.getTask().getTaskNum());
        stringBuffer.append("小题)");

        //        break;
        //    case AppType.COURSE_TALENT:
        //        stringBuffer.append("(");
        //        if (!mHistroy) {
        //            //只有进行的中的显示当前， 已完成就只显示总的
        //            stringBuffer.append(bean.getConductNum());
        //            stringBuffer.append("/");
        //        }
        //        stringBuffer.append(bean.getTotalNum());
        //        stringBuffer.append("阶段)");
        //        break;
        //    case AppType.COURSE_CHARACTER:
        //        stringBuffer.append("(");
        //        if (!mHistroy) {
        //            //只有进行的中的显示当前， 已完成就只显示总的
        //            stringBuffer.append(bean.getConductNum());
        //            stringBuffer.append("/");
        //        }
        //        stringBuffer.append(bean.getTotalNum());
        //        stringBuffer.append("天)");
        //        break;
        //    case AppType.COURSE_THOUGHT:
        //        break;
        //    default:
        //        break;
        //}
        return stringBuffer.toString();
    }
}
