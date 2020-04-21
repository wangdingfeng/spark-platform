package com.spark.platform.common.base.constants;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.constants
 * @ClassName: GlobalsConstants
 * @Description: 系统常量
 * @Version: 1.0
 */
public class GlobalsConstants {

    /**
     * jwt对称加密
     * */
    public static final String OAUTH_SIGNING_KEY = "spark_oauth_key";

    public static final String OAUTH_AUTH_FORM_URI = "/authentication/form";

    public static final String OAUTH_AUTH_REQUIRE_URI = "/authentication/require";


    /**
     * Redis Cache
     */
    public static final String REDIS_USER_CACHE = "RedisUserCache";
    /**
     * 字典子项list
     */
    public static final String REDIS_DICT_CACHE = "RedisDictCache";

    /**
     * Redis Cache
     */
    public static final String REDIS_CLIENT_CACHE = "RedisClientCache";

    /**
     * 缓存中user的key
     */
    public static final String USER_KEY_PREFIX = "SparkUser_";
    /**
     * 缓存中user的key
     */
    public static final String DICT_KEY_PREFIX = "SparkDict_";

    /**
     * oauth 客户端信息
     */
    public static final String CLIENT_DETAILS_KEY =  "SparkClient_";

    /**
     * Redis默认过期时长，单位：秒  5分钟
     */
    public static final long DEFAULT_EXPIRE = 60 * 5;

    /**
     * Redis 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;

    /**
     * Redis set 前缀
     */
    public static final String KEY_SET_PREFIX = "_set:";

    /**
     * Redis list 前缀
     */
    public static final String KEY_LIST_PREFIX = "_list:";

    /**
     * ip
     */
    public static final String UNKNOWN = "unknown";

    /**
     * druid配置
     */
    public static final String DB_PREFIX = "spring.datasource";
    /**
     *  spark security配置
     */
    public static final String SPARK_PREFIX = "spark";

    /**
     *  spark security配置
     */
    public static final String SPARK_OAUTH_PREFIX = "spark.security";
    /**
     * oauth security配置
     */
    public static final String OAUTH_SECURITY_PREFIX = "security.oauth2.client";

    /**
     * security  过滤url 配置
     */
    public static final String FILTER_IGNORE = "ignore";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    public static final Integer FAIL = 1;

    /**
     * oauth 相关前缀
     */
    public static final String OAUTH_TOKEN_URL = "/auth/oauth/token";

    public static final String  OAUTH_PREFIX = "oauth:";

    public static final String CURRENT = "current";

    public static final String SIZE = "size";

    /**
     * 默认用户名
     */
    public static final String DEFAULT_USER_SYSTEM = "system";
    /**
     * 默认密码
     */
    public static final String DEFAULT_USER_PASSWORD  = "123456";
    /**
     * 文件上传保存临时路径
     */
    public static final String FILE_PATH_TEMP = "temp";
    /**
     * 文件存储业务路径
     */
    public static final String FILE_PATH_BIZ = "biz";

}
