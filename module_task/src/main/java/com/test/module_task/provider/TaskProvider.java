package com.test.module_task.provider;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.basemoudle.provider.ITaskProvider;
import com.test.module_task.TaskFragment;

/**
 * @author zhuj 2019-08-31 16:13.
 */
@Route(path="/task/taskProvider")
public class TaskProvider implements ITaskProvider {

    @Override
    public Fragment getMainTaskFragment() {
        return TaskFragment.newInstance(null, null);
    }

    @Override
    public void init(Context context) {

    }
}
