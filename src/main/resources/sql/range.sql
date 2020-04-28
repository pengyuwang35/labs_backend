/*
Navicat MySQL Data Transfer

Source Server         : wpy
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : labs

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-04-28 20:50:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for range
-- ----------------------------
DROP TABLE IF EXISTS `range`;
CREATE TABLE `range` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `range_code` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '场地编码',
  `range_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '场地名字',
  `range_location` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '场地位置',
  `status` int(11) DEFAULT NULL COMMENT '场地状态',
  `close_reason` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '关闭原因',
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `created_by` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of range
-- ----------------------------
INSERT INTO `range` VALUES ('3', '001', '宿舍', 'S1', '1', null, '睡觉', '2020-04-26 21:46:07', '2020-04-26 21:46:08', null, null);
INSERT INTO `range` VALUES ('4', '002', '教室', 'N5', '2', '疫情', '学习', '2020-04-26 22:27:46', '2020-04-26 22:27:49', null, null);
INSERT INTO `range` VALUES ('5', 'RG20200425112415', '实验室', 'K2', '1', null, '做实验', '2020-04-26 22:28:12', '2020-04-26 22:28:14', null, null);
