package com.test.basemoudle;

/**
 * Created by zhuj on 2017/6/8 下午5:47.
 */
public class AppConstants {
  /**
   * 按钮 圆角边框宽度 ，单位像素
   */
  public final static int BUTTON_FRAME_WIDTH = 1;

  /**
   * APP key
   */
  public final static String APP_KEY = "PWEEDU_TEACH_API_PROD";
  public final static String APP_KEY_TEST = "PWEEDU_TEACH_API_TEST";

  /**
   * 如变量名
   */
  public final static String APP_SECRET = "729302a6c152cb0eaceccd035c1b6781";
  public final static String APP_SECRET_TEST = "0ecf26bae6c382ffe8cf5033add1ad95";



  //-----------------------------RxBus----------------------------------
  /**
   * rxbus，是否是homeUserFragment  点击右边下一页
   */
  public static final String RXBUS_VIEWPAGER_NEXT = "rxbus_viewpager_next";
  public static final String RXBUS_KID_SELECT = "rxbus_kid_select";
  public static final String RXBUS_HEAD_CHANGE = "rxbus_head_change";
  /**
   * 聊天信息发生了变化
   */
  public static final String RXBUS_CHAT_BEAN = "rxbus_chat_bean";

  /**
   * 有推送消息
   */
  public static final String RXBUS_PUSH_MESSAGE = "rxbus_push_message";
  /**
   * 版本检测
   */
  public static final String RXBUS_VERSION = "rxubs_version";

  /**
   * 语聊消息红点
   */
  public static final String RXBUS_PUSH_CHAT = "rxbus_push_chat";

  public static final String RXBUS_HABIT_REFRESH = "rxbus_habit_refresh";

  /**
   * 发起定位
   */
  public static final String RXBUS_START_LOCAL = "rxbus_start_local";

  public static final String RXBUS_IM_CLICK = "rx_im_click";

  /**
   * 极光错误
   */
  public static final String RXBUS_IM_CHAT_ERROR = "rx_im_chat_error";

  /**
   * 通知视频播放完毕
   * **/
  public static final String RXBUS_AUTO_COMPLETION = "rxbus_auto_completion";
  public static final String RXBUS_CHANGE_STATE = "rxbus_change_state";

  public static final String RXBUS_PERMISSION_FLOAT = "rxbus_permission_float";

  /**
   * 侧边栏关闭事件
   */
  public static final String RXBUS_SLIDE_CLOSE = "rxbus_slide_close";

  /**
   * 删除任务
   * */
  public static final String RXBUS_DEL_TASK = "rxbus_del_task";

  /**
   * 选择任务
   * */
  public static final String RXBUS_SELECT_TASK = "rxbus_select_task";

  /**
   * 新增任务
   * */
  public static final String RXBUS_ADD_TASK = "rxbus_add_task";

  /**
   * 清除所有任务
   * */
  public static final String RXBUS_REMOVE_ALL_TASK = "rxbus_remove_all_task";

  /**
   * 更新播放窗口
   * */
  public static final String RXBUS_PLAYER_WINDOW = "rxbus_player_window";

  /**
   * 更新歌曲信息
   * */
  public static final String RXBUS_UPDATE_STORY = "rxbus_update_story";
  /**
   * 发布作业引导结束
   */
  public static final String RXBUS_GUIDE_PUBLISH_OVER = "rxbus_guide_publish_over";


  /**
   * H5录音事件
   */
  public static final String RXBUS_H5_RECORD = "rxbus_h5_record";
  public static final String RXBUS_H5_RECORD_PLAY_OVER = "rxbus_h5_record_play_finish";
  public static final String RXBUS_H5_RECORD_RESULT = "rxbus_h5_record_result";
  public static final String RXBUS_H5_RECORD_EVENT = "rxbus_h5_record_OvalError";
  public static final String RXBUS_H5_MEMBER = "rxbus_h5_member";


  //-------------------------------------------------------------
  /**
   * 微信应用ID
   **/
  public static final String WX_APP_ID = "wx3126cf211ccc543b";
  public static final String WX_APP_SCRENT = "wx3126cf211ccc543b";
  public static final String QQ_APP_ID = "1106233883";
  public static final String QQ_APP_KEY = "c2qF4QYz0pX3MHof";
  public static final String SINA_APP_KEY = "3938575922";
  public static final String SINA_APP_SCRENT = "766f8c2c790c761253d1229ee57d015a";
  public static final String JIGUAN_KEY = "a11540abdf145f446a417099";

    /**
   * 版本更新已下载字节数
   */
  public static String KEY_UPDATE_DOWNLOAD_BYTES = "key_update_download_bytes";
  public static String KEY_UPDATE_DOWNLOAD_COMPLETE = "key_update_download_complete";

