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
     */
    public static final String OAUTH_SIGNING_KEY = "spark_oauth_key";

    public static final String OAUTH_AUTH_FORM_URI = "/authentication/form";

    public static final String OAUTH_AUTH_REQUIRE_URI = "/authentication/require";

    /**
     * ip
     */
    public static final String UNKNOWN = "unknown";

    /**
     * druid配置
     */
    public static final String DB_PREFIX = "spring.datasource";
    /**
     * spark security配置
     */
    public static final String SPARK_PREFIX = "spark";
    /**
     * minio 配置
     */
    public static final String MINIO_PREFIX = "minio";

    /**
     * spark security配置
     */
    public static final String SPARK_OAUTH_PREFIX = "spark.security";
    /**
     * oauth security配置
     */
    public static final String OAUTH_SECURITY_PREFIX = "security.oauth2.client";

    /**
     * security  过滤url 配置
     */
    public static final String SPARK_FILTER_IGNORE = "spark.ignore";
    /**
     * 日志配置
     */
    public static final String SPARK_LOG_FILTER = "spark.log";

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

    public static final String OAUTH_PREFIX = "oauth:";

    public static final String CURRENT = "current";

    public static final String SIZE = "size";

    /**
     * 默认用户名
     */
    public static final String DEFAULT_USER_SYSTEM = "system";
    /**
     * 默认密码
     */
    public static final String DEFAULT_USER_PASSWORD = "123456";
    /**
     * 文件上传保存临时路径
     */
    public static final String FILE_PATH_TEMP = "temp";
    /**
     * 文件存储业务路径
     */
    public static final String FILE_PATH_BIZ = "biz";
    /**
     * 角色前缀
     */
    public static final String ROLE_PREFIX = "ROLE_";
    /**
     * 超级管理员角色
     */
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    /**
     * like条件
     */
    public static final String LIKE_CHARACTER = "%";
    /**
     * 文件 separator
     */
    public static final String FILE_SEPARATOR = "/";


    public static String getCacheKey(String prefix, String key) {
        return prefix + "::" + key;
    }


}
