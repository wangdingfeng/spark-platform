/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : wangdingfeng.mysql.rds.aliyuncs.com:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 20/02/2021 15:00:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shop_coupon
-- ----------------------------
DROP TABLE IF EXISTS `shop_coupon`;
CREATE TABLE `shop_coupon`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优惠券名称',
  `coupon_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优惠券类型 新人券 现金券 满减券 折扣券',
  `denomination` decimal(10, 2) NULL DEFAULT NULL COMMENT '面额 优惠多少钱和打多少折',
  `fixed_denomination` decimal(10, 0) NULL DEFAULT NULL COMMENT '固定面额 即 大于多少开始优惠',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间 ',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `is_limited` tinyint(1) NULL DEFAULT 0 COMMENT '是否限量',
  `total` int(10) NULL DEFAULT NULL COMMENT '发放总数',
  `last_total` int(10) NULL DEFAULT 0 COMMENT '剩余总数',
  `status` tinyint(1) NOT NULL COMMENT '状态 0 未开始 1进行中 2已结束',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_coupon
-- ----------------------------
INSERT INTO `shop_coupon` VALUES (1, '新人优惠券', 'new', 30.00, 1, '2021-01-03 16:00:00', '2021-12-31 16:00:00', 0, 1, 0, 1, '2021-01-04 17:54:54', 'admin', '2021-01-25 22:05:07', 'admin', NULL, 0);
INSERT INTO `shop_coupon` VALUES (2, '9折优惠券,限量领取', 'discount', 9.00, NULL, '2021-01-11 16:00:00', '2021-06-29 16:00:00', 1, 100, 99, 1, '2021-01-12 16:28:08', 'admin', '2021-02-04 10:17:16', 'admin', NULL, 0);
INSERT INTO `shop_coupon` VALUES (3, '大促销满减券', 'fullRed', 20.00, 60, '2021-02-03 16:00:00', '2021-04-29 16:00:00', 1, 90, 90, 1, '2021-02-04 10:19:23', 'admin', '2021-02-04 10:19:23', 'admin', NULL, 0);

-- ----------------------------
-- Table structure for shop_coupon_user
-- ----------------------------
DROP TABLE IF EXISTS `shop_coupon_user`;
CREATE TABLE `shop_coupon_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `coupon_id` int(11) NULL DEFAULT NULL COMMENT '优惠券ID',
  `end_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `use_time` datetime NULL DEFAULT NULL COMMENT '使用时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态 0  未使用 1 已使用 2 已过期',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券发放记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_coupon_user
-- ----------------------------
INSERT INTO `shop_coupon_user` VALUES (4, 2, 1, '2021-12-31 16:00:00', NULL, 0, '2021-01-26 17:02:46', '2021-01-26 17:02:46');
INSERT INTO `shop_coupon_user` VALUES (5, 2, 2, '2021-06-29 16:00:00', NULL, 0, '2021-02-04 11:14:22', '2021-02-04 11:14:22');

-- ----------------------------
-- Table structure for shop_goods
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods`;
CREATE TABLE `shop_goods`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_sn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `category_ids` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品分类',
  `category_names` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品分类name',
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品关键词',
  `brief` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品简介',
  `home_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '首页图片',
  `unit` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `is_new` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否新品',
  `is_hot` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否人气推荐',
  `retail_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '零售价格',
  `wholesale_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '批发价格',
  `min_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '最小单价',
  `max_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '最大价格',
  `specs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格',
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `sort_order` smallint(4) NULL DEFAULT 100 COMMENT '排序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 0 待上架 1 上架 2 下架',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  `stock` int(7) NULL DEFAULT 0 COMMENT '库存',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '上架时间',
  `publisher` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上架人',
  `sale_num` int(10) NULL DEFAULT 0 COMMENT '销售件数',
  `activity` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动状态 0 正常 1秒杀 2 团购',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_goods
-- ----------------------------
INSERT INTO `shop_goods` VALUES (5, '10001', '赣南脐橙单果', '4,9,8,18', '生鲜,时令水果,柑桔橙柚,橙子', '精选', '汁水充盈，个头硕大', 'http://qliiucyce.hb-bkt.clouddn.com/5bbb875feb394cfbb52ceee48a5bac11.jpg', 'piece/pieces', '1', '1', 4.90, 4.00, 10.00, 20.00, '3', '<p><img src=\"http://qliiucyce.hb-bkt.clouddn.com/5615d313c4984ba3aa49de2b738595ca.jpeg\" alt=\"\" /><img src=\"http://qliiucyce.hb-bkt.clouddn.com/deebd562bb2945b7a6d6e959054335ea.jpeg\" alt=\"\" /></p>', 100, '1', '2021-01-10 19:03:09', 'admin', '2021-01-10 19:04:51', 'admin', NULL, 1, 186, '2021-01-10 19:04:51', 'admin', 100, '0');
INSERT INTO `shop_goods` VALUES (6, '001', '奶油草莓', '1,11', '甄选水果,葡提莓果', '精选', '奶油草莓', 'https://oss.xiapeiyi.com/shop/goods/b36a28567663433da35cb728170d9135.jpg', '1', '1', '1', 60.00, 60.00, 60.00, 0.00, '3', '<p>奶油草莓</p>', 100, '1', '2021-01-12 16:17:18', 'admin', '2021-01-23 09:29:25', 'admin', NULL, 0, 9999, '2021-01-23 09:29:25', 'admin', 100, '1');
INSERT INTO `shop_goods` VALUES (7, '002', '椰子', '1,16', '甄选水果,热带水果', '年货', '椰子', 'https://oss.xiapeiyi.com/shop/goods/7047c9be614a49949938e9c293065e0b.png', '1', '0', '0', 1.00, 1.00, 1.00, 0.00, '3', '<p>1</p>', 100, '1', '2021-01-12 16:19:26', 'admin', '2021-01-20 22:31:58', 'admin', NULL, 0, 1, '2021-01-20 14:00:34', 'admin', 100, '0');
INSERT INTO `shop_goods` VALUES (9, '003', '苹果', '1,12', '甄选水果,苹果梨焦', '年货', '苹果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', '1', '1', '1', 1.00, 1.00, 1.00, 0.00, '3', '<p>1</p>', 100, '1', '2021-01-12 16:22:10', 'admin', '2021-01-20 13:55:46', 'admin', NULL, 0, 1, '2021-01-20 13:55:46', 'admin', 100, '0');
INSERT INTO `shop_goods` VALUES (11, '004', '葡萄', '1,11', '甄选水果,葡提莓果', '年货', '葡萄', 'https://oss.xiapeiyi.com/shop/goods/a10e70b86fa04703b76663ef167050b7.png', '1', '1', '1', 10.00, 5.00, 1.00, 0.00, '3', '<p>1</p>', 100, '1', '2021-01-12 16:23:03', 'admin', '2021-01-28 22:30:01', 'admin', NULL, 0, 6, '2021-01-20 14:00:31', 'admin', 100, '2');
INSERT INTO `shop_goods` VALUES (12, '005', '111', '1,14', '甄选水果,桃李杏枣', '年货', '11', 'https://oss.xiapeiyi.com/shop/goods/6a47a2678e3149e5a05f1a6ed151b84e.png', '1', '1', '1', 1.00, 1.00, 1.00, 0.00, '3', '<p>1</p>', 100, '1', '2021-01-12 16:24:51', 'admin', '2021-01-22 20:58:03', 'admin', NULL, 0, 1, '2021-01-22 20:58:03', 'admin', 100, '0');
INSERT INTO `shop_goods` VALUES (13, NULL, '测试商品', '1,10', '甄选水果,柑桔橙柚', '精选', '这是一个测试标题这是一个测试标题这是一个测试标题这是一个测试标题', 'http://qliiucyce.hb-bkt.clouddn.com/86c9d2fff511415ea09f1a4e26085cb0.jpg', 'kg', '1', '1', 1.00, 1.00, 1.00, 0.00, '3', '<p>1111</p>', 100, '0', '2021-01-12 16:35:34', 'admin', '2021-01-12 16:37:19', 'admin', NULL, 1, 1, NULL, NULL, 100, '0');
INSERT INTO `shop_goods` VALUES (14, NULL, '进口香蕉', '1,12', '甄选水果,苹果梨焦', '精选', '香甜软糯', 'https://oss.xiapeiyi.com/shop/goods/f6c6f58b7aff47afa0225658a15e91de.jpeg', '1', '1', '1', 10.00, 9.00, 8.00, 16.00, '2', '<p><img src=\"https://oss.xiapeiyi.com/shop/goods/fe1d5cce84d04b9593635793b11c2b1b.jpg\" alt=\"图片\" width=\"1176\" height=\"935\" /></p>', 100, '1', '2021-01-21 23:15:41', 'admin', '2021-01-21 23:17:22', 'admin', NULL, 0, 200, '2021-01-21 23:17:22', 'admin', 100, '0');
INSERT INTO `shop_goods` VALUES (15, NULL, '红颜草莓', '1,11', '甄选水果,葡提莓果', '精选', '好吃不贵的红颜草莓', 'https://oss.xiapeiyi.com/shop/goods/53a94bfc73af4b4d951df1c120071d44.jpg', '1', '1', '1', 68.00, 60.00, 17.80, 30.00, '2', '<p>红颜草莓</p>', 100, '1', '2021-01-22 23:17:54', 'admin', '2021-01-22 23:30:57', 'admin', NULL, 0, 200, '2021-01-22 23:25:15', 'admin', 100, '1');
INSERT INTO `shop_goods` VALUES (16, NULL, '菲律宾凤梨', '1,16', '甄选水果,热带水果', '精选', '菲律宾凤梨', 'https://oss.xiapeiyi.com/shop/goods/b7ed8a9c75fa4e79b3e4946060175095.jpg', '1', '1', '1', 13.20, 13.00, 8.00, 16.00, '2', '<p>菲律宾凤梨</p>', 100, '1', '2021-01-22 23:22:10', 'admin', '2021-01-22 23:31:34', 'admin', NULL, 0, 200, '2021-01-22 23:25:10', 'admin', 100, '1');
INSERT INTO `shop_goods` VALUES (17, NULL, '泰国桂圆', '1,16', '甄选水果,热带水果', '精选', '泰国桂圆', 'https://oss.xiapeiyi.com/shop/goods/2bbfa72e3147474cad58c29e96a64751.jpg', '1', '0', '0', 9.90, 9.00, 10.00, 40.00, '3,1,2', '<p>泰国桂圆</p>', 100, '1', '2021-01-22 23:23:33', 'admin', '2021-01-23 19:27:52', 'admin', NULL, 0, 630, '2021-01-22 23:25:08', 'admin', 100, '0');
INSERT INTO `shop_goods` VALUES (18, NULL, '橘子春见(中)', '1,10', '甄选水果,柑桔橙柚', '精选', '橘子春见(中)', 'https://oss.xiapeiyi.com/shop/goods/e8e65446e3ff4ab78e7b85f7a456c12d.jpg', '1', '0', '0', 12.80, 12.00, 5.90, 0.00, '3', '<p>橘子春见(中)</p>', 100, '1', '2021-01-22 23:24:54', 'admin', '2021-01-22 23:26:59', 'admin', NULL, 0, 1000, '2021-01-22 23:25:02', 'admin', 100, '1');
INSERT INTO `shop_goods` VALUES (19, NULL, '石榴', '1,10', '甄选水果,柑桔橙柚', '石榴', '石榴', 'https://oss.xiapeiyi.com/shop/goods/c2750e72fbdd49b29f5571fec2cae608.jpeg', '1', '0', '0', 10.00, 10.00, 10.00, 0.00, '2', '<p>石榴</p>', 100, '1', '2021-01-26 13:23:55', 'admin', '2021-01-26 13:24:02', 'admin', NULL, 0, 10, '2021-01-26 13:24:02', 'admin', 100, '0');
INSERT INTO `shop_goods` VALUES (20, NULL, '牛油果', '1,16', '甄选水果,热带水果', '牛油果', '牛油果', 'https://oss.xiapeiyi.com/shop/goods/c354827057ea43dfadfcc45470592b58.jpeg', '1', '1', '1', 10.00, 10.00, 10.00, 0.00, '2', '<p>牛油果</p>', 100, '1', '2021-01-26 13:25:26', 'admin', '2021-01-26 13:25:26', 'admin', NULL, 0, 297, NULL, NULL, 100, '0');
INSERT INTO `shop_goods` VALUES (21, NULL, '荔枝', '1,16', '甄选水果,热带水果', '荔枝,团长推荐', '荔枝', 'https://oss.xiapeiyi.com/shop/goods/abafbd5bc7ab46a7b3c2c14e648e0700.jpg', '1', '1', '1', 10.00, 10.00, 10.00, 0.00, '2', '<p>荔枝</p>', 100, '1', '2021-01-26 13:26:21', 'admin', '2021-01-26 13:26:21', 'admin', NULL, 0, 198, NULL, NULL, 100, '0');
INSERT INTO `shop_goods` VALUES (22, NULL, '西柚', '1,10', '甄选水果,柑桔橙柚', '西柚', '西柚', 'https://oss.xiapeiyi.com/shop/goods/6b2d5ea301954df1b85b01aea793da1f.jpeg', '1', '1', '1', 10.00, 10.00, 10.00, 0.00, '2', '<p>西柚</p>', 100, '1', '2021-01-26 13:27:21', 'admin', '2021-01-26 13:27:21', 'admin', NULL, 0, 10, NULL, NULL, 100, '0');
INSERT INTO `shop_goods` VALUES (23, NULL, '杨桃', '1,14', '甄选水果,桃李杏枣', '杨桃', '杨桃', 'https://oss.xiapeiyi.com/shop/goods/53fdbec9ab9f45a4ac73ffbd0feb0488.jpg', '1', '1', '1', 10.00, 10.00, 10.00, 0.00, '2', '<p>杨桃</p>', 100, '1', '2021-01-26 13:28:38', 'admin', '2021-01-26 13:28:38', 'admin', NULL, 0, 10, NULL, NULL, 100, '0');
INSERT INTO `shop_goods` VALUES (24, NULL, '红毛丹', '1,16', '甄选水果,热带水果', '红毛丹', '红毛丹', 'https://oss.xiapeiyi.com/shop/goods/6950a33dcd324895b6fce5a13fabfa73.jpg', '1', '1', '1', 10.00, 10.00, 10.00, 0.00, '2', '<p>红毛丹</p>', 100, '1', '2021-01-26 13:29:35', 'admin', '2021-01-26 13:29:35', 'admin', NULL, 0, 10, NULL, NULL, 100, '0');
INSERT INTO `shop_goods` VALUES (25, NULL, '油桃', '1,14', '甄选水果,桃李杏枣', '油桃', '油桃', 'https://oss.xiapeiyi.com/shop/goods/d72db68393154b38b92bd03430fd5f42.jpeg', '1', '1', '1', 10.00, 10.00, 10.00, 0.00, '3', '<p>油桃</p>', 100, '1', '2021-01-26 13:30:21', 'admin', '2021-01-26 13:30:21', 'admin', NULL, 0, 10, NULL, NULL, 100, '0');
INSERT INTO `shop_goods` VALUES (26, NULL, '苹果', '1,12', '甄选水果,苹果梨焦', '苹果,精选,当季畅销,年货精选', '苹果', 'https://oss.xiapeiyi.com/shop/goods/fc68f2a0e9cb439dab7714e992a3e0d2.jpeg', '1', '1', '1', 10.00, 10.00, 10.00, 0.00, '3', '<p>苹果</p>', 100, '1', '2021-01-26 13:31:12', 'admin', '2021-01-26 13:31:12', 'admin', NULL, 0, 10, NULL, NULL, 100, '0');
INSERT INTO `shop_goods` VALUES (27, NULL, '百香果', '1,16', '甄选水果,热带水果', '百香果,团长推荐', '百香果', 'https://oss.xiapeiyi.com/shop/goods/d57444e852524af895bfeed4c2812969.jpeg', '1', '1', '1', 10.00, 11.00, 10.00, 0.00, '3', '<p>百香果</p>', 100, '1', '2021-01-26 13:32:43', 'admin', '2021-01-26 13:32:43', 'admin', NULL, 0, 20, NULL, NULL, 100, '0');
INSERT INTO `shop_goods` VALUES (28, NULL, '杏子', '1,14', '甄选水果,桃李杏枣', '杏子,团长推荐', '杏子', 'https://oss.xiapeiyi.com/shop/goods/fe9a0a8bc8a24039a1095a1af0968b6f.jpeg', '1', '1', '1', 1.00, 1.00, 1.00, 0.00, '3', '<p>杏子</p>', 100, '1', '2021-01-26 13:33:28', 'admin', '2021-01-26 13:33:28', 'admin', NULL, 0, 1, NULL, NULL, 100, '0');

