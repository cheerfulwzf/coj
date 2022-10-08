/*
 Navicat Premium Data Transfer

 Source Server         : 虚拟机1
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.44.3:3308
 Source Schema         : oj_cms

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 08/10/2022 22:56:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_contest
-- ----------------------------
DROP TABLE IF EXISTS `cms_contest`;
CREATE TABLE `cms_contest`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  `begin_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_contest
-- ----------------------------

-- ----------------------------
-- Table structure for cms_contest_q_realation
-- ----------------------------
DROP TABLE IF EXISTS `cms_contest_q_realation`;
CREATE TABLE `cms_contest_q_realation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contest_id` bigint(20) NULL DEFAULT NULL,
  `q_id` bigint(20) NULL DEFAULT NULL,
  `sort_value` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_contest_q_realation
-- ----------------------------

-- ----------------------------
-- Table structure for cms_contest_ranklist
-- ----------------------------
DROP TABLE IF EXISTS `cms_contest_ranklist`;
CREATE TABLE `cms_contest_ranklist`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contest_id` bigint(20) NULL DEFAULT NULL,
  `u_id` bigint(20) NULL DEFAULT NULL,
  `contest_q_id` bigint(20) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `rate` int(11) NULL DEFAULT NULL,
  `time_used` int(11) NULL DEFAULT NULL,
  `is_first` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_contest_ranklist
-- ----------------------------

-- ----------------------------
-- Table structure for cms_contest_user_group
-- ----------------------------
DROP TABLE IF EXISTS `cms_contest_user_group`;
CREATE TABLE `cms_contest_user_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contest_id` bigint(20) NULL DEFAULT NULL,
  `accout` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `group_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_contest_user_group
-- ----------------------------

-- ----------------------------
-- Table structure for cms_contest_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `cms_contest_user_relation`;
CREATE TABLE `cms_contest_user_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NULL DEFAULT NULL,
  `uid` bigint(20) NULL DEFAULT NULL,
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_contest_user_relation
-- ----------------------------

-- ----------------------------
-- Table structure for cms_notice
-- ----------------------------
DROP TABLE IF EXISTS `cms_notice`;
CREATE TABLE `cms_notice`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  `relation_contest_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_notice
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
