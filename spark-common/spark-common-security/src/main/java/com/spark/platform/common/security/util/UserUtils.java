package com.spark.platform.common.security.util;

import com.spark.platform.common.security.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.util
 * @ClassName: UserUtils
 * @Description: 用户工具类
 * @Version: 1.0
 */
@Slf4j
public class UserUtils {

    public static LoginUser getLoginUser() {
        LoginUser loginUser = null;
        try {
            loginUser =(LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            log.info("============当前无登录信息，请注意================");
        }
        return loginUser;
    }
}