  /**
   * oss缩略图尺寸， 根据 原图width - 240, 等比例缩放
   */
  public static final String OSS_PICTUREH_THUMBNAIL = "?x-oss-process=image/resize,w_240";
  public static final String OSS_PICTUREH_THUMBNAIL_480 = "?x-oss-process=image/resize,w_480";
  public static final String OSS_PICTUREH_THUMBNAIL_1080 = "?x-oss-process=image/resize,w_1080";

  /**
   * 首页初始化数据
   */
  public static final String COURSE_INIT = "{\n"
          + "\t\"code\": 0,\n"
          + "\t\"message\": \"成功\",\n"
          + "\t\"data\": [{\n"
          + "\t\t\"type\": 0,\n"
          + "\t\t\"contList\": [{\n"
          + "\t\t\t\"id\": 4,\n"
          + "\t\t\t\"title\": \"思维实践\",\n"
          + "\t\t\t\"subTitle\": \"思维实践副标题\",\n"
          + "\t\t\t\"rule\": \"思维实践规则\",\n"
          + "\t\t\t\"supCategorys\": [{\n"
          + "\t\t\t\t\"supCategoryName\": \"选择分类\",\n"
          + "\t\t\t\t\"categorys\": [{\n"
          + "\t\t\t\t\t\"id\": 1,\n"
          + "\t\t\t\t\t\"categoryName\": \"A分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 2,\n"
          + "\t\t\t\t\t\"categoryName\": \"B分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 3,\n"
          + "\t\t\t\t\t\"categoryName\": \"C分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 4,\n"
          + "\t\t\t\t\t\"categoryName\": \"D分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 8,\n"
          + "\t\t\t\t\t\"categoryName\": \"E分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 9,\n"
          + "\t\t\t\t\t\"categoryName\": \"F分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 10,\n"
          + "\t\t\t\t\t\"categoryName\": \"G分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 11,\n"
          + "\t\t\t\t\t\"categoryName\": \"H分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}]\n"
          + "\t\t\t}]\n"
          + "\t\t}, {\n"
          + "\t\t\t\"id\": 3,\n"
          + "\t\t\t\"title\": \"性格塑造\",\n"
          + "\t\t\t\"subTitle\": \"性格塑造副标题\",\n"
          + "\t\t\t\"rule\": \"性格塑造规则\",\n"
          + "\t\t\t\"supCategorys\": [{\n"
          + "\t\t\t\t\"supCategoryName\": \"选择分类\",\n"
          + "\t\t\t\t\"categorys\": [{\n"
          + "\t\t\t\t\t\"id\": 5,\n"
          + "\t\t\t\t\t\"categoryName\": \"D分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 6,\n"
          + "\t\t\t\t\t\"categoryName\": \"E分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 7,\n"
          + "\t\t\t\t\t\"categoryName\": \"F分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 12,\n"
          + "\t\t\t\t\t\"categoryName\": \"C分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 13,\n"
          + "\t\t\t\t\t\"categoryName\": \"A分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 14,\n"
          + "\t\t\t\t\t\"categoryName\": \"B分类\",\n"
          + "\t\t\t\t\t\"isShow\": null\n"
          + "\t\t\t\t}]\n"
          + "\t\t\t}]\n"
          + "\t\t}, {\n"
          + "\t\t\t\"id\": 1,\n"
          + "\t\t\t\"title\": \"学习力提升\",\n"
          + "\t\t\t\"subTitle\": \"学习力提升副标题\",\n"
          + "\t\t\t\"rule\": \"学些力提升规则修改在了\",\n"
          + "\t\t\t\"supCategorys\": [{\n"
          + "\t\t\t\t\"supCategoryName\": \"选择分类\",\n"
          + "\t\t\t\t\"categorys\": []\n"
          + "\t\t\t}]\n"
          + "\t\t}, {\n"
          + "\t\t\t\"id\": 2,\n"
          + "\t\t\t\"title\": \"天赋认知\",\n"
          + "\t\t\t\"subTitle\": \"天赋认知副标题\",\n"
          + "\t\t\t\"rule\": \"天赋认知规则\",\n"
          + "\t\t\t\"supCategorys\": [{\n"
          + "\t\t\t\t\"supCategoryName\": \"选择分类\",\n"
          + "\t\t\t\t\"categorys\": []\n"
          + "\t\t\t}]\n"
          + "\t\t}]\n"
          + "\t}, {\n"
          + "\t\t\"type\": 1,\n"
          + "\t\t\"contList\": [{\n"
          + "\t\t\t\"id\": 0,\n"
          + "\t\t\t\"title\": \"推荐\",\n"
          + "\t\t\t\"subTitle\": \"\",\n"
          + "\t\t\t\"rule\": \"\",\n"
          + "\t\t\t\"supCategorys\": []\n"
          + "\t\t}, {\n"
          + "\t\t\t\"id\": 1,\n"
          + "\t\t\t\"title\": \"名师教育推荐\",\n"
          + "\t\t\t\"subTitle\": \"\",\n"
          + "\t\t\t\"rule\": \"\",\n"
          + "\t\t\t\"supCategorys\": []\n"
          + "\t\t}, {\n"
          + "\t\t\t\"id\": 2,\n"
          + "\t\t\t\"title\": \"故事推荐\",\n"
          + "\t\t\t\"subTitle\": \"\",\n"
          + "\t\t\t\"rule\": \"\",\n"
          + "\t\t\t\"supCategorys\": [{\n"
          + "\t\t\t\t\"supCategoryName\": \"选择分类\",\n"
          + "\t\t\t\t\"categorys\": [{\n"
          + "\t\t\t\t\t\"id\": 1,\n"
          + "\t\t\t\t\t\"categoryName\": \"PWE分类\",\n"
          + "\t\t\t\t\t\"isShow\": true\n"
          + "\t\t\t\t}]\n"
          + "\t\t\t}, {\n"
          + "\t\t\t\t\"supCategoryName\": \"PWE分类\",\n"
          + "\t\t\t\t\"categorys\": [{\n"
          + "\t\t\t\t\t\"id\": 2,\n"
          + "\t\t\t\t\t\"categoryName\": \"德商\",\n"
          + "\t\t\t\t\t\"isShow\": true\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 3,\n"
          + "\t\t\t\t\t\"categoryName\": \"智商\",\n"
          + "\t\t\t\t\t\"isShow\": true\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 4,\n"
          + "\t\t\t\t\t\"categoryName\": \"情商\",\n"
          + "\t\t\t\t\t\"isShow\": true\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 5,\n"
          + "\t\t\t\t\t\"categoryName\": \"胆商\",\n"
          + "\t\t\t\t\t\"isShow\": false\n"
          + "\t\t\t\t}]\n"
          + "\t\t\t}, {\n"
          + "\t\t\t\t\"supCategoryName\": \"选择分类\",\n"
          + "\t\t\t\t\"categorys\": [{\n"
          + "\t\t\t\t\t\"id\": 6,\n"
          + "\t\t\t\t\t\"categoryName\": \"功能分类\",\n"
          + "\t\t\t\t\t\"isShow\": true\n"
          + "\t\t\t\t}]\n"
          + "\t\t\t}, {\n"
          + "\t\t\t\t\"supCategoryName\": \"功能分类\",\n"
          + "\t\t\t\t\"categorys\": [{\n"
          + "\t\t\t\t\t\"id\": 7,\n"
          + "\t\t\t\t\t\"categoryName\": \"探险系列\",\n"
          + "\t\t\t\t\t\"isShow\": true\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 8,\n"
          + "\t\t\t\t\t\"categoryName\": \"魔法系列\",\n"
          + "\t\t\t\t\t\"isShow\": true\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 9,\n"
          + "\t\t\t\t\t\"categoryName\": \"历史经典\",\n"
          + "\t\t\t\t\t\"isShow\": true\n"
          + "\t\t\t\t}, {\n"
          + "\t\t\t\t\t\"id\": 10,\n"
          + "\t\t\t\t\t\"categoryName\": \"百科系列\",\n"
          + "\t\t\t\t\t\"isShow\": false\n"
          + "\t\t\t\t}]\n"
          + "\t\t\t}]\n"
          + "\t\t}, {\n"
          + "\t\t\t\"id\": 3,\n"
          + "\t\t\t\"title\": \"环创推荐\",\n"
          + "\t\t\t\"subTitle\": \"\",\n"
          + "\t\t\t\"rule\": \"\",\n"
          + "\t\t\t\"supCategorys\": []\n"
          + "\t\t}, {\n"
          + "\t\t\t\"id\": 4,\n"
          + "\t\t\t\"title\": \"手工推荐\",\n"
          + "\t\t\t\"subTitle\": \"\",\n"
          + "\t\t\t\"rule\": \"\",\n"
          + "\t\t\t\"supCategorys\": []\n"
          + "\t\t}, {\n"
          + "\t\t\t\"id\": 5,\n"
          + "\t\t\t\"title\": \"科学小实验推荐\",\n"
          + "\t\t\t\"subTitle\": \"\",\n"
          + "\t\t\t\"rule\": \"\",\n"
          + "\t\t\t\"supCategorys\": []\n"
          + "\t\t}]\n"
          + "\t}],\n"
          + "\t\"timestamp\": 1537583292556\n"
          + "}";


}
