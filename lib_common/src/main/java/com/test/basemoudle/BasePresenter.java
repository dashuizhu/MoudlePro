package com.test.basemoudle;

import android.arch.lifecycle.LifecycleOwner;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import org.jetbrains.annotations.NotNull;

/**
 * presenter的基类
 * 调用{@link #addDisposable(Disposable)} 记录网络操作，
 * 然后可使用{@link #destroy()} 关闭
 *
 * @author zhuj
 * @date 2017/6/9 下午4:09
 */
public class BasePresenter implements IPresenter {

  protected IBaseView mBaseView;

  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  public void destroy() {
    if (mCompositeDisposable == null) {
      return;
    }
    if (!mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.dispose();
    }
    mCompositeDisposable = null;
  }

  protected void addDisposable(Disposable disposable) {
    if (mCompositeDisposable == null) {
      return;
    }
    mCompositeDisposable.add(disposable);
  }

  //在宿主生命周期时， 自动释放
  @Override
  public void onDestroy(@NotNull LifecycleOwner owner) {
    destroy();
  }
}
