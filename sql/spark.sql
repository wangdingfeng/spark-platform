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

 Date: 31/03/2020 16:29:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `pid` bigint(11) DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) DEFAULT NULL COMMENT '父级ids',
  `simple_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简称',
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '全称',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `dept_type` int(11) DEFAULT NULL COMMENT '部门类型(0 公司1部门)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ----------------------------
--  Records of `sys_dept`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES ('1', '0', null, 'spark', 'spark开源公司', null, '0', '10', '2020-03-21 10:07:50', 'admin', '2020-03-21 10:07:50', 'admin', null, b'0'), ('2', '1', null, '技术部', '信息技术部', null, '1', '10', '2020-03-21 10:11:50', 'admin', '2020-03-21 10:11:50', 'admin', null, b'0'), ('3', '7', null, '研发一部', '研发一部门', null, '1', '10', '2020-03-21 10:12:31', 'admin', '2020-03-21 13:04:32', 'admin', null, b'0'), ('4', '1', null, '内容部门', '内容部门', null, '1', '10', '2020-03-21 10:13:17', 'admin', '2020-04-14 16:48:09', 'admin', null, b'0'), ('5', '4', null, '文章一部', '文章一部', null, '1', '10', '2020-03-21 10:13:56', 'admin', '2020-04-14 16:48:33', 'admin', null, b'0'), ('6', '2', null, '测试部', '测试部', null, '1', '10', '2020-03-21 13:03:00', 'admin', '2020-03-21 13:03:00', 'admin', null, b'0'), ('7', '2', null, '研发部', '研发部', null, '0', '10', '2020-03-21 13:04:01', 'admin', '2020-03-21 13:04:01', 'admin', null, b'0'), ('8', '6', null, '测试一部', '测试一部门', null, '0', '10', '2020-03-21 13:05:09', 'admin', '2020-03-21 13:05:09', 'admin', null, b'0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '字典名称',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '字典类型',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

-- ----------------------------
--  Records of `sys_dict`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES ('1', '用户状态', 'user_status', '用户状态', '2020-03-26 11:36:58', 'admin', '2020-03-26 11:36:58', 'admin', null, b'0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_dict_item`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) DEFAULT NULL COMMENT '父id',
  `type` varchar(64) DEFAULT NULL COMMENT '类型',
  `label` varchar(64) DEFAULT NULL COMMENT '标签值',
  `value` varchar(64) DEFAULT NULL COMMENT '字典值',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `description` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '描述',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL COMMENT '修改人',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='字典子表';

