package com.spark.platform.admin.api.feign.client;


import com.spark.platform.admin.api.dto.UserDTO;
import com.spark.platform.admin.api.entity.user.User;
import com.spark.platform.admin.api.feign.fallback.UserClientFallBackFactory;
import com.spark.platform.common.base.constants.ServiceNameConstants;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.feign.config.FeignRequestInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.feign.client
 * @ClassName: UserClient
 * @Description:
 * @Version: 1.0
 */
@FeignClient(contextId = "userClient", name = ServiceNameConstants.SPARK_ADMIN, fallbackFactory = UserClientFallBackFactory.class)
public interface UserClient {
    /**
     * 通过账号获取用户信息
     * @param username 账号
     * @return
     */
    @GetMapping("/user/api")
    ApiResponse<UserDTO> getUserByUserName(@RequestParam("username") String username);

    /**
     * 通过id获取用户
     * @param id
     * @return
     */
    @GetMapping("/user/info/{id}")
    ApiResponse<User> getUserByUserId(@PathVariable("id") Long id);
}
