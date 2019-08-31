package com.test.basemoudle.provider;

import android.support.v4.app.Fragment;
import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @author zhuj 2019-08-31 16:11.
 */
public interface ITaskProvider extends IProvider {

    Fragment getMainTaskFragment();

}