-- ----------------------------
--  Records of `sys_dict_item`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` VALUES ('1', '1', 'user_status', '禁用', '0', '10', '禁用', '2020-03-26 13:32:22', 'admin', '2020-03-26 13:43:45', 'admin', null, b'0'), ('2', '1', 'user_status', '正常', '1', '11', '正常', '2020-03-26 13:41:34', 'admin', '2020-03-26 13:41:34', 'admin', null, b'0'), ('3', '1', 'user_status', '锁定', '2', '12', '锁定', '2020-03-26 13:46:17', 'admin', '2020-03-26 13:46:17', 'admin', null, b'0'), ('4', '1', 'user_status', '过期', '3', '13', '过期', '2020-03-26 13:46:33', 'admin', '2020-03-26 13:46:33', 'admin', null, b'0'), ('5', '1', 'user_status', '测试删除', '5', '16', '测试删除', '2020-03-26 13:46:50', 'admin', '2020-03-26 13:48:33', 'admin', null, b'1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_file_info`
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_code` varchar(64) DEFAULT NULL COMMENT '文件存储名称 uuid',
  `file_name` varchar(128) DEFAULT NULL,
  `file_type` varchar(32) DEFAULT NULL COMMENT '文件类型',
  `file_size` decimal(10,0) DEFAULT NULL COMMENT '文件大小',
  `file_path` varchar(128) DEFAULT NULL COMMENT '文件路径',
  `biz_id` varchar(32) DEFAULT NULL COMMENT '文件绑定id',
  `biz_type` varchar(64) DEFAULT NULL COMMENT '文件绑定类型',
  `status` char(1) DEFAULT NULL COMMENT '状态 0 未绑定 1 绑定',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='文件信息表';

-- ----------------------------
--  Records of `sys_file_info`
-- ----------------------------
BEGIN;
INSERT INTO `sys_file_info` VALUES ('1', 'ff178ae9034a45f39c150caf8ced16e2', '我的头像.jpg', 'jpg', '20', '/Users/wangdingfeng/Downloads/spark/file/biz/PROCESS_ARTICLE/10001/ff178ae9034a45f39c150caf8ced16e2.jpg', '10001', 'PROCESS_ARTICLE', '1', '2020-04-18 19:15:27', 'admin', '2020-04-18 19:18:54', 'system', null, b'1'), ('2', 'a7c63db300524ddc840735a59e3a2056', '图1.jpg', 'jpg', '86', 'biz/PROCESS_ARTICLE/10002/a7c63db300524ddc840735a59e3a2056.jpg', '10002', 'PROCESS_ARTICLE', '1', '2020-04-18 20:14:14', 'admin', '2020-04-18 20:15:21', 'system', null, b'0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `pid` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `type` char(1) DEFAULT NULL COMMENT '类型',
  `i_frame` bit(1) DEFAULT NULL COMMENT '是否外链',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `component` varchar(128) DEFAULT NULL COMMENT '组件路径',
  `permission` varchar(128) DEFAULT NULL COMMENT '权限',
  `hidden` bit(1) DEFAULT NULL COMMENT '是否隐藏',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `sort` bigint(20) NOT NULL COMMENT '排序',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('1', '系统设置', '0', '0', b'0', 'example', 'Layout', '', b'0', 'example', '1', '2020-04-02 15:37:06', 'admin', '2020-03-20 18:13:12', 'admin', null, b'0'), ('2', '用户管理', '1', '1', b'0', '/user', 'sys/user', '', b'0', 'user', '1', '2020-03-16 15:38:27', 'admin', '2020-03-16 15:38:32', 'admin', null, b'0'), ('3', '新增', '2', '2', b'0', null, null, 'user:add', b'0', null, '2', '2020-03-16 15:41:23', 'admin', '2020-03-21 21:29:53', 'admin', null, b'0'), ('4', '编辑', '2', '2', b'0', null, null, 'user:edit', b'0', null, '3', '2020-03-16 15:42:22', 'admin', '2020-03-21 21:32:02', 'admin', null, b'0'), ('5', '删除', '2', '2', b'0', null, null, 'user:delete', b'0', null, '4', '2020-03-16 15:43:05', 'admin', '2020-03-21 21:32:14', 'admin', null, b'0'), ('6', '角色管理', '1', '1', b'0', '/role', 'sys/role', null, b'0', 'tree', '5', '2020-03-16 15:44:21', 'admin', '2020-03-16 15:44:28', 'admin', null, b'0'), ('7', '新增', '6', '2', b'0', null, null, 'role:add', b'0', null, '6', '2020-03-18 15:05:22', 'admin', '2020-03-21 21:32:38', 'admin', null, b'0'), ('8', '编辑', '6', '2', b'0', null, null, 'role:edit', b'0', null, '7', '2020-03-18 15:06:05', 'admin', '2020-03-21 21:32:26', 'admin', null, b'0'), ('9', '删除', '6', '2', b'0', null, null, 'role:delete', b'0', null, '8', '2020-03-18 15:06:46', 'admin', '2020-03-21 21:32:47', 'admin', null, b'0'), ('10', '菜单管理', '1', '1', b'0', '/menu', 'sys/menu', null, b'0', 'table', '10', '2020-03-18 15:07:57', 'admin', '2020-03-18 15:08:04', 'admin', null, b'0'), ('11', '新增', '10', '2', b'0', '', '', 'menu:add', b'0', '', '100', '2020-03-20 13:45:34', 'admin', '2020-03-21 21:32:58', 'admin', null, b'0'), ('12', '编辑', '10', '2', b'0', '', '', 'menu:edit', b'0', '', '110', '2020-03-21 09:35:26', 'admin', '2020-03-21 21:33:09', 'admin', null, b'0'), ('13', '删除', '10', '2', b'0', '', '', 'menu:delete', b'0', '', '120', '2020-03-21 09:36:04', 'admin', '2020-03-21 21:33:18', 'admin', null, b'0'), ('14', '部门管理', '1', '1', b'0', '/dept', 'sys/dept', '', b'0', 'dept', '20', '2020-03-21 09:38:25', 'admin', '2020-03-21 09:38:25', 'admin', null, b'0'), ('15', '新增', '14', '2', b'0', '', '', 'dept:add', b'0', '', '210', '2020-03-21 09:39:01', 'admin', '2020-03-21 09:39:01', 'admin', null, b'0'), ('16', '编辑', '14', '2', b'0', '', '', 'dept:edit', b'0', '', '220', '2020-03-21 09:39:22', 'admin', '2020-03-21 09:39:22', 'admin', null, b'0'), ('17', '删除', '14', '2', b'0', '', '', 'dept:delete', b'0', '', '230', '2020-03-21 09:39:42', 'admin', '2020-03-21 09:39:42', 'admin', null, b'0'), ('18', '字典管理', '1', '1', b'0', '/dict', 'sys/dict', '', b'0', 'dictionary', '30', '2020-03-21 14:49:56', 'admin', '2020-04-18 21:19:48', 'admin', null, b'0'), ('19', '新增', '18', '2', b'0', '', '', 'dict:add', b'0', '', '10', '2020-03-21 14:50:31', 'admin', '2020-03-21 14:50:31', 'admin', null, b'0'), ('20', '编辑', '18', '2', b'0', '', '', 'dict:edit', b'0', '', '10', '2020-03-21 14:50:48', 'admin', '2020-03-21 14:50:48', 'admin', null, b'0'), ('21', '删除', '18', '2', b'0', '', '', 'dict:delete', b'0', '', '10', '2020-03-21 14:51:03', 'admin', '2020-03-21 14:51:03', 'admin', null, b'0'), ('22', '客户端管理', '1', '1', b'0', '/oauth', 'sys/oauth', '', b'0', 'oauthClient', '40', '2020-03-21 15:39:23', 'admin', '2020-04-18 21:19:52', 'admin', null, b'0'), ('23', '系统监控', '0', '0', b'0', 'monitor', 'Layout', '', b'0', 'sparkler', '10', '2020-03-21 21:37:44', 'admin', '2020-03-21 21:37:44', 'admin', null, b'0'), ('24', '接口文档', '23', '1', b'1', 'http://106.13.179.172:8002/swagger-ui.html', null, '', b'0', '', '20', '2020-03-21 21:39:27', 'admin', '2020-04-18 23:05:21', 'admin', null, b'0'), ('25', '注册中心', '23', '1', b'1', 'http://106.13.179.172:8761/', null, '', b'0', '', '30', '2020-03-21 21:58:22', 'admin', '2020-04-18 23:05:27', 'admin', null, b'0'), ('26', 'Admin监控', '23', '1', b'1', 'http://106.13.179.172:8980/wallboard', null, '', b'0', '', '40', '2020-03-22 14:12:05', 'admin', '2020-04-18 23:05:35', 'admin', null, b'0'), ('27', '系统日志', '23', '1', b'0', '/log', 'sys/log', '', b'0', '', '5', '2020-03-25 10:02:45', 'admin', '2020-03-25 10:28:02', 'admin', null, b'0'), ('28', '协同管理', '0', '0', b'0', 'act', 'Layout', '', b'0', 'act', '9', '2020-04-03 20:41:47', 'admin', '2020-04-08 20:41:36', 'admin', null, b'0'), ('29', '待办事项', '28', '1', b'0', '/tasks', 'act/tasks', '', b'0', 'guide', '10', '2020-04-06 14:47:36', 'admin', '2020-04-14 15:00:15', 'admin', null, b'0'), ('30', '已办事项', '28', '1', b'0', '/histasks', 'act/histasks', '', b'0', 'handle', '20', '2020-04-06 14:48:34', 'admin', '2020-04-14 15:13:24', 'admin', null, b'0'), ('31', '流程管理', '28', '1', b'0', '/process', 'act/process', '', b'0', 'model', '30', '2020-04-06 14:50:20', 'admin', '2020-04-14 15:15:38', 'admin', null, b'0'), ('32', '研发工具', '0', '0', b'0', 'wrench', 'Layout', '', b'0', 'wrench', '10', '2020-04-15 15:20:55', 'admin', '2020-04-15 15:20:55', 'admin', null, b'0'), ('33', '代码生成', '32', '1', b'0', '/code', 'sys/gen', '', b'0', 'code', '10', '2020-04-15 15:33:51', 'admin', '2020-04-15 15:33:51', 'admin', null, b'0'), ('34', '表单例子', '32', '1', b'0', '/form', 'form', '', b'0', 'edit', '20', '2020-04-16 11:22:02', 'admin', '2020-04-16 11:22:42', 'admin', null, b'0'), ('35', '文件管理', '1', '1', b'0', '/file', 'sys/file', '', b'0', '_file', '50', '2020-04-18 15:05:21', 'admin', '2020-04-18 21:20:00', 'admin', null, b'0'), ('36', '登录日志', '23', '1', b'0', '/login-log', 'sys/login-log', '', b'0', '', '10', '2020-04-18 23:05:07', 'admin', '2020-04-18 23:06:15', 'admin', null, b'0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `resource_ids` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `scope` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `authorized_grant_types` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `authorities` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `autoapprove` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='oauth2客户端表';

-- ----------------------------
--  Records of `sys_oauth_client_details`
-- ----------------------------
BEGIN;
INSERT INTO `sys_oauth_client_details` VALUES ('1', 'spark-admin', null, 'spark-admin-secret', 'all,read,write', 'password,refresh_token,authorization_code,client_credentials', null, null, '21600', '28800', 'true');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(30) DEFAULT NULL COMMENT '角色编号',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `dept_name` varchar(64) DEFAULT NULL COMMENT '部门简称',
  `status` int(1) DEFAULT NULL COMMENT '状态 0无效 1有效',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `del_flag` int(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '超级管理员', '7', '研发部', null, '2020-03-19 22:54:10', 'admin', '2020-03-22 10:57:32', 'admin', null, '0'), ('2', '系统管理员', 'system', '我是', '7', '研发部', null, '2020-03-19 22:55:31', 'admin', '2020-03-22 14:01:50', 'admin', null, '0'), ('3', '测试角色', 'role_test', '', '2', null, null, '2020-03-19 22:56:06', 'admin', '2020-03-19 22:56:06', 'admin', null, '1'), ('4', '测试角色1', 'role_test1', '', '2', null, null, '2020-03-19 23:01:32', 'admin', '2020-03-19 23:01:32', 'admin', null, '1'), ('5', '文章审核组长', 'role_group_leader', '文章审核组长', '5', '文章一部', null, '2020-04-14 16:49:52', 'admin', '2020-04-14 16:50:19', 'admin', null, '0'), ('6', '主编', 'role_editor_manage', '主编', '5', '文章一部', null, '2020-04-14 16:55:45', 'admin', '2020-04-14 16:55:45', 'admin', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(11) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=423 DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单表';

-- ----------------------------
--  Records of `sys_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('265', '5', '28'), ('266', '5', '29'), ('267', '5', '30'), ('268', '6', '28'), ('269', '6', '29'), ('270', '6', '30'), ('271', '2', '1'), ('272', '2', '2'), ('273', '2', '3'), ('274', '2', '4'), ('275', '2', '5'), ('276', '2', '6'), ('277', '2', '7'), ('278', '2', '10'), ('279', '2', '14'), ('280', '2', '23'), ('281', '2', '24'), ('282', '2', '25'), ('283', '2', '26'), ('284', '2', '27'), ('285', '2', '28'), ('286', '2', '29'), ('287', '2', '30'), ('288', '2', '31'), ('388', '1', '1'), ('389', '1', '2'), ('390', '1', '3'), ('391', '1', '4'), ('392', '1', '5'), ('393', '1', '6'), ('394', '1', '7'), ('395', '1', '8'), ('396', '1', '9'), ('397', '1', '10'), ('398', '1', '11'), ('399', '1', '12'), ('400', '1', '13'), ('401', '1', '14'), ('402', '1', '15'), ('403', '1', '16'), ('404', '1', '17'), ('405', '1', '18'), ('406', '1', '19'), ('407', '1', '20'), ('408', '1', '22'), ('409', '1', '35'), ('410', '1', '23'), ('411', '1', '24'), ('412', '1', '25'), ('413', '1', '26'), ('414', '1', '27'), ('415', '1', '36'), ('416', '1', '28'), ('417', '1', '29'), ('418', '1', '30'), ('419', '1', '31'), ('420', '1', '32'), ('421', '1', '33'), ('422', '1', '34');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `phone` varchar(13) DEFAULT NULL COMMENT '手机号',
  `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `last_login_ip` varchar(60) DEFAULT NULL COMMENT '最后登录IP',
  `last_login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后登陆时间',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `dept_id` bigint(11) DEFAULT NULL COMMENT '部门id 一个用户只有 一个部门',
  `dept_name` varchar(64) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '状态 0无效 1有效',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `del_flag` int(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '$2a$10$ts07XkBaX7OwC5xA449gh.MO1Sa3KfyJlcx./lZKkMEgP8XDSoR9e', '1', '123456789', '1234@qq.com', '0:0:0:0:0:0:0:1', '2020-04-14 16:43:03', 'sunny/9.jpg', '7', '研发部', '1', '2020-03-19 14:35:18', 'admin', '2020-04-16 14:21:44', 'admin', '我是一个备注', '0'), ('7', 'system', '小王', '$2a$10$oFol0M7BlopTxvVYMos35uLwnF2g2YobjderyjY2srSmefuBoSXKG', '0', '123456789', '123@qq.com', 'string', '2020-04-13 09:29:57', 'others/14.jpg', '0', null, '1', '2020-03-19 17:09:31', 'admin', '2020-03-19 17:09:31', 'admin', null, '1'), ('8', 'test', '测试用户', '$2a$10$dmBV0Cy0dh2bDzkCclF5yerWRRPHawU5O.MEYll9.r0IUlwZKUA7q', '0', '12345677', '123@qq.com', '122.224.152.194', '2020-04-13 09:29:58', 'others/14.jpg', '6', '测试部', '1', '2020-03-19 20:22:18', 'admin', '2020-04-01 10:07:15', 'admin', '1232', '0'), ('9', 'test1', '测试用户1', '$2a$10$NsOBCqf3LOXiQxGWs/QkteofPQTfd2qBuAfG31Y6IJx5bsObLY4am', '0', '1231231231', '123@qq.com', null, '2020-04-13 09:29:58', 'others/14.jpg', null, null, '1', '2020-03-19 20:24:03', 'admin', '2020-03-19 20:24:03', 'admin', null, '1'), ('10', 'spark', '火花', '$2a$10$a6TPwO6wnXbouCwfowwb/.MqnfKgs3bxW3EwH7SUiUHeF4PfJYK7e', '1', '123123', '123@qq.com', '0:0:0:0:0:0:0:1', '2020-04-13 09:29:59', 'others/14.jpg', '3', '研发一部', '1', '2020-03-19 20:31:04', 'admin', '2020-04-16 14:21:52', 'admin', '12', '0'), ('11', 'zhubian2', '主编2', '$2a$10$kXbLvWcO3i155u7pTbjR4uCabdytnKNKUMB4ozaLCu5htDzVVKH4u', '1', '1231232199', '123@qq.com', null, '2020-04-14 16:59:11', 'sunny/5.jpg', '5', '文章一部', '1', '2020-03-19 20:47:09', 'admin', '2020-04-14 17:28:48', 'admin', '主编', '0'), ('12', 'zhubian1', '主编1', '$2a$10$RtbN3jyBh5Zvd0H99PkYwuH6zmsMfN37bEZkyow78Dh/KTn0C4Ev.', '1', '1999999999', '12312312@qq.com', null, '2020-04-13 09:30:05', 'others/14.jpg', '5', '文章一部', '1', '2020-03-21 19:56:30', 'admin', '2020-04-14 17:28:40', 'admin', null, '0'), ('13', 'zuzhang', '组长', '$2a$10$DMb.Gx/GbX657UT6Z5/Lx.AOYRZhHsK0/KWlflqxlNxhhoZmbAPz6', '0', '12312312323', '123555@qq.com', null, '2020-04-14 17:15:27', 'others/1.jpg', '5', '文章一部', '1', '2020-04-14 16:54:44', 'admin', '2020-04-14 17:28:33', 'admin', '组长', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) DEFAULT NULL COMMENT '角色id',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('7', '2', '8'), ('14', '5', null), ('18', '5', '13'), ('19', '6', '12'), ('20', '6', '11'), ('21', '1', '1'), ('22', '2', '10');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
--  Table structure for `sys_log_api`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_api`;
CREATE TABLE `sys_log_api` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) DEFAULT NULL COMMENT 'url',
  `method` varchar(128) DEFAULT NULL COMMENT '方法名',
  `params` text COMMENT '参数',
  `create_time` datetime DEFAULT NULL COMMENT '访问时间',
  `times` int(11) DEFAULT NULL COMMENT '耗时',
  `creator` varchar(32) DEFAULT NULL COMMENT '访问用户',
  `ip` varchar(50) DEFAULT NULL COMMENT '访问ip',
  `address` varchar(64) DEFAULT NULL COMMENT '地址',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `error_log` text COMMENT '错误日志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1251530028188925955 DEFAULT CHARSET=utf8mb4 COMMENT='api请求日志表';

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
--  Table structure for `sys_log_login`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) DEFAULT NULL COMMENT '登录用户账号',
  `system` varchar(128) DEFAULT NULL COMMENT '登录系统',
  `browser` varchar(128) DEFAULT NULL COMMENT '登录浏览器',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `location` varchar(128) DEFAULT NULL COMMENT '登录地点',
  `location_ip` varchar(128) DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='登录日志';

SET FOREIGN_KEY_CHECKS = 1;
