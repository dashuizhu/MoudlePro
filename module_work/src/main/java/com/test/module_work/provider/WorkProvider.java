package com.test.module_work.provider;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.basemoudle.provider.IWorkProvider;
import com.test.module_work.ui.TaskWorkListFragment;

/**
 * @author zhuj 2019-08-31 16:13.
 */
@Route(path = "/work/workProvider", name = "作业")
public class WorkProvider implements IWorkProvider {

    @Override
    public Fragment getMainWorkFragment() {
        return TaskWorkListFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
