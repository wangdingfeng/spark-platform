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

 Date: 07/05/2020 17:12:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `author` varchar(122) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `importance` int(1) NULL DEFAULT NULL COMMENT '重要性',
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `platforms` varchar(62) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台',
  `is_original` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否原创',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 0 暂存 1 退回修改 2 组长审核 3 主编审核 4 审核通过 5 审核不通过',
  `publish_time` datetime(0) NULL DEFAULT NULL COMMENT '状态 0 暂存 1 退回修改 2 组长审核 3 主编审核 4 审核通过 5 审核不通过',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime(0) NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` int(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  `content_short` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '概括',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES (1, 'Plans for the Next Iteration of Vue.js', 'polaris.wang', 3, '', '[\"a-platform\"]', '1', '<p>Last week at<a href=\"https://vuejs.london/summary\" rel=\"nofollow\">Vue.js London</a>I gave a brief sneak peek of what&rsquo;s coming in the next major version of Vue. This post provides an in-depth overview of the plan.</p>\n<h3>Why a new majorversion?</h3>\n<p>Vue 2.0 was released<a href=\"https://medium.com/the-vue-point/vue-2-0-is-here-ef1f26acf4b8\" rel=\"nofollow\">exactly two years ago</a>(how time flies!). During this period, the core has remained backwards compatible with five minor releases. We&rsquo;ve accumulated a number of ideas that would bring improvements, but they were held off because they would result in breaking changes. At the same time, the JavaScript ecosystem and the language itself has been evolving rapidly. There are greatly improved tools that could enhance our workflow, and many new language features that could unlock simpler, more complete, and more efficient solutions to the problems Vue is trying to solve. What&rsquo;s more exciting is that we are seeing ES2015 support becoming a baseline for all major evergreen browsers. Vue 3.0 aims to leverage these new language features to make Vue core smaller, faster, and more powerful.</p>\n<p>Vue 3.0 is currently in prototyping phase, and we have already implemented a runtime close to feature-parity with 2.x.<strong>Many of the items listed below are either already implemented, or confirmed to be feasible. Ones that are not yet implemented or still in exploration phase are marked with a *.</strong></p>\n<h4>Other Runtime Improvements</h4>\n<blockquote>TL;DR: smaller, faster, tree-shakable features, fragments &amp; portals, custom renderer API.</blockquote>\n<h4>Compiler Improvements*</h4>\n<blockquote>TL;DR: tree-shaking friendly output, more AOT optimizations, parser with better error info and source map support.</blockquote>\n<h4>IE11 Support*</h4>\n<blockquote>TL;DR: it will be supported, but in a separate build with the same reactivity limitations of Vue 2.x.</blockquote>\n<p>The new codebase currently targets evergreen browsers only and assumes baseline native ES2015 support. But alas, we know a lot of our users still need to support IE11 for the foreseeable future. Most of the ES2015 features used can be transpiled / polyfilled for IE11, with the exception for Proxies. Our plan is to implement an alternative observer with the same API, but using the good old ES5<code>Object.defineProperty</code>API. A separate build of Vue 3.x will be distributed using this observer implementation. However, this build will be subject to the same change detection caveats of Vue 2.x and thus not fully compatible with the &ldquo;modern&rdquo; build of 3.x. We are aware that this imposes some inconvenience for library authors as they will need to be aware of compatibility for two different builds, but we will make sure to provide clear guidelines on this when we reach that stage.</p>\n<h3>How Do We GetThere</h3>\n<p>First of all, although we are announcing it today, we do not have a definitive timeline yet. What we do know at the moment is the steps we will be taking to get there:</p>\n<h4>1. Internal Feedback for the Runtime Prototype</h4>\n<p>This is the phase we are in right now. Currently, we already have a working runtime prototype that includes the new observer, Virtual DOM and component implementation. We have invited a group of authors of influential community projects to provide feedback for the internal changes, and would like to make sure they are comfortable with the changes before moving forward. We want to ensure that important libraries in the ecosystem will be ready at the same time when we release 3.0, so that users relying on those projects can upgrade easily.</p>\n<h4>2. Public Feedback viaRFCs</h4>\n<p>Once we gain a certain level of confidence in the new design, for each breaking change we will be opening a dedicated RFC issue which includes:</p>\n<p>We will anticipate public feedback from the wider community to help us consolidate these ideas.</p>\n<h4>3. Introduce Compatible Features in 2.x &amp;2.x-next</h4>\n<p>We are not forgetting about 2.x! In fact, we plan to use 2.x to progressively accustom users to the new changes. We will be gradually introducing confirmed API changes into 2.x via opt-in adaptors, and 2.x-next will allow users to try out the new Proxy-based observer.</p>\n<p>The last minor release in 2.x will become LTS and continue to receive bug and security fixes for 18 months when 3.0 is released.</p>\n<h4>4. AlphaPhase</h4>\n<p>Next, we will finish up the compiler and server-side rendering parts of 3.0 and start making alpha releases. These will mostly be for stability testing purposes in small greenfield apps.</p>\n<h4>5. BetaPhase</h4>\n<p>During beta phase, our main goal is updating support libraries and tools like Vue Router, Vuex, Vue CLI, Vue DevTools and make sure they work smoothly with the new core. We will also be working with major library authors from the community to help them get ready for 3.0.</p>\n<h4>6. RCPhase</h4>\n<p>Once we consider the API and codebase stable, we will enter RC phase with API freeze. During this phase we will also work on a &ldquo;compat build&rdquo;: a build of 3.0 that includes compatibility layers for 2.x API. This build will also ship with a flag you can turn on to emit deprecation warnings for 2.x API usage in your app. The compat build can be used as a guide to upgrade your app to 3.0.</p>\n<h4>7. IE11build</h4>\n<p>The last task before the final release will be the IE11 compatibility build as mentioned above.</p>\n<h4>8. FinalRelease</h4>\n<p>In all honesty, we don&rsquo;t know when this will happen yet, but likely in 2019. Again, we care more about shipping something that is solid and stable rather than hitting specific dates. There is a lot of work to be done, but we are excited for what&rsquo;s coming next!</p>', '4', '2020-05-04 15:32:18', '2020-05-04 15:32:24', 'admin', '2020-05-07 16:40:48', 'system', NULL, 0, '测试');
INSERT INTO `cms_article` VALUES (3, 'VUE 文章审核', 'polaris.wang', 3, '', '[\"a-platform\"]', NULL, '<p>Last week at<a href=\"https://vuejs.london/summary\" rel=\"nofollow\">Vue.js London</a>I gave a brief sneak peek of what&rsquo;s coming in the next major version of Vue. This post provides an in-depth overview of the plan.</p>\n<h3>Why a new majorversion?</h3>\n<p>Vue 2.0 was released<a href=\"https://medium.com/the-vue-point/vue-2-0-is-here-ef1f26acf4b8\" rel=\"nofollow\">exactly two years ago</a>(how time flies!). During this period, the core has remained backwards compatible with five minor releases. We&rsquo;ve accumulated a number of ideas that would bring improvements, but they were held off because they would result in breaking changes. At the same time, the JavaScript ecosystem and the language itself has been evolving rapidly. There are greatly improved tools that could enhance our workflow, and many new language features that could unlock simpler, more complete, and more efficient solutions to the problems Vue is trying to solve. What&rsquo;s more exciting is that we are seeing ES2015 support becoming a baseline for all major evergreen browsers. Vue 3.0 aims to leverage these new language features to make Vue core smaller, faster, and more powerful.</p>\n<p>Vue 3.0 is currently in prototyping phase, and we have already implemented a runtime close to feature-parity with 2.x.<strong>Many of the items listed below are either already implemented, or confirmed to be feasible. Ones that are not yet implemented or still in exploration phase are marked with a *.</strong></p>\n<h4>Other Runtime Improvements</h4>\n<blockquote>TL;DR: smaller, faster, tree-shakable features, fragments &amp; portals, custom renderer API.</blockquote>\n<h4>Compiler Improvements*</h4>\n<blockquote>TL;DR: tree-shaking friendly output, more AOT optimizations, parser with better error info and source map support.</blockquote>\n<h4>IE11 Support*</h4>\n<blockquote>TL;DR: it will be supported, but in a separate build with the same reactivity limitations of Vue 2.x.</blockquote>\n<p>The new codebase currently targets evergreen browsers only and assumes baseline native ES2015 support. But alas, we know a lot of our users still need to support IE11 for the foreseeable future. Most of the ES2015 features used can be transpiled / polyfilled for IE11, with the exception for Proxies. Our plan is to implement an alternative observer with the same API, but using the good old ES5<code>Object.defineProperty</code>API. A separate build of Vue 3.x will be distributed using this observer implementation. However, this build will be subject to the same change detection caveats of Vue 2.x and thus not fully compatible with the &ldquo;modern&rdquo; build of 3.x. We are aware that this imposes some inconvenience for library authors as they will need to be aware of compatibility for two different builds, but we will make sure to provide clear guidelines on this when we reach that stage.</p>\n<h3>How Do We GetThere</h3>\n<p>First of all, although we are announcing it today, we do not have a definitive timeline yet. What we do know at the moment is the steps we will be taking to get there:</p>\n<h4>1. Internal Feedback for the Runtime Prototype</h4>\n<p>This is the phase we are in right now. Currently, we already have a working runtime prototype that includes the new observer, Virtual DOM and component implementation. We have invited a group of authors of influential community projects to provide feedback for the internal changes, and would like to make sure they are comfortable with the changes before moving forward. We want to ensure that important libraries in the ecosystem will be ready at the same time when we release 3.0, so that users relying on those projects can upgrade easily.</p>\n<h4>2. Public Feedback viaRFCs</h4>\n<p>Once we gain a certain level of confidence in the new design, for each breaking change we will be opening a dedicated RFC issue which includes:</p>\n<p>We will anticipate public feedback from the wider community to help us consolidate these ideas.</p>\n<h4>3. Introduce Compatible Features in 2.x &amp;2.x-next</h4>\n<p>We are not forgetting about 2.x! In fact, we plan to use 2.x to progressively accustom users to the new changes. We will be gradually introducing confirmed API changes into 2.x via opt-in adaptors, and 2.x-next will allow users to try out the new Proxy-based observer.</p>\n<p>The last minor release in 2.x will become LTS and continue to receive bug and security fixes for 18 months when 3.0 is released.</p>\n<h4>4. AlphaPhase</h4>\n<p>Next, we will finish up the compiler and server-side rendering parts of 3.0 and start making alpha releases. These will mostly be for stability testing purposes in small greenfield apps.</p>\n<h4>5. BetaPhase</h4>\n<p>During beta phase, our main goal is updating support libraries and tools like Vue Router, Vuex, Vue CLI, Vue DevTools and make sure they work smoothly with the new core. We will also be working with major library authors from the community to help them get ready for 3.0.</p>\n<h4>6. RCPhase</h4>\n<p>Once we consider the API and codebase stable, we will enter RC phase with API freeze. During this phase we will also work on a &ldquo;compat build&rdquo;: a build of 3.0 that includes compatibility layers for 2.x API. This build will also ship with a flag you can turn on to emit deprecation warnings for 2.x API usage in your app. The compat build can be used as a guide to upgrade your app to 3.0.</p>\n<h4>7. IE11build</h4>\n<p>The last task before the final release will be the IE11 compatibility build as mentioned above.</p>\n<h4>8. FinalRelease</h4>\n<p>In all honesty, we don&rsquo;t know when this will happen yet, but likely in 2019. Again, we care more about shipping something that is solid and stable rather than hitting specific dates. There is a lot of work to be done, but we are excited for what&rsquo;s coming next!</p>', '2', '2020-05-07 16:47:43', '2020-05-07 16:47:56', 'admin', '2020-05-07 16:47:56', 'admin', NULL, 0, 'VUE');

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
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, NULL, 'spark', 'spark开源公司', NULL, 0, 10, '2020-03-21 10:07:50', 'admin', '2020-03-21 10:07:50', 'admin', NULL, b'0');
INSERT INTO `sys_dept` VALUES (2, 1, NULL, '技术部', '信息技术部', NULL, 1, 10, '2020-03-21 10:11:50', 'admin', '2020-03-21 10:11:50', 'admin', NULL, b'0');
INSERT INTO `sys_dept` VALUES (3, 7, NULL, '研发一部', '研发一部门', NULL, 1, 10, '2020-03-21 10:12:31', 'admin', '2020-03-21 13:04:32', 'admin', NULL, b'0');
INSERT INTO `sys_dept` VALUES (4, 1, NULL, '内容部门', '内容部门', NULL, 1, 10, '2020-03-21 10:13:17', 'admin', '2020-04-14 16:48:09', 'admin', NULL, b'0');
INSERT INTO `sys_dept` VALUES (5, 4, NULL, '文章一部', '文章一部', NULL, 1, 10, '2020-03-21 10:13:56', 'admin', '2020-04-14 16:48:33', 'admin', NULL, b'0');
INSERT INTO `sys_dept` VALUES (6, 2, NULL, '测试部', '测试部', NULL, 1, 10, '2020-03-21 13:03:00', 'admin', '2020-03-21 13:03:00', 'admin', NULL, b'0');
INSERT INTO `sys_dept` VALUES (7, 2, NULL, '研发部', '研发部', NULL, 0, 10, '2020-03-21 13:04:01', 'admin', '2020-03-21 13:04:01', 'admin', NULL, b'0');
INSERT INTO `sys_dept` VALUES (8, 6, NULL, '测试一部', '测试一部门', NULL, 0, 10, '2020-03-21 13:05:09', 'admin', '2020-03-21 13:05:09', 'admin', NULL, b'0');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字典类型',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '用户状态', 'user_status', '用户状态', '2020-03-26 11:36:58', 'admin', '2020-03-26 11:36:58', 'admin', NULL, b'0');
INSERT INTO `sys_dict` VALUES (2, '性别', 'sex', '0 女 1 男', '2020-04-29 21:28:17', 'admin', '2020-04-29 21:28:17', 'admin', NULL, b'0');
INSERT INTO `sys_dict` VALUES (3, '文件状态', 'file_status', '文件状态 0 未绑定 1已绑定', '2020-05-02 09:53:31', 'admin', '2020-05-02 09:54:18', 'admin', NULL, b'0');
INSERT INTO `sys_dict` VALUES (4, '流程业务类型', 'processs_type', '用于业务流程类型', '2020-05-02 09:55:00', 'admin', '2020-05-02 09:55:00', 'admin', NULL, b'0');
INSERT INTO `sys_dict` VALUES (5, '文章状态', 'article_status', '0 暂存 1 退回修改 2 组长审核 3 主编审核 4 审核通过 5 审核不通过', '2020-05-07 13:53:09', 'admin', '2020-05-07 13:53:09', 'admin', NULL, b'0');

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
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime(0) NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) NOT NULL COMMENT '状态',
  `value1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1, 1, 'user_status', '禁用', '0', 10, '禁用', '2020-03-26 13:32:22', 'admin', '2020-03-26 13:43:45', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (2, 1, 'user_status', '正常', '1', 11, '正常', '2020-03-26 13:41:34', 'admin', '2020-03-26 13:41:34', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (3, 1, 'user_status', '锁定', '2', 12, '锁定', '2020-03-26 13:46:17', 'admin', '2020-03-26 13:46:17', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (4, 1, 'user_status', '过期', '3', 13, '过期', '2020-03-26 13:46:33', 'admin', '2020-03-26 13:46:33', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (5, 1, 'user_status', '测试删除', '5', 16, '测试删除', '2020-03-26 13:46:50', 'admin', '2020-03-26 13:48:33', 'admin', NULL, b'1', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (6, 2, 'sex', '女', '0', 10, '', '2020-04-29 21:29:55', 'admin', '2020-04-29 21:29:55', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (7, 2, 'sex', '男', '1', 11, '', '2020-04-29 21:30:07', 'admin', '2020-04-29 21:30:07', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (8, 3, 'file_status', '未绑定', '0', 10, '', '2020-05-02 09:53:55', 'admin', '2020-05-02 09:53:55', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (9, 3, 'file_status', '已绑定', '1', 11, '', '2020-05-02 09:54:04', 'admin', '2020-05-02 09:54:04', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (10, 4, 'processs_type', '文章审核', 'PROCESS_ARTICLE', 10, '文章审核流程', '2020-05-02 09:55:53', 'admin', '2020-05-02 09:55:53', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (11, 5, 'article_status', '暂存', '0', 10, '', '2020-05-07 13:53:25', 'admin', '2020-05-07 13:53:25', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (12, 5, 'article_status', '退回修改', '1', 10, '', '2020-05-07 13:53:35', 'admin', '2020-05-07 13:53:35', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (13, 5, 'article_status', '组长审核', '2', 10, '', '2020-05-07 13:53:43', 'admin', '2020-05-07 13:53:43', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (14, 5, 'article_status', '主编审核', '3', 10, '', '2020-05-07 13:53:48', 'admin', '2020-05-07 13:53:48', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (15, 5, 'article_status', '审核通过', '4', 10, '', '2020-05-07 13:53:54', 'admin', '2020-05-07 13:53:54', 'admin', NULL, b'0', NULL, NULL);
INSERT INTO `sys_dict_item` VALUES (16, 5, 'article_status', '审核不通过', '5', 10, '', '2020-05-07 13:53:59', 'admin', '2020-05-07 13:53:59', 'admin', NULL, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件存储名称 uuid',
  `file_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_size` decimal(10, 0) NULL DEFAULT NULL COMMENT '文件大小',
  `file_path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `biz_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件绑定id',
  `biz_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件绑定类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 0 未绑定 1 绑定',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------
INSERT INTO `sys_file_info` VALUES (1, 'c54dc1f48449446388fd5737d065326b', '空白.docx', 'docx', 45, 'biz/PROCESS_ARTICLE/10002/c54dc1f48449446388fd5737d065326b.docx', '10002', 'PROCESS_ARTICLE', '1', '2020-04-19 11:42:25', 'admin', '2020-04-19 14:08:53', 'admin', NULL, b'0');
INSERT INTO `sys_file_info` VALUES (2, '035f388d041f434db5943a59fbff54f4', '我的头像.jpg', 'jpg', 20, 'temp/2020-04-19/035f388d041f434db5943a59fbff54f4.jpg', NULL, NULL, '0', '2020-04-19 11:42:45', 'admin', '2020-04-19 11:42:45', 'admin', NULL, b'0');

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
  `sort` bigint(20) NOT NULL COMMENT '排序',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统设置', 0, '0', b'0', 'example', 'Layout', '', b'0', 'example', 1, '2020-04-02 15:37:06', 'admin', '2020-03-20 18:13:12', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (2, '用户管理', 1, '1', b'0', '/user', 'sys/user/index', '', b'0', 'user', 1, '2020-03-16 15:38:27', 'admin', '2020-05-02 09:35:41', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (3, '新增', 2, '2', b'0', NULL, NULL, 'user:add', b'0', NULL, 2, '2020-03-16 15:41:23', 'admin', '2020-03-21 21:29:53', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (4, '编辑', 2, '2', b'0', NULL, NULL, 'user:edit', b'0', NULL, 3, '2020-03-16 15:42:22', 'admin', '2020-03-21 21:32:02', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (5, '删除', 2, '2', b'0', NULL, NULL, 'user:delete', b'0', NULL, 4, '2020-03-16 15:43:05', 'admin', '2020-03-21 21:32:14', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (6, '角色管理', 1, '1', b'0', '/role', 'sys/role/index', NULL, b'0', 'tree', 5, '2020-03-16 15:44:21', 'admin', '2020-05-02 09:35:48', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (7, '新增', 6, '2', b'0', NULL, NULL, 'role:add', b'0', NULL, 6, '2020-03-18 15:05:22', 'admin', '2020-03-21 21:32:38', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (8, '编辑', 6, '2', b'0', NULL, NULL, 'role:edit', b'0', NULL, 7, '2020-03-18 15:06:05', 'admin', '2020-03-21 21:32:26', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (9, '删除', 6, '2', b'0', NULL, NULL, 'role:delete', b'0', NULL, 8, '2020-03-18 15:06:46', 'admin', '2020-03-21 21:32:47', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (10, '菜单管理', 1, '1', b'0', '/menu', 'sys/menu/index', NULL, b'0', 'table', 10, '2020-03-18 15:07:57', 'admin', '2020-03-18 15:08:04', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (11, '新增', 10, '2', b'0', '', '', 'menu:add', b'0', '', 100, '2020-03-20 13:45:34', 'admin', '2020-03-21 21:32:58', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (12, '编辑', 10, '2', b'0', '', '', 'menu:edit', b'0', '', 110, '2020-03-21 09:35:26', 'admin', '2020-03-21 21:33:09', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (13, '删除', 10, '2', b'0', '', '', 'menu:delete', b'0', '', 120, '2020-03-21 09:36:04', 'admin', '2020-03-21 21:33:18', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (14, '部门管理', 1, '1', b'0', '/dept', 'sys/dept/index', '', b'0', 'dept', 20, '2020-03-21 09:38:25', 'admin', '2020-05-02 09:35:56', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (15, '新增', 14, '2', b'0', '', '', 'dept:add', b'0', '', 210, '2020-03-21 09:39:01', 'admin', '2020-03-21 09:39:01', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (16, '编辑', 14, '2', b'0', '', '', 'dept:edit', b'0', '', 220, '2020-03-21 09:39:22', 'admin', '2020-03-21 09:39:22', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (17, '删除', 14, '2', b'0', '', '', 'dept:delete', b'0', '', 230, '2020-03-21 09:39:42', 'admin', '2020-03-21 09:39:42', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (18, '字典管理', 1, '1', b'0', '/dict', 'sys/dict/index', '', b'0', 'dictionary', 30, '2020-03-21 14:49:56', 'admin', '2020-05-02 09:36:03', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (19, '新增', 18, '2', b'0', '', '', 'dict:add', b'0', '', 10, '2020-03-21 14:50:31', 'admin', '2020-03-21 14:50:31', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (20, '编辑', 18, '2', b'0', '', '', 'dict:edit', b'0', '', 10, '2020-03-21 14:50:48', 'admin', '2020-03-21 14:50:48', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (21, '删除', 18, '2', b'0', '', '', 'dict:delete', b'0', '', 10, '2020-03-21 14:51:03', 'admin', '2020-03-21 14:51:03', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (22, '客户端管理', 1, '1', b'0', '/oauth', 'sys/oauth/index', '', b'0', 'oauthClient', 40, '2020-03-21 15:39:23', 'admin', '2020-05-02 09:36:10', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (23, '系统监控', 0, '0', b'0', 'monitor', 'Layout', '', b'0', 'sparkler', 10, '2020-03-21 21:37:44', 'admin', '2020-03-21 21:37:44', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (24, '接口文档', 23, '1', b'1', 'http://www.sparkplatform.cn:9001/swagger-ui.html', NULL, '', b'0', '', 20, '2020-03-21 21:39:27', 'admin', '2020-05-07 16:53:55', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (25, '注册中心', 23, '1', b'1', 'http://106.13.179.172:9000', NULL, '', b'0', '', 30, '2020-03-21 21:58:22', 'admin', '2020-05-07 16:54:40', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (26, 'Admin监控', 23, '1', b'1', 'http://www.sparkplatform.cn:9002', NULL, '', b'0', '', 40, '2020-03-22 14:12:05', 'admin', '2020-05-07 16:55:10', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (27, '系统日志', 23, '1', b'0', '/log', 'sys/log/index', '', b'0', '', 5, '2020-03-25 10:02:45', 'admin', '2020-05-02 09:37:16', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (28, '协同管理', 0, '0', b'0', 'act', 'Layout', '', b'0', 'act', 5, '2020-04-03 20:41:47', 'admin', '2020-05-07 16:58:13', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (29, '待办事项', 28, '1', b'0', '/tasks', 'act/tasks/index', '', b'0', 'guide', 10, '2020-04-06 14:47:36', 'admin', '2020-05-02 09:36:28', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (30, '已办事项', 28, '1', b'0', '/histasks', 'act/histasks/index', '', b'0', 'handle', 20, '2020-04-06 14:48:34', 'admin', '2020-05-02 09:36:34', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (31, '流程管理', 28, '1', b'0', '/process', 'act/process/index', '', b'0', 'model', 30, '2020-04-06 14:50:20', 'admin', '2020-05-02 09:36:41', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (32, '研发工具', 0, '0', b'0', 'wrench', 'Layout', '', b'0', 'wrench', 20, '2020-04-15 15:20:55', 'admin', '2020-05-03 11:11:28', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (33, '代码生成', 32, '1', b'0', '/code', 'sys/gen/index', '', b'0', 'code', 10, '2020-04-15 15:33:51', 'admin', '2020-05-02 09:37:32', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (34, '表单例子', 32, '1', b'0', '/form', 'form/index', '', b'0', 'edit', 20, '2020-04-16 11:22:02', 'admin', '2020-05-02 09:37:38', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (35, '文件管理', 1, '1', b'0', '/file', 'sys/file/index', '', b'0', '_file', 50, '2020-04-18 15:05:21', 'admin', '2020-05-02 09:36:18', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (36, '登录日志', 23, '1', b'0', '/login-log', 'sys/login-log/index', '', b'0', '', 10, '2020-04-18 23:05:07', 'admin', '2020-05-02 09:37:23', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (37, '新增', 22, '2', b'0', '', NULL, 'oauth:add', b'0', '', 10, '2020-04-26 11:37:24', 'admin', '2020-04-26 11:37:24', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (38, '编辑', 22, '2', b'0', '', NULL, 'oauth:edit', b'0', '', 10, '2020-04-26 11:37:48', 'admin', '2020-04-26 11:37:48', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (39, '删除', 22, '2', b'0', '', NULL, 'oauth:delete', b'0', '', 10, '2020-04-26 11:38:07', 'admin', '2020-04-26 11:38:07', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (40, '内容平台', 0, '0', b'0', 'cms', 'Layout', '', b'0', 'international', 9, '2020-05-03 11:12:52', 'admin', '2020-05-07 16:58:24', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (41, '文章列表', 40, '1', b'0', '/article-list', 'cms/article/index', '', b'0', 'loggers', 10, '2020-05-03 11:14:06', 'admin', '2020-05-03 11:14:18', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (42, '添加文章', 40, '1', b'0', '/article-create', 'cms/article/create', '', b'0', 'edit', 9, '2020-05-03 11:15:47', 'admin', '2020-05-04 14:55:08', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (43, '编辑文章', 40, '1', b'0', '/article-edit', 'cms/article/edit', '', b'1', '', 10, '2020-05-04 14:57:14', 'admin', '2020-05-06 11:33:34', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (44, '处理文章', 40, '1', b'0', '/article-task', 'cms/article/task', '', b'1', '', 16, '2020-05-04 17:13:16', 'admin', '2020-05-06 13:42:16', 'admin', NULL, b'0');
INSERT INTO `sys_menu` VALUES (45, '数据库监控', 23, '1', b'0', 'http://49.232.72.173:9020/druid', NULL, '', b'0', '', 50, '2020-05-07 16:57:11', 'admin', '2020-05-07 16:58:01', 'admin', NULL, b'0');

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `autoapprove` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户端表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES (1, 'spark-admin', NULL, 'spark-admin-secret', 'all,read,write', 'password,refresh_token,authorization_code,client_credentials', NULL, NULL, 21600, 28800, 'true');

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
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime(0) NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` int(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'role_admin', '超级管理员', 7, '研发部', NULL, '2020-03-19 22:54:10', 'admin', '2020-04-27 15:06:47', 'admin', NULL, 0);
INSERT INTO `sys_role` VALUES (2, '系统管理员', 'role_system', '我是', 7, '研发部', NULL, '2020-03-19 22:55:31', 'admin', '2020-04-27 15:06:54', 'admin', NULL, 0);
INSERT INTO `sys_role` VALUES (3, '测试角色', 'role_test', '', 2, NULL, NULL, '2020-03-19 22:56:06', 'admin', '2020-03-19 22:56:06', 'admin', NULL, 1);
INSERT INTO `sys_role` VALUES (4, '测试角色1', 'role_test1', '', 2, NULL, NULL, '2020-03-19 23:01:32', 'admin', '2020-03-19 23:01:32', 'admin', NULL, 1);
INSERT INTO `sys_role` VALUES (5, '文章审核组长', 'role_group_leader', '文章审核组长', 5, '文章一部', NULL, '2020-04-14 16:49:52', 'admin', '2020-04-14 16:50:19', 'admin', NULL, 0);
INSERT INTO `sys_role` VALUES (6, '主编', 'role_editor_manage', '主编', 5, '文章一部', NULL, '2020-04-14 16:55:45', 'admin', '2020-04-14 16:55:45', 'admin', NULL, 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(11) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 590 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (265, 5, 28);
INSERT INTO `sys_role_menu` VALUES (266, 5, 29);
INSERT INTO `sys_role_menu` VALUES (267, 5, 30);
INSERT INTO `sys_role_menu` VALUES (268, 6, 28);
INSERT INTO `sys_role_menu` VALUES (269, 6, 29);
INSERT INTO `sys_role_menu` VALUES (270, 6, 30);
INSERT INTO `sys_role_menu` VALUES (271, 2, 1);
INSERT INTO `sys_role_menu` VALUES (272, 2, 2);
INSERT INTO `sys_role_menu` VALUES (273, 2, 3);
INSERT INTO `sys_role_menu` VALUES (274, 2, 4);
INSERT INTO `sys_role_menu` VALUES (275, 2, 5);
INSERT INTO `sys_role_menu` VALUES (276, 2, 6);
INSERT INTO `sys_role_menu` VALUES (277, 2, 7);
INSERT INTO `sys_role_menu` VALUES (278, 2, 10);
INSERT INTO `sys_role_menu` VALUES (279, 2, 14);
INSERT INTO `sys_role_menu` VALUES (280, 2, 23);
INSERT INTO `sys_role_menu` VALUES (281, 2, 24);
INSERT INTO `sys_role_menu` VALUES (282, 2, 25);
INSERT INTO `sys_role_menu` VALUES (283, 2, 26);
INSERT INTO `sys_role_menu` VALUES (284, 2, 27);
INSERT INTO `sys_role_menu` VALUES (285, 2, 28);
INSERT INTO `sys_role_menu` VALUES (286, 2, 29);
INSERT INTO `sys_role_menu` VALUES (287, 2, 30);
INSERT INTO `sys_role_menu` VALUES (288, 2, 31);
INSERT INTO `sys_role_menu` VALUES (545, 1, 1);
INSERT INTO `sys_role_menu` VALUES (546, 1, 2);
INSERT INTO `sys_role_menu` VALUES (547, 1, 3);
INSERT INTO `sys_role_menu` VALUES (548, 1, 4);
INSERT INTO `sys_role_menu` VALUES (549, 1, 5);
INSERT INTO `sys_role_menu` VALUES (550, 1, 6);
INSERT INTO `sys_role_menu` VALUES (551, 1, 7);
INSERT INTO `sys_role_menu` VALUES (552, 1, 8);
INSERT INTO `sys_role_menu` VALUES (553, 1, 9);
INSERT INTO `sys_role_menu` VALUES (554, 1, 10);
INSERT INTO `sys_role_menu` VALUES (555, 1, 11);
INSERT INTO `sys_role_menu` VALUES (556, 1, 12);
INSERT INTO `sys_role_menu` VALUES (557, 1, 13);
INSERT INTO `sys_role_menu` VALUES (558, 1, 14);
INSERT INTO `sys_role_menu` VALUES (559, 1, 15);
INSERT INTO `sys_role_menu` VALUES (560, 1, 16);
INSERT INTO `sys_role_menu` VALUES (561, 1, 17);
INSERT INTO `sys_role_menu` VALUES (562, 1, 18);
INSERT INTO `sys_role_menu` VALUES (563, 1, 19);
INSERT INTO `sys_role_menu` VALUES (564, 1, 20);
INSERT INTO `sys_role_menu` VALUES (565, 1, 21);
INSERT INTO `sys_role_menu` VALUES (566, 1, 22);
INSERT INTO `sys_role_menu` VALUES (567, 1, 37);
INSERT INTO `sys_role_menu` VALUES (568, 1, 38);
INSERT INTO `sys_role_menu` VALUES (569, 1, 39);
INSERT INTO `sys_role_menu` VALUES (570, 1, 35);
INSERT INTO `sys_role_menu` VALUES (571, 1, 23);
INSERT INTO `sys_role_menu` VALUES (572, 1, 24);
INSERT INTO `sys_role_menu` VALUES (573, 1, 25);
INSERT INTO `sys_role_menu` VALUES (574, 1, 26);
INSERT INTO `sys_role_menu` VALUES (575, 1, 27);
INSERT INTO `sys_role_menu` VALUES (576, 1, 36);
INSERT INTO `sys_role_menu` VALUES (577, 1, 45);
INSERT INTO `sys_role_menu` VALUES (578, 1, 28);
INSERT INTO `sys_role_menu` VALUES (579, 1, 29);
INSERT INTO `sys_role_menu` VALUES (580, 1, 30);
INSERT INTO `sys_role_menu` VALUES (581, 1, 31);
INSERT INTO `sys_role_menu` VALUES (582, 1, 32);
INSERT INTO `sys_role_menu` VALUES (583, 1, 33);
INSERT INTO `sys_role_menu` VALUES (584, 1, 34);
INSERT INTO `sys_role_menu` VALUES (585, 1, 40);
INSERT INTO `sys_role_menu` VALUES (586, 1, 41);
INSERT INTO `sys_role_menu` VALUES (587, 1, 42);
INSERT INTO `sys_role_menu` VALUES (588, 1, 43);
INSERT INTO `sys_role_menu` VALUES (589, 1, 44);

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
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime(0) NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` int(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '超级管理员', '$2a$10$ts07XkBaX7OwC5xA449gh.MO1Sa3KfyJlcx./lZKkMEgP8XDSoR9e', 1, '123456789', '1234@qq.com', 'others/18.jpg', 7, '研发部', 1, '2020-03-19 14:35:18', 'admin', '2020-05-06 20:27:47', 'admin', '我是一个备注', 0);
INSERT INTO `sys_user` VALUES (7, 'system', '小王', '$2a$10$oFol0M7BlopTxvVYMos35uLwnF2g2YobjderyjY2srSmefuBoSXKG', 0, '123456789', '123@qq.com', 'others/14.jpg', 0, NULL, 1, '2020-03-19 17:09:31', 'admin', '2020-03-19 17:09:31', 'admin', NULL, 1);
INSERT INTO `sys_user` VALUES (8, 'tester', '测试用户', '$2a$10$dmBV0Cy0dh2bDzkCclF5yerWRRPHawU5O.MEYll9.r0IUlwZKUA7q', 0, '12345677', '123@qq.com', 'others/14.jpg', 6, '测试部', 1, '2020-03-19 20:22:18', 'admin', '2020-04-14 22:14:36', 'admin', '1232', 0);
INSERT INTO `sys_user` VALUES (9, 'test1', '测试用户1', '$2a$10$NsOBCqf3LOXiQxGWs/QkteofPQTfd2qBuAfG31Y6IJx5bsObLY4am', 0, '1231231231', '123@qq.com', 'others/14.jpg', NULL, NULL, 1, '2020-03-19 20:24:03', 'admin', '2020-03-19 20:24:03', 'admin', NULL, 1);
INSERT INTO `sys_user` VALUES (10, 'spark', '火花', '$2a$10$a6TPwO6wnXbouCwfowwb/.MqnfKgs3bxW3EwH7SUiUHeF4PfJYK7e', 1, '123123', '123@qq.com', 'others/14.jpg', 3, '研发一部', 1, '2020-03-19 20:31:04', 'admin', '2020-04-20 19:01:12', 'admin', '1', 0);
INSERT INTO `sys_user` VALUES (11, 'zhubian2', '主编2', '$2a$10$kXbLvWcO3i155u7pTbjR4uCabdytnKNKUMB4ozaLCu5htDzVVKH4u', 0, '1231232199', '123@qq.com', 'sunny/5.jpg', 5, '文章一部', 1, '2020-03-19 20:47:09', 'admin', '2020-04-14 22:14:23', 'admin', '主编', 0);
INSERT INTO `sys_user` VALUES (12, 'zhubian1', '主编1', '$2a$10$RtbN3jyBh5Zvd0H99PkYwuH6zmsMfN37bEZkyow78Dh/KTn0C4Ev.', 1, '1999999999', '12312312@qq.com', 'others/14.jpg', 5, '文章一部', 1, '2020-03-21 19:56:30', 'admin', '2020-04-14 17:28:40', 'admin', NULL, 0);
INSERT INTO `sys_user` VALUES (13, 'zuzhang', '组长', '$2a$10$DMb.Gx/GbX657UT6Z5/Lx.AOYRZhHsK0/KWlflqxlNxhhoZmbAPz6', 0, '12312312323', '123555@qq.com', 'others/1.jpg', 5, '文章一部', 1, '2020-04-14 16:54:44', 'admin', '2020-04-14 17:28:33', 'admin', '组长', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) NULL DEFAULT NULL COMMENT '角色id',
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (18, 5, 13);
INSERT INTO `sys_user_role` VALUES (19, 6, 12);
INSERT INTO `sys_user_role` VALUES (21, 6, 11);
INSERT INTO `sys_user_role` VALUES (22, 2, 8);
INSERT INTO `sys_user_role` VALUES (26, 2, 10);
INSERT INTO `sys_user_role` VALUES (29, 1, 1);
INSERT INTO `sys_user_role` VALUES (30, 5, 1);


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
) ENGINE=InnoDB AUTO_INCREMENT=1258323580937121795 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='日志表请求日志表';


CREATE TABLE `sys_log_login` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) DEFAULT NULL COMMENT '登录用户账号',
  `system` varchar(128) DEFAULT NULL COMMENT '登录系统',
  `browser` varchar(128) DEFAULT NULL COMMENT '登录浏览器',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `location` varchar(128) DEFAULT NULL COMMENT '登录地点',
  `location_ip` varchar(128) DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='登录日志';

SET FOREIGN_KEY_CHECKS = 1;
