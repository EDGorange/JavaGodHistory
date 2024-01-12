package com.example.springdemo.inter;

/**
 * @author ruilx
 */
public interface RedisKey {

    /**
     * 门户系统用户登录会话信息key
     */
    String PORTAL_USER_SESSION_KEY = "user_session:";

    /**
     * 用户信息缓存key
     */
    String USER_INFO_KEY = "user_info:";

    /**
     * 验证码id缓存key
     */
    String AUTHORIZATION_CODE_KEY = "authorization_code:";

    /**
     * 发送邮箱校验时长
     */
    String EMAIL_CODE_KEY = "email_code:";

    /**
     * id作为key缓存
     */
    String DICT_ID_KEY = "dict_id:";

    /**
     * id作为key缓存
     */
    String MESSAGE_NUM = "message_num:";

    /**
     * 组作为key缓存
     */
    String DICT_CACHE_KEY = "dict_cache:";

    /**
     * 地区key
     */
    String ADDRESS_SESSION_KEY = "location_session:";

    /**
     * 后台管理系统用户登录会话信息key
     */
    String MANAGEMENT_USER_SESSION_KEY = "admin_session:";
    /**
     * 后台管理系统用户资源
     */
    String MANAGEMENT_USER_RESOURCE_KEY = "user_resource:";

    /**
     * 阿里云视频登录会话信息key
     */
    String CVC_USER_KEY = "cvc_user:";
    /**
     * 阿里云视频登录会话信息key
     */
    String CVC_ADMIN_KEY = "cvc_admin:";
    /**
     * 首页外站链接key
     */
    String PORTAL_HOME_OUT_WEBSITE_RESOURCE = "out_website:";
    /**
     * 首页外站链接key
     */
    String PORTAL_HOME_PICTURE_DATA = "picture_data:";

    /**
     * 门户系统首页政策资讯查看key
     */
    String PORTAL_HOME_POLICY_INFORMATION = "policy_information:";
    /**
     * 门户系统首页投资动态查看key
     */
    String PORTAL_HOME_INVESTMENT_TRENDS = "investment_trends:";
    /**
     * 门户系统首页轮播图查看key
     */
    String PORTAL_HOME_PICTURE_ROTATION = "picture_rotation:";
    /**
     * 门户系统首页通知公告查看key
     */
    String PORTAL_HOME_NOTICE = "portal_home_notice:";

    /**
     * 门户系统首页key中文
     */
    String PORTAL_HOME_ZH = "portal_home_zh:";

    /**
     * 门户系统首页key英文
     */
    String PORTAL_HOME_EN = "portal_home_en:";

    /**
     * 门户系统首页key中文
     */
    String PORTAL_TAIL_ZH = "portal_tail_zh:";

    /**
     * 门户系统首页key英文
     */
    String PORTAL_TAIL_EN = "portal_tail_en:";
    /**
     * 投资信息发布者key
     */
    String INVESTMENT_INFO_PUBLISHER = "publisher_id:";

    /**
     * 招商信息发布人key
     */
    String ATTRACT_INVES_INFO_PUB = "attract_inves_id";

    /**
     * 恶意刷接口key
     */
    String NO_REPEAT_SUBMIT = "no_repeat_submit:";


    /**
     * 投资对接key
     */
    String INVESTMENT_DOCKING_APPLY = "investment_docking_id";

    /**
     * 留言间隔时间
     */
    String LEAVE_MESSAGE_TIME = "leave_message_time";
}