-- ----------------------------
-- Table structure for shop_goods_attr
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods_attr`;
CREATE TABLE `shop_goods_attr`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `attr_id` int(11) NOT NULL COMMENT '属性ID',
  `goods_id` int(11) NOT NULL COMMENT '商品主键',
  `attr_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 131 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_goods_attr
-- ----------------------------
INSERT INTO `shop_goods_attr` VALUES (1, 1, 1, '颜色');
INSERT INTO `shop_goods_attr` VALUES (47, 3, 3, '重量');
INSERT INTO `shop_goods_attr` VALUES (60, 1, 2, '颜色');
INSERT INTO `shop_goods_attr` VALUES (61, 2, 2, '尺码');
INSERT INTO `shop_goods_attr` VALUES (62, 1, 4, '颜色');
INSERT INTO `shop_goods_attr` VALUES (63, 2, 4, '尺码');
INSERT INTO `shop_goods_attr` VALUES (65, 3, 5, '重量');
INSERT INTO `shop_goods_attr` VALUES (69, 3, 8, '重量');
INSERT INTO `shop_goods_attr` VALUES (74, 3, 10, '重量');
INSERT INTO `shop_goods_attr` VALUES (81, 3, 13, '重量');
INSERT INTO `shop_goods_attr` VALUES (89, 3, 12, '重量');
INSERT INTO `shop_goods_attr` VALUES (94, 3, 9, '重量');
INSERT INTO `shop_goods_attr` VALUES (101, 3, 7, '重量');
INSERT INTO `shop_goods_attr` VALUES (105, 3, 11, '重量');
INSERT INTO `shop_goods_attr` VALUES (107, 2, 14, '件数');
INSERT INTO `shop_goods_attr` VALUES (108, 2, 15, '件数');
INSERT INTO `shop_goods_attr` VALUES (109, 2, 16, '件数');
INSERT INTO `shop_goods_attr` VALUES (111, 3, 18, '重量');
INSERT INTO `shop_goods_attr` VALUES (112, 3, 6, '重量');
INSERT INTO `shop_goods_attr` VALUES (117, 3, 17, '重量');
INSERT INTO `shop_goods_attr` VALUES (118, 1, 17, '颜色');
INSERT INTO `shop_goods_attr` VALUES (119, 2, 17, '件数');
INSERT INTO `shop_goods_attr` VALUES (120, 2, 19, '件数');
INSERT INTO `shop_goods_attr` VALUES (121, 2, 20, '件数');
INSERT INTO `shop_goods_attr` VALUES (122, 2, 21, '件数');
INSERT INTO `shop_goods_attr` VALUES (123, 2, 22, '件数');
INSERT INTO `shop_goods_attr` VALUES (124, 2, 23, '件数');
INSERT INTO `shop_goods_attr` VALUES (125, 2, 24, '件数');
INSERT INTO `shop_goods_attr` VALUES (126, 3, 25, '重量');
INSERT INTO `shop_goods_attr` VALUES (127, 3, 26, '重量');
INSERT INTO `shop_goods_attr` VALUES (128, 2, 27, '件数');
INSERT INTO `shop_goods_attr` VALUES (129, 3, 27, '重量');
INSERT INTO `shop_goods_attr` VALUES (130, 3, 28, '重量');

