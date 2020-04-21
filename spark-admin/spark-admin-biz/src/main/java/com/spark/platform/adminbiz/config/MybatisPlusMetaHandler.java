package com.spark.platform.adminbiz.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.security.model.LoginUser;
import com.spark.platform.common.security.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.config
 * @ClassName: MybatisPlusMetaHandler
 * @Author: wangdingfeng
 * @Description: mybatis plus 插入更新监听
 * @Version: 1.0
 */
@Slf4j
@Component
public class MybatisPlusMetaHandler implements MetaObjectHandler {

    /**
     * 插入操作
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //获取当前登录账号
        String account = getAccount();
        //放入当前操作者信息
        this.setFieldValByName("creator",account,metaObject);
        this.setFieldValByName("modifier",account,metaObject);
        this.setFieldValByName("createDate",LocalDateTime.now(),metaObject);
        this.setFieldValByName("modifyDate",LocalDateTime.now(),metaObject);
        this.setFieldValByName("delFlag",0,metaObject);
    }

    /**
     * 更新操作
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //获取当前登录账号
        String account = getAccount();
        this.setFieldValByName("modifier",account,metaObject);
        this.setFieldValByName("modifyDate", LocalDateTime.now(),metaObject);
    }

    /**
     * 获取当前登录账号
     * @return
     */
    private String getAccount(){
        LoginUser user = UserUtils.getLoginUser();
        if(null == user){
           return GlobalsConstants.DEFAULT_USER_SYSTEM;
        }
        return user.getUsername();
    }
}
