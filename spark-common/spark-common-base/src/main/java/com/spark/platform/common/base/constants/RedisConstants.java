package com.spark.platform.common.base.constants;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.constants
 * @ClassName: RedisConstants
 * @Author: wangdingfeng
 * @Description: redis缓key
 * @Date: 2020/11/10 15:20
 * @Version: 1.0
 */
public interface RedisConstants {

    /**
     * Redis Cache
     */
    String USER_CACHE = "RedisUserCache";
    /**
     * 字典子项list
     */
    String DICT_CACHE = "RedisDictCache";

    /**
     * Redis Cache
     */
    String CLIENT_CACHE = "RedisClientCache";

    /**
     * 缓存中user的key
     */
    String USER_KEY_PREFIX = "SparkUser_";
    /**
     * 缓存中user 前端需要的数据全部信息的key
     */
    String USER_INFO_KEY_PREFIX = "SparkUser_Info_";
    /**
     * 缓存中字典的key
     */
    String DICT_KEY_ALL_PREFIX = "SparkAllDict";
    /**
     * 缓存中user的key
     */
    String DICT_KEY_PREFIX = "SparkDict_";

    /**
     * oauth 客户端信息
     */
    String CLIENT_DETAILS_KEY = "SparkClient_";

    /**
     * Redis默认过期时长，单位：秒  5分钟
     */
    long DEFAULT_EXPIRE = 60 * 5;

    /**
     * Redis 不设置过期时长
     */
    long NOT_EXPIRE = -1;

    /**
     * Redis set 前缀
     */
    String KEY_SET_PREFIX = "_set:";

    /**
     * Redis list 前缀
     */
    String KEY_LIST_PREFIX = "_list:";

    String SHOP_ATTR_CACHE = "ShopAttrCache";
    /**
     * 分类
     */
    String SHOP_CATEGORY_CACHE = "shopCategoryCache";
}
