package com.spark.platform.common.security.service;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import com.spark.platform.adminapi.dto.UserDTO;
import com.spark.platform.adminapi.entity.log.LogLogin;
import com.spark.platform.adminapi.feign.client.LogClient;
import com.spark.platform.adminapi.feign.client.UserClient;
import com.spark.platform.adminapi.vo.UserVo;
import com.spark.platform.common.base.constants.BizConstants;
import com.spark.platform.common.base.exception.CommonException;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.security.model.LoginUser;
import com.spark.platform.common.utils.AddressUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.service
 * @ClassName: SparkUserDetailService
 * @Description: 用户登录 查询登录用户
 * @Version: 1.0
 */
@Service
@AllArgsConstructor
public class SparkUserDetailService implements UserDetailsService {

    private final UserClient userClient;
    private final LogClient logClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new CommonException("登录名不能为空");
        }
        ApiResponse apiResponse = userClient.getUserByUserName(username);
        UserDTO userDTO = JSON.parseObject(JSON.toJSONString(apiResponse.getData(), true), UserDTO.class);
        if (userDTO == null) {
            throw new CommonException("登录名不存在");
        }
        UserVo user = userDTO.getSysUser();
        if (BizConstants.USER_STATUS_EXPIRED.equals(user.getStatus())) {
            throw new CommonException("用户已过期");
        } else if (BizConstants.USER_STATUS_LOCKED.equals(user.getStatus())) {
            throw new CommonException("用户已锁定");
        } else if (BizConstants.USER_STATUS_UNUSED.equals(user.getStatus())) {
            throw new CommonException("用户已禁用");
        }
        //查询用户具有的权限
        List<String> authList = userDTO.getPermissions();
        List<GrantedAuthority> lists = new ArrayList<>();
        if (authList != null && authList.size() > 0) {
            for (String auth : authList) {
                lists.add(new SimpleGrantedAuthority(auth));
            }
        }
        LoginUser loginUser = new LoginUser(username, user.getPassword(), user.getNickname(), user.getStatus(), lists);
        loginUser.setId(user.getId());
        loginUser.setDeptId(user.getDeptId());
        //保存登录日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        saveLoginLog(username,request);
        return loginUser;
    }

    /**
     * 保存登录日志信息
     */
    private void saveLoginLog(final String username,final HttpServletRequest request) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1,new BasicThreadFactory.Builder().namingPattern("user-thread-pool-%d").daemon(true).build());
        scheduledThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                String ip = AddressUtils.getIpAddress(request);
                String location = AddressUtils.getCityInfo(ip);
                UserAgent userAgent = UserAgentUtil.parse(request.getHeader("user-agent"));
                String os = userAgent.getOs().getName();
                String browser = userAgent.getBrowser().getName();
                LogLogin loginLog = new LogLogin(username, os, browser, LocalDateTime.now(), location, ip);
                logClient.saveLoginLog(loginLog);
            }
        });


    }
}
