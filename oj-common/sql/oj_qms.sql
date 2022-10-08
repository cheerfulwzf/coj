/*
 Navicat Premium Data Transfer

 Source Server         : 虚拟机1
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.44.3:3308
 Source Schema         : oj_qms

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 08/10/2022 23:03:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qms_q_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `qms_q_tag_relation`;
CREATE TABLE `qms_q_tag_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qid` bigint(20) NULL DEFAULT NULL,
  `tid` bigint(20) NULL DEFAULT NULL,
  `t_value` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qms_q_tag_relation
-- ----------------------------
INSERT INTO `qms_q_tag_relation` VALUES (1, 1, 3, '入门');
INSERT INTO `qms_q_tag_relation` VALUES (2, 1, 1, 'dp动态规划');
INSERT INTO `qms_q_tag_relation` VALUES (4, 2, 1, NULL);
INSERT INTO `qms_q_tag_relation` VALUES (5, 3, 2, '贪心');
INSERT INTO `qms_q_tag_relation` VALUES (6, 3, 1, 'dp动态规划');

-- ----------------------------
-- Table structure for qms_question
-- ----------------------------
DROP TABLE IF EXISTS `qms_question`;
CREATE TABLE `qms_question`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '题目内容，存储md格式',
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目标题',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `diff_level` int(11) NULL DEFAULT NULL COMMENT '难度等级',
  `submit_count` int(11) NULL DEFAULT NULL COMMENT '提交总次数',
  `out_samples` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '输出',
  `in_samples` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '输入',
  `is_show` int(11) NULL DEFAULT NULL COMMENT '是否显示',
  `time_limit` double NULL DEFAULT NULL COMMENT '时间使用限制',
  `memory_limit` double NULL DEFAULT NULL COMMENT '内存使用限制',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `q_idx`(`title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qms_question
-- ----------------------------
INSERT INTO `qms_question` VALUES (1, '输入A、B的值，输出A+B的值', 'A+B Problem', '2022-03-23 13:29:45', '2022-03-23 13:41:30', 1, 1, '100:100:100', '99 1:99 1:99 1', 1, 1024, 65535);
INSERT INTO `qms_question` VALUES (2, '将数字1，2，3……，n×n填成一个n×n的螺旋方阵。', '数字螺旋方阵', '2022-06-22 20:40:38', '2022-06-22 20:40:42', 1, 0, '4', '10 11 12 1\r\n9 16 13 2\r\n8 15 14 3\r\n7 6 5 4', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for qms_tag
-- ----------------------------
DROP TABLE IF EXISTS `qms_tag`;
CREATE TABLE `qms_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qms_tag
-- ----------------------------
INSERT INTO `qms_tag` VALUES (1, 'dp动态规划', '2022-03-22 21:06:29');
INSERT INTO `qms_tag` VALUES (2, '贪心', '2022-03-23 12:50:56');
INSERT INTO `qms_tag` VALUES (3, '入门', '2022-03-23 14:04:16');

SET FOREIGN_KEY_CHECKS = 1;
