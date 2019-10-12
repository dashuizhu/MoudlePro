package com.test.module_work.ui.presenter;

import com.test.basemoudle.BasePresenter;
import com.test.basemoudle.IBaseView;
import com.test.basemoudle.bean.NetworkResult;
import com.test.module_work.R;
import com.test.module_work.bean.TaskWorkPublishResult;
import com.test.module_work.ui.model.TaskWorkModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * 登入注册
 *
 * @author zhuj
 * @date 2017/6/9 下午4:04
 */
public class TaskWorkPresenter extends BasePresenter {

    public final static int PAGE_SIZE = 10;

    private TaskWorkModel mModel;

    public TaskWorkPresenter(IBaseView baseView) {
        this.mBaseView = baseView;
        this.mModel = new TaskWorkModel();
    }

    public void refreshList(final boolean isHistory) {
        mModel.getWorkList(isHistory, 1, PAGE_SIZE, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskWorkPublishResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(TaskWorkPublishResult networkResult) {
                        networkResult.setTag(NetworkResult.TAG_REFRESH);
                        mBaseView.hideProgress();
                        mBaseView.onSuccess(networkResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBaseView.onLoadError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void loadmoreList(boolean isHistory, int page) {
        mModel.getWorkList(isHistory, page, PAGE_SIZE, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskWorkPublishResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(TaskWorkPublishResult networkResult) {
                        networkResult.setTag(NetworkResult.TAG_LOADMORE);
                        mBaseView.hideProgress();
                        mBaseView.onSuccess(networkResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBaseView.onLoadError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取课程的  学生完成情况列表
     * @param publishId
     */
    //public void getWorkPublishDetail(int publishId) {
    //    mModel.getWorkPublishComple(publishId)
    //            .subscribeOn(Schedulers.io())
    //            .observeOn(AndroidSchedulers.mainThread())
    //            .subscribe(new Observer<TaskWorkPublishDetailResult>() {
    //                @Override
    //                public void onSubscribe(Disposable d) {
    //                    addDisposable(d);
    //                }
    //
    //                @Override
    //                public void onNext(TaskWorkPublishDetailResult networkResult) {
    //                    mBaseView.hideProgress();
    //                    mBaseView.onSuccess(networkResult);
    //                }
    //
    //                @Override
    //                public void onError(Throwable e) {
    //                    e.printStackTrace();
    //                    mBaseView.onLoadError(e);
    //                }
    //
    //                @Override
    //                public void onComplete() {
    //
    //                }
    //            });
    //}

    /**
     * 催交作业
     */
    public void setWorkStudentPrompt(int publishId) {
        mBaseView.showProgress();
        mModel.setWorkStudentPrompt(publishId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NetworkResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(NetworkResult networkResult) {
                        mBaseView.hideProgress();
                        mBaseView.onSuccess(networkResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBaseView.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
