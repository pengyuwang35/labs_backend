/*
Navicat MySQL Data Transfer

Source Server         : wpy
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : labs

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-04-28 20:51:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for competition_event
-- ----------------------------
DROP TABLE IF EXISTS `competition_event`;
CREATE TABLE `competition_event` (
  `id` int(11) NOT NULL,
  `competition_event_code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `competition_event_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `suite_type` int(11) DEFAULT NULL,
  `range_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `plan_start_at` date DEFAULT NULL,
  `plan_end_at` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of competition_event
-- ----------------------------
INSERT INTO `competition_event` VALUES ('1', '001', '篮球', '1', '001', '2020-04-25', '2020-04-25', '2');
INSERT INTO `competition_event` VALUES ('2', '002', '足球', '1', 'RG20200425112415', '2020-04-26', '2020-04-26', '3');
INSERT INTO `competition_event` VALUES ('3', '003', '排球', '1', '002', '2020-04-26', '2020-04-26', '3');
