/*
Navicat MySQL Data Transfer

Source Server         : blog_demo
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : member_management

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2019-04-19 17:26:51
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
INSERT INTO `t_category_info` VALUES ('1', 'C000001', '上衣', '这是上衣', null, '2019-04-18 09:33:34', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'primary');
INSERT INTO `t_category_info` VALUES ('2', 'C000002', '牛仔裤', '这是牛仔裤', null, '2019-04-18 09:33:34', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'danger');
INSERT INTO `t_category_info` VALUES ('3', 'C000003', '裙装', '这是裙装', null, '2019-04-18 09:54:46', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'success');
INSERT INTO `t_category_info` VALUES ('4', 'C000004', '上衣', '这是上衣', null, '2019-04-18 09:33:34', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'primary');
INSERT INTO `t_category_info` VALUES ('5', 'C000005', '牛仔裤', '这是牛仔裤', null, '2019-04-18 09:33:34', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'danger');
INSERT INTO `t_category_info` VALUES ('6', 'C000006', '上衣', '这是上衣', null, '2019-04-18 09:33:34', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'primary');
INSERT INTO `t_category_info` VALUES ('7', 'C000007', '牛仔裤', '这是牛仔裤', null, '2019-04-18 09:33:34', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'danger');
INSERT INTO `t_category_info` VALUES ('8', 'C000008', '裙装', '这是裙装', null, '2019-04-18 09:54:46', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'success');
INSERT INTO `t_category_info` VALUES ('9', 'C000009', '上衣', '这是上衣', null, '2019-04-18 09:33:34', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'primary');
INSERT INTO `t_category_info` VALUES ('10', 'C000010', '牛仔裤', '这是牛仔裤', null, '2019-04-18 09:33:34', 'qiqi', '2019-04-17 20:32:44', 'qiqi', 'T', 'danger');
INSERT INTO `t_category_info` VALUES ('11', 'C000010', '秋冬款', '羊毛制品', '12345656', '2019-04-18 11:39:38', 'qiqi', '2019-04-18 11:39:38', null, 'T', 'primary');
INSERT INTO `t_category_info` VALUES ('12', 'C000011', '短裤', '666', '12345656', '2019-04-18 16:41:36', 'qiqi', '2019-04-18 16:41:36', null, 'T', 'primary');

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
  `id` int(11) NOT NULL COMMENT '主键',
  `order_id` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '订单编号',
  `member_id` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '会员编号',
  `order_amount` decimal(2,0) DEFAULT NULL COMMENT '订单金额',
  `pay_amount` decimal(2,0) DEFAULT NULL COMMENT '实付金额',
  `remark` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `added_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '新增时间',
  `added_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '新增人',
  `last_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改时间',
  `last_modifed_by_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '最后修改人',
  `valid` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否有效(1有效，0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='会员订单表';

-- ----------------------------
-- Records of t_member_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_product_info
-- ----------------------------
DROP TABLE IF EXISTS `t_product_info`;
CREATE TABLE `t_product_info` (
  `id` int(11) NOT NULL COMMENT '主键',
  `product_id` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '商品ID',
  `product_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `category_id` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '类别编号',
  `storage_id` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '库存编号',
  `price` decimal(10,0) DEFAULT NULL COMMENT '商品价格',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品信息表';

-- ----------------------------
-- Records of t_product_info
-- ----------------------------
INSERT INTO `t_product_info` VALUES ('1', 'P000001', '2018秋冬新款男士休闲时尚军绿飞行夹克秋冬新款男', 'C000001', 'S000001', '100', '秋冬款', '哈哈哈', '10', '2019-04-17 20:35:15', 'qiqi', '2019-04-17 20:30:59', 'qiqi', 'T', '//img.yzcdn.cn/upload_files/2017/07/02/af5b9f44deaeb68000d7e4a711160c53.jpg');
INSERT INTO `t_product_info` VALUES ('2', 'P000002', '2018秋冬新款男士休闲时尚军绿飞行夹克秋冬新款男', 'C000001', 'S000001', '100', '秋冬款', '哈哈哈', '10', '2019-04-17 20:31:48', 'qiqi', '2019-04-17 20:30:59', 'qiqi', 'T', '//img.yzcdn.cn/upload_files/2017/07/02/af5b9f44deaeb68000d7e4a711160c53.jpg');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='库存信息表';

-- ----------------------------
-- Records of t_storage_info
-- ----------------------------
INSERT INTO `t_storage_info` VALUES ('1', 'S000001', '华东仓库', '1000', '10', '无', null, 'qiqi', '2019-04-17 20:41:15', 'qiqi', '2019-04-17 20:33:30', 'T');

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
