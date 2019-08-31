package com.test.mymoudle.ui.provider;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.basemoudle.provider.IMyProvider;
import com.test.mymoudle.ui.MineFragment;

/**
 * @author zhuj 2019-08-31 16:14.
 */
@Route(path="/my/myProvider", name = "我的")
public class MyProvider implements IMyProvider {

    @Override
    public Fragment getMainMyFragment() {
        return new MineFragment();
    }

    @Override
    public void init(Context context) {

    }
}
