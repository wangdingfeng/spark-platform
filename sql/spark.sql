/*
 Navicat Premium Data Transfer

 Source Server         :
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           :
 Source Schema         : spark

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 24/06/2020 17:36:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `dept_id` bigint(11) NULL DEFAULT NULL COMMENT '部门id 一个用户只有 一个部门',
  `dept_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT NULL COMMENT '状态 0无效 1有效',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '超级管理员', '$2a$10$ts07XkBaX7OwC5xA449gh.MO1Sa3KfyJlcx./lZKkMEgP8XDSoR9e', 1, '123456789', 'wangdingfeng@live.com', 'others/18.jpg', 7, '研发部', 1, 'admin', '2020-03-19 14:35:18', 'admin', '2021-01-22 16:20:21', '我是一个备注11', 0);
INSERT INTO `sys_user` VALUES (10, 'spark', '火花', '$2a$10$wzbrofpaLJlttu.0anLX3uT8iDoV.JER8k0HmnHKfTJeLjXbQZX2.', 1, '123123', '123@qq.com', 'others/14.jpg', 3, '研发一部', 1, 'admin', '2020-03-19 20:31:04', 'admin', '2020-12-30 16:52:08', '1', 0);
INSERT INTO `sys_user` VALUES (11, 'zhubian2', '主编2', '$2a$10$T7q/efLKfeRhfpLS9bDaueMxngZmC6Zb/s9.si0Gbsjof0CR2sFV6', 0, '1231232199', '123@qq.com', 'sunny/5.jpg', 5, '文章一部', 1, 'admin', '2020-03-19 20:47:09', 'admin', '2020-12-28 11:43:12', '主编', 0);
INSERT INTO `sys_user` VALUES (12, 'zhubian1', '主编1', '$2a$10$RtbN3jyBh5Zvd0H99PkYwuH6zmsMfN37bEZkyow78Dh/KTn0C4Ev.', 1, '1999999999', '12312312@qq.com', 'others/14.jpg', 5, '文章一部', 1, 'admin', '2020-03-21 19:56:30', 'admin', '2020-11-13 20:19:55', NULL, 0);
INSERT INTO `sys_user` VALUES (13, 'zuzhang', '组长', '$2a$10$DMb.Gx/GbX657UT6Z5/Lx.AOYRZhHsK0/KWlflqxlNxhhoZmbAPz6', 0, '12312312323', '123555@qq.com', 'others/10.jpg', 0, '根目录', 1, 'admin', '2020-04-14 16:54:44', 'admin', '2020-11-13 20:23:41', '组长', 0);
INSERT INTO `sys_user` VALUES (24, 'system', '系统管理员', '$2a$10$ZLRrshM2lmppckQf6/evI.SgXb8Hd8jl0HV8KCm0Ogy5Nnxnr9W2i', 1, '', 'system@qq.com', 'others/18.jpg', 7, '研发部', 1, 'admin', '2021-01-22 16:04:08', 'admin', '2021-01-22 16:04:08', '系统管理员 除删除外的所有操作', 0);

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '授权ID',
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '秘钥',
  `resource_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源ID',
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限范围',
  `authorized_grant_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权类型',
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '重定向地址',
  `authorities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权的权限',
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT 'token 时效',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT '刷新时效',
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '否自动Approval',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '附加信息',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `client_id`(`client_id`) USING BTREE COMMENT '授权ID不能重复'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户端表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES (1, 'spark-admin', 'spark-admin-secret', NULL, 'all,read,write', 'password,refresh_token,authorization_code,client_credentials,implicit', NULL, NULL, 21600, 28800, 'true', NULL, 'admin', '2021-02-20 13:52:25', 'admin', '2021-02-20 13:52:30', NULL, 0);
INSERT INTO `sys_oauth_client_details` VALUES (2, 'gerrit', 'gerrit', NULL, 'read,all,write', 'authorization_code,refresh_token,password,implicit,client_credentials', '', NULL, 21600, 28800, 'true', NULL, 'admin', '2021-02-20 13:53:08', 'admin', '2021-02-20 13:53:12', NULL, 0);
INSERT INTO `sys_oauth_client_details` VALUES (3, 'spark-app', 'spark-app-secret', NULL, 'all', 'client_credentials', '', NULL, 21600, 28800, 'false', NULL, 'admin', '2021-02-20 13:53:16', 'admin', '2021-02-20 13:53:19', NULL, 0);
INSERT INTO `sys_oauth_client_details` VALUES (4, 'spark-wx', 'spark-wx', NULL, 'all', 'client_credentials', '', NULL, 21600, 28800, 'false', NULL, 'admin', '2020-12-10 09:17:08', 'admin', '2020-12-10 09:17:08', NULL, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编号',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `dept_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门简称',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态 0无效 1有效',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 (0 是  1否)',
  `ds_type` int(1) NULL DEFAULT NULL COMMENT '数据权限类型',
  `ds_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义数据权限',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_code`(`role_code`) USING BTREE COMMENT '角色编号不能重复'
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'ROLE_ADMIN', '超级管理员', 7, '研发部', NULL, 'admin', '2020-03-19 22:54:10', 'admin', '2020-11-10 17:26:01', NULL, 0, 1, NULL);
INSERT INTO `sys_role` VALUES (2, '系统管理员', 'ROLE_SYSTEM', '我是', 7, '研发部', NULL, 'admin', '2020-03-19 22:55:31', 'admin', '2020-04-27 15:06:54', NULL, 0, NULL, NULL);
INSERT INTO `sys_role` VALUES (3, '测试角色', 'ROLE_TEST', '', 2, NULL, NULL, 'admin', '2020-03-19 22:56:06', 'admin', '2020-03-19 22:56:06', NULL, 1, NULL, NULL);
INSERT INTO `sys_role` VALUES (4, '测试角色1', 'ROLE_TEST_1', '', 2, NULL, NULL, 'admin', '2020-03-19 23:01:32', 'admin', '2020-03-19 23:01:32', NULL, 1, NULL, NULL);
INSERT INTO `sys_role` VALUES (5, '文章审核组长', 'ROLE_GROUP_LEADER', '文章审核组长', 5, '文章一部', NULL, 'admin', '2020-04-14 16:49:52', 'admin', '2020-08-12 16:14:08', NULL, 0, 3, '0');
INSERT INTO `sys_role` VALUES (6, '主编', 'ROLE_EDITOR_MANAGE', '主编', 5, '文章一部', NULL, 'admin', '2020-04-14 16:55:45', 'admin', '2020-11-23 02:31:24', NULL, 0, 3, NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `pid` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `i_frame` bit(1) NULL DEFAULT NULL COMMENT '是否外链',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `component` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `permission` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `hidden` bit(1) NULL DEFAULT NULL COMMENT '是否隐藏',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` bigint(20) NOT NULL DEFAULT 0 COMMENT '排序',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, '0', b'0', 'example', 'Layout', '', b'0', 'example', 1, 'admin', '2020-04-02 15:37:06', 'admin', '2020-12-10 20:49:10', NULL, 0);
INSERT INTO `sys_menu` VALUES (2, '用户管理', 1, '1', b'0', '/user', 'sys/user/index', '', b'0', 'user', 1, 'admin', '2020-03-16 15:38:27', 'admin', '2020-05-02 09:35:41', NULL, 0);
INSERT INTO `sys_menu` VALUES (3, '新增', 2, '2', b'0', NULL, NULL, 'user:add', b'0', NULL, 2, 'admin', '2020-03-16 15:41:23', 'admin', '2020-12-11 00:18:53', NULL, 0);
INSERT INTO `sys_menu` VALUES (4, '编辑', 2, '2', b'0', NULL, NULL, 'user:edit', b'0', NULL, 3, 'admin', '2020-03-16 15:42:22', 'admin', '2020-03-21 21:32:02', NULL, 0);
INSERT INTO `sys_menu` VALUES (5, '删除', 2, '2', b'0', NULL, NULL, 'user:delete', b'0', NULL, 4, 'admin', '2020-03-16 15:43:05', 'admin', '2020-03-21 21:32:14', NULL, 0);
INSERT INTO `sys_menu` VALUES (6, '角色管理', 1, '1', b'0', '/role', 'sys/role/index', NULL, b'0', 'tree', 5, 'admin', '2020-03-16 15:44:21', 'admin', '2020-05-02 09:35:48', NULL, 0);
INSERT INTO `sys_menu` VALUES (7, '新增', 6, '2', b'0', NULL, NULL, 'role:add', b'0', NULL, 6, 'admin', '2020-03-18 15:05:22', 'admin', '2020-03-21 21:32:38', NULL, 0);
INSERT INTO `sys_menu` VALUES (8, '编辑', 6, '2', b'0', NULL, NULL, 'role:edit', b'0', NULL, 7, 'admin', '2020-03-18 15:06:05', 'admin', '2020-03-21 21:32:26', NULL, 0);
INSERT INTO `sys_menu` VALUES (9, '删除', 6, '2', b'0', NULL, NULL, 'role:delete', b'0', NULL, 8, 'admin', '2020-03-18 15:06:46', 'admin', '2020-03-21 21:32:47', NULL, 0);
INSERT INTO `sys_menu` VALUES (10, '菜单管理', 1, '1', b'0', '/menu', 'sys/menu/index', NULL, b'0', 'table', 10, 'admin', '2020-03-18 15:07:57', 'admin', '2020-03-18 15:08:04', NULL, 0);
INSERT INTO `sys_menu` VALUES (11, '新增', 10, '2', b'0', '', '', 'menu:add', b'0', '', 100, 'admin', '2020-03-20 13:45:34', 'admin', '2020-03-21 21:32:58', NULL, 0);
INSERT INTO `sys_menu` VALUES (12, '编辑', 10, '2', b'0', '', '', 'menu:edit', b'0', '', 110, 'admin', '2020-03-21 09:35:26', 'admin', '2020-03-21 21:33:09', NULL, 0);
INSERT INTO `sys_menu` VALUES (13, '删除', 10, '2', b'0', '', '', 'menu:delete', b'0', '', 120, 'admin', '2020-03-21 09:36:04', 'admin', '2020-03-21 21:33:18', NULL, 0);
INSERT INTO `sys_menu` VALUES (14, '部门管理', 1, '1', b'0', '/dept', 'sys/dept/index', '', b'0', 'dept', 20, 'admin', '2020-03-21 09:38:25', 'admin', '2020-10-18 15:20:43', NULL, 0);
INSERT INTO `sys_menu` VALUES (15, '新增', 14, '2', b'0', '', '', 'dept:add', b'0', '', 210, 'admin', '2020-03-21 09:39:01', 'admin', '2020-03-21 09:39:01', NULL, 0);
INSERT INTO `sys_menu` VALUES (16, '编辑', 14, '2', b'0', '', '', 'dept:edit', b'0', '', 220, 'admin', '2020-03-21 09:39:22', 'admin', '2020-03-21 09:39:22', NULL, 0);
INSERT INTO `sys_menu` VALUES (17, '删除', 14, '2', b'0', '', '', 'dept:delete', b'0', '', 230, 'admin', '2020-03-21 09:39:42', 'admin', '2020-03-21 09:39:42', NULL, 0);
INSERT INTO `sys_menu` VALUES (18, '字典管理', 1, '1', b'0', '/dict', 'sys/dict/index', '', b'0', 'dictionary', 30, 'admin', '2020-03-21 14:49:56', 'admin', '2020-05-02 09:36:03', NULL, 0);
INSERT INTO `sys_menu` VALUES (19, '新增', 18, '2', b'0', '', '', 'dict:add', b'0', '', 10, 'admin', '2020-03-21 14:50:31', 'admin', '2020-03-21 14:50:31', NULL, 0);
INSERT INTO `sys_menu` VALUES (20, '编辑', 18, '2', b'0', '', '', 'dict:edit', b'0', '', 10, 'admin', '2020-03-21 14:50:48', 'admin', '2020-03-21 14:50:48', NULL, 0);
INSERT INTO `sys_menu` VALUES (21, '删除', 18, '2', b'0', '', '', 'dict:delete', b'0', '', 10, 'admin', '2020-03-21 14:51:03', 'admin', '2020-03-21 14:51:03', NULL, 0);
INSERT INTO `sys_menu` VALUES (22, '客户端管理', 1, '1', b'0', '/oauth', 'sys/oauth/index', '', b'0', 'oauthClient', 40, 'admin', '2020-03-21 15:39:23', 'admin', '2020-05-02 09:36:10', NULL, 0);
INSERT INTO `sys_menu` VALUES (23, '系统监控', 0, '0', b'0', 'monitor', 'Layout', '', b'0', 'oauthClient', 10, 'admin', '2020-03-21 21:37:44', 'admin', '2020-12-10 19:56:43', NULL, 0);
INSERT INTO `sys_menu` VALUES (24, '接口文档', 23, '1', b'1', 'https://api.xiapeiyi.com/swagger-ui.html', NULL, '', b'0', '', 20, 'admin', '2020-03-21 21:39:27', 'admin', '2021-01-21 14:06:32', NULL, 0);
INSERT INTO `sys_menu` VALUES (25, 'Nacos', 23, '1', b'1', 'http://nacos.xiapeiyi.com/nacos', NULL, '', b'0', '', 30, 'admin', '2020-03-21 21:58:22', 'admin', '2021-01-21 14:07:07', NULL, 0);
INSERT INTO `sys_menu` VALUES (26, 'Admin监控', 23, '1', b'1', 'http://www.sparkplatform.cn:9002', NULL, '', b'0', '', 40, 'admin', '2020-03-22 14:12:05', 'admin', '2020-05-07 16:55:10', NULL, 0);
INSERT INTO `sys_menu` VALUES (27, '系统日志', 23, '1', b'0', '/log', 'sys/log/index', '', b'0', '', 5, 'admin', '2020-03-25 10:02:45', 'admin', '2020-05-02 09:37:16', NULL, 0);
INSERT INTO `sys_menu` VALUES (28, '协同管理', 0, '0', b'0', 'act', 'Layout', '', b'0', 'act', 5, 'admin', '2020-04-03 20:41:47', 'admin', '2020-05-07 16:58:13', NULL, 0);
INSERT INTO `sys_menu` VALUES (29, '待办事项', 28, '1', b'0', '/tasks', 'act/tasks/index', '', b'0', 'guide', 10, 'admin', '2020-04-06 14:47:36', 'admin', '2020-05-30 11:06:53', NULL, 0);
INSERT INTO `sys_menu` VALUES (30, '已办事项', 28, '1', b'0', '/histasks', 'act/histasks/index', '', b'0', 'handle', 20, 'admin', '2020-04-06 14:48:34', 'admin', '2020-05-02 09:36:34', NULL, 0);
INSERT INTO `sys_menu` VALUES (31, '流程管理', 28, '1', b'0', '/process', 'act/process/index', '', b'0', 'model', 30, 'admin', '2020-04-06 14:50:20', 'admin', '2020-05-02 09:36:41', NULL, 0);
INSERT INTO `sys_menu` VALUES (32, '研发工具', 0, '0', b'0', 'wrench', 'Layout', '', b'0', 'wrench', 20, 'admin', '2020-04-15 15:20:55', 'admin', '2020-05-03 11:11:28', NULL, 0);
INSERT INTO `sys_menu` VALUES (33, '代码生成', 32, '1', b'0', '/code', 'sys/gen/index', '', b'0', 'code', 10, 'admin', '2020-04-15 15:33:51', 'admin', '2020-05-02 09:37:32', NULL, 0);
INSERT INTO `sys_menu` VALUES (34, '表单例子', 32, '1', b'0', '/form', 'form/index', '', b'0', 'edit', 20, 'admin', '2020-04-16 11:22:02', 'admin', '2020-05-02 09:37:38', NULL, 0);
INSERT INTO `sys_menu` VALUES (35, '文件管理', 58, '1', b'0', '/file', 'sys/file/index', '', b'0', '_file', 50, 'admin', '2020-04-18 15:05:21', 'admin', '2020-12-10 19:56:59', NULL, 0);
INSERT INTO `sys_menu` VALUES (36, '登录日志', 23, '1', b'0', '/login-log', 'sys/login-log/index', '', b'0', '', 10, 'admin', '2020-04-18 23:05:07', 'admin', '2020-05-02 09:37:23', NULL, 0);
INSERT INTO `sys_menu` VALUES (37, '新增', 22, '2', b'0', '', NULL, 'oauth:add', b'0', '', 10, 'admin', '2020-04-26 11:37:24', 'admin', '2020-04-26 11:37:24', NULL, 0);
INSERT INTO `sys_menu` VALUES (38, '编辑', 22, '2', b'0', '', NULL, 'oauth:edit', b'0', '', 10, 'admin', '2020-04-26 11:37:48', 'admin', '2020-04-26 11:37:48', NULL, 0);
INSERT INTO `sys_menu` VALUES (39, '删除', 22, '2', b'0', '', NULL, 'oauth:delete', b'0', '', 10, 'admin', '2020-04-26 11:38:07', 'admin', '2020-04-26 11:38:07', NULL, 0);
INSERT INTO `sys_menu` VALUES (40, '内容平台', 0, '0', b'0', 'cms', 'Layout', '', b'0', 'international', 9, 'admin', '2020-05-03 11:12:52', 'admin', '2020-05-07 16:58:24', NULL, 0);
INSERT INTO `sys_menu` VALUES (41, '文章列表', 40, '1', b'0', '/article/list', 'cms/article/list', '', b'0', 'loggers', 10, 'admin', '2020-05-03 11:14:06', 'admin', '2020-06-18 14:54:03', NULL, 0);
INSERT INTO `sys_menu` VALUES (45, '数据库监控', 23, '1', b'0', 'http://49.232.72.173:9020/druid', NULL, '', b'0', '', 50, 'admin', '2020-05-07 16:57:11', 'admin', '2020-05-07 16:58:01', NULL, 0);
INSERT INTO `sys_menu` VALUES (46, '流程设计', 28, '1', b'0', '/model', 'act/model/index', '', b'0', 'act', 25, 'admin', '2020-05-29 17:22:35', 'admin', '2020-05-30 11:07:02', NULL, 0);
INSERT INTO `sys_menu` VALUES (47, '实例管理', 28, '1', b'0', '/instance', 'act/instance/index', '', b'0', 'star', 90, 'admin', '2020-06-01 17:50:35', 'admin', '2020-06-01 17:50:35', NULL, 0);
INSERT INTO `sys_menu` VALUES (48, '定时任务', 58, '1', b'0', '/quartz', 'sys/quartz/index', '', b'0', 'clock', 60, 'admin', '2020-06-12 14:55:32', 'admin', '2020-12-10 19:57:35', NULL, 0);
INSERT INTO `sys_menu` VALUES (49, '新增', 48, '2', b'0', '', NULL, 'quartz:add', b'0', '', 10, 'admin', '2020-06-12 14:56:55', 'admin', '2020-06-12 14:56:55', NULL, 0);
INSERT INTO `sys_menu` VALUES (50, '编辑', 48, '2', b'0', '', NULL, 'quartz:edit', b'0', '', 11, 'admin', '2020-06-12 14:57:11', 'admin', '2020-06-12 14:57:11', NULL, 0);
INSERT INTO `sys_menu` VALUES (51, '删除', 48, '2', b'0', '', NULL, 'quartz:delete', b'0', '', 13, 'admin', '2020-06-12 14:57:25', 'admin', '2020-06-12 14:57:25', NULL, 0);
INSERT INTO `sys_menu` VALUES (57, 'Minio', 23, '1', b'1', 'https://oss.xiapeiyi.com', NULL, '', b'0', '', 60, 'admin', '2020-11-10 17:25:23', 'admin', '2021-01-21 14:07:42', NULL, 0);
INSERT INTO `sys_menu` VALUES (58, '扩展功能', 0, '0', b'0', 'extend', 'Layout', '', b'0', 'sparkler', 3, 'admin', '2020-12-10 19:56:30', 'admin', '2020-12-10 19:56:30', NULL, 0);
INSERT INTO `sys_menu` VALUES (65, '行政区域', 58, '1', b'0', '/area', 'sys/area/index', '', b'0', 'icon-location', 10, 'admin', '2020-12-13 13:26:36', 'admin', '2020-12-13 13:27:35', NULL, 0);
INSERT INTO `sys_menu` VALUES (66, '商城会员', 0, '0', b'0', '/shop/user', 'Layout', '', b'0', 'wechat', 15, 'admin', '2020-12-13 16:43:53', 'admin', '2021-01-05 17:56:07', NULL, 0);
INSERT INTO `sys_menu` VALUES (67, '会员管理', 66, '1', b'0', '/shop/user', 'shop/user/index', '', b'0', 'user', 10, 'admin', '2020-12-13 16:45:06', 'admin', '2020-12-13 16:45:06', NULL, 0);
INSERT INTO `sys_menu` VALUES (68, '收货地址', 66, '1', b'0', '/shop/user/address', 'shop/user/address/index', '', b'0', 'icon-location', 20, 'admin', '2020-12-13 17:12:47', 'admin', '2020-12-19 12:00:00', NULL, 0);
INSERT INTO `sys_menu` VALUES (69, '会员收藏', 66, '1', b'0', '/shop/user/collect', 'shop/user/collect/index', '', b'0', 'collect', 30, 'admin', '2020-12-13 17:26:04', 'admin', '2020-12-19 12:00:12', NULL, 0);
INSERT INTO `sys_menu` VALUES (70, '会员足迹', 66, '1', b'0', '/shop/user/footprint', 'shop/user/footprint/index', '', b'0', 'footprint', 40, 'admin', '2020-12-13 17:26:47', 'admin', '2020-12-19 12:00:19', NULL, 0);
INSERT INTO `sys_menu` VALUES (71, '微信凭证', 58, '1', b'0', '/shop/auth', 'shop/auth/index', '', b'0', 'wechat', 10, 'admin', '2020-12-14 11:31:06', 'admin', '2020-12-14 11:31:06', NULL, 0);
INSERT INTO `sys_menu` VALUES (72, '编辑', 71, '2', b'0', '', NULL, 'shop:auth:edit', b'0', '', 10, 'admin', '2020-12-14 13:10:52', 'admin', '2020-12-14 13:10:52', NULL, 0);
INSERT INTO `sys_menu` VALUES (73, '删除', 71, '2', b'0', '', NULL, 'shop:auth:del', b'0', '', 11, 'admin', '2020-12-14 13:11:23', 'admin', '2020-12-14 13:11:27', NULL, 0);
INSERT INTO `sys_menu` VALUES (74, '商品管理', 0, '0', b'0', '/goods', 'Layout', '', b'0', 'goods', 16, 'admin', '2020-12-15 14:55:49', 'admin', '2021-01-05 17:55:14', NULL, 0);
INSERT INTO `sys_menu` VALUES (75, '商品列表', 74, '1', b'0', '/goods/list', 'shop/goods/list', '', b'0', 'goods-1', 11, 'admin', '2020-12-15 15:00:30', 'admin', '2021-01-05 17:56:20', NULL, 0);
INSERT INTO `sys_menu` VALUES (76, '新增', 75, '2', b'0', '', NULL, 'shop:goods:add', b'0', '', 10, 'admin', '2020-12-15 15:08:19', 'admin', '2020-12-15 15:08:19', NULL, 1);
INSERT INTO `sys_menu` VALUES (77, '编辑', 75, '2', b'0', '', NULL, 'shop:goods:edit', b'0', '', 11, 'admin', '2020-12-15 15:08:41', 'admin', '2020-12-15 15:08:41', NULL, 0);
INSERT INTO `sys_menu` VALUES (78, '删除', 75, '2', b'0', '', NULL, 'shop:goods:del', b'0', '', 12, 'admin', '2020-12-15 15:08:57', 'admin', '2020-12-15 15:08:57', NULL, 0);
INSERT INTO `sys_menu` VALUES (79, '商品规格', 74, '1', b'0', '/specs', 'shop/specs/index', '', b'0', 'specs', 20, 'admin', '2020-12-16 14:41:22', 'admin', '2021-01-05 17:56:32', NULL, 0);
INSERT INTO `sys_menu` VALUES (80, '商品分类', 74, '1', b'0', '/goods/category', 'shop/goods/category/index', '', b'0', 'nested copy', 30, 'admin', '2020-12-18 11:28:12', 'admin', '2021-01-05 17:56:38', NULL, 0);
INSERT INTO `sys_menu` VALUES (81, '编辑', 80, '2', b'0', '', NULL, 'shop:category:edit', b'0', '', 10, 'admin', '2020-12-18 11:28:36', 'admin', '2020-12-18 11:28:36', NULL, 0);
INSERT INTO `sys_menu` VALUES (82, '删除', 80, '2', b'0', '', NULL, 'shop:category:del', b'0', '', 10, 'admin', '2020-12-18 11:28:50', 'admin', '2020-12-18 11:28:50', NULL, 0);
INSERT INTO `sys_menu` VALUES (83, '商品评价', 74, '1', b'0', '/goods/comment', 'shop/goods/comment/index', '', b'0', 'form', 40, 'admin', '2020-12-18 23:12:02', 'admin', '2021-01-05 17:56:44', NULL, 0);
INSERT INTO `sys_menu` VALUES (84, '购物车', 66, '1', b'0', '/shop/user/cart', 'shop/user/cart/index', '', b'0', 'shopping', 24, 'admin', '2020-12-19 11:58:48', 'admin', '2020-12-19 12:00:06', NULL, 0);
INSERT INTO `sys_menu` VALUES (85, '订单中心', 0, '0', b'0', 'order', 'Layout', '', b'0', 'documentation', 17, 'admin', '2020-12-22 17:10:22', 'admin', '2021-01-07 18:12:30', NULL, 0);
INSERT INTO `sys_menu` VALUES (86, '订单管理', 85, '1', b'0', '/order/normal', 'shop/order/order-normal', '', b'0', 'nested copy', 10, 'admin', '2020-12-22 17:11:06', 'admin', '2021-01-07 21:13:17', NULL, 0);
INSERT INTO `sys_menu` VALUES (87, '商城设置', 0, '0', b'0', 'shop/setting', 'Layout', '', b'0', 'wrench', 18, 'admin', '2021-01-03 14:47:44', 'admin', '2021-01-05 17:56:54', NULL, 0);
INSERT INTO `sys_menu` VALUES (88, '轮播图', 87, '1', b'0', '/swiper', 'shop/setting/swiper/index', '', b'0', 'swiper', 10, 'admin', '2021-01-03 14:51:09', 'admin', '2021-01-07 13:37:13', NULL, 0);
INSERT INTO `sys_menu` VALUES (89, '营销中心', 0, '0', b'0', 'marketing', 'Layout', '', b'0', 'marketing', 19, 'admin', '2021-01-04 17:00:24', 'admin', '2021-01-04 17:00:24', NULL, 0);
INSERT INTO `sys_menu` VALUES (90, '优惠券', 89, '1', b'0', '/coupon', 'shop/marketing/coupon/index', '', b'0', 'coupon', 10, 'admin', '2021-01-04 17:12:41', 'admin', '2021-01-06 21:20:25', NULL, 0);
INSERT INTO `sys_menu` VALUES (94, '秒杀设置', 95, '1', b'0', '/seckill/manage', 'shop/marketing/seckill/manage/index', '', b'0', 'setting', 10, 'admin', '2021-01-05 17:35:41', 'admin', '2021-01-05 17:57:08', NULL, 0);
INSERT INTO `sys_menu` VALUES (95, '秒杀管理', 89, '1', b'0', 'seckill', 'ParentView', '', b'0', 'seckill', 20, 'admin', '2021-01-05 17:42:02', 'admin', '2021-01-05 17:48:39', NULL, 0);
INSERT INTO `sys_menu` VALUES (96, '秒杀商品', 95, '1', b'0', '/seckill/goods', 'shop/marketing/seckill/goods/index', '', b'0', 'goods', 12, 'admin', '2021-01-05 17:45:05', 'admin', '2021-01-05 17:57:15', NULL, 0);
INSERT INTO `sys_menu` VALUES (97, '团购管理', 89, '1', b'0', 'pink', 'ParentView', '', b'0', 'pink', 30, 'admin', '2021-01-06 21:23:21', 'admin', '2021-01-06 21:23:32', NULL, 0);
INSERT INTO `sys_menu` VALUES (98, '团购商品', 97, '1', b'0', '/pink/goods', 'shop/marketing/pink/goods/index', '', b'0', 'goods-1', 10, 'admin', '2021-01-06 21:28:17', 'admin', '2021-01-06 21:28:17', NULL, 0);
INSERT INTO `sys_menu` VALUES (99, '团购列表', 97, '1', b'0', '/pink/list', 'shop/marketing/pink/index', '', b'0', 'list', 11, 'admin', '2021-01-06 21:29:02', 'admin', '2021-01-06 21:29:02', NULL, 0);
INSERT INTO `sys_menu` VALUES (100, '团购订单', 85, '1', b'0', '/order/pink', 'shop/order/order-pink', '', b'0', 'pink', 11, 'admin', '2021-01-07 21:23:33', 'admin', '2021-01-07 21:23:53', NULL, 0);
INSERT INTO `sys_menu` VALUES (101, '秒杀订单', 85, '1', b'0', '/order/seckill', 'shop/order/order-seckill', '', b'0', 'seckill', 12, 'admin', '2021-01-07 21:24:37', 'admin', '2021-01-07 21:24:37', NULL, 0);
INSERT INTO `sys_menu` VALUES (102, '退款/纠纷', 85, '1', b'0', '/order/refund', 'shop/order/order-refund', '', b'0', 'link', 15, 'admin', '2021-01-12 15:16:26', 'admin', '2021-01-12 15:16:26', NULL, 0);
INSERT INTO `sys_menu` VALUES (103, '授权', 6, '2', b'0', '', NULL, 'role:auth', b'0', '', 10, 'admin', '2021-01-22 15:37:26', 'admin', '2021-01-22 15:37:26', NULL, 0);
INSERT INTO `sys_menu` VALUES (105, '新增', 75, '2', b'0', '', NULL, 'shop:goods:add', b'0', '', 1, 'admin', '2021-01-22 23:14:20', 'admin', '2021-01-22 23:14:20', NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) NULL DEFAULT NULL COMMENT '角色id',
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (42, 2, 10);
INSERT INTO `sys_user_role` VALUES (43, 5, 10);
INSERT INTO `sys_user_role` VALUES (44, 2, 14);
INSERT INTO `sys_user_role` VALUES (49, 1, 16);
INSERT INTO `sys_user_role` VALUES (50, 2, 16);
INSERT INTO `sys_user_role` VALUES (51, 1, 17);
INSERT INTO `sys_user_role` VALUES (52, 2, 18);
INSERT INTO `sys_user_role` VALUES (53, 2, 8);
INSERT INTO `sys_user_role` VALUES (56, 6, 11);
INSERT INTO `sys_user_role` VALUES (61, 2, 19);
INSERT INTO `sys_user_role` VALUES (66, 1, 20);
INSERT INTO `sys_user_role` VALUES (70, 5, 21);
INSERT INTO `sys_user_role` VALUES (75, 5, 22);
INSERT INTO `sys_user_role` VALUES (80, 6, 12);
INSERT INTO `sys_user_role` VALUES (81, 5, 13);
INSERT INTO `sys_user_role` VALUES (86, 6, 23);
INSERT INTO `sys_user_role` VALUES (87, 1, 1);
INSERT INTO `sys_user_role` VALUES (88, 5, 1);
INSERT INTO `sys_user_role` VALUES (89, 2, 1);
INSERT INTO `sys_user_role` VALUES (90, 6, 1);
INSERT INTO `sys_user_role` VALUES (91, 2, 24);
INSERT INTO `sys_user_role` VALUES (92, 5, 24);
INSERT INTO `sys_user_role` VALUES (93, 6, 24);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(11) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1985 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (265, 5, 28);
INSERT INTO `sys_role_menu` VALUES (266, 5, 29);
INSERT INTO `sys_role_menu` VALUES (267, 5, 30);
INSERT INTO `sys_role_menu` VALUES (981, 6, 28);
INSERT INTO `sys_role_menu` VALUES (982, 6, 29);
INSERT INTO `sys_role_menu` VALUES (983, 6, 30);
INSERT INTO `sys_role_menu` VALUES (984, 6, 31);
INSERT INTO `sys_role_menu` VALUES (985, 6, 46);
INSERT INTO `sys_role_menu` VALUES (1736, 2, 1);
INSERT INTO `sys_role_menu` VALUES (1737, 2, 2);
INSERT INTO `sys_role_menu` VALUES (1738, 2, 3);
INSERT INTO `sys_role_menu` VALUES (1739, 2, 4);
INSERT INTO `sys_role_menu` VALUES (1740, 2, 6);
INSERT INTO `sys_role_menu` VALUES (1741, 2, 7);
INSERT INTO `sys_role_menu` VALUES (1742, 2, 8);
INSERT INTO `sys_role_menu` VALUES (1743, 2, 10);
INSERT INTO `sys_role_menu` VALUES (1744, 2, 11);
INSERT INTO `sys_role_menu` VALUES (1745, 2, 12);
INSERT INTO `sys_role_menu` VALUES (1746, 2, 14);
INSERT INTO `sys_role_menu` VALUES (1747, 2, 15);
INSERT INTO `sys_role_menu` VALUES (1748, 2, 16);
INSERT INTO `sys_role_menu` VALUES (1749, 2, 18);
INSERT INTO `sys_role_menu` VALUES (1750, 2, 19);
INSERT INTO `sys_role_menu` VALUES (1751, 2, 20);
INSERT INTO `sys_role_menu` VALUES (1752, 2, 22);
INSERT INTO `sys_role_menu` VALUES (1753, 2, 37);
INSERT INTO `sys_role_menu` VALUES (1754, 2, 38);
INSERT INTO `sys_role_menu` VALUES (1755, 2, 23);
INSERT INTO `sys_role_menu` VALUES (1756, 2, 24);
INSERT INTO `sys_role_menu` VALUES (1757, 2, 25);
INSERT INTO `sys_role_menu` VALUES (1758, 2, 26);
INSERT INTO `sys_role_menu` VALUES (1759, 2, 27);
INSERT INTO `sys_role_menu` VALUES (1760, 2, 36);
INSERT INTO `sys_role_menu` VALUES (1761, 2, 45);
INSERT INTO `sys_role_menu` VALUES (1762, 2, 57);
INSERT INTO `sys_role_menu` VALUES (1763, 2, 28);
INSERT INTO `sys_role_menu` VALUES (1764, 2, 29);
INSERT INTO `sys_role_menu` VALUES (1765, 2, 30);
INSERT INTO `sys_role_menu` VALUES (1766, 2, 31);
INSERT INTO `sys_role_menu` VALUES (1767, 2, 46);
INSERT INTO `sys_role_menu` VALUES (1768, 2, 47);
INSERT INTO `sys_role_menu` VALUES (1769, 2, 32);
INSERT INTO `sys_role_menu` VALUES (1770, 2, 33);
INSERT INTO `sys_role_menu` VALUES (1771, 2, 34);
INSERT INTO `sys_role_menu` VALUES (1772, 2, 40);
INSERT INTO `sys_role_menu` VALUES (1773, 2, 41);
INSERT INTO `sys_role_menu` VALUES (1774, 2, 58);
INSERT INTO `sys_role_menu` VALUES (1775, 2, 35);
INSERT INTO `sys_role_menu` VALUES (1776, 2, 48);
INSERT INTO `sys_role_menu` VALUES (1777, 2, 49);
INSERT INTO `sys_role_menu` VALUES (1778, 2, 50);
INSERT INTO `sys_role_menu` VALUES (1779, 2, 51);
INSERT INTO `sys_role_menu` VALUES (1780, 2, 65);
INSERT INTO `sys_role_menu` VALUES (1781, 2, 71);
INSERT INTO `sys_role_menu` VALUES (1782, 2, 72);
INSERT INTO `sys_role_menu` VALUES (1783, 2, 73);
INSERT INTO `sys_role_menu` VALUES (1784, 2, 66);
INSERT INTO `sys_role_menu` VALUES (1785, 2, 67);
INSERT INTO `sys_role_menu` VALUES (1786, 2, 68);
INSERT INTO `sys_role_menu` VALUES (1787, 2, 69);
INSERT INTO `sys_role_menu` VALUES (1788, 2, 70);
INSERT INTO `sys_role_menu` VALUES (1789, 2, 84);
INSERT INTO `sys_role_menu` VALUES (1790, 2, 74);
INSERT INTO `sys_role_menu` VALUES (1791, 2, 75);
INSERT INTO `sys_role_menu` VALUES (1792, 2, 77);
INSERT INTO `sys_role_menu` VALUES (1793, 2, 78);
INSERT INTO `sys_role_menu` VALUES (1794, 2, 79);
INSERT INTO `sys_role_menu` VALUES (1795, 2, 80);
INSERT INTO `sys_role_menu` VALUES (1796, 2, 81);
INSERT INTO `sys_role_menu` VALUES (1797, 2, 83);
INSERT INTO `sys_role_menu` VALUES (1798, 2, 85);
INSERT INTO `sys_role_menu` VALUES (1799, 2, 86);
INSERT INTO `sys_role_menu` VALUES (1800, 2, 100);
INSERT INTO `sys_role_menu` VALUES (1801, 2, 101);
INSERT INTO `sys_role_menu` VALUES (1802, 2, 102);
INSERT INTO `sys_role_menu` VALUES (1803, 2, 87);
INSERT INTO `sys_role_menu` VALUES (1804, 2, 88);
INSERT INTO `sys_role_menu` VALUES (1805, 2, 89);
INSERT INTO `sys_role_menu` VALUES (1806, 2, 90);
INSERT INTO `sys_role_menu` VALUES (1807, 2, 95);
INSERT INTO `sys_role_menu` VALUES (1808, 2, 94);
INSERT INTO `sys_role_menu` VALUES (1809, 2, 96);
INSERT INTO `sys_role_menu` VALUES (1810, 2, 97);
INSERT INTO `sys_role_menu` VALUES (1811, 2, 98);
INSERT INTO `sys_role_menu` VALUES (1812, 2, 99);
INSERT INTO `sys_role_menu` VALUES (1899, 1, 1);
INSERT INTO `sys_role_menu` VALUES (1900, 1, 2);
INSERT INTO `sys_role_menu` VALUES (1901, 1, 3);
INSERT INTO `sys_role_menu` VALUES (1902, 1, 4);
INSERT INTO `sys_role_menu` VALUES (1903, 1, 5);
INSERT INTO `sys_role_menu` VALUES (1904, 1, 6);
INSERT INTO `sys_role_menu` VALUES (1905, 1, 7);
INSERT INTO `sys_role_menu` VALUES (1906, 1, 8);
INSERT INTO `sys_role_menu` VALUES (1907, 1, 9);
INSERT INTO `sys_role_menu` VALUES (1908, 1, 103);
INSERT INTO `sys_role_menu` VALUES (1909, 1, 10);
INSERT INTO `sys_role_menu` VALUES (1910, 1, 11);
INSERT INTO `sys_role_menu` VALUES (1911, 1, 12);
INSERT INTO `sys_role_menu` VALUES (1912, 1, 13);
INSERT INTO `sys_role_menu` VALUES (1913, 1, 14);
INSERT INTO `sys_role_menu` VALUES (1914, 1, 15);
INSERT INTO `sys_role_menu` VALUES (1915, 1, 16);
INSERT INTO `sys_role_menu` VALUES (1916, 1, 17);
INSERT INTO `sys_role_menu` VALUES (1917, 1, 18);
INSERT INTO `sys_role_menu` VALUES (1918, 1, 19);
INSERT INTO `sys_role_menu` VALUES (1919, 1, 20);
INSERT INTO `sys_role_menu` VALUES (1920, 1, 21);
INSERT INTO `sys_role_menu` VALUES (1921, 1, 22);
INSERT INTO `sys_role_menu` VALUES (1922, 1, 37);
INSERT INTO `sys_role_menu` VALUES (1923, 1, 38);
INSERT INTO `sys_role_menu` VALUES (1924, 1, 39);
INSERT INTO `sys_role_menu` VALUES (1925, 1, 23);
INSERT INTO `sys_role_menu` VALUES (1926, 1, 24);
INSERT INTO `sys_role_menu` VALUES (1927, 1, 25);
INSERT INTO `sys_role_menu` VALUES (1928, 1, 26);
INSERT INTO `sys_role_menu` VALUES (1929, 1, 27);
INSERT INTO `sys_role_menu` VALUES (1930, 1, 36);
INSERT INTO `sys_role_menu` VALUES (1931, 1, 45);
INSERT INTO `sys_role_menu` VALUES (1932, 1, 57);
INSERT INTO `sys_role_menu` VALUES (1933, 1, 28);
INSERT INTO `sys_role_menu` VALUES (1934, 1, 29);
INSERT INTO `sys_role_menu` VALUES (1935, 1, 30);
INSERT INTO `sys_role_menu` VALUES (1936, 1, 31);
INSERT INTO `sys_role_menu` VALUES (1937, 1, 46);
INSERT INTO `sys_role_menu` VALUES (1938, 1, 47);
INSERT INTO `sys_role_menu` VALUES (1939, 1, 32);
INSERT INTO `sys_role_menu` VALUES (1940, 1, 33);
INSERT INTO `sys_role_menu` VALUES (1941, 1, 34);
INSERT INTO `sys_role_menu` VALUES (1942, 1, 40);
INSERT INTO `sys_role_menu` VALUES (1943, 1, 41);
INSERT INTO `sys_role_menu` VALUES (1944, 1, 58);
INSERT INTO `sys_role_menu` VALUES (1945, 1, 35);
INSERT INTO `sys_role_menu` VALUES (1946, 1, 48);
INSERT INTO `sys_role_menu` VALUES (1947, 1, 49);
INSERT INTO `sys_role_menu` VALUES (1948, 1, 50);
INSERT INTO `sys_role_menu` VALUES (1949, 1, 51);
INSERT INTO `sys_role_menu` VALUES (1950, 1, 65);
INSERT INTO `sys_role_menu` VALUES (1951, 1, 71);
INSERT INTO `sys_role_menu` VALUES (1952, 1, 72);
INSERT INTO `sys_role_menu` VALUES (1953, 1, 73);
INSERT INTO `sys_role_menu` VALUES (1954, 1, 66);
INSERT INTO `sys_role_menu` VALUES (1955, 1, 67);
INSERT INTO `sys_role_menu` VALUES (1956, 1, 68);
INSERT INTO `sys_role_menu` VALUES (1957, 1, 69);
INSERT INTO `sys_role_menu` VALUES (1958, 1, 70);
INSERT INTO `sys_role_menu` VALUES (1959, 1, 84);
INSERT INTO `sys_role_menu` VALUES (1960, 1, 74);
INSERT INTO `sys_role_menu` VALUES (1961, 1, 75);
INSERT INTO `sys_role_menu` VALUES (1962, 1, 77);
INSERT INTO `sys_role_menu` VALUES (1963, 1, 78);
INSERT INTO `sys_role_menu` VALUES (1964, 1, 105);
INSERT INTO `sys_role_menu` VALUES (1965, 1, 79);
INSERT INTO `sys_role_menu` VALUES (1966, 1, 80);
INSERT INTO `sys_role_menu` VALUES (1967, 1, 81);
INSERT INTO `sys_role_menu` VALUES (1968, 1, 82);
INSERT INTO `sys_role_menu` VALUES (1969, 1, 83);
INSERT INTO `sys_role_menu` VALUES (1970, 1, 85);
INSERT INTO `sys_role_menu` VALUES (1971, 1, 86);
INSERT INTO `sys_role_menu` VALUES (1972, 1, 100);
INSERT INTO `sys_role_menu` VALUES (1973, 1, 101);
INSERT INTO `sys_role_menu` VALUES (1974, 1, 102);
INSERT INTO `sys_role_menu` VALUES (1975, 1, 87);
INSERT INTO `sys_role_menu` VALUES (1976, 1, 88);
INSERT INTO `sys_role_menu` VALUES (1977, 1, 89);
INSERT INTO `sys_role_menu` VALUES (1978, 1, 90);
INSERT INTO `sys_role_menu` VALUES (1979, 1, 95);
INSERT INTO `sys_role_menu` VALUES (1980, 1, 94);
INSERT INTO `sys_role_menu` VALUES (1981, 1, 96);
INSERT INTO `sys_role_menu` VALUES (1982, 1, 97);
INSERT INTO `sys_role_menu` VALUES (1983, 1, 98);
INSERT INTO `sys_role_menu` VALUES (1984, 1, 99);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `pid` bigint(11) NULL DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级ids',
  `simple_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简称',
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '全称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `dept_type` int(11) NULL DEFAULT NULL COMMENT '部门类型(0 公司1部门)',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '0', 'spark', 'spark开源公司', '北京朝阳区', 0, 10, 'admin', '2020-03-21 10:07:50', 'admin', '2020-07-30 11:20:15', NULL, 0);
INSERT INTO `sys_dept` VALUES (2, 1, '0,1', '技术部', '信息技术部', '朝阳区建国路', 1, 10, 'admin', '2020-03-21 10:11:50', 'admin', '2020-08-01 09:57:19', NULL, 0);
INSERT INTO `sys_dept` VALUES (3, 7, '0,1,2,7', '研发一部', '研发一部门', NULL, 1, 10, 'admin', '2020-03-21 10:12:31', 'admin', '2020-07-02 15:57:39', NULL, 0);
INSERT INTO `sys_dept` VALUES (4, 1, '0,1', '内容部门', '内容部门', NULL, 1, 10, 'admin', '2020-03-21 10:13:17', 'admin', '2020-07-02 15:57:42', NULL, 0);
INSERT INTO `sys_dept` VALUES (5, 4, '0,1,4', '文章一部', '文章一部', NULL, 1, 10, 'admin', '2020-03-21 10:13:56', 'admin', '2020-07-02 15:57:43', NULL, 0);
INSERT INTO `sys_dept` VALUES (6, 2, '0,1,2', '测试部', '测试部', '大望路', 1, 10, 'admin', '2020-03-21 13:03:00', 'admin', '2020-08-01 09:59:21', NULL, 0);
INSERT INTO `sys_dept` VALUES (7, 2, '0,1,2', '研发部', '研发部', NULL, 0, 10, 'admin', '2020-03-21 13:04:01', 'admin', '2020-07-02 15:57:37', NULL, 0);
INSERT INTO `sys_dept` VALUES (8, 6, '0,1,2,6', '测试一部', '测试一部门', NULL, 0, 10, 'admin', '2020-03-21 13:05:09', 'admin', '2020-07-02 15:57:33', NULL, 0);
INSERT INTO `sys_dept` VALUES (9, 0, '0', '2', '2', '2', 0, 10, 'admin', '2020-09-03 16:35:05', 'admin', '2020-09-03 16:35:05', NULL, 1);


-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字典类型',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '用户状态', 'user_status', '用户状态', 'admin', '2020-03-26 11:36:58', 'admin', '2020-03-26 11:36:58', NULL, 0);
INSERT INTO `sys_dict` VALUES (2, '性别', 'sex', '0 女 1 男', 'admin', '2020-04-29 21:28:17', 'admin', '2020-04-29 21:28:17', NULL, 0);
INSERT INTO `sys_dict` VALUES (3, '文件状态', 'file_status', '文件状态 0 未绑定 1已绑定', 'admin', '2020-05-02 09:53:31', 'admin', '2020-05-02 09:54:18', NULL, 0);
INSERT INTO `sys_dict` VALUES (4, '流程业务类型', 'processs_type', '用于业务流程类型', 'admin', '2020-05-02 09:55:00', 'admin', '2020-05-02 09:55:00', NULL, 0);
INSERT INTO `sys_dict` VALUES (5, '文章状态', 'article_status', '0 暂存 1 退回修改 2 组长审核 3 主编审核 4 审核通过 5 审核不通过', 'admin', '2020-05-07 13:53:09', 'admin', '2020-05-07 13:53:09', NULL, 0);
INSERT INTO `sys_dict` VALUES (6, '是否', 'yes_no', '0 否 1 是', 'admin', '2020-06-11 10:13:07', 'admin', '2020-06-11 10:24:54', NULL, 0);
INSERT INTO `sys_dict` VALUES (7, '执行任务策略', 'quartz_misfire_policy', '定时任务调度', 'admin', '2020-06-11 10:14:42', 'admin', '2020-10-13 14:32:18', NULL, 0);
INSERT INTO `sys_dict` VALUES (8, '定时任务状态', 'quartz_status', '0 暂停 1 正常', 'admin', '2020-06-11 13:50:12', 'admin', '2020-06-11 13:50:12', NULL, 0);
INSERT INTO `sys_dict` VALUES (9, '定时任务日志状态', 'quartz_log_status', '0 正常 1 失败', 'admin', '2020-06-11 16:37:25', 'admin', '2020-06-11 16:37:25', NULL, 0);
INSERT INTO `sys_dict` VALUES (10, '定时任务组', 'quartz_group', 'DEFAULT 默认 SYSTEM 系统 BUSINESS', 'admin', '2020-06-11 17:28:03', 'admin', '2020-06-11 17:28:03', NULL, 0);
INSERT INTO `sys_dict` VALUES (11, '任务类型', 'quartz_type', '0 bean类型 1 rest类型 2 消息队列', 'admin', '2020-06-12 11:55:49', 'admin', '2020-06-12 11:55:49', NULL, 0);
INSERT INTO `sys_dict` VALUES (12, '角色数据权限范围', 'role_ds_type', '全部:1 本级:2  自定义:3', 'admin', '2020-08-07 17:50:39', 'admin', '2020-08-07 17:50:39', NULL, 0);
INSERT INTO `sys_dict` VALUES (13, '部门类型', 'dept_type', '部门类型 0 公司 1 部门 2 小组', 'admin', '2020-08-07 17:52:04', 'admin', '2020-10-14 09:53:44', NULL, 0);
INSERT INTO `sys_dict` VALUES (14, 'aaaa', 'ck', '啊啊啊啊', 'admin', '2020-10-14 16:05:40', 'admin', '2020-10-14 16:05:40', NULL, 1);
INSERT INTO `sys_dict` VALUES (15, '按按', 'aa', '按按', 'admin', '2020-10-14 16:20:36', 'admin', '2020-10-14 16:20:36', NULL, 1);
INSERT INTO `sys_dict` VALUES (16, 'aaa', 'aaa', 'aaa', 'admin', '2020-10-14 16:25:05', 'admin', '2020-10-14 16:25:05', NULL, 1);
INSERT INTO `sys_dict` VALUES (17, '是是是', 'sss', '', 'admin', '2020-10-19 21:30:05', 'admin', '2020-10-19 21:30:05', NULL, 1);
INSERT INTO `sys_dict` VALUES (18, '区域类型', 'area_type', '', 'admin', '2020-12-13 13:28:43', 'admin', '2020-12-13 13:28:43', NULL, 0);
INSERT INTO `sys_dict` VALUES (19, '商城会员类型', 'shop_user_type', '0  普通会员 1 VIP', 'admin', '2020-12-13 16:56:56', 'admin', '2020-12-13 16:56:56', NULL, 0);
INSERT INTO `sys_dict` VALUES (20, '商城会员', 'shop_user_status', '0 可用, 1 禁用, 2 注销', 'admin', '2020-12-13 16:57:51', 'admin', '2020-12-13 16:57:51', NULL, 0);
INSERT INTO `sys_dict` VALUES (21, '单位', 'goods_unit', '商品单位', 'admin', '2020-12-15 16:49:18', 'admin', '2020-12-15 16:49:18', NULL, 0);
INSERT INTO `sys_dict` VALUES (22, '组件类型', 'attr_type', '用于商品规格组件类型 ', 'admin', '2020-12-16 14:49:27', 'admin', '2020-12-16 14:49:27', NULL, 0);
INSERT INTO `sys_dict` VALUES (23, '商品关键词', 'goods_keywords', '商品关键词', 'admin', '2020-12-18 13:56:54', 'admin', '2020-12-18 13:56:54', NULL, 0);
INSERT INTO `sys_dict` VALUES (24, '商品状态', 'goods_status', '0 待上架 1 上架 2 下架', 'admin', '2020-12-18 21:10:40', 'admin', '2020-12-18 21:10:40', NULL, 0);
INSERT INTO `sys_dict` VALUES (25, '订单类型', 'shop_order_type', '0 普通订单 1 团购订单 2 秒杀订单', 'admin', '2020-12-22 17:17:19', 'admin', '2020-12-22 17:17:19', NULL, 0);
INSERT INTO `sys_dict` VALUES (26, '订单状态', 'shop_order_status', '0 待付款 1 已取消 2 已付款 3 已发货 4 用户确认收货 5 退款 6 完成', 'admin', '2020-12-22 17:19:12', 'admin', '2020-12-22 17:19:12', NULL, 0);
INSERT INTO `sys_dict` VALUES (27, '发货状态', 'shop_shipping_status', '0 待发货 1 已发货 2 已收货 2 退货', 'admin', '2020-12-22 17:21:20', 'admin', '2020-12-22 17:21:20', NULL, 0);
INSERT INTO `sys_dict` VALUES (28, '支付状态', 'shop_pay_status', '0 待付款 2 已付款', 'admin', '2020-12-22 17:22:21', 'admin', '2020-12-22 17:22:21', NULL, 0);
INSERT INTO `sys_dict` VALUES (29, '轮播图类型', 'swiper_type', ' none 无类型 goods 商品', 'admin', '2021-01-03 15:05:39', 'admin', '2021-01-03 15:05:39', NULL, 0);
INSERT INTO `sys_dict` VALUES (30, '状态', 'status', '通用状态 0 正常 1 停用', 'admin', '2021-01-03 15:06:57', 'admin', '2021-01-03 15:06:57', NULL, 0);
INSERT INTO `sys_dict` VALUES (31, '优惠券类型', 'coupon_type', '优惠券类型 新人券 现金券 满减券 折扣券', 'admin', '2021-01-04 17:16:41', 'admin', '2021-01-04 17:16:41', NULL, 0);
INSERT INTO `sys_dict` VALUES (32, '优惠券状态', 'coupon_status', '0 未开始 1进行中 2已结束', 'admin', '2021-01-04 17:19:59', 'admin', '2021-01-04 17:19:59', NULL, 0);
INSERT INTO `sys_dict` VALUES (33, '商品活动状态', 'goods_activity', '0 正常 1秒杀 2 团购', 'admin', '2021-01-06 17:00:52', 'admin', '2021-01-06 17:00:52', NULL, 0);
INSERT INTO `sys_dict` VALUES (34, '拼团商品状态', 'shop_pink_status', '0 待成团 1 拼团中 2 拼团成功3 拼团失败', 'admin', '2021-01-07 13:21:17', 'admin', '2021-01-07 13:29:58', NULL, 0);
INSERT INTO `sys_dict` VALUES (35, '订单状态', 'order_status', '0 待付款 1 已取消 2 已付款 3 已发货  4 退款 5 完成 6 待评价', 'admin', '2021-01-07 15:42:59', 'admin', '2021-01-12 10:10:22', NULL, 0);
INSERT INTO `sys_dict` VALUES (36, '发货状态', 'shipping_status', ' 0 待发货 1 已发货 2 已收货 3 退货', 'admin', '2021-01-07 16:13:24', 'admin', '2021-01-07 16:13:24', NULL, 0);
INSERT INTO `sys_dict` VALUES (37, '发货状态', 'shipping_status', ' 0 待发货 1 已发货 2 已收货 3 退货', 'admin', '2021-01-07 16:13:26', 'admin', '2021-01-07 16:13:26', NULL, 1);
INSERT INTO `sys_dict` VALUES (38, '退款状态', 'refund_status', ' 0 申请中 1 退款完成 2 拒绝退款', 'admin', '2021-01-07 16:19:57', 'admin', '2021-01-07 16:19:57', NULL, 0);
INSERT INTO `sys_dict` VALUES (39, '快递公司', 'express_company', '快递公司代码', 'admin', '2021-01-11 18:05:10', 'admin', '2021-01-11 18:05:10', NULL, 0);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) NULL DEFAULT NULL COMMENT '父id',
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `label` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签值',
  `value` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典值',
  `sort` int(10) NULL DEFAULT 0 COMMENT '排序',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `value1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展属性1',
  `value2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展属性2',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 128 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1, 1, 'user_status', '禁用', '0', 10, '禁用', NULL, NULL, 'admin', '2020-03-26 13:32:22', 'admin', '2020-03-26 13:43:45', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (2, 1, 'user_status', '正常', '1', 11, '正常', NULL, NULL, 'admin', '2020-03-26 13:41:34', 'admin', '2020-03-26 13:41:34', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (3, 1, 'user_status', '锁定', '2', 12, '锁定', NULL, NULL, 'admin', '2020-03-26 13:46:17', 'admin', '2020-03-26 13:46:17', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (4, 1, 'user_status', '过期', '3', 13, '过期', NULL, NULL, 'admin', '2020-03-26 13:46:33', 'admin', '2020-03-26 13:46:33', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (5, 1, 'user_status', '测试删除', '5', 16, '测试删除', NULL, NULL, 'admin', '2020-03-26 13:46:50', 'admin', '2020-03-26 13:48:33', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (6, 2, 'sex', '女', '0', 10, '', NULL, NULL, 'admin', '2020-04-29 21:29:55', 'admin', '2020-04-29 21:29:55', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (7, 2, 'sex', '男', '1', 11, '', NULL, NULL, 'admin', '2020-04-29 21:30:07', 'admin', '2020-04-29 21:30:07', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (8, 3, 'file_status', '未绑定', '0', 10, '', NULL, NULL, 'admin', '2020-05-02 09:53:55', 'admin', '2020-05-02 09:53:55', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (9, 3, 'file_status', '已绑定', '1', 11, '', NULL, NULL, 'admin', '2020-05-02 09:54:04', 'admin', '2020-05-02 09:54:04', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (10, 4, 'processs_type', '文章审核', 'PROCESS_ARTICLE', 10, '文章审核流程', '/article/task', NULL, 'admin', '2020-05-02 09:55:53', 'admin', '2020-06-18 15:12:51', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (11, 5, 'article_status', '暂存', '0', 10, '', NULL, NULL, 'admin', '2020-05-07 13:53:25', 'admin', '2020-05-07 13:53:25', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (12, 5, 'article_status', '退回修改', '1', 10, '', NULL, NULL, 'admin', '2020-05-07 13:53:35', 'admin', '2020-05-07 13:53:35', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (13, 5, 'article_status', '组长审核', '2', 10, '', NULL, NULL, 'admin', '2020-05-07 13:53:43', 'admin', '2020-05-07 13:53:43', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (14, 5, 'article_status', '主编审核', '3', 10, '', NULL, NULL, 'admin', '2020-05-07 13:53:48', 'admin', '2020-05-07 13:53:48', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (15, 5, 'article_status', '审核通过', '4', 10, '', NULL, NULL, 'admin', '2020-05-07 13:53:54', 'admin', '2020-05-07 13:53:54', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (16, 5, 'article_status', '审核不通过', '5', 10, '', NULL, NULL, 'admin', '2020-05-07 13:53:59', 'admin', '2020-05-07 13:53:59', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (17, 6, '是否', '否', '0', 11, '', NULL, NULL, 'admin', '2020-06-11 10:13:24', 'admin', '2020-06-11 10:13:49', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (18, 6, '是否', '是', '1', 10, '', NULL, NULL, 'admin', '2020-06-11 10:13:45', 'admin', '2020-06-11 10:13:45', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (19, 7, '执行任务策略', '默认', '0', 10, '', NULL, NULL, 'admin', '2020-06-11 10:15:03', 'admin', '2020-06-11 10:15:03', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (20, 7, '执行任务策略', '立即触发执行', '1', 11, '', NULL, NULL, 'admin', '2020-06-11 10:15:11', 'admin', '2020-06-11 10:15:15', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (21, 7, '执行任务策略', '触发一次执行', '2', 14, '', NULL, NULL, 'admin', '2020-06-11 10:15:27', 'admin', '2020-06-11 10:16:07', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (22, 7, '执行任务策略', '不触发立即执行', '3', 18, '', NULL, NULL, 'admin', '2020-06-11 10:15:58', 'admin', '2020-06-11 10:15:58', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (23, 8, 'quartz_status', '暂停', '0', 10, '', NULL, NULL, 'admin', '2020-06-11 13:50:33', 'admin', '2020-06-11 13:50:33', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (24, 8, 'quartz_status', '正常', '1', 11, '', NULL, NULL, 'admin', '2020-06-11 13:50:41', 'admin', '2020-06-11 13:50:41', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (25, 9, 'quartz_log_status', '正常', '0', 10, '', NULL, NULL, 'admin', '2020-06-11 16:37:37', 'admin', '2020-06-11 16:37:37', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (26, 9, 'quartz_log_status', '失败', '1', 11, '', NULL, NULL, 'admin', '2020-06-11 16:37:42', 'admin', '2020-06-11 16:37:50', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (27, 10, 'quartz_group', '默认', 'DEFAULT', 10, '', NULL, NULL, 'admin', '2020-06-11 17:28:15', 'admin', '2020-06-11 17:28:15', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (28, 10, 'quartz_group', '系统', 'SYSTEM', 11, '', NULL, NULL, 'admin', '2020-06-11 17:28:31', 'admin', '2020-06-11 17:28:31', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (29, 10, 'quartz_group', '业务', 'BUSINESS', 12, '', NULL, NULL, 'admin', '2020-06-11 17:28:45', 'admin', '2020-06-11 17:28:45', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (30, 11, 'quartz_type', 'java bean', '0', 10, '', NULL, NULL, 'admin', '2020-06-12 11:56:23', 'admin', '2020-06-12 11:56:47', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (31, 11, 'quartz_type', 'rest请求', '1', 11, '', NULL, NULL, 'admin', '2020-06-12 11:56:56', 'admin', '2020-08-16 22:00:45', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (32, 11, 'quartz_type', '消息队列', '2', 12, '', NULL, NULL, 'admin', '2020-06-12 11:57:04', 'admin', '2020-06-12 11:57:14', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (33, 12, 'role_ds_type', '全部', '1', 10, '', NULL, NULL, 'admin', '2020-08-07 17:51:07', 'admin', '2020-10-13 15:04:55', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (34, 12, 'role_ds_type', '本级', '2', 11, '', NULL, NULL, 'admin', '2020-08-07 17:51:16', 'admin', '2020-08-07 17:51:16', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (35, 12, 'role_ds_type', '本级以及子级', '3', 13, '', NULL, NULL, 'admin', '2020-08-07 17:51:34', 'admin', '2020-08-07 17:51:34', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (36, 12, 'role_ds_type', '自定义', '4', 13, '', NULL, NULL, 'admin', '2020-08-07 17:51:44', 'admin', '2020-11-10 16:26:18', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (37, 13, 'dept_type', '公司', '0', 10, '111', NULL, NULL, 'admin', '2020-08-07 17:52:19', 'admin', '2020-11-13 09:26:51', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (38, 13, 'dept_type', '部门', '1', 11, '', NULL, NULL, 'admin', '2020-08-07 17:52:27', 'admin', '2020-10-14 09:53:55', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (39, 13, 'dept_type', '小组', '2', 12, '', NULL, NULL, 'admin', '2020-08-07 17:52:36', 'admin', '2020-08-07 17:52:36', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (40, 15, 'aa', 'aaaa', '啊啊啊啊', 10, '啊啊啊啊啊', NULL, NULL, 'admin', '2020-10-14 17:53:57', 'admin', '2020-10-14 17:53:57', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (41, 15, 'aa', 'nnnn', 'nnnn', 10, '这是我们的哈哈哈哈', NULL, NULL, 'admin', '2020-10-15 09:12:26', 'admin', '2020-10-15 09:12:26', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (42, 15, 'aa', '重中之重', '22222', 10, '2222', '222', '222', 'admin', '2020-10-15 09:48:55', 'admin', '2020-10-15 09:48:55', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (43, 15, 'aa', 'bbbb', 'bbbb', 10, 'bbbb', NULL, NULL, 'admin', '2020-10-15 09:51:43', 'admin', '2020-10-15 09:51:43', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (44, 18, 'area_type', '省级', '1', 10, '', NULL, NULL, 'admin', '2020-12-13 13:28:55', 'admin', '2020-12-13 13:28:55', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (45, 18, 'area_type', '地级市', '2', 10, '', NULL, NULL, 'admin', '2020-12-13 13:29:21', 'admin', '2020-12-13 13:29:21', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (46, 18, 'area_type', '区级', '3', 10, '', NULL, NULL, 'admin', '2020-12-13 13:29:37', 'admin', '2020-12-13 13:29:37', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (47, 19, 'shop_user_type', '普通会员', '0', 10, '', NULL, NULL, 'admin', '2020-12-13 16:58:07', 'admin', '2020-12-13 16:58:07', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (48, 19, 'shop_user_type', 'VIP', '1', 10, '', NULL, NULL, 'admin', '2020-12-13 16:58:15', 'admin', '2020-12-13 16:58:15', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (49, 20, 'shop_user_status', '可用', '0', 10, '', NULL, NULL, 'admin', '2020-12-13 16:58:29', 'admin', '2020-12-13 16:58:29', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (50, 20, 'shop_user_status', '禁用', '1', 10, '', NULL, NULL, 'admin', '2020-12-13 16:58:43', 'admin', '2020-12-13 16:58:43', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (51, 20, 'shop_user_status', '注销', '2', 10, '', NULL, NULL, 'admin', '2020-12-13 16:58:54', 'admin', '2020-12-13 16:58:54', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (52, 21, 'goods_unit', '件/个', 'piece/pieces', 1, '', NULL, NULL, 'admin', '2020-12-15 16:52:14', 'admin', '2020-12-15 16:52:14', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (53, 21, 'goods_unit', '双', 'pair', 2, '', NULL, NULL, 'admin', '2020-12-15 16:52:33', 'admin', '2020-12-15 16:53:07', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (54, 21, 'goods_unit', '包', 'pack/packs', 3, '', NULL, NULL, 'admin', '2020-12-15 16:53:31', 'admin', '2020-12-15 16:53:31', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (55, 21, 'goods_unit', '毫升', 'milliliter', 4, '', NULL, NULL, 'admin', '2020-12-15 16:54:09', 'admin', '2020-12-15 16:54:09', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (56, 21, 'goods_unit', '毫克', 'milligram', 5, '', NULL, NULL, 'admin', '2020-12-15 16:54:28', 'admin', '2020-12-15 16:54:28', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (57, 21, 'goods_unit', '盎司', 'ounce', 6, '', NULL, NULL, 'admin', '2020-12-15 16:54:45', 'admin', '2020-12-15 16:54:50', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (58, 21, 'goods_unit', '套', 'set/sets', 7, '', NULL, NULL, 'admin', '2020-12-15 16:55:15', 'admin', '2020-12-15 16:55:15', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (59, 21, 'goods_unit', '码', 'yard/yards', 8, '', NULL, NULL, 'admin', '2020-12-15 16:55:50', 'admin', '2020-12-15 16:55:50', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (60, 22, 'attr_type', '单选', 'list_box', 10, '用于select', NULL, NULL, 'admin', '2020-12-16 14:49:57', 'admin', '2020-12-16 14:49:57', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (61, 22, 'attr_type', '多选', 'check_box', 10, '用户checkbox', NULL, NULL, 'admin', '2020-12-16 14:50:18', 'admin', '2020-12-16 14:50:18', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (62, 22, 'attr_type', '输入', 'input', 10, '用户输入框', NULL, NULL, 'admin', '2020-12-16 14:50:34', 'admin', '2020-12-16 14:50:34', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (63, 23, 'goods_keywords', '团长推荐', '团长推荐', 10, '', NULL, NULL, 'admin', '2020-12-18 13:57:32', 'admin', '2020-12-18 13:57:32', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (64, 23, 'goods_keywords', '包邮', '包邮', 10, '', NULL, NULL, 'admin', '2020-12-18 13:57:37', 'admin', '2020-12-18 13:57:37', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (65, 24, 'goods_status', '待上架', '0', 10, '', NULL, NULL, 'admin', '2020-12-18 21:10:54', 'admin', '2020-12-18 21:10:54', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (66, 24, 'goods_status', '已上架', '1', 11, '', NULL, NULL, 'admin', '2020-12-18 21:11:00', 'admin', '2020-12-18 21:11:11', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (67, 24, 'goods_status', '下架', '2', 12, '', NULL, NULL, 'admin', '2020-12-18 21:11:06', 'admin', '2020-12-18 21:11:17', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (68, 21, 'goods_unit', 'KG', 'kg', 10, '', NULL, NULL, 'admin', '2020-12-19 14:58:00', 'admin', '2020-12-19 14:58:00', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (69, 25, 'shop_order_type', '普通订单', '0', 10, '', NULL, NULL, 'admin', '2020-12-22 17:17:38', 'admin', '2020-12-22 17:17:38', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (70, 25, 'shop_order_type', '团购订单', '1', 10, '', NULL, NULL, 'admin', '2020-12-22 17:17:46', 'admin', '2020-12-22 17:17:46', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (71, 25, 'shop_order_type', '秒杀订单', '2', 10, '', NULL, NULL, 'admin', '2020-12-22 17:17:52', 'admin', '2020-12-22 17:17:52', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (72, 26, 'shop_order_status', '待付款', '0', 10, '', NULL, NULL, 'admin', '2020-12-22 17:19:24', 'admin', '2020-12-22 17:19:24', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (73, 26, 'shop_order_status', '已取消', '1', 11, '', NULL, NULL, 'admin', '2020-12-22 17:19:31', 'admin', '2020-12-22 17:19:31', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (74, 26, 'shop_order_status', '已付款', '2', 12, '', NULL, NULL, 'admin', '2020-12-22 17:19:40', 'admin', '2020-12-22 17:19:40', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (75, 26, 'shop_order_status', '已发货', '3', 13, '', NULL, NULL, 'admin', '2020-12-22 17:19:48', 'admin', '2020-12-22 17:19:48', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (76, 26, 'shop_order_status', '用户确认收货', '4', 14, '', NULL, NULL, 'admin', '2020-12-22 17:19:58', 'admin', '2020-12-22 17:19:58', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (77, 26, 'shop_order_status', '退款', '5', 15, '', NULL, NULL, 'admin', '2020-12-22 17:20:08', 'admin', '2020-12-22 17:20:08', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (78, 26, 'shop_order_status', '完成', '6', 16, '', NULL, NULL, 'admin', '2020-12-22 17:20:19', 'admin', '2020-12-22 17:20:19', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (79, 27, 'shop_shipping_status', '待发货', '0', 10, '', NULL, NULL, 'admin', '2020-12-22 17:21:32', 'admin', '2020-12-22 17:21:32', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (80, 27, 'shop_shipping_status', '已发货', '1', 11, '', NULL, NULL, 'admin', '2020-12-22 17:21:41', 'admin', '2020-12-22 17:21:41', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (81, 27, 'shop_shipping_status', '已收货', '2', 12, '', NULL, NULL, 'admin', '2020-12-22 17:21:49', 'admin', '2020-12-22 17:21:49', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (82, 27, 'shop_shipping_status', '退货', '3', 13, '', NULL, NULL, 'admin', '2020-12-22 17:22:02', 'admin', '2020-12-22 17:22:02', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (83, 28, 'shop_pay_status', '待付款', '0', 10, '', NULL, NULL, 'admin', '2020-12-22 17:22:33', 'admin', '2020-12-22 17:22:33', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (84, 28, 'shop_pay_status', '已付款', '1', 11, '', NULL, NULL, 'admin', '2020-12-22 17:22:41', 'admin', '2020-12-22 17:22:41', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (85, 29, 'swiper_type', '无', 'none', 10, '', NULL, NULL, 'admin', '2021-01-03 15:05:53', 'admin', '2021-01-03 15:05:53', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (86, 29, 'swiper_type', '商品', 'goods', 11, '', NULL, NULL, 'admin', '2021-01-03 15:06:07', 'admin', '2021-01-03 15:06:07', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (87, 30, 'status', '正常', '0', 10, '', NULL, NULL, 'admin', '2021-01-03 15:07:09', 'admin', '2021-01-03 15:07:09', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (88, 30, 'status', '停用', '1', 11, '', NULL, NULL, 'admin', '2021-01-03 15:07:14', 'admin', '2021-01-03 15:07:14', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (89, 31, 'coupon_type', '新人券', 'new', 10, '', NULL, NULL, 'admin', '2021-01-04 17:16:53', 'admin', '2021-01-04 17:16:53', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (90, 31, 'coupon_type', '现金券', 'money', 11, '', NULL, NULL, 'admin', '2021-01-04 17:17:16', 'admin', '2021-01-04 17:17:16', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (91, 31, 'coupon_type', '满减券', 'fullRed', 12, '', NULL, NULL, 'admin', '2021-01-04 17:18:24', 'admin', '2021-01-04 17:18:24', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (92, 31, 'coupon_type', '折扣券', 'discount', 16, '', NULL, NULL, 'admin', '2021-01-04 17:18:50', 'admin', '2021-01-04 17:18:50', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (93, 32, 'coupon_status', '未开始', '0', 10, '', NULL, NULL, 'admin', '2021-01-04 17:20:14', 'admin', '2021-01-04 17:20:14', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (94, 32, 'coupon_status', '进行中', '1', 11, '', NULL, NULL, 'admin', '2021-01-04 17:20:21', 'admin', '2021-01-04 17:20:21', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (95, 32, 'coupon_status', '已结束', '2', 13, '', NULL, NULL, 'admin', '2021-01-04 17:20:27', 'admin', '2021-01-04 17:20:27', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (96, 33, 'goods_activity', '正常', '0', 10, '', NULL, NULL, 'admin', '2021-01-06 17:01:03', 'admin', '2021-01-06 17:01:03', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (97, 33, 'goods_activity', '秒杀', '1', 10, '', NULL, NULL, 'admin', '2021-01-06 17:01:08', 'admin', '2021-01-06 17:01:08', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (98, 33, 'goods_activity', '团购', '2', 10, '', NULL, NULL, 'admin', '2021-01-06 17:01:13', 'admin', '2021-01-06 17:01:13', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (99, 34, 'shop_pink_status', '待成团', '0', 10, '', NULL, NULL, 'admin', '2021-01-07 13:21:34', 'admin', '2021-01-07 13:30:11', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (100, 34, 'shop_pink_status', '拼团中', '1', 11, '', NULL, NULL, 'admin', '2021-01-07 13:21:46', 'admin', '2021-01-07 13:30:16', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (101, 34, 'shop_pink_status', '拼团成功', '2', 11, '', NULL, NULL, 'admin', '2021-01-07 13:21:52', 'admin', '2021-01-07 13:30:27', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (102, 34, 'shop_pink_status', '拼团失败', '3', 10, '', NULL, NULL, 'admin', '2021-01-07 13:22:04', 'admin', '2021-01-07 13:30:34', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (103, 35, 'order_status', '待付款', '0', 10, '', NULL, NULL, 'admin', '2021-01-07 15:45:14', 'admin', '2021-01-07 15:45:14', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (104, 35, 'order_status', '已取消', '1', 11, '', NULL, NULL, 'admin', '2021-01-07 15:45:21', 'admin', '2021-01-07 15:45:21', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (105, 35, 'order_status', '已付款', '2', 12, '', NULL, NULL, 'admin', '2021-01-07 15:45:34', 'admin', '2021-01-07 15:45:34', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (106, 35, 'order_status', '已发货', '3', 13, '', NULL, NULL, 'admin', '2021-01-07 15:46:00', 'admin', '2021-01-07 15:46:00', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (107, 35, 'order_status', '用户确认收货', '4', 14, '', NULL, NULL, 'admin', '2021-01-07 15:46:10', 'admin', '2021-01-07 15:46:10', NULL, 1);
INSERT INTO `sys_dict_item` VALUES (108, 35, 'order_status', '退款', '4', 15, '', NULL, NULL, 'admin', '2021-01-07 15:46:18', 'admin', '2021-01-12 10:10:40', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (109, 35, 'order_status', '完成', '5', 16, '', NULL, NULL, 'admin', '2021-01-07 15:46:29', 'admin', '2021-01-12 10:10:45', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (110, 35, 'order_status', '待评价', '6', 17, '', NULL, NULL, 'admin', '2021-01-07 15:46:51', 'admin', '2021-01-12 10:10:48', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (111, 36, 'shipping_status', '待发货', '0', 10, '', NULL, NULL, 'admin', '2021-01-07 16:13:42', 'admin', '2021-01-07 16:13:42', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (112, 36, 'shipping_status', '已发货', '1', 11, '', NULL, NULL, 'admin', '2021-01-07 16:13:50', 'admin', '2021-01-07 16:13:50', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (113, 36, 'shipping_status', '已收货', '2', 12, '', NULL, NULL, 'admin', '2021-01-07 16:13:57', 'admin', '2021-01-07 16:14:11', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (114, 36, 'shipping_status', '退货', '3', 13, '', NULL, NULL, 'admin', '2021-01-07 16:14:06', 'admin', '2021-01-07 16:14:06', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (115, 38, 'refund_status', '申请中', '0', 10, '', NULL, NULL, 'admin', '2021-01-07 16:20:22', 'admin', '2021-01-07 16:20:22', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (116, 38, 'refund_status', '退款完成', '1', 11, '', NULL, NULL, 'admin', '2021-01-07 16:20:32', 'admin', '2021-01-07 16:20:43', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (117, 38, 'refund_status', '拒绝退款', '2', 12, '', NULL, NULL, 'admin', '2021-01-07 16:20:40', 'admin', '2021-01-07 16:20:40', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (118, 39, 'express_company', '顺丰速运', 'SF', 10, '', NULL, NULL, 'admin', '2021-01-11 18:05:30', 'admin', '2021-01-11 18:05:30', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (119, 39, 'express_company', '中通快递', 'ZTO', 11, '', NULL, NULL, 'admin', '2021-01-11 18:05:47', 'admin', '2021-01-11 18:05:47', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (120, 39, 'express_company', '申通快递', 'STO', 12, '', NULL, NULL, 'admin', '2021-01-11 18:06:05', 'admin', '2021-01-11 18:06:05', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (121, 39, 'express_company', '圆通速递', 'YTO', 14, '', NULL, NULL, 'admin', '2021-01-11 18:06:26', 'admin', '2021-01-11 18:06:26', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (122, 39, 'express_company', '韵达速递', 'YD', 15, '', NULL, NULL, 'admin', '2021-01-11 18:06:50', 'admin', '2021-01-11 18:06:50', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (123, 21, 'goods_unit', '个/件', '1', 10, '', NULL, NULL, 'admin', '2021-01-12 16:14:18', 'admin', '2021-01-12 16:14:18', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (124, 23, 'goods_keywords', '精选', '精选', 1, '', NULL, NULL, 'admin', '2021-01-22 23:19:35', 'admin', '2021-01-22 23:19:35', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (125, 23, 'goods_keywords', '时令鲜果', '时令鲜果', 2, '', NULL, NULL, 'admin', '2021-01-22 23:19:52', 'admin', '2021-01-22 23:19:52', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (126, 23, 'goods_keywords', '当季畅销', '当季畅销', 3, '', NULL, NULL, 'admin', '2021-01-22 23:20:14', 'admin', '2021-01-22 23:20:21', NULL, 0);
INSERT INTO `sys_dict_item` VALUES (127, 23, 'goods_keywords', '年货精选', '年货精选', 5, '', NULL, NULL, 'admin', '2021-01-22 23:20:36', 'admin', '2021-01-22 23:20:36', NULL, 0);


-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件存储名称 uuid',
  `file_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `file_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_size` int(15) NULL DEFAULT NULL COMMENT '文件大小',
  `file_path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `biz_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件绑定id',
  `biz_type` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件绑定类型',
  `service_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务名称',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 0 未绑定 1 绑定',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------
INSERT INTO `sys_file_info` VALUES (1, 'c54dc1f48449446388fd5737d065326b', '空白.docx', 'docx', 45, 'biz/PROCESS_ARTICLE/10002/c54dc1f48449446388fd5737d065326b.docx', '10002', 'PROCESS_ARTICLE', NULL, '1', 'admin', '2020-04-19 11:42:25', 'admin', '2020-04-19 14:08:53', NULL, 1);
INSERT INTO `sys_file_info` VALUES (2, '035f388d041f434db5943a59fbff54f4', '我的头像.jpg', 'jpg', 20, 'temp/2020-04-19/035f388d041f434db5943a59fbff54f4.jpg', NULL, NULL, NULL, '0', 'admin', '2020-04-19 11:42:45', 'admin', '2020-04-19 11:42:45', NULL, 0);
INSERT INTO `sys_file_info` VALUES (3, '8659900097a143b8959403f262c9c038', 'Blue+白色动态14键.bds', 'bds', 303829, 'biz/string/string/8659900097a143b8959403f262c9c038.bds', 'string', 'string', 'string', '1', 'admin', '2020-11-07 15:45:10', 'admin', '2020-11-07 15:56:59', NULL, 1);
INSERT INTO `sys_file_info` VALUES (4, '6913fb1d80b44a84af8b3996db938b9b', '接口参数.txt', 'txt', 3759, 'temp/2020-12-11/6913fb1d80b44a84af8b3996db938b9b.txt', NULL, NULL, NULL, '0', 'admin', '2020-12-11 16:43:02', 'admin', '2020-12-11 16:43:02', NULL, 0);


-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用类型 0 bean类型 1 rest类型 2 消息队列',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（1允许 0禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态(1-正常，0-锁定)',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统测试（无参）', 'DEFAULT', '0', 'simpleTask.doNoParams', '0/10 * * * * ? *', '3', '1', '0', 'admin', '2020-06-10 16:45:49', 'admin', '2021-01-25 16:01:41', NULL, 0);
INSERT INTO `sys_job` VALUES (2, '系统测试（有参）', 'DEFAULT', '0', 'simpleTask.doParams(\'spark\')', '0/15 * * * * ? *', '3', '1', '0', 'admin', '2020-06-10 16:45:51', 'admin', '2020-12-07 16:53:36', NULL, 0);
INSERT INTO `sys_job` VALUES (3, '系统测试（多参）', 'DEFAULT', '0', 'simpleTask.doMultipleParams(\'saprk\', true, 2000L, 316.50D, 100)', '0/20 * * * * ? *', '3', '1', '0', 'admin', '2020-06-10 16:45:53', 'admin', '2020-12-07 16:53:37', NULL, 0);

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
CREATE TABLE `sys_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_id` int(10) DEFAULT NULL COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `times` int(10) DEFAULT NULL COMMENT '执行时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `start_time` datetime(3) DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(3) DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '异常信息',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=658289 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='定时任务调度日志表';


-- ----------------------------
-- Table structure for sys_log_api
-- ----------------------------
CREATE TABLE `sys_log_api` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'url',
  `method` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '方法名',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '参数',
  `create_time` datetime DEFAULT NULL COMMENT '访问时间',
  `times` int(11) DEFAULT NULL COMMENT '耗时',
  `creator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '访问用户',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '访问ip',
  `location` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `error_log` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '错误日志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1363016463782359043 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='日志表请求日志表';

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
CREATE TABLE `sys_log_login` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录用户账号',
  `os_type` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录系统',
  `browser` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录浏览器',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `location` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录地点',
  `ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2916 DEFAULT CHARSET=utf8 COMMENT='登录日志';


