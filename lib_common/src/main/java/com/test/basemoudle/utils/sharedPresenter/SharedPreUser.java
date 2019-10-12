package com.test.basemoudle.utils.sharedPresenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.test.basemoudle.BaseApplication;

/**
 * 用来保存user相关数据， 切换用户，直接清除文件
 *
 * @author zhuj
 * @date 2017/6/15 下午6:11
 */
public class SharedPreUser extends BaseSharedPre {

    private static SharedPreUser mSharedPre;

    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "buddyTeacher_share_user_data";

    /**
     * 用户昵称
     */
    public static final String KEY_USER_NAME = "key_user_nick";

    /**
     * 用户性别
     */
    public static final String KEY_USER_GANDER = "key_user_gander";

    /**
     * 用户头像
     */
    public static final String KEY_USER_AVATOR = "key_userinfo_avator";

    /**
     * 学校Id
     */
    public static final String KEY_SCHOOL_ID   = "key_school_id";
    /**
     * 学校名称
     */
    public static final String KEY_SCHOOL_NAME = "key_school_name";

    /**
     * 班级id
     */
    public static final String KEY_GRADE_ID   = "key_grade_id";
    /**
     * 班级名称
     */
    public static final String KEY_GRADE_NAME = "key_grade_name";
    /**
     * 班级等级
     */
    public static final String KEY_GRADE_RANK = "key_grade_rank";
    /**
     * 班级学生数量
     */
    public static final String KEY_GRADE_NUM  = "key_grade_num";
    /**
     * 老师id
     */
    public static final String KEY_MEMBER     = "key_member";

    /**
     * accessToken
     */
    public static final String KEY_TOKEN         = "accessToken";
    public static final String KEY_REFRESH_TOKEN = "refreshToken";
    /**
     * token过期时间
     */
    public static final String KEY_TOKEN_EXPIRED = "accessTokenExpired";

    /**
     * 未读消息数
     */
    public static final String KEY_REMIND = "key_remind_count";

    @Override
    SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreUser getInstance() {
        if (mSharedPre == null) {
            synchronized (SharedPreUser.class) {
                if (mSharedPre == null) {
                    mSharedPre = new SharedPreUser();
                }
            }
        }
        return mSharedPre;
    }

    public String getToken() {
        return (String) get(BaseApplication.getContext(), KEY_TOKEN, "");
    }

    public String getRefreshToken() {
        return (String) get(BaseApplication.getContext(), KEY_REFRESH_TOKEN, "");
    }

    public String getUserNick() {
        return (String) get(BaseApplication.getContext(), KEY_USER_NAME, "");
    }

    public String getUserAvator() {
        return (String) get(BaseApplication.getContext(), KEY_USER_AVATOR, "");
    }

    public int getSchoolId() {
        return (Integer) get(BaseApplication.getContext(), KEY_SCHOOL_ID, 0);
    }

    public String getSchoolName() {
        return (String) get(BaseApplication.getContext(), KEY_SCHOOL_NAME, "");
    }

    public int getKeyGradeId() {
        return (Integer) get(BaseApplication.getContext(), KEY_GRADE_ID, -1);
    }

    public String getKeyGradeName() {
        return (String) get(BaseApplication.getContext(), KEY_GRADE_NAME, "");
    }

    public String getKeyGnder() {
        return (String) get(BaseApplication.getContext(), KEY_USER_GANDER, "");
    }

    public int getKeyGradeRank(int defaultInt) {
        return (Integer) get(BaseApplication.getContext(), KEY_GRADE_RANK, defaultInt);
    }

    public int getKeyGradeNum() {
        return (Integer) get(BaseApplication.getContext(), KEY_GRADE_NUM, 0);
    }

    public int getMemberId() {
        return (Integer) get(BaseApplication.getContext(), KEY_MEMBER, 0);
    }

    public int[] getRemindUnread() {
        String str = (String) get(BaseApplication.getContext(), KEY_REMIND, "0|0|0|0");
        Log.d("SharedPreUser", " countREAD " + str);
        String[] strArray = str.split("\\|");
        int[] array = new int[4];

        for (int i = 0; i < strArray.length; i++) {
            int count = 0;
            try {
                count = Integer.parseInt(strArray[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            array[i] = count;
        }
        return array;
    }


}
