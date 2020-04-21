package com.spark.platform.common.security.service;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import com.spark.platform.adminapi.entity.authority.Menu;
import com.spark.platform.adminapi.entity.log.LogLogin;
import com.spark.platform.adminapi.feign.client.LogClient;
import com.spark.platform.adminapi.feign.client.MenuClient;
import com.spark.platform.adminapi.feign.client.UserClient;
import com.spark.platform.common.base.constants.BizConstants;
import com.spark.platform.adminapi.entity.user.User;
import com.spark.platform.common.base.exception.CommonException;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.security.model.LoginUser;
import com.spark.platform.common.utils.AddressUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.service
 * @ClassName: SparkUserDetailService
 * @Description: 用户登录 查询登录用户
 * @Version: 1.0
 */
@Component
public class SparkUserDetailService implements UserDetailsService {

    @Autowired
    private UserClient userClient;
    @Autowired
    private MenuClient menuClient;
    @Autowired
    private LogClient logClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new CommonException("登录名不能为空");
        }
        ApiResponse apiResponse = userClient.getUserByUserName(username);
        User user = JSON.parseObject(JSON.toJSONString(apiResponse.getData(), true), User.class);
        if (user == null) {
            throw new CommonException("登录名不存在");
        } else if (BizConstants.USER_STATUS_EXPIRED.equals(user.getStatus())) {
            throw new CommonException("用户已过期");
        } else if (BizConstants.USER_STATUS_LOCKED.equals(user.getStatus())) {
            throw new CommonException("用户已锁定");
        } else if (BizConstants.USER_STATUS_UNUSED.equals(user.getStatus())) {
            throw new CommonException("用户已禁用");
        }
        //查询用户具有的权限
        ApiResponse response = menuClient.findAuthByUserId(user.getId());
        List<Menu> authList = JSON.parseArray(JSON.toJSONString(response.getData(), true), Menu.class);
        List<GrantedAuthority> lists = new ArrayList<>();
        if (authList != null && authList.size() > 0) {
            for (Menu auth : authList) {
                lists.add(new SimpleGrantedAuthority(auth.getPermission()));
            }
        }
        LoginUser loginUser = new LoginUser(username, user.getPassword(), user.getNickname(), user.getStatus(), lists);
        loginUser.setId(user.getId());
        loginUser.setDeptId(user.getDeptId());
        //保存登录日志
        saveLoginLog(username);
        return loginUser;
    }

    /**
     * 保存登录日志信息
     */
    private void saveLoginLog(String username) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = AddressUtils.getIpAddress(request);
        String location = AddressUtils.getCityInfo(ip);
        UserAgent userAgent = UserAgentUtil.parse(request.getHeader("user-agent"));
        String os = userAgent.getOs().getName();
        String browser = userAgent.getBrowser().getName();
        LogLogin loginLog = new LogLogin(username, os, browser, LocalDateTime.now(), location, ip);
        logClient.saveLoginLog(loginLog);
    }
}
