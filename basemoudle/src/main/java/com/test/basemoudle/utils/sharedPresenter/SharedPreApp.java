package com.test.basemoudle.utils.sharedPresenter;

import android.content.Context;
import android.content.SharedPreferences;
import com.test.basemoudle.BaseApplication;

/**
 * 用来保存app的 数据，更换用户时可以不清空
 *
 * @author zhuj
 * @date 2017/6/15 下午6:11
 */
public class SharedPreApp extends BaseSharedPre {

    public static final     String       KEY_NOTCH = "key_notch";
    public static final     String       KEY_GUIDE = "key_guide";
    private static volatile SharedPreApp mSharedPre;

    public static final String NOTCH_TOP = "key_notch_top";
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "buddy_teacher_share_app_data";
    /**
     * 首页课程保存默认数据
     */
    public static final String KEY_COURSE_THEME = "course_theme_int";
    /**
     * 主题规则
     */
    public static final String KEY_COURSE_CAPACITY_RULE = "course_theme_capacity_rule";
    public static final String KEY_COURSE_TALENT_RULE = "course_theme_talent_rule";
    public static final String KEY_COURSE_CHARACTER_RULE = "course_theme_character_rule";
    public static final String KEY_COURSE_THOUGHT_RULE = "course_theme_thought_rule";
    /**
     * 上次拉取主题数据时间
     */
    public static final String KEY_COURSE_INIT_TIME = "course_theme_last_time";

    /**
     * 版本更新红点提醒
     */
    public static final String KEY_REMIND_VERSION = "key_remind_version";

    public static final String KEY_USER_NAME = "key_user_name";

    public static final String KEY_URI_PICTURE = "key_uri_picture";

    public static final String KEY_URI_CROP = "key_uri_crop";

    public static final String KEY_PASSWORD = "key_password";

    public static final String LAST_LOGIN_STATE = "last_login_state";

    public static final String KEY_LAST_VERSION_TIME = "key_last_version_time";

    public static final String KEY_CAPACITY_CATEGORY = "key_capacity_category";
    public static final String KEY_CHARACTER_CATEGORY = "key_character_category";
    public static final String KEY_THOUGHT_CATEGORY = "key_thought_category";
    /**
     * 课程名
     * 后面接  _课程id，
     */
    public static final String KEY_COURSE_NAME = "key_course_name_";

    public static final String KEY_OSS_KEYID = "oss_keyid";
    public static final String KEY_OSS_SECRET = "oss_secret";
    public static final String KEY_OSS_TOKEN = "oss_token";

    public static final String KEY_SOE_SECRET_ID = "soe_secret_id";
    public static final String KEY_SOE_SECRET_KEY = "soe_secret_key";
    public static final String KEY_SOE_TOKEN = "soe_token";

    public static final String PLAY_POSITION = "play_position";
    public static final String PLAY_MODE = "play_mode";
    public static final String SPLASH_URL = "splash_url";
    public static final String NIGHT_MODE = "night_mode";
    public static final String MOBILE_NETWORK_PLAY = "mobile_network_play";

    @Override
    SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreApp getInstance() {
        if (mSharedPre == null) {
            synchronized (SharedPreApp.class) {
                if (mSharedPre == null) {
                    mSharedPre = new SharedPreApp();
                }
            }
        }
        return mSharedPre;
    }

    public void clearUserInfo() {
        put(BaseApplication.getContext(), SharedPreApp.KEY_PASSWORD, "");
    }

    public boolean isVersionRemind() {
        return (boolean) get(BaseApplication.getContext(), KEY_REMIND_VERSION, false);
    }

    public boolean enableMobileNetworkPlay() {
        return (boolean) get(BaseApplication.getContext(), MOBILE_NETWORK_PLAY, false);
    }

    public int getPlayMode() {
        return (Integer) get(BaseApplication.getContext(), PLAY_MODE, 0);
    }

    public int getPlayPosition() {
        return (Integer) get(BaseApplication.getContext(), PLAY_POSITION, 0);
    }
}
