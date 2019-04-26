/*
Navicat MySQL Data Transfer

Source Server         : blog_demo
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : member_management

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2019-04-26 09:15:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_category_info
-- ----------------------------
DROP TABLE IF EXISTS `t_category_info`;
CREATE TABLE `t_category_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '类别ID',
  `category_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '类别名',
  `describute` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '类别描述',
  `remark` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `added_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '新增时间',
  `added_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '新增人',
  `last_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改时间',
  `last_modified_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '最后修改人',
  `valid` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '有效标识',
  `category_type` varchar(11) COLLATE utf8_bin DEFAULT '' COMMENT '类别标签类型（primary，success，danger）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品类别信息表';

-- ----------------------------
-- Records of t_category_info
-- ----------------------------
INSERT INTO `t_category_info` VALUES ('1', 'C000001', '上衣1', '这是上衣', null, '2019-04-22 19:57:55', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'primary');
INSERT INTO `t_category_info` VALUES ('2', 'C000002', '牛仔裤2', '这是牛仔裤', null, '2019-04-22 19:57:56', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'danger');
INSERT INTO `t_category_info` VALUES ('3', 'C000003', '裙装3', '这是裙装', null, '2019-04-22 19:57:59', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'success');
INSERT INTO `t_category_info` VALUES ('4', 'C000004', '上4', '这是上衣', null, '2019-04-22 19:58:00', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'primary');
INSERT INTO `t_category_info` VALUES ('5', 'C000005', '牛仔裤5', '这是牛仔裤', null, '2019-04-22 19:58:01', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'danger');
INSERT INTO `t_category_info` VALUES ('6', 'C000006', '上衣6', '这是上衣', null, '2019-04-22 19:58:02', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'primary');
INSERT INTO `t_category_info` VALUES ('7', 'C000007', '牛仔裤7', '这是牛仔裤', null, '2019-04-22 19:58:03', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'danger');
INSERT INTO `t_category_info` VALUES ('8', 'C000008', '裙装8', '这是裙装', null, '2019-04-22 19:58:04', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'success');
INSERT INTO `t_category_info` VALUES ('9', 'C000009', '上衣9', '这是上衣', null, '2019-04-22 19:58:04', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'primary');
INSERT INTO `t_category_info` VALUES ('10', 'C000010', '牛仔裤10', '这是牛仔裤', null, '2019-04-22 19:58:06', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'danger');
INSERT INTO `t_category_info` VALUES ('11', 'C000011', '秋冬款11', '羊毛制品', '12345656', '2019-04-22 19:58:07', 'qiqi', '2019-04-18 11:39:38', null, 'T', 'primary');
INSERT INTO `t_category_info` VALUES ('12', 'C000012', '短裤12', '666', '12345656', '2019-04-22 19:58:10', 'qiqi', '2019-04-18 16:41:36', null, 'T', 'primary');

-- ----------------------------
-- Table structure for t_integral_config
-- ----------------------------
DROP TABLE IF EXISTS `t_integral_config`;
CREATE TABLE `t_integral_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `integral_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `integral_min_amount` int(10) DEFAULT NULL,
  `integral_max_amount` int(10) DEFAULT NULL,
  `level` int(10) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `added_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `added_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_modified_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `valid` char(2) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='积分配置表';

-- ----------------------------
-- Records of t_integral_config
-- ----------------------------
INSERT INTO `t_integral_config` VALUES ('1', 'IT100001', '1', '100', '1', '0.95', 'qiqi', '2019-04-26 08:26:18', 'qiqi', '2019-04-26 08:24:23', 'T');
INSERT INTO `t_integral_config` VALUES ('2', 'IT100002', '101', '300', '2', '0.90', 'qiqi', '2019-04-26 08:26:45', 'qiqi', '2019-04-26 08:24:23', 'T');
INSERT INTO `t_integral_config` VALUES ('3', 'IT100003', '301', '500', '3', '0.85', 'qiqi', '2019-04-26 08:27:02', 'qiqi', '2019-04-26 08:24:23', 'T');

-- ----------------------------
-- Table structure for t_member_info
-- ----------------------------
DROP TABLE IF EXISTS `t_member_info`;
CREATE TABLE `t_member_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '会员ID',
  `member_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '会员名称',
  `member_phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '会员手机号',
  `birthday` date DEFAULT NULL COMMENT '会员生日',
  `member_email` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '会员邮箱',
  `gender` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `integral` int(2) DEFAULT NULL COMMENT '会员积分',
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '加入时间',
  `added_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '新增时间',
  `added_by_name` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '新增人',
  `last_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改时间',
  `last_modified_by_name` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '最后修改人',
  `valid` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='会员信息表';

-- ----------------------------
-- Records of t_member_info
-- ----------------------------
INSERT INTO `t_member_info` VALUES ('1', '10001', '褚珅', '15262057926', '2019-04-17', '1028438733@qq.com', '1', '100', '2019-04-17 15:35:03', '2019-04-15 21:04:12', 'qiqi', '2019-04-15 21:04:12', 'qiqi', 'T');
INSERT INTO `t_member_info` VALUES ('2', '10002', '琪琪', '15262057926', '2019-04-15', '1028438733@qq.com', '2', '100', '2019-04-17 15:35:13', '2019-04-15 21:04:12', 'qiqi', '2019-04-15 21:04:12', 'qiqi', 'T');
INSERT INTO `t_member_info` VALUES ('3', '10003', '琪小琪', '15262057926', '2019-04-03', '1028438733@qq.com', '1', '100', '2019-04-17 15:35:07', '2019-04-15 21:04:12', 'qiqi', '2019-04-17 09:00:11', 'qiqi', 'T');
INSERT INTO `t_member_info` VALUES ('4', 'M000003', '陈平安', '13671592354', '2019-04-03', '1028438733@qq.com', '1', '200', '2019-04-17 15:35:17', '2019-04-17 11:40:16', 'qiqi', '2019-04-17 11:40:16', 'qiqi', 'T');
INSERT INTO `t_member_info` VALUES ('5', 'M000004', '宁姚', '15996989760', '2019-04-02', 'pingan.163.com', '2', '200', '2019-04-17 15:35:24', '2019-04-17 11:41:47', 'qiqi', '2019-04-17 11:41:55', 'qiqi', 'T');
INSERT INTO `t_member_info` VALUES ('6', 'M000005', '裴女侠', '13676768970', '2018-04-17', 'lps@163.com', '2', '100', '2019-04-17 15:37:40', '2019-04-17 11:44:07', 'qiqi', '2019-04-17 15:37:40', 'qiqi', 'T');
INSERT INTO `t_member_info` VALUES ('7', 'M000006', '周米粒', '13678960988', '2015-04-17', 'lps@zml.com', '2', '200', '2019-04-17 15:38:20', '2019-04-17 15:38:20', 'qiqi', '2019-04-17 15:38:20', 'qiqi', 'T');
INSERT INTO `t_member_info` VALUES ('8', 'M000007', '朱敛', '13656778907', '1979-04-17', 'lps@zl.com', '1', '200', '2019-04-18 16:14:11', '2019-04-17 15:39:17', 'qiqi', '2019-04-18 16:14:11', 'qiqi', 'T');

-- ----------------------------
-- Table structure for t_member_order
-- ----------------------------
DROP TABLE IF EXISTS `t_member_order`;
CREATE TABLE `t_member_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_rel_id` varchar(11) COLLATE utf8_bin NOT NULL,
  `member_id` varchar(11) COLLATE utf8_bin NOT NULL,
  `order_total_pay` decimal(10,2) DEFAULT NULL,
  `real_total_pay` decimal(10,2) DEFAULT NULL,
  `order_total_amount` int(11) DEFAULT NULL,
  `remark` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `added_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `added_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `valid` char(2) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='会员订单表';

-- ----------------------------
-- Records of t_member_order
-- ----------------------------
INSERT INTO `t_member_order` VALUES ('1', 'OR000002', 'M000006', '2000.00', '1800.00', '2', null, '2019-04-26 08:57:48', null, '2019-04-26 08:57:48', null, 'T');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `product_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `order_amount` int(10) DEFAULT NULL,
  `order_pay` decimal(10,2) DEFAULT NULL,
  `real_pay` decimal(10,2) DEFAULT NULL,
  `order_status` char(2) COLLATE utf8_bin DEFAULT NULL,
  `added_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `added_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_modified_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `valid` char(2) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单明细表';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', 'O100001', 'P100001', '10', '1000.00', '900.00', '20', 'qiqi', '2019-04-26 08:37:17', 'qiqi', '2019-04-26 08:37:23', 'T');
INSERT INTO `t_order` VALUES ('7', 'O000002', 'P000005', '2', '2000.00', '1800.00', '20', 'qiqi', '2019-04-26 08:57:45', 'qiqi', '2019-04-26 08:57:45', 'T');

-- ----------------------------
-- Table structure for t_order_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_order_rel`;
CREATE TABLE `t_order_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_rel_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `order_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `added_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `added_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_modified_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `valid` char(2) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单关联表';

-- ----------------------------
-- Records of t_order_rel
-- ----------------------------
INSERT INTO `t_order_rel` VALUES ('1', 'OR100001', 'O100001', 'qiqi', '2019-04-26 08:52:40', 'qiqi', '2019-04-26 08:52:45', 'T');
INSERT INTO `t_order_rel` VALUES ('4', 'OR000002', 'O000002', 'qiqi', '2019-04-26 08:57:45', 'qiqi', '2019-04-26 08:57:45', 'T');

-- ----------------------------
-- Table structure for t_product_category
-- ----------------------------
DROP TABLE IF EXISTS `t_product_category`;
CREATE TABLE `t_product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `category_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `added_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `added_by_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_modified_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_by_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `valid` char(2) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品类别关系表';

-- ----------------------------
-- Records of t_product_category
-- ----------------------------
INSERT INTO `t_product_category` VALUES ('1', 'P000003', 'C000003', 'qiqi', '2019-04-22 10:27:33', 'qiqi', '2019-04-22 10:27:33', 'T');
INSERT INTO `t_product_category` VALUES ('2', 'P000003', 'C000011', 'qiqi', '2019-04-22 10:27:33', 'qiqi', '2019-04-22 10:27:33', 'T');
INSERT INTO `t_product_category` VALUES ('3', 'P000001', 'C000004', 'qiqi', '2019-04-22 10:27:33', 'qiqi', '2019-04-22 10:27:33', 'T');
INSERT INTO `t_product_category` VALUES ('4', 'P000001', 'C000005', 'qiqi', '2019-04-22 10:53:50', 'qiqi', '2019-04-22 10:53:46', 'T');
INSERT INTO `t_product_category` VALUES ('5', 'P000002', 'C000001', 'qiqi', '2019-04-22 10:54:22', 'qiqi', '2019-04-22 10:54:21', 'T');
INSERT INTO `t_product_category` VALUES ('6', 'P000002', 'C000002', 'qiqi', '2019-04-22 10:54:25', 'qiqi', '2019-04-22 10:54:23', 'T');
INSERT INTO `t_product_category` VALUES ('7', 'P000004', 'C000004', 'qiqi', '2019-04-22 19:57:26', 'qiqi', '2019-04-22 19:57:26', 'F');
INSERT INTO `t_product_category` VALUES ('8', 'P000004', 'C000006', 'qiqi', '2019-04-22 19:57:26', 'qiqi', '2019-04-22 19:57:26', 'F');
INSERT INTO `t_product_category` VALUES ('9', 'P000005', 'C000009', 'qiqi', '2019-04-22 19:42:43', 'qiqi', '2019-04-22 19:42:43', 'F');
INSERT INTO `t_product_category` VALUES ('10', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:42:43', 'qiqi', '2019-04-22 19:42:43', 'F');
INSERT INTO `t_product_category` VALUES ('11', 'P000006', 'C000010', 'qiqi', '2019-04-22 13:03:27', 'qiqi', '2019-04-22 13:03:27', 'F');
INSERT INTO `t_product_category` VALUES ('12', 'P000005', 'C000009', 'qiqi', '2019-04-22 19:43:11', 'qiqi', '2019-04-22 19:43:11', 'F');
INSERT INTO `t_product_category` VALUES ('13', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:43:11', 'qiqi', '2019-04-22 19:43:11', 'F');
INSERT INTO `t_product_category` VALUES ('14', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:43:11', 'qiqi', '2019-04-22 19:43:11', 'F');
INSERT INTO `t_product_category` VALUES ('15', 'P000005', 'C000009', 'qiqi', '2019-04-22 19:44:34', 'qiqi', '2019-04-22 19:44:34', 'F');
INSERT INTO `t_product_category` VALUES ('16', 'P000005', 'C000009', 'qiqi', '2019-04-22 19:44:40', 'qiqi', '2019-04-22 19:44:40', 'F');
INSERT INTO `t_product_category` VALUES ('17', 'P000005', 'C000009', 'qiqi', '2019-04-22 19:44:40', 'qiqi', '2019-04-22 19:44:40', 'F');
INSERT INTO `t_product_category` VALUES ('18', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:44:40', 'qiqi', '2019-04-22 19:44:40', 'F');
INSERT INTO `t_product_category` VALUES ('19', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:56:28', 'qiqi', '2019-04-22 19:56:28', 'F');
INSERT INTO `t_product_category` VALUES ('20', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:54:23', 'qiqi', '2019-04-22 19:44:40', 'F');
INSERT INTO `t_product_category` VALUES ('21', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:57:06', 'qiqi', '2019-04-22 19:57:06', 'F');
INSERT INTO `t_product_category` VALUES ('22', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:58:22', 'qiqi', '2019-04-22 19:58:22', 'F');
INSERT INTO `t_product_category` VALUES ('23', 'P000005', 'C000008', 'qiqi', '2019-04-22 19:58:22', 'qiqi', '2019-04-22 19:58:22', 'F');
INSERT INTO `t_product_category` VALUES ('24', 'P000004', 'C000004', 'qiqi', '2019-04-23 11:07:12', 'qiqi', '2019-04-23 11:07:12', 'F');
INSERT INTO `t_product_category` VALUES ('25', 'P000005', 'C000007', 'qiqi', '2019-04-22 19:58:28', 'qiqi', '2019-04-22 19:58:28', 'F');
INSERT INTO `t_product_category` VALUES ('26', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:58:28', 'qiqi', '2019-04-22 19:58:28', 'F');
INSERT INTO `t_product_category` VALUES ('27', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:58:28', 'qiqi', '2019-04-22 19:58:28', 'F');
INSERT INTO `t_product_category` VALUES ('28', 'P000005', 'C000008', 'qiqi', '2019-04-22 19:58:28', 'qiqi', '2019-04-22 19:58:28', 'F');
INSERT INTO `t_product_category` VALUES ('29', 'P000005', 'C000007', 'qiqi', '2019-04-22 19:59:06', 'qiqi', '2019-04-22 19:59:06', 'F');
INSERT INTO `t_product_category` VALUES ('30', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:59:06', 'qiqi', '2019-04-22 19:59:06', 'F');
INSERT INTO `t_product_category` VALUES ('31', 'P000005', 'C000008', 'qiqi', '2019-04-22 19:59:06', 'qiqi', '2019-04-22 19:59:06', 'F');
INSERT INTO `t_product_category` VALUES ('32', 'P000005', 'C000007', 'qiqi', '2019-04-22 19:59:06', 'qiqi', '2019-04-22 19:59:06', 'F');
INSERT INTO `t_product_category` VALUES ('33', 'P000005', 'C000010', 'qiqi', '2019-04-22 19:59:42', 'qiqi', '2019-04-22 19:59:42', 'F');
INSERT INTO `t_product_category` VALUES ('34', 'P000005', 'C000008', 'qiqi', '2019-04-22 19:59:42', 'qiqi', '2019-04-22 19:59:42', 'F');
INSERT INTO `t_product_category` VALUES ('35', 'P000005', 'C000008', 'qiqi', '2019-04-22 20:20:58', 'qiqi', '2019-04-22 20:20:58', 'F');
INSERT INTO `t_product_category` VALUES ('36', 'P000005', 'C000012', 'qiqi', '2019-04-22 20:48:02', 'qiqi', '2019-04-22 20:48:02', 'F');
INSERT INTO `t_product_category` VALUES ('37', 'P000005', 'C000008', 'qiqi', '2019-04-22 20:48:02', 'qiqi', '2019-04-22 20:48:02', 'F');
INSERT INTO `t_product_category` VALUES ('38', 'P000005', 'C000011', 'qiqi', '2019-04-22 20:48:02', 'qiqi', '2019-04-22 20:48:02', 'F');
INSERT INTO `t_product_category` VALUES ('39', 'P000005', 'C000011', 'qiqi', '2019-04-22 20:50:54', 'qiqi', '2019-04-22 20:50:54', 'F');
INSERT INTO `t_product_category` VALUES ('40', 'P000005', 'C000010', 'qiqi', '2019-04-22 20:50:54', 'qiqi', '2019-04-22 20:50:54', 'F');
INSERT INTO `t_product_category` VALUES ('41', 'P000005', 'C000005', 'qiqi', '2019-04-22 20:50:54', 'qiqi', '2019-04-22 20:50:54', 'F');
INSERT INTO `t_product_category` VALUES ('42', 'P000005', 'C000011', 'qiqi', '2019-04-22 20:50:58', 'qiqi', '2019-04-22 20:50:58', 'F');
INSERT INTO `t_product_category` VALUES ('43', 'P000005', 'C000010', 'qiqi', '2019-04-22 20:50:58', 'qiqi', '2019-04-22 20:50:58', 'F');
INSERT INTO `t_product_category` VALUES ('44', 'P000005', 'C000005', 'qiqi', '2019-04-22 20:50:58', 'qiqi', '2019-04-22 20:50:58', 'F');
INSERT INTO `t_product_category` VALUES ('45', 'P000005', 'C000011', 'qiqi', '2019-04-22 20:51:33', 'qiqi', '2019-04-22 20:51:33', 'F');
INSERT INTO `t_product_category` VALUES ('46', 'P000005', 'C000010', 'qiqi', '2019-04-22 20:51:33', 'qiqi', '2019-04-22 20:51:33', 'F');
INSERT INTO `t_product_category` VALUES ('47', 'P000005', 'C000005', 'qiqi', '2019-04-22 20:51:33', 'qiqi', '2019-04-22 20:51:33', 'F');
INSERT INTO `t_product_category` VALUES ('48', 'P000005', 'C000011', 'qiqi', '2019-04-22 20:51:33', 'qiqi', '2019-04-22 20:51:33', 'T');
INSERT INTO `t_product_category` VALUES ('49', 'P000005', 'C000010', 'qiqi', '2019-04-22 20:51:33', 'qiqi', '2019-04-22 20:51:33', 'T');
INSERT INTO `t_product_category` VALUES ('50', 'P000005', 'C000005', 'qiqi', '2019-04-22 20:51:33', 'qiqi', '2019-04-22 20:51:33', 'T');
INSERT INTO `t_product_category` VALUES ('51', 'P000004', 'C000004', 'qiqi', '2019-04-23 11:07:28', 'qiqi', '2019-04-23 11:07:28', 'F');
INSERT INTO `t_product_category` VALUES ('52', 'P000004', 'C000004', 'qiqi', '2019-04-23 11:07:28', 'qiqi', '2019-04-23 11:07:28', 'T');

-- ----------------------------
-- Table structure for t_product_info
-- ----------------------------
DROP TABLE IF EXISTS `t_product_info`;
CREATE TABLE `t_product_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '商品ID',
  `product_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `category_id` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '类别编号',
  `storage_id` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '库存编号',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `describute` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '商品描述',
  `specification` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '商品规格',
  `scope` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '适用范围',
  `added_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '新增时间',
  `added_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '新增人',
  `last_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改时间',
  `last_modified_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '最后修改人',
  `valid` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否有效',
  `product_img_src` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品信息表';

-- ----------------------------
-- Records of t_product_info
-- ----------------------------
INSERT INTO `t_product_info` VALUES ('1', 'P000001', '2018秋冬新款男士休闲时尚军绿飞行夹克秋冬新款男', '', 'S000001', '100.00', '秋冬款', '哈哈哈', '10', '2019-04-22 10:52:42', 'qiqi', '2019-04-17 20:30:59', 'qiqi', 'T', '//img.yzcdn.cn/upload_files/2017/07/02/af5b9f44deaeb68000d7e4a711160c53.jpg');
INSERT INTO `t_product_info` VALUES ('2', 'P000002', '2018秋冬新款男士休闲时尚军绿飞行夹克秋冬新款男', '', 'S000001', '100.00', '秋冬款', '哈哈哈', '10', '2019-04-22 13:10:02', 'qiqi', '2019-04-17 20:30:59', 'qiqi', 'T', '//img.yzcdn.cn/upload_files/2017/07/02/af5b9f44deaeb68000d7e4a711160c53.jpg');
INSERT INTO `t_product_info` VALUES ('3', 'P000003', '羊毛衫', null, 'S000002', '100.00', '春季新款', null, null, '2019-04-22 11:07:50', 'qiqi', '2019-04-22 10:27:29', 'qiqi', 'T', '//img.yzcdn.cn/upload_files/2017/07/02/af5b9f44deaeb68000d7e4a711160c53.jpg');
INSERT INTO `t_product_info` VALUES ('4', 'P000004', '春季贾佳', null, 'S000003', '100.00', '111', null, null, '2019-04-23 11:07:28', 'qiqi', '2019-04-23 11:07:28', 'qiqi', 'T', 'http://qiqi-management.oss-cn-beijing.aliyuncs.com/timg1.png?Expires=1871348846&OSSAccessKeyId=LTAILFfm6C9zjLYO&Signature=wtpYwsV8ioBFrzXGwcyk4xHdjNQ%3D');
INSERT INTO `t_product_info` VALUES ('5', 'P000005', '春季贾佳家111', null, 'S000004', '1000.00', '1111', null, null, '2019-04-22 20:52:07', 'qiqi', '2019-04-22 20:51:33', 'qiqi', 'T', 'http://qiqi-management.oss-cn-beijing.aliyuncs.com/2_161641_2.jpg?Expires=1871297452&OSSAccessKeyId=LTAILFfm6C9zjLYO&Signature=Y37Y%2FwM5n68ZEmvdpO1xl4qIQ8A%3D');
INSERT INTO `t_product_info` VALUES ('6', 'P000006', '琪琪傻子', null, 'S000005', '1111.00', '111', null, null, '2019-04-22 13:03:24', 'qiqi', '2019-04-22 11:12:11', 'qiqi', 'F', 'http://qiqi-management.oss-cn-beijing.aliyuncs.com/login_bg.png?Expires=1871262730&OSSAccessKeyId=LTAILFfm6C9zjLYO&Signature=UAszgsMYta34I2qOWH38IaGY07c%3D');

-- ----------------------------
-- Table structure for t_storage_info
-- ----------------------------
DROP TABLE IF EXISTS `t_storage_info`;
CREATE TABLE `t_storage_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `storage_id` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '库存ID',
  `storage_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '库存名',
  `storage_size` int(11) DEFAULT NULL COMMENT '库存容量',
  `product_amount` int(11) DEFAULT NULL COMMENT '商品数量',
  `rule` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '存入规则',
  `remark` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `added_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '新增人',
  `added_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '新增时间',
  `last_modified_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '最后修改人',
  `last_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改时间',
  `valid` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='库存信息表';

-- ----------------------------
-- Records of t_storage_info
-- ----------------------------
INSERT INTO `t_storage_info` VALUES ('1', 'S000001', '华东仓库', '1000', '10', '无', null, 'qiqi', '2019-04-17 20:41:15', 'qiqi', '2019-04-17 20:33:30', 'T');
INSERT INTO `t_storage_info` VALUES ('2', 'S000002', '羊毛衫', '10000', '100', null, null, 'qiqi', '2019-04-22 10:52:27', 'qiqi', '2019-04-22 10:27:34', 'T');
INSERT INTO `t_storage_info` VALUES ('3', 'S000003', '春季贾佳', '100', '10', null, null, 'qiqi', '2019-04-22 11:00:13', 'qiqi', '2019-04-22 11:00:13', 'T');
INSERT INTO `t_storage_info` VALUES ('4', 'S000004', '春季贾佳家', '100', '10', null, null, 'qiqi', '2019-04-22 11:11:30', 'qiqi', '2019-04-22 11:11:30', 'T');
INSERT INTO `t_storage_info` VALUES ('5', 'S000005', '琪琪傻子', '10', '1', null, null, 'qiqi', '2019-04-22 13:03:24', 'qiqi', '2019-04-22 11:12:11', 'F');

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '用户昵称',
  `email` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用户邮箱(实际登录名)',
  `password` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '登录密码',
  `mobile` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `gender` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '性别(0：女，1：男)',
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `img_src` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '附件地址',
  `admin_flag` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '超级管理员标识(1：普通，0：超级)',
  `added_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '新增时间',
  `added_by_name` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '新增人',
  `last_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改时间',
  `last_modified_by_name` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '最后修改人',
  `valid` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '是否有效(T：有效，F：无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='记录系统用户信息';

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('1', '褚珅', '1028438733@qq.com', '5fa39c52a2f547f8f3be7ff050ff6b2f', '15262057926', '2', '上海市杨浦区', 'http://qiqi-management.oss-cn-beijing.aliyuncs.com/avartar?Expires=1871025593&OSSAccessKeyId=LTAILFfm6C9zjLYO&Signature=U0PLJcshm1n48EsOjRlJvFcgnjQ%3D', '1', '2019-04-19 17:20:50', 'qiqi', '2019-04-19 17:20:50', 'qiqi', 'T');