-- ----------------------------
-- Table structure for shop_goods_attr_val
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods_attr_val`;
CREATE TABLE `shop_goods_attr_val`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `attr_id` int(11) NOT NULL COMMENT '属性ID',
  `attr_val_id` int(11) NOT NULL COMMENT '主键',
  `attr_val` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性值',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品ID',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 237 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_goods_attr_val
-- ----------------------------
INSERT INTO `shop_goods_attr_val` VALUES (1, 1, 1, '蓝色', 1, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (93, 3, 10, '1KG', 3, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (94, 3, 11, '2KG', 3, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (95, 3, 12, '4KG', 3, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (121, 1, 1, '蓝色', 2, 'http://qliiucyce.hb-bkt.clouddn.com/020e38b6c8324d1e84189031cfee9474.jpg');
INSERT INTO `shop_goods_attr_val` VALUES (122, 1, 2, '红色', 2, 'http://qliiucyce.hb-bkt.clouddn.com/f4945f46b70f416caa5191fba7db9208.jpg');
INSERT INTO `shop_goods_attr_val` VALUES (123, 2, 4, 'XS', 2, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (124, 2, 5, 'S', 2, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (125, 2, 7, 'L', 2, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (126, 1, 2, '红色', 4, 'http://qliiucyce.hb-bkt.clouddn.com/8987121841a84531b170c22a16d68e1b.jpg');
INSERT INTO `shop_goods_attr_val` VALUES (127, 2, 4, 'XS', 4, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (128, 2, 5, 'S', 4, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (129, 2, 6, 'M', 4, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (132, 3, 10, '1KG', 5, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (133, 3, 11, '2KG', 5, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (137, 3, 10, '1KG', 8, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (142, 3, 10, '1KG', 10, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (143, 3, 11, '2KG', 10, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (144, 3, 12, '4KG', 10, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (153, 3, 11, '2KG', 13, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (161, 3, 10, '1KG', 12, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (168, 3, 10, '1KG', 9, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (177, 3, 10, '1KG', 7, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (187, 3, 10, '1KG', 11, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (188, 3, 11, '2KG', 11, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (189, 3, 12, '4KG', 11, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (192, 2, 4, '1', 14, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (193, 2, 5, '2', 14, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (194, 2, 4, '1', 15, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (195, 2, 5, '2', 15, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (196, 2, 4, '1', 16, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (197, 2, 5, '2', 16, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (199, 3, 10, '1KG', 18, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (200, 3, 10, '1KG', 6, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (213, 3, 10, '1KG', 17, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (214, 3, 11, '2KG', 17, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (215, 3, 12, '4KG', 17, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (216, 1, 1, '蓝色', 17, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (217, 1, 2, '红色', 17, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (218, 1, 3, '灰色', 17, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (219, 2, 4, '1', 17, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (220, 2, 5, '2', 17, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (221, 2, 6, '3', 17, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (222, 2, 4, '1', 19, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (223, 2, 4, '1', 20, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (224, 2, 5, '2', 20, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (225, 2, 6, '3', 20, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (226, 2, 4, '1', 21, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (227, 2, 5, '2', 21, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (228, 2, 4, '1', 22, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (229, 2, 4, '1', 23, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (230, 2, 4, '1', 24, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (231, 3, 10, '1KG', 25, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (232, 3, 10, '1KG', 26, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (233, 2, 5, '2', 27, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (234, 3, 10, '1KG', 27, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (235, 3, 11, '2KG', 27, NULL);
INSERT INTO `shop_goods_attr_val` VALUES (236, 3, 10, '1KG', 28, NULL);

-- ----------------------------
-- Table structure for shop_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods_category`;
CREATE TABLE `shop_goods_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  `pids` varchar(122) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父ids',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类图',
  `sort` int(5) NULL DEFAULT NULL COMMENT '排序',
  `is_leaf` tinyint(1) NULL DEFAULT 1 COMMENT '是否最末级',
  `level` smallint(2) NULL DEFAULT 1 COMMENT '层次级别',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_goods_category
-- ----------------------------
INSERT INTO `shop_goods_category` VALUES (1, 0, '0', '甄选水果', 'https://oss.xiapeiyi.com/shop/category/80e9730caac648fdb81b1b13fecf441b.png', 1, 0, 1, '2021-01-12 15:56:31', 'admin', '2021-01-26 13:41:52', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (2, 0, '0', '新鲜蔬菜', 'http://49.232.72.173:9000/shop/category/aaac48ba6f19482aa802899cf5b1352b.png', 1, 1, 1, '2021-01-12 15:57:00', 'admin', '2021-01-20 18:00:31', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (3, 0, '0', '海鲜水产', 'http://49.232.72.173:9000/shop/category/1186d6b481b648a88082f641ff3c6a4c.png', 1, 1, 1, '2021-01-12 15:57:12', 'admin', '2021-01-20 18:00:46', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (4, 0, '0', '冷冻副食', 'http://49.232.72.173:9000/shop/category/0dd9beba0397459e97a04b90ae7907db.png', 1, 1, 1, '2021-01-12 15:57:30', 'admin', '2021-01-20 18:02:20', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (5, 0, '0', '肉蛋禽类', 'https://oss.xiapeiyi.com/shop/category/f1f1b7d6c07d4c7c858f18122ac94fe9.png', 1, 1, 1, '2021-01-12 15:57:53', 'admin', '2021-01-26 13:41:31', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (6, 0, '0', '粮油调味', 'http://49.232.72.173:9000/shop/category/37e82af7884d4a6eaa50dc769cb8a9b0.png', 1, 1, 1, '2021-01-12 15:58:10', 'admin', '2021-01-20 18:01:41', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (7, 0, '0', '休闲零食', 'https://oss.xiapeiyi.com/shop/category/fe08f82735bd415aaa57d2c5a24ef78d.png', 1, 1, 1, '2021-01-12 15:58:20', 'admin', '2021-01-26 13:41:16', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (8, 0, '0', '乳品烘焙', 'https://oss.xiapeiyi.com/shop/category/78abd4ab4abb457284070673305a0042.png', 1, 1, 1, '2021-01-12 15:58:43', 'admin', '2021-01-26 13:41:04', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (9, 0, '0', '方便速食', 'http://49.232.72.173:9000/shop/category/d4baa502c973426092f44ef9224ec9a7.png', 1, 1, 1, '2021-01-12 15:59:14', 'admin', '2021-01-20 18:02:08', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (10, 1, '0,1', '柑桔橙柚', 'http://49.232.72.173:9000/shop/category/f3a373c7579a4ee6afbc79874527d4db.png', 1, 1, 2, '2021-01-12 15:59:49', 'admin', '2021-01-20 17:59:21', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (11, 1, '0,1', '葡提莓果', 'http://49.232.72.173:9000/shop/category/a6092b59ad7e4b7daca4e2f6d1614c9e.png', 1, 1, 2, '2021-01-12 16:00:20', 'admin', '2021-01-20 17:59:30', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (12, 1, '0,1', '苹果梨焦', 'http://49.232.72.173:9000/shop/category/8698f9c2da724d3ba45df01c5dbe4b22.png', 1, 1, 2, '2021-01-12 16:00:41', 'admin', '2021-01-20 17:59:39', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (13, 1, '0,1', '猕猴桃', 'http://49.232.72.173:9000/shop/category/52fdf8fd660f4057a64d1ac72e2f7a9e.png', 1, 1, 2, '2021-01-12 16:00:59', 'admin', '2021-01-20 17:59:48', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (14, 1, '0,1', '桃李杏枣', 'http://49.232.72.173:9000/shop/category/e50ada86bfd64eefa68a86fb27d0bee1.png', 1, 1, 2, '2021-01-12 16:01:14', 'admin', '2021-01-20 17:59:56', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (15, 1, '0,1', '西瓜/蜜瓜', 'http://49.232.72.173:9000/shop/category/81ec0dfc94c14772b19e3e08919d1d4b.png', 1, 1, 2, '2021-01-12 16:01:30', 'admin', '2021-01-20 18:00:03', 'admin', NULL, 0);
INSERT INTO `shop_goods_category` VALUES (16, 1, '0,1', '热带水果', 'https://oss.xiapeiyi.com/shop/category/3a8b3ce3959b4e67bfe9ff54d50b2498.png', 1, 1, 2, '2021-01-12 16:01:42', 'admin', '2021-01-26 13:40:45', 'admin', NULL, 0);

-- ----------------------------
-- Table structure for shop_goods_comment
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods_comment`;
CREATE TABLE `shop_goods_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单ID',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `star` smallint(1) NULL DEFAULT NULL COMMENT '描述相符',
  `pic` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品评价' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_goods_comment
-- ----------------------------
INSERT INTO `shop_goods_comment` VALUES (1, 2, 1, NULL, '商品非常满意', 5, NULL, '2020-12-18 22:51:31', 0);

-- ----------------------------
-- Table structure for shop_goods_gallery
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods_gallery`;
CREATE TABLE `shop_goods_gallery`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品主键',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `sort` int(1) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` int(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品主图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_goods_gallery
-- ----------------------------
INSERT INTO `shop_goods_gallery` VALUES (1, 2, 'http://127.0.0.1:9090/file/biz\\2020-12-19\\ee0d8636736e4a82858aa733c0099abc.jpg', NULL, '2020-12-19 11:44:58', 'admin', '2020-12-21 15:16:10', 'admin', NULL, 1);
INSERT INTO `shop_goods_gallery` VALUES (2, 2, 'http://127.0.0.1:9090/file/biz\\2020-12-19\\3ab4e7f36aee4e7d8f397eeaaf3eaa69.jpg', NULL, '2020-12-19 11:44:58', 'admin', '2020-12-21 15:16:10', 'admin', NULL, 1);
INSERT INTO `shop_goods_gallery` VALUES (3, 2, 'http://127.0.0.1:9090/file/biz\\2020-12-19\\9a8790ea921e4f0c9467c6692ad20983.jpg', NULL, '2020-12-19 11:44:58', 'admin', '2020-12-21 15:16:11', 'admin', NULL, 1);
INSERT INTO `shop_goods_gallery` VALUES (4, 2, 'http://127.0.0.1:9090/file/biz\\2020-12-19\\105d715c480c4b6fadf435de0d074928.jpg', NULL, '2020-12-19 11:44:58', 'admin', '2020-12-21 15:16:11', 'admin', NULL, 1);
INSERT INTO `shop_goods_gallery` VALUES (5, 2, 'http://127.0.0.1:9090/file/biz\\2020-12-19\\ddfeac2c1dd74e6c97ecac965bdd12b0.jpg', NULL, '2020-12-19 11:44:58', 'admin', '2020-12-21 15:16:11', 'admin', NULL, 1);
INSERT INTO `shop_goods_gallery` VALUES (6, 2, 'http://127.0.0.1:9090/file/biz\\2020-12-19\\216eb3e00c184a5e87e669ab51d1b855.jpg', NULL, '2020-12-19 11:44:58', 'admin', '2020-12-21 15:16:11', 'admin', NULL, 1);
INSERT INTO `shop_goods_gallery` VALUES (7, 3, 'http://127.0.0.1:9090/file/biz\\2020-12-19\\046ed4fa4f3541dda25b7a4c3531079a.jpg', NULL, '2020-12-19 14:59:58', 'admin', '2020-12-19 17:01:11', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (8, 3, 'http://127.0.0.1:9090/file/biz\\2020-12-19\\59e3644268f54a77985802a6e191dd5c.jpg', NULL, '2020-12-19 15:01:25', 'admin', '2020-12-19 17:01:11', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (9, 4, 'http://qliiucyce.hb-bkt.clouddn.com/4d18338896ad44d2af8a0687b871818d.jpg', NULL, '2020-12-19 16:02:18', 'admin', '2021-01-06 17:21:11', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (10, 4, 'http://qliiucyce.hb-bkt.clouddn.com/61afd39d0bf745af9b8e9e0a7c3ee5be.jpg', NULL, '2020-12-19 16:02:18', 'admin', '2021-01-06 17:21:11', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (11, 4, 'http://qliiucyce.hb-bkt.clouddn.com/1d6209c57faf4bfb9e1b2266123885dc.jpg', NULL, '2020-12-19 16:02:18', 'admin', '2021-01-06 17:21:11', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (12, 4, 'http://qliiucyce.hb-bkt.clouddn.com/feb06b9316734b92b45096576031f0b8.jpg', NULL, '2020-12-19 16:02:18', 'admin', '2021-01-06 17:21:11', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (13, 4, 'http://qliiucyce.hb-bkt.clouddn.com/5dcf7cef256747098235f0e6c5d97177.jpg', NULL, '2020-12-19 16:02:18', 'admin', '2021-01-06 17:21:11', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (14, 4, 'http://qliiucyce.hb-bkt.clouddn.com/886b234ec1d44c31a1b7378740e4492d.jpg', NULL, '2020-12-19 16:02:18', 'admin', '2021-01-06 17:21:11', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (15, 3, 'http://qliiucyce.hb-bkt.clouddn.com/5f22a4db2e0e406295fd70b48400f9d2.jpg', NULL, '2020-12-19 16:38:33', 'admin', '2020-12-19 17:01:11', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (16, 3, 'http://qliiucyce.hb-bkt.clouddn.com/27db7b3a8d214313b9ef7ec6dc1894d0.jpg', NULL, '2020-12-19 16:38:33', 'admin', '2020-12-19 17:01:11', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (17, 2, 'http://qliiucyce.hb-bkt.clouddn.com/052d625a53a54378820b420783cdd2b7.jpeg', NULL, '2020-12-19 21:30:48', 'admin', '2021-01-06 17:20:07', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (18, 2, 'http://qliiucyce.hb-bkt.clouddn.com/012b4661a15e419f8b6fc955f95e38d1.jpeg', NULL, '2020-12-19 21:30:48', 'admin', '2021-01-06 17:20:07', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (19, 2, 'http://qliiucyce.hb-bkt.clouddn.com/2be347368c3d485ab08de411404b4d26.jpg', NULL, '2020-12-19 21:30:48', 'admin', '2021-01-06 17:20:07', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (20, 2, 'http://qliiucyce.hb-bkt.clouddn.com/7d7210ebbca14b1587d14ec6eca7c2dd.JPG', NULL, '2020-12-19 21:30:48', 'admin', '2021-01-06 17:20:07', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (21, 2, 'http://qliiucyce.hb-bkt.clouddn.com/695981413449411d934d2ccf358d3bc2.jpg', NULL, '2020-12-19 21:30:48', 'admin', '2021-01-06 17:20:07', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (22, 2, 'http://qliiucyce.hb-bkt.clouddn.com/b63c1607d3da40aaba6746a978d3aa98.jpg', NULL, '2020-12-19 21:30:48', 'admin', '2021-01-06 17:20:07', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (23, 5, 'http://qliiucyce.hb-bkt.clouddn.com/5615d313c4984ba3aa49de2b738595ca.jpeg', NULL, '2021-01-10 19:03:10', 'admin', '2021-01-10 19:04:23', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (24, 5, 'http://qliiucyce.hb-bkt.clouddn.com/deebd562bb2945b7a6d6e959054335ea.jpeg', NULL, '2021-01-10 19:03:10', 'admin', '2021-01-10 19:04:24', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (25, 6, 'http://qliiucyce.hb-bkt.clouddn.com/d30a17ffe9124680be8c1ad9a3114854.jpg', NULL, '2021-01-12 16:17:18', 'admin', '2021-01-20 13:51:16', 'admin', NULL, 1);
INSERT INTO `shop_goods_gallery` VALUES (26, 7, 'http://qliiucyce.hb-bkt.clouddn.com/ceb2e79a749c4db9ac80ae8bacc4bef5.png', NULL, '2021-01-12 16:19:26', 'admin', '2021-01-20 13:50:49', 'admin', NULL, 1);
INSERT INTO `shop_goods_gallery` VALUES (27, 8, 'http://qliiucyce.hb-bkt.clouddn.com/ceb2e79a749c4db9ac80ae8bacc4bef5.png', NULL, '2021-01-12 16:19:27', 'admin', '2021-01-12 16:19:27', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (28, 13, 'http://qliiucyce.hb-bkt.clouddn.com/d516fee63b3b436da2e146a7d02682fe.jpg', NULL, '2021-01-12 16:35:34', 'admin', '2021-01-12 16:37:19', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (29, 13, 'http://qliiucyce.hb-bkt.clouddn.com/63d0d47481c04f188f682273d7c320eb.jpg', NULL, '2021-01-12 16:35:34', 'admin', '2021-01-12 16:37:19', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (30, 9, 'https://oss.xiapeiyi.com/shop/goods/65e1eee6e69a4aaf95b9cad873d151e8.png', NULL, '2021-01-20 13:55:39', 'admin', '2021-01-20 13:55:39', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (31, 9, 'https://oss.xiapeiyi.com/shop/goods/119728f04d8e4c1daafa1f08a1cad79b.png', NULL, '2021-01-20 13:55:39', 'admin', '2021-01-20 13:55:39', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (32, 6, 'https://oss.xiapeiyi.com/shop/goods/d8a3743f0d264c269b238b0e72812556.png', NULL, '2021-01-20 13:57:38', 'admin', '2021-01-23 09:27:56', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (33, 6, 'https://oss.xiapeiyi.com/shop/goods/ecd2b34578c84ea78be7db21eed5092b.png', NULL, '2021-01-20 13:57:38', 'admin', '2021-01-23 09:27:56', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (34, 7, 'https://oss.xiapeiyi.com/shop/goods/a0e473b87aa44eb2b5198c45be41eedb.png', NULL, '2021-01-20 13:59:13', 'admin', '2021-01-20 22:31:58', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (35, 7, 'https://oss.xiapeiyi.com/shop/goods/2881dcc8ade147bc8eac9ea37ae8c398.png', NULL, '2021-01-20 13:59:13', 'admin', '2021-01-20 22:31:58', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (36, 11, 'https://oss.xiapeiyi.com/shop/goods/d22df87b1bb2461b89249934e9c3f04c.png', NULL, '2021-01-20 14:00:23', 'admin', '2021-01-20 22:47:26', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (37, 11, 'https://oss.xiapeiyi.com/shop/goods/955a1b8eaa8845639a2e2d21d3633484.png', NULL, '2021-01-20 14:00:23', 'admin', '2021-01-20 22:47:26', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (38, 14, 'https://oss.xiapeiyi.com/shop/goods/0bf8966f14bc460aa01f5e1f16ad8310.jpeg', NULL, '2021-01-21 23:15:41', 'admin', '2021-01-21 23:17:16', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (39, 14, 'https://oss.xiapeiyi.com/shop/goods/fe1d5cce84d04b9593635793b11c2b1b.jpg', NULL, '2021-01-21 23:15:41', 'admin', '2021-01-21 23:17:16', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (40, 15, 'https://oss.xiapeiyi.com/shop/goods/9d793fa460f94b298da0186942e14a5c.jpg', NULL, '2021-01-22 23:17:54', 'admin', '2021-01-22 23:17:54', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (41, 16, 'https://oss.xiapeiyi.com/shop/goods/86cedf1ced42476cb964a01e55d578c4.jpg', NULL, '2021-01-22 23:22:10', 'admin', '2021-01-22 23:22:10', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (42, 17, 'https://oss.xiapeiyi.com/shop/goods/5f0574ddfa5c499db7170f13645deb17.jpg', NULL, '2021-01-22 23:23:33', 'admin', '2021-01-23 19:27:52', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (43, 18, 'https://oss.xiapeiyi.com/shop/goods/ffb9de4d8a65470a8e16cbadc779eb46.jpg', NULL, '2021-01-22 23:24:54', 'admin', '2021-01-22 23:24:54', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (44, 19, 'https://oss.xiapeiyi.com/shop/goods/7b910d6c870445bebc3cf7b6ba5dcf0e.jpeg', NULL, '2021-01-26 13:23:55', 'admin', '2021-01-26 13:23:55', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (45, 20, 'https://oss.xiapeiyi.com/shop/goods/5ac9652253b7437fb377b208266df577.jpeg', NULL, '2021-01-26 13:25:26', 'admin', '2021-01-26 13:25:26', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (46, 21, 'https://oss.xiapeiyi.com/shop/goods/063157e95d564270bdfa79d84db91a92.jpg', NULL, '2021-01-26 13:26:21', 'admin', '2021-01-26 13:26:21', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (47, 22, 'https://oss.xiapeiyi.com/shop/goods/40f715265abe4b94a3abd163a956b744.jpeg', NULL, '2021-01-26 13:27:21', 'admin', '2021-01-26 13:27:21', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (48, 23, 'https://oss.xiapeiyi.com/shop/goods/062006dc25dc47e489ca602177b999a0.jpg', NULL, '2021-01-26 13:28:38', 'admin', '2021-01-26 13:28:38', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (49, 24, 'https://oss.xiapeiyi.com/shop/goods/8763fde7a15c46428535b8db97ee8021.jpg', NULL, '2021-01-26 13:29:35', 'admin', '2021-01-26 13:29:35', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (50, 25, 'https://oss.xiapeiyi.com/shop/goods/3aeedb0b2c50445588640a25c6dcab9f.jpeg', NULL, '2021-01-26 13:30:21', 'admin', '2021-01-26 13:30:21', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (51, 26, 'https://oss.xiapeiyi.com/shop/goods/4a544d33c6364e33b5d1c6126501115b.jpeg', NULL, '2021-01-26 13:31:12', 'admin', '2021-01-26 13:31:12', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (52, 27, 'https://oss.xiapeiyi.com/shop/goods/fe989374dee34c35b36b364c2bb57de5.jpeg', NULL, '2021-01-26 13:32:43', 'admin', '2021-01-26 13:32:43', 'admin', NULL, 0);
INSERT INTO `shop_goods_gallery` VALUES (53, 28, 'https://oss.xiapeiyi.com/shop/goods/6c0ea6c83cc44e1c9c6da015fcdd025f.jpeg', NULL, '2021-01-26 13:33:28', 'admin', '2021-01-26 13:33:28', 'admin', NULL, 0);

-- ----------------------------
-- Table structure for shop_goods_param
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods_param`;
CREATE TABLE `shop_goods_param`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品ID',
  `param_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数名',
  `param_value` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` int(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品产品参数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_goods_param
-- ----------------------------
INSERT INTO `shop_goods_param` VALUES (1, 2, '品牌', 'spark', '2020-12-18 22:03:21', 'admin', '2020-12-18 22:03:21', 'admin', NULL, 1);
INSERT INTO `shop_goods_param` VALUES (2, 2, '质地', '柔软', '2020-12-18 22:03:21', 'admin', '2021-01-06 17:20:07', 'admin', NULL, 0);
INSERT INTO `shop_goods_param` VALUES (3, 4, '1', '1', '2020-12-19 16:02:19', 'admin', '2021-01-06 17:21:11', 'admin', NULL, 0);
INSERT INTO `shop_goods_param` VALUES (4, 5, '品牌', '每日优鲜', '2021-01-10 19:03:10', 'admin', '2021-01-10 19:04:24', 'admin', NULL, 0);
INSERT INTO `shop_goods_param` VALUES (5, 9, '1斤', '10', '2021-01-12 16:22:11', 'admin', '2021-01-20 13:55:39', 'admin', NULL, 0);
INSERT INTO `shop_goods_param` VALUES (6, 9, '2斤', '20', '2021-01-12 16:22:11', 'admin', '2021-01-20 13:55:39', 'admin', NULL, 0);
INSERT INTO `shop_goods_param` VALUES (7, 7, '成分', '椰子', '2021-01-20 22:30:59', 'admin', '2021-01-20 22:31:59', 'admin', NULL, 0);
INSERT INTO `shop_goods_param` VALUES (8, 11, '成分', '葡萄', '2021-01-20 22:33:30', 'admin', '2021-01-20 22:47:26', 'admin', NULL, 0);
INSERT INTO `shop_goods_param` VALUES (9, 14, '品牌', '自己运营', '2021-01-21 23:15:42', 'admin', '2021-01-21 23:17:16', 'admin', NULL, 0);

-- ----------------------------
-- Table structure for shop_goods_sku
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods_sku`;
CREATE TABLE `shop_goods_sku`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `attr_val_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性值搭配,逗号',
  `attr_vals` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性值name搭配,逗号',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `stock` int(10) NULL DEFAULT NULL COMMENT '库存',
  `sku_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编码',
  `goods_id` int(11) NOT NULL COMMENT '商品主键',
  `activity_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '活动价格',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 236 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性搭配' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_goods_sku
-- ----------------------------
INSERT INTO `shop_goods_sku` VALUES (1, '1', NULL, 1.00, 1, '1', 1, 0.00);
INSERT INTO `shop_goods_sku` VALUES (81, '10', '1KG', 1.00, 4444, NULL, 3, 0.00);
INSERT INTO `shop_goods_sku` VALUES (82, '11', '2KG', 2.00, 4444, NULL, 3, 0.00);
INSERT INTO `shop_goods_sku` VALUES (83, '12', '4KG', 4.00, 444, NULL, 3, 0.00);
INSERT INTO `shop_goods_sku` VALUES (109, '1:1,2:4', '蓝色,XS', 1.00, 1, '2', 2, 0.00);
INSERT INTO `shop_goods_sku` VALUES (110, '1:1,2:5', '蓝色,S', 1.00, 1, '2', 2, 0.00);
INSERT INTO `shop_goods_sku` VALUES (111, '1:1,2:7', '蓝色,L', 1.00, 1, '2', 2, 0.00);
INSERT INTO `shop_goods_sku` VALUES (112, '1:2,2:4', '红色,XS', 1.00, 1, '2', 2, 0.00);
INSERT INTO `shop_goods_sku` VALUES (113, '1:2,2:5', '红色,S', 1.00, 1, '2', 2, 0.00);
INSERT INTO `shop_goods_sku` VALUES (114, '1:2,2:7', '红色,L', 1.00, 1, '2', 2, 0.00);
INSERT INTO `shop_goods_sku` VALUES (115, '1:2,2:4', '红色,XS', 2.00, 2, NULL, 4, 1.00);
INSERT INTO `shop_goods_sku` VALUES (116, '1:2,2:5', '红色,S', 2.00, 2, NULL, 4, 1.00);
INSERT INTO `shop_goods_sku` VALUES (117, '1:2,2:6', '红色,M', 2.00, 2, NULL, 4, 1.00);
INSERT INTO `shop_goods_sku` VALUES (120, '3:10', '1KG', 10.00, 91, '', 5, 0.00);
INSERT INTO `shop_goods_sku` VALUES (121, '3:11', '2KG', 20.00, 98, NULL, 5, 0.00);
INSERT INTO `shop_goods_sku` VALUES (125, '3:10', '1KG', 1.00, 1, '1', 8, 0.00);
INSERT INTO `shop_goods_sku` VALUES (130, '3:10', '1KG', 1.00, 2, '2', 10, 0.00);
INSERT INTO `shop_goods_sku` VALUES (131, '3:11', '2KG', 1.00, 2, '2', 10, 0.00);
INSERT INTO `shop_goods_sku` VALUES (132, '3:12', '4KG', 1.00, 2, '2', 10, 0.00);
INSERT INTO `shop_goods_sku` VALUES (141, '3:11', '2KG', 1.00, 1, NULL, 13, 0.00);
INSERT INTO `shop_goods_sku` VALUES (149, '3:10', '1KG', 1.00, 1, '1', 12, 0.00);
INSERT INTO `shop_goods_sku` VALUES (156, '3:10', '1KG', 1.00, 1, '1', 9, 0.00);
INSERT INTO `shop_goods_sku` VALUES (165, '3:10', '1KG', 1.00, 1, '1', 7, 0.00);
INSERT INTO `shop_goods_sku` VALUES (175, '3:10', '1KG', 1.00, 2, '2', 11, 0.80);
INSERT INTO `shop_goods_sku` VALUES (176, '3:11', '2KG', 1.00, 2, '2', 11, 0.80);
INSERT INTO `shop_goods_sku` VALUES (177, '3:12', '4KG', 1.00, 2, '2', 11, 0.80);
INSERT INTO `shop_goods_sku` VALUES (180, '2:4', '1', 8.00, 100, NULL, 14, 0.00);
INSERT INTO `shop_goods_sku` VALUES (181, '2:5', '2', 16.00, 100, NULL, 14, 0.00);
INSERT INTO `shop_goods_sku` VALUES (182, '2:4', '1', 17.80, 100, NULL, 15, 15.80);
INSERT INTO `shop_goods_sku` VALUES (183, '2:5', '2', 30.00, 100, NULL, 15, 28.80);
INSERT INTO `shop_goods_sku` VALUES (184, '2:4', '1', 8.00, 100, NULL, 16, 7.00);
INSERT INTO `shop_goods_sku` VALUES (185, '2:5', '2', 16.00, 100, NULL, 16, 14.00);
INSERT INTO `shop_goods_sku` VALUES (187, '3:10', '1KG', 5.90, 1000, NULL, 18, 4.50);
INSERT INTO `shop_goods_sku` VALUES (188, '3:10', '1KG', 60.00, 9999, '001', 6, 55.00);
INSERT INTO `shop_goods_sku` VALUES (195, '3:10,1:1,2:4', '1KG,蓝色,1', 10.00, 10, '10', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (196, '3:10,1:1,2:5', '1KG,蓝色,2', 10.00, 10, '10', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (197, '3:10,1:1,2:6', '1KG,蓝色,3', 10.00, 10, '10', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (198, '3:10,1:2,2:4', '1KG,红色,1', 10.00, 10, '10', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (199, '3:10,1:2,2:5', '1KG,红色,2', 10.00, 10, '10', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (200, '3:10,1:2,2:6', '1KG,红色,3', 10.00, 10, '10', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (201, '3:10,1:3,2:4', '1KG,灰色,1', 10.00, 10, '10', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (202, '3:10,1:3,2:5', '1KG,灰色,2', 10.00, 10, '10', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (203, '3:10,1:3,2:6', '1KG,灰色,3', 10.00, 10, '10', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (204, '3:11,1:1,2:4', '2KG,蓝色,1', 20.00, 20, '20', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (205, '3:11,1:1,2:5', '2KG,蓝色,2', 20.00, 20, '20', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (206, '3:11,1:1,2:6', '2KG,蓝色,3', 20.00, 20, '20', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (207, '3:11,1:2,2:4', '2KG,红色,1', 20.00, 20, '20', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (208, '3:11,1:2,2:5', '2KG,红色,2', 20.00, 20, '20', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (209, '3:11,1:2,2:6', '2KG,红色,3', 20.00, 20, '20', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (210, '3:11,1:3,2:4', '2KG,灰色,1', 20.00, 20, '20', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (211, '3:11,1:3,2:5', '2KG,灰色,2', 20.00, 20, '20', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (212, '3:11,1:3,2:6', '2KG,灰色,3', 20.00, 20, '20', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (213, '3:12,1:1,2:4', '4KG,蓝色,1', 40.00, 40, '40', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (214, '3:12,1:1,2:5', '4KG,蓝色,2', 40.00, 40, '40', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (215, '3:12,1:1,2:6', '4KG,蓝色,3', 40.00, 40, '40', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (216, '3:12,1:2,2:4', '4KG,红色,1', 40.00, 40, '40', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (217, '3:12,1:2,2:5', '4KG,红色,2', 40.00, 40, '40', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (218, '3:12,1:2,2:6', '4KG,红色,3', 40.00, 40, '40', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (219, '3:12,1:3,2:4', '4KG,灰色,1', 40.00, 40, '40', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (220, '3:12,1:3,2:5', '4KG,灰色,2', 40.00, 40, '40', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (221, '3:12,1:3,2:6', '4KG,灰色,3', 40.00, 40, '40', 17, 0.00);
INSERT INTO `shop_goods_sku` VALUES (222, '2:4', '1', 10.00, 10, '', 19, 0.00);
INSERT INTO `shop_goods_sku` VALUES (223, '2:4', '1', 10.00, 99, NULL, 20, 0.00);
INSERT INTO `shop_goods_sku` VALUES (224, '2:5', '2', 10.00, 99, NULL, 20, 0.00);
INSERT INTO `shop_goods_sku` VALUES (225, '2:6', '3', 10.00, 99, NULL, 20, 0.00);
INSERT INTO `shop_goods_sku` VALUES (226, '2:4', '1', 10.00, 99, NULL, 21, 0.00);
INSERT INTO `shop_goods_sku` VALUES (227, '2:5', '2', 10.00, 99, NULL, 21, 0.00);
INSERT INTO `shop_goods_sku` VALUES (228, '2:4', '1', 10.00, 10, NULL, 22, 0.00);
INSERT INTO `shop_goods_sku` VALUES (229, '2:4', '1', 10.00, 10, NULL, 23, 0.00);
INSERT INTO `shop_goods_sku` VALUES (230, '2:4', '1', 10.00, 10, NULL, 24, 0.00);
INSERT INTO `shop_goods_sku` VALUES (231, '3:10', '1KG', 10.00, 10, NULL, 25, 0.00);
INSERT INTO `shop_goods_sku` VALUES (232, '3:10', '1KG', 10.00, 10, NULL, 26, 0.00);
INSERT INTO `shop_goods_sku` VALUES (233, '2:5,3:10', '2,1KG', 10.00, 10, NULL, 27, 0.00);
INSERT INTO `shop_goods_sku` VALUES (234, '2:5,3:11', '2,2KG', 10.00, 10, NULL, 27, 0.00);
INSERT INTO `shop_goods_sku` VALUES (235, '3:10', '1KG', 1.00, 1, NULL, 28, 0.00);

-- ----------------------------
-- Table structure for shop_order
-- ----------------------------
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_sn` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `user_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '会员ID',
  `order_type` tinyint(1) NULL DEFAULT NULL COMMENT '订单类型 0 普通订单 1 团购订单 2 秒杀订单',
  `order_status` smallint(1) NOT NULL DEFAULT 0 COMMENT '订单状态 0 待付款 1 已取消 2 已付款 3 已发货 4 用户确认收货 5 退款 6 完成',
  `shipping_status` smallint(1) NULL DEFAULT 0 COMMENT '发货状态 0 待发货 1 已发货 2 已收货 2 退货',
  `refund_status` smallint(1) NULL DEFAULT NULL COMMENT '退款状态 0 申请中 1 退款完成 2 拒绝退款',
  `biz_id` bigint(20) NULL DEFAULT NULL COMMENT '业务id 对应的是 团购列表ID 秒杀商品的ID',
  `consignee` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '收件人名称',
  `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '省',
  `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '市',
  `district` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '区',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '详细地址',
  `mobile` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '电话',
  `shipping_fee` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '运费',
  `pay_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '支付Name',
  `pay_id` tinyint(3) NOT NULL DEFAULT 0 COMMENT '支付ID',
  `actual_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '实际需要支付的金额',
  `order_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '订单总价',
  `goods_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品总价',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `confirm_time` datetime NULL DEFAULT NULL COMMENT '用户确认收货时间',
  `send_time` datetime NULL DEFAULT NULL COMMENT '发货时间',
  `complete_time` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `freight_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '配送费用',
  `coupon_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用的优惠券id',
  `coupon_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '优惠券价格',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '系统订单备注',
  `user_remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户备注',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_sn`(`order_sn`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `order_status`(`order_status`) USING BTREE,
  INDEX `shipping_status`(`shipping_status`) USING BTREE,
  INDEX `pay_id`(`pay_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_order
-- ----------------------------
INSERT INTO `shop_order` VALUES (2, '1348562110866657280', 2, 0, 3, 1, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:27:17', '2021-01-12 09:56:16');
INSERT INTO `shop_order` VALUES (3, '1348564262020321280', 2, 0, 4, 0, 2, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:35:50', '2021-01-12 17:46:44');
INSERT INTO `shop_order` VALUES (4, '1348564630129217536', 2, 0, 3, 1, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, '2021-01-14 15:28:36', NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:37:18', '2021-01-14 15:28:44');
INSERT INTO `shop_order` VALUES (5, '1348564632234758144', 2, 0, 1, 0, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:37:18', '2021-02-18 13:07:21');
INSERT INTO `shop_order` VALUES (6, '1348564633006510080', 2, 0, 5, 0, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:37:18', '2021-01-11 17:37:18');
INSERT INTO `shop_order` VALUES (7, '1348564633795039232', 2, 0, 1, 0, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:37:18', '2021-02-18 13:07:12');
INSERT INTO `shop_order` VALUES (8, '1348564634554208256', 2, 0, 0, 0, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:37:19', '2021-01-11 17:37:19');
INSERT INTO `shop_order` VALUES (9, '1348564635325960192', 2, 0, 0, 0, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:37:19', '2021-01-11 17:37:19');
INSERT INTO `shop_order` VALUES (10, '1348564636068352000', 2, 0, 0, 0, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:37:19', '2021-01-11 17:37:19');
INSERT INTO `shop_order` VALUES (11, '1348564636806549504', 2, 0, 0, 0, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:37:19', '2021-01-11 17:37:19');
INSERT INTO `shop_order` VALUES (12, '1348564637645410304', 2, 0, 0, 0, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:37:19', '2021-01-11 17:37:19');
INSERT INTO `shop_order` VALUES (13, '1348564638471688192', 2, 0, 0, 0, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 10.00, 10.00, 10.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:37:20', '2021-01-11 17:37:20');
INSERT INTO `shop_order` VALUES (14, '1348565153897123840', 2, 0, 0, 0, NULL, NULL, '王小峰', '浙江省', '杭州市', '江干区', '浙江省杭州市江干区彭埠街道', '18513262490', 0.00, '', 0, 20.00, 20.00, 20.00, NULL, NULL, NULL, NULL, 0.00, 0, 0.00, NULL, '测试', '2021-01-11 17:39:22', '2021-01-11 17:39:22');

-- ----------------------------
-- Table structure for shop_order_express
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_express`;
CREATE TABLE `shop_order_express`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单ID',
  `shipper_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '运费ID',
  `shipper_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '物流公司名称',
  `shipper_code` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '物流公司代码',
  `logistic_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '快递单号',
  `traces` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '物流跟踪信息',
  `is_finish` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否完成',
  `request_count` int(11) NULL DEFAULT 0 COMMENT '总查询次数',
  `request_time` datetime NULL DEFAULT NULL COMMENT '最近一次向第三方查询物流信息时间',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `modify_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单物流信息表，发货时生成' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_order_express
-- ----------------------------
INSERT INTO `shop_order_express` VALUES (1, 1, 1, '顺丰快递', 'SF', '2021011001000', '开始发货', 0, 0, NULL, '2021-01-07 23:01:34', '2021-01-07 23:01:37');
INSERT INTO `shop_order_express` VALUES (2, 1, 0, '中通快递', 'ZTO', '1111', '', 0, 0, NULL, '2021-01-12 09:52:48', '2021-01-12 09:52:48');
INSERT INTO `shop_order_express` VALUES (4, 2, 0, '中通快递', 'ZTO', '202101120111', '', 0, 0, NULL, '2021-01-12 09:56:16', '2021-01-12 09:56:16');
INSERT INTO `shop_order_express` VALUES (5, 4, 0, '圆通速递', 'YTO', 'YT5144498012364', '签收成功', 0, 0, NULL, '2021-01-14 15:28:44', '2021-01-14 15:28:44');

-- ----------------------------
-- Table structure for shop_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_goods`;
CREATE TABLE `shop_order_goods`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单ID',
  `goods_id` int(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品ID',
  `goods_sn` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品编号',
  `goods_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品title',
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `number` smallint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '下单数',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '下单单价',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品总额',
  `goods_attr_val_ids` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规格',
  `goods_attr_vals` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '规格名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '下单商品详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_order_goods
-- ----------------------------
INSERT INTO `shop_order_goods` VALUES (1, 1, 2, '2222', '好吃不贵的栗子', 'http://qliiucyce.hb-bkt.clouddn.com/3eede97ee19a40a0901d103eb4ac7e95.jpg', 1, 0.00, NULL, '1:1:,2:2', '红色,L');
INSERT INTO `shop_order_goods` VALUES (2, 2, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (3, 3, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (4, 4, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (5, 5, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (6, 6, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (7, 7, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (8, 8, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (9, 9, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (10, 10, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (11, 11, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (12, 12, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (13, 13, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 1, 10.00, 10.00, '3:10', '1KG');
INSERT INTO `shop_order_goods` VALUES (14, 14, 5, '10001', '赣南脐橙单果', 'https://oss.xiapeiyi.com/shop/goods/9f53f249ecfa4710830b2383973bd9c7.png', 2, 10.00, 20.00, '3:11', '2KG');

-- ----------------------------
-- Table structure for shop_order_refund
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_refund`;
CREATE TABLE `shop_order_refund`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `refund_sn` bigint(20) NULL DEFAULT NULL COMMENT '退款单号',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单ID',
  `order_sn` bigint(22) NULL DEFAULT NULL COMMENT '订单编号',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '退款用户',
  `order_goods_id` int(11) NULL DEFAULT NULL COMMENT '退款商品ID',
  `num` int(4) NULL DEFAULT NULL COMMENT '退款数量',
  `refund_status` tinyint(1) NULL DEFAULT NULL COMMENT '退款状态 0 申请中 1 退款完成 2 拒绝退款',
  `order_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `refund_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '退款金额',
  `refund_time` datetime NULL DEFAULT NULL COMMENT '退款时间',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款图片',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款原因',
  `refused_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拒绝退款原因',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE COMMENT 'order_id'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '退款管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_order_refund
-- ----------------------------
INSERT INTO `shop_order_refund` VALUES (1, 1348893439302963200, 3, 1348564262020321280, 1, 6, 1, 2, 10.00, 10.00, NULL, 'http://qliiucyce.hb-bkt.clouddn.com/5bbb875feb394cfbb52ceee48a5bac11.jpg', '我不喜欢', '拒绝', '2021-01-12 15:23:52', '2021-01-12 17:46:44');

-- ----------------------------
-- Table structure for shop_pink_goods
-- ----------------------------
DROP TABLE IF EXISTS `shop_pink_goods`;
CREATE TABLE `shop_pink_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` int(11) NOT NULL COMMENT '商品ID',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `effective_time` int(10) NULL DEFAULT NULL COMMENT '拼团有效时间 小时',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '拼团价格',
  `people` int(2) NULL DEFAULT NULL COMMENT '参团人数',
  `quota` int(255) NULL DEFAULT NULL COMMENT '限购',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态 0 关闭 1开始',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '拼团产品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_pink_goods
-- ----------------------------
INSERT INTO `shop_pink_goods` VALUES (2, 11, '2021-01-28 11:00:00', '2021-01-28 15:00:00', 1, 0.80, 2, 1, 1, 'admin', '2021-01-28 22:30:00', 'admin', '2021-01-28 22:30:04', NULL, 0);

-- ----------------------------
-- Table structure for shop_pink_user
-- ----------------------------
DROP TABLE IF EXISTS `shop_pink_user`;
CREATE TABLE `shop_pink_user`  (
  `id` int(20) NOT NULL COMMENT '团购单ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户主键',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品主键',
  `goods_title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `order_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单IDs',
  `people` int(2) NULL DEFAULT NULL COMMENT '几人团',
  `count_people` int(2) NULL DEFAULT NULL COMMENT '几人参加',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态 1 进行中 2 已完成 3 未完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '拼团用户列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_pink_user
-- ----------------------------
INSERT INTO `shop_pink_user` VALUES (1, 1, 2, '【值享焕新版】Apple iPhone 12 Pro Max (A2412) 128GB 金色 支持移动联通电信5G 双卡双待手机', '1000000002,1000003', 2, 1, '2021-01-07 11:41:19', '2021-01-08 11:41:21', 1);

-- ----------------------------
-- Table structure for shop_seckill
-- ----------------------------
DROP TABLE IF EXISTS `shop_seckill`;
CREATE TABLE `shop_seckill`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '秒杀名称',
  `start_time` time NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` time NULL DEFAULT NULL COMMENT '结束时间',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `sort` int(2) NULL DEFAULT NULL COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态 0 关闭 1开始',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秒杀配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_seckill
-- ----------------------------
INSERT INTO `shop_seckill` VALUES (1, '8点秒杀', '08:00:00', '10:00:00', 'https://oss.xiapeiyi.com/shop/seckill/9647b2b8376f48edbfa708b211e5181d.png', 1, 1, 'admin', '2021-01-06 10:16:29', 'admin', '2021-02-04 13:49:54', NULL, 0);

-- ----------------------------
-- Table structure for shop_seckill_goods
-- ----------------------------
DROP TABLE IF EXISTS `shop_seckill_goods`;
CREATE TABLE `shop_seckill_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品主键',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `kill_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '秒杀价格',
  `sales` int(10) NULL DEFAULT 0 COMMENT '秒杀销量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `is_quota` tinyint(1) NULL DEFAULT NULL COMMENT '是否限购',
  `quota` int(10) NULL DEFAULT 0 COMMENT '限购总数',
  `del_flag` tinyint(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品秒杀配置列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_seckill_goods
-- ----------------------------
INSERT INTO `shop_seckill_goods` VALUES (1, 2, '2021-01-25 08:00:00', '2021-01-25 10:00:00', 0.90, 100, NULL, 1, 2, 1);
INSERT INTO `shop_seckill_goods` VALUES (2, 6, '2021-02-10 08:00:00', '2021-02-10 10:00:00', 55.00, 1000, NULL, 0, 0, 0);
INSERT INTO `shop_seckill_goods` VALUES (3, 15, '2021-02-10 08:00:00', '2021-02-10 10:00:00', 15.80, 888, NULL, 0, 0, 0);
INSERT INTO `shop_seckill_goods` VALUES (4, 16, '2021-02-10 08:00:00', '2021-02-10 10:00:00', 7.00, 332, NULL, NULL, 0, 0);
INSERT INTO `shop_seckill_goods` VALUES (5, 18, '2021-02-10 08:00:00', '2021-02-10 10:00:00', 4.50, 456, NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for shop_setting_swiper
-- ----------------------------
DROP TABLE IF EXISTS `shop_setting_swiper`;
CREATE TABLE `shop_setting_swiper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `sort` tinyint(2) NULL DEFAULT NULL COMMENT '排序',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型 none 无类型 goods 商品',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品ID',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 0 正常 1 停用 ',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '首页轮播图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_setting_swiper
-- ----------------------------
INSERT INTO `shop_setting_swiper` VALUES (1, 'https://oss.xiapeiyi.com/shop/swiper/f6492f255d394b60a0baccddd3ff088a.jpg', 2, 'goods', 11, '0', '2021-01-03 16:30:33', 'admin', '2021-01-23 14:04:11', 'admin', NULL, 0);
INSERT INTO `shop_setting_swiper` VALUES (2, 'https://oss.xiapeiyi.com/shop/swiper/9245c2be59984b54b93cf214a07b25b6.jpg', 1, 'none', NULL, '0', '2021-01-21 20:17:30', 'admin', '2021-01-23 14:04:21', 'admin', NULL, 0);
INSERT INTO `shop_setting_swiper` VALUES (3, 'https://oss.xiapeiyi.com/shop/swiper/20dbd132471c448e824e7a2911bffa74.jpg', 1, 'none', NULL, '0', '2021-01-21 20:17:40', 'admin', '2021-01-21 20:17:40', 'admin', NULL, 0);

-- ----------------------------
-- Table structure for shop_specs_attr
-- ----------------------------
DROP TABLE IF EXISTS `shop_specs_attr`;
CREATE TABLE `shop_specs_attr`  (
  `attr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `attr_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性名称',
  `attr_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件类型',
  `is_pic` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否上传图片',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` int(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`attr_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品规格属性' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_specs_attr
-- ----------------------------
INSERT INTO `shop_specs_attr` VALUES (1, '颜色', 'list_box', '1', '2020-12-16 16:05:15', 'admin', '2020-12-16 16:14:20', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr` VALUES (2, '件数', 'check_box', '0', '2020-12-16 16:26:33', 'admin', '2021-01-12 16:25:50', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr` VALUES (3, '重量', 'check_box', '0', '2020-12-19 13:59:36', 'admin', '2020-12-19 13:59:36', 'admin', NULL, 0);

-- ----------------------------
-- Table structure for shop_specs_attr_val
-- ----------------------------
DROP TABLE IF EXISTS `shop_specs_attr_val`;
CREATE TABLE `shop_specs_attr_val`  (
  `attr_val_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `attr_id` int(11) NULL DEFAULT NULL COMMENT '属性ID',
  `attr_val` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性值',
  `sort` int(1) NULL DEFAULT NULL COMMENT '排序',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` int(1) NOT NULL COMMENT '是否删除 (0 是  1否)',
  PRIMARY KEY (`attr_val_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品规格属性值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_specs_attr_val
-- ----------------------------
INSERT INTO `shop_specs_attr_val` VALUES (1, 1, '蓝色', 1, '2020-12-16 16:05:15', 'admin', '2020-12-16 16:14:20', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr_val` VALUES (2, 1, '红色', 2, '2020-12-16 16:05:15', 'admin', '2020-12-16 16:14:20', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr_val` VALUES (3, 1, '灰色', 3, '2020-12-16 16:14:20', 'admin', '2020-12-16 16:14:20', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr_val` VALUES (4, 2, '1', 1, '2020-12-16 16:26:33', 'admin', '2021-01-12 16:25:50', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr_val` VALUES (5, 2, '2', 2, '2020-12-16 16:26:33', 'admin', '2021-01-12 16:25:50', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr_val` VALUES (6, 2, '3', 3, '2020-12-16 16:26:33', 'admin', '2021-01-12 16:25:50', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr_val` VALUES (7, 2, '4', 4, '2020-12-16 16:26:33', 'admin', '2021-01-12 16:25:50', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr_val` VALUES (8, 2, '5', 5, '2020-12-16 16:26:33', 'admin', '2021-01-12 16:25:50', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr_val` VALUES (9, 2, '6', 6, '2020-12-16 16:26:33', 'admin', '2021-01-12 16:25:50', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr_val` VALUES (10, 3, '1KG', 1, '2020-12-19 13:59:36', 'admin', '2020-12-19 13:59:36', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr_val` VALUES (11, 3, '2KG', 2, '2020-12-19 13:59:36', 'admin', '2020-12-19 13:59:36', 'admin', NULL, 0);
INSERT INTO `shop_specs_attr_val` VALUES (12, 3, '4KG', 3, '2020-12-19 13:59:36', 'admin', '2020-12-19 13:59:36', 'admin', NULL, 0);

-- ----------------------------
-- Table structure for shop_user
-- ----------------------------
DROP TABLE IF EXISTS `shop_user`;
CREATE TABLE `shop_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `gender` tinyint(1) NOT NULL DEFAULT 1 COMMENT '性别：0 未知， 1男， 1 女',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `last_login_ip` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '最近一次登录IP地址',
  `user_type` tinyint(3) NULL DEFAULT 0 COMMENT '用户类型 0 普通用户',
  `nickname` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称或网络名称',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户手机号码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户头像图片',
  `wx_openid` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '微信登录openid',
  `status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '0 可用, 1 禁用, 2 注销',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'shop会员管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_user
-- ----------------------------
INSERT INTO `shop_user` VALUES (1, '微信用户xxx', '123456', 1, '2020-12-13', '2020-12-13 16:47:33', '122.122.122.122', 0, 'Mr_王小峰', '18513262490', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJDZJCdn1xIDrMVpfntF0ZDKjeHENEZLQ8nqSPuMQ92ibnrUOPpibpRoj4MJW9SmfDFPDGnscZeR4dw/132', '011GAkFa1dh69A0c5MHa1us5eE1GAkFQ', 0, '2020-12-13 16:48:03', '2020-12-13 16:48:08', 0);
INSERT INTO `shop_user` VALUES (2, '微信用户oaDJg5', '', 1, NULL, '2021-02-19 11:57:02', '124.160.214.140', 0, 'Mr_王小峰', '123888888', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJDZJCdn1xIDrMVpfntF0ZDKjeHENEZLQ8nqSPuMQ92ibnrUOPpibpRojShZuAxVbmPyfIjOcnwvJgQ/132', 'oaDJg5SAy2AIlqKJ30Wp2uffOZPk', 0, '2021-01-16 17:18:40', NULL, 0);
INSERT INTO `shop_user` VALUES (3, '微信用户oaQMI5', '', 1, NULL, '2021-01-29 21:31:50', '183.158.249.102', 0, '', '', '', 'oaQMI5kqJUOOuvmVAPVsV3s7rwG0', 0, '2021-01-18 18:49:03', NULL, 0);

-- ----------------------------
-- Table structure for shop_user_address
-- ----------------------------
DROP TABLE IF EXISTS `shop_user_address`;
CREATE TABLE `shop_user_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '收货人名称',
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户表的用户ID',
  `province_id` int(11) NOT NULL DEFAULT 0 COMMENT '行政区域表的省ID',
  `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
  `city_id` int(11) NOT NULL DEFAULT 0 COMMENT '行政区域表的市ID',
  `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市',
  `area_id` int(11) NOT NULL DEFAULT 0 COMMENT '行政区域表的区县ID',
  `area` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区县',
  `address` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '具体收货地址',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否默认地址',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_user_address
-- ----------------------------
INSERT INTO `shop_user_address` VALUES (1, '王小峰', 2, 330000, '浙江省', 330100, '杭州市', 330104, '江干区', '彭埠街道', '18513262490', 1, '2020-12-13 17:14:33', '2021-02-10 14:44:48', 0);
INSERT INTO `shop_user_address` VALUES (7, '王小峰', 2, 110000, '北京市', 110100, '北京市', 110105, '朝阳区', '大望路22号', '15538107993', 0, '2021-01-29 17:29:30', '2021-02-10 14:45:06', 0);
INSERT INTO `shop_user_address` VALUES (8, '', 2, 0, NULL, 0, NULL, 0, NULL, '', '', 0, '2021-01-29 17:44:34', '2021-01-29 17:44:34', 1);

-- ----------------------------
-- Table structure for shop_user_cart
-- ----------------------------
DROP TABLE IF EXISTS `shop_user_cart`;
CREATE TABLE `shop_user_cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '会员ID',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品ID',
  `attr_val_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格搭配',
  `attr_vals` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格搭配翻译',
  `num` int(5) NULL DEFAULT NULL COMMENT '数量',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `attr_val_ids`(`attr_val_ids`) USING BTREE COMMENT 'attr_val_ids',
  INDEX `user_id`(`user_id`) USING BTREE COMMENT 'user_id',
  INDEX `goods_id`(`goods_id`) USING BTREE COMMENT 'goods_id'
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_user_cart
-- ----------------------------
INSERT INTO `shop_user_cart` VALUES (1, 1, 9, '3:10', '1KG', 2, '2021-01-20 17:07:38', 0);
INSERT INTO `shop_user_cart` VALUES (2, 3, 17, '3:10,1:1,2:4', '1KG,蓝色,1', 4, '2021-01-25 23:04:43', 0);
INSERT INTO `shop_user_cart` VALUES (3, 2, 14, '2:4', '1', 1, '2021-01-26 22:40:54', 1);
INSERT INTO `shop_user_cart` VALUES (4, 2, 16, '2:4', '1', 3, '2021-01-26 22:55:51', 1);
INSERT INTO `shop_user_cart` VALUES (5, 2, 18, '3:10', '1KG', 2, '2021-01-26 22:57:00', 1);
INSERT INTO `shop_user_cart` VALUES (6, 2, 25, '3:10', '1KG', 1, '2021-01-26 23:04:27', 0);
INSERT INTO `shop_user_cart` VALUES (7, 2, 14, '2:5', '2', 1, '2021-01-26 23:05:51', 0);
INSERT INTO `shop_user_cart` VALUES (8, 2, 27, '2:5,3:10', '2,1KG', 1, '2021-01-26 23:07:10', 1);
INSERT INTO `shop_user_cart` VALUES (9, 2, 26, '3:10', '1KG', 1, '2021-02-01 14:47:56', 0);
INSERT INTO `shop_user_cart` VALUES (10, 2, 28, '3:10', '1KG', 2, '2021-02-02 16:53:45', 1);
INSERT INTO `shop_user_cart` VALUES (11, 2, 18, '3:10', '1KG', 1, '2021-02-04 14:50:07', 0);
INSERT INTO `shop_user_cart` VALUES (12, 2, 21, '2:4', '1', 1, '2021-02-10 14:19:43', 1);
INSERT INTO `shop_user_cart` VALUES (13, 2, 27, '2:5,3:10', '2,1KG', 1, '2021-02-10 14:19:57', 0);

-- ----------------------------
-- Table structure for shop_user_collect
-- ----------------------------
DROP TABLE IF EXISTS `shop_user_collect`;
CREATE TABLE `shop_user_collect`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) NOT NULL COMMENT '用户Id',
  `goods_id` int(10) NULL DEFAULT NULL COMMENT '商品Id',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户收藏' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_user_collect
-- ----------------------------
INSERT INTO `shop_user_collect` VALUES (1, 1, 2, '2020-12-13 17:27:24', 1);
INSERT INTO `shop_user_collect` VALUES (2, 1, 9, '2021-01-20 16:18:02', 0);
INSERT INTO `shop_user_collect` VALUES (3, 2, 6, '2021-02-03 10:06:49', 0);
INSERT INTO `shop_user_collect` VALUES (4, 2, 14, '2021-02-03 10:29:02', 1);
INSERT INTO `shop_user_collect` VALUES (5, 2, 16, '2021-02-03 11:18:27', 1);
INSERT INTO `shop_user_collect` VALUES (6, 2, 16, '2021-02-03 11:18:41', 0);
INSERT INTO `shop_user_collect` VALUES (7, 2, 26, '2021-02-03 11:54:01', 1);
INSERT INTO `shop_user_collect` VALUES (8, 2, 15, '2021-02-10 14:42:49', 1);
INSERT INTO `shop_user_collect` VALUES (9, 2, 15, '2021-02-10 14:42:51', 0);

-- ----------------------------
-- Table structure for shop_user_footprint
-- ----------------------------
DROP TABLE IF EXISTS `shop_user_footprint`;
CREATE TABLE `shop_user_footprint`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `goods_id` int(10) NOT NULL COMMENT '产品id',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户浏览足迹' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_user_footprint
-- ----------------------------
INSERT INTO `shop_user_footprint` VALUES (4, 1, 9, '2021-01-20 15:40:25', 0);
INSERT INTO `shop_user_footprint` VALUES (5, 1, 12, '2021-01-20 15:43:36', 0);
INSERT INTO `shop_user_footprint` VALUES (6, 2, 16, '2021-01-27 15:35:19', 0);
INSERT INTO `shop_user_footprint` VALUES (7, 2, 15, '2021-01-27 15:36:10', 0);
INSERT INTO `shop_user_footprint` VALUES (8, 2, 6, '2021-01-27 16:45:25', 0);
INSERT INTO `shop_user_footprint` VALUES (9, 2, 14, '2021-01-27 18:05:40', 0);
INSERT INTO `shop_user_footprint` VALUES (10, 2, 17, '2021-01-27 18:06:52', 0);
INSERT INTO `shop_user_footprint` VALUES (11, 2, 6, '2021-01-28 16:35:44', 0);
INSERT INTO `shop_user_footprint` VALUES (12, 2, 6, '2021-01-30 11:27:32', 0);
INSERT INTO `shop_user_footprint` VALUES (13, 2, 14, '2021-01-30 15:17:09', 0);
INSERT INTO `shop_user_footprint` VALUES (14, 2, 15, '2021-01-30 16:40:38', 0);
INSERT INTO `shop_user_footprint` VALUES (15, 2, 16, '2021-01-30 19:40:23', 0);
INSERT INTO `shop_user_footprint` VALUES (16, 2, 6, '2021-02-01 14:43:21', 0);
INSERT INTO `shop_user_footprint` VALUES (17, 2, 18, '2021-02-01 14:47:40', 0);
INSERT INTO `shop_user_footprint` VALUES (18, 2, 26, '2021-02-01 14:47:51', 0);
INSERT INTO `shop_user_footprint` VALUES (19, 2, 15, '2021-02-01 14:48:13', 0);
INSERT INTO `shop_user_footprint` VALUES (20, 2, 6, '2021-02-02 16:51:24', 0);
INSERT INTO `shop_user_footprint` VALUES (21, 2, 18, '2021-02-02 16:53:30', 0);
INSERT INTO `shop_user_footprint` VALUES (22, 2, 28, '2021-02-02 16:53:42', 0);
INSERT INTO `shop_user_footprint` VALUES (23, 2, 14, '2021-02-02 17:34:53', 0);
INSERT INTO `shop_user_footprint` VALUES (24, 2, 6, '2021-02-03 09:36:10', 0);
INSERT INTO `shop_user_footprint` VALUES (25, 2, 18, '2021-02-03 09:55:26', 0);
INSERT INTO `shop_user_footprint` VALUES (26, 2, 14, '2021-02-03 09:56:09', 0);
INSERT INTO `shop_user_footprint` VALUES (27, 2, 16, '2021-02-03 11:18:12', 0);
INSERT INTO `shop_user_footprint` VALUES (28, 2, 26, '2021-02-03 11:53:59', 0);
INSERT INTO `shop_user_footprint` VALUES (29, 2, 26, '2021-02-04 10:31:55', 0);
INSERT INTO `shop_user_footprint` VALUES (30, 2, 6, '2021-02-04 11:14:52', 0);
INSERT INTO `shop_user_footprint` VALUES (31, 2, 18, '2021-02-04 11:21:06', 0);
INSERT INTO `shop_user_footprint` VALUES (32, 2, 14, '2021-02-04 11:23:11', 0);
INSERT INTO `shop_user_footprint` VALUES (33, 2, 15, '2021-02-04 11:32:22', 0);
INSERT INTO `shop_user_footprint` VALUES (34, 2, 16, '2021-02-04 11:39:11', 0);
INSERT INTO `shop_user_footprint` VALUES (35, 2, 17, '2021-02-04 11:39:19', 0);
INSERT INTO `shop_user_footprint` VALUES (36, 2, 6, '2021-02-10 13:47:00', 0);
INSERT INTO `shop_user_footprint` VALUES (37, 2, 18, '2021-02-10 14:19:15', 0);
INSERT INTO `shop_user_footprint` VALUES (38, 2, 26, '2021-02-10 14:19:31', 0);
INSERT INTO `shop_user_footprint` VALUES (39, 2, 21, '2021-02-10 14:19:40', 0);
INSERT INTO `shop_user_footprint` VALUES (40, 2, 27, '2021-02-10 14:19:53', 0);
INSERT INTO `shop_user_footprint` VALUES (41, 2, 15, '2021-02-10 14:42:44', 0);
INSERT INTO `shop_user_footprint` VALUES (42, 2, 9, '2021-02-10 14:46:16', 0);
INSERT INTO `shop_user_footprint` VALUES (43, 2, 6, '2021-02-19 11:57:07', 0);
INSERT INTO `shop_user_footprint` VALUES (44, 2, 15, '2021-02-19 11:57:14', 0);

-- ----------------------------
-- Table structure for shop_wx_auth
-- ----------------------------
DROP TABLE IF EXISTS `shop_wx_auth`;
CREATE TABLE `shop_wx_auth`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'appID',
  `secret` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'secret',
  `client_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'oauth2 clientId',
  `mchid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '直连商户号',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `del_flag` bit(1) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信小程序授权信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_wx_auth
-- ----------------------------
INSERT INTO `shop_wx_auth` VALUES (1, 'wx28480e43e3e8636f', 'fdd8df9f3ddad22a9216e6fe72f62f5d', 'spark-app', NULL, '2020-12-14 13:24:01', 'admin', '2020-12-15 14:25:35', 'admin', '微信小程序', b'0');
INSERT INTO `shop_wx_auth` VALUES (2, 'wxd9544ddf5fa3c9b8', 'a6ef177adeb1b519fb37ddc3134d4df0', 'spark-app', NULL, '2021-01-16 17:41:53', 'admin', '2021-01-16 17:41:53', 'admin', '回家小程序', b'0');

SET FOREIGN_KEY_CHECKS = 1;
