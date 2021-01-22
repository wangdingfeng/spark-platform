package com.spark.platform.admin.biz.service.user.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.spark.platform.admin.api.dto.UserDTO;
import com.spark.platform.admin.api.entity.authority.Menu;
import com.spark.platform.admin.api.entity.role.Role;
import com.spark.platform.admin.api.entity.user.UserRole;
import com.spark.platform.admin.api.vo.MenuVue;
import com.spark.platform.admin.api.vo.UserExcelVo;
import com.spark.platform.admin.api.vo.UserVo;
import com.spark.platform.admin.biz.dao.user.UserDao;
import com.spark.platform.admin.biz.dao.user.UserRoleDao;
import com.spark.platform.admin.biz.service.menu.MenuService;
import com.spark.platform.admin.biz.service.role.RoleService;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.constants.RedisConstants;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.admin.api.entity.user.User;
import com.spark.platform.admin.biz.service.user.UserService;
import com.spark.platform.common.base.support.WrapperSupport;
import com.spark.platform.common.security.model.LoginUser;
import com.spark.platform.common.security.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.user.impl
 * @ClassName: UserServiceImpl
 * @Description: 用户Service
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    private final UserRoleDao userRoleDao;
    private final RoleService roleService;
    private final MenuService menuService;

    @Override
    @Cacheable(value = RedisConstants.USER_CACHE, unless = "#result == null", key = "T(com.spark.platform.common.base.constants.RedisConstants).USER_KEY_PREFIX.concat(T(String).valueOf(#username))")
    public UserDTO loadUserByUserName(String username) {
        UserDTO userDto = new UserDTO();
        UserVo userVo = super.baseMapper.findByUserName(username);
        if (null == userVo) {
            return null;
        }
        userDto.setSysUser(userVo);
        //查询角色信息
        userDto.setRoles(roleService.getRoleByUserId(userVo.getId()).stream().map(Role::getRoleCode).collect(toList()));
        //查询权限
        userDto.setPermissions(menuService.findAuthByUserId(userVo.getId()).stream().map(Menu::getPermission).collect(toList()));
        return userDto;
    }

    @Override
    public User loadUserByUserId(Long userId) {
        return super.baseMapper.findByUserId(userId);
    }

    @Override
    @Cacheable(value = RedisConstants.USER_CACHE, unless = "#result == null", key = "T(com.spark.platform.common.base.constants.RedisConstants).USER_INFO_KEY_PREFIX.concat(T(String).valueOf(#username))")
    public UserDTO getUserInfo(String username) {
        UserDTO userDto = new UserDTO();
        LoginUser loginUser = UserUtils.getLoginUser();
        User user = this.getById(loginUser.getId());
        UserVo userVo = new UserVo();
        List<Role> roleList = roleService.getRoleByUserId(loginUser.getId());
        //查询角色name信息
        List<String> roleNames = roleList.stream().map(Role::getRoleName).collect(toList());
        //查询角色信息
        List<String> roles = roleList.stream().map(Role::getRoleCode).collect(toList());
        //查询路由菜案信息
        List<MenuVue> menuList = menuService.findMenuTree(loginUser.getUsername());
        //查询权限信息
        List<String> authList = menuService.findAuthByUserId(loginUser.getId()).stream().map(Menu::getPermission).collect(toList());
        BeanUtils.copyProperties(user, userVo);
        userDto.setSysUser(userVo);
        userDto.setRoles(roles);
        userDto.setRoleNames(roleNames);
        userDto.setMenus(menuList);
        userDto.setPermissions(authList);
        return userDto;
    }

    @Override
    public User saveUser(User user) {
        validateUserName(user.getUsername(), user.getId());
        this.save(user);
        return user;
    }

    @Override
    @CacheEvict(value = RedisConstants.USER_CACHE, allEntries = true)
    public boolean updateUser(User user) {
        //只允许小写
        user.setUsername(user.getUsername().toLowerCase());
        //校验用户名
        validateUserName(user.getUsername(), user.getId());
        //修改用户角色
        int i = userRoleDao.deleteByUserId(user.getId());
        log.info("删除用户：{}角色:{}个", user.getId(), i);
        userRoleDao.insertBatch(user.getId(), user.getRoles());
        return super.updateById(user);
    }

    @Override
    public IPage findPage(User user, Page page) {
        return super.baseMapper.selectPage(page, buildWhere(user));
    }

    /**
     * 构建查询条件
     *
     * @param user
     * @return
     */
    private QueryWrapper buildWhere(User user) {
        QueryWrapper wrapper = new QueryWrapper<User>();
        WrapperSupport.putParamsLike(wrapper, user, "username", "nickname");
        WrapperSupport.putParamsEqual(wrapper, user, "status", "deptId");
        return wrapper;
    }

    @Override
    public void restPassword(String[] ids) {
        List<User> users = Lists.newArrayList();
        for (String id : ids) {
            User user = new User();
            user.setId(Long.valueOf(id));
            user.setPassword(new BCryptPasswordEncoder().encode(GlobalsConstants.DEFAULT_USER_PASSWORD));
            users.add(user);
        }
        super.updateBatchById(users);
    }

    @Override
    @CacheEvict(value = RedisConstants.USER_CACHE, allEntries = true)
    public void updateUserInfo(User user) {
        //修改密码
        User userInfo = super.getById(user.getId());
        if (StringUtils.isNotBlank(user.getPassword())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = user.getPassword();
            if (passwordEncoder.matches(password, userInfo.getPassword())) {
                throw new BusinessException("新密码与旧密码重复，请重新修改新密码");
            }
            user.setPassword(passwordEncoder.encode(password));
        }
        super.updateById(user);
    }

    @Override
    public List<Long> findRolIdsByUserId(Long userId) {
        return userRoleDao.selectList(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId)).stream().map(UserRole::getRoleId).collect(Collectors.toList());
    }

    @Override
    public boolean save(User entity) {
        //只允许小写
        entity.setUsername(entity.getUsername().toLowerCase());
        //校验用户名
        validateUserName(entity.getUsername(), null);
        //保存密码 默认密码
        entity.setPassword(new BCryptPasswordEncoder().encode(GlobalsConstants.DEFAULT_USER_PASSWORD));
        //修改用户角色
        super.save(entity);
        userRoleDao.insertBatch(entity.getId(), entity.getRoles());
        return true;
    }

    /**
     * 校验用户名是否重复
     *
     * @return
     */
    @Override
    public void validateUserName(String username, Long id) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        if (null != id) {
            queryWrapper.ne(User::getId, id);
        }
        queryWrapper.eq(User::getUsername, username);
        if (0 != super.count(queryWrapper)) {
            throw new BusinessException("账号重复");
        }
    }

    @Override
    public List<User> findUsersByRoleId(Long roleId) {
        return super.baseMapper.findUsersByRoleId(roleId);
    }

    @Override
    public void exportExcel(User user, HttpServletResponse response) throws Exception {
        List<UserExcelVo> excelList = super.baseMapper.export(buildWhere(user));
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ExcelTypeEnum.XLSX.getValue());
        EasyExcel.write(response.getOutputStream(), UserExcelVo.class).autoCloseStream(Boolean.FALSE).sheet("用户信息").doWrite(excelList);
    }
}