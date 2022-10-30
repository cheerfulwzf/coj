/*
 Navicat Premium Data Transfer

 Source Server         : 虚拟机1
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.44.3:3308
 Source Schema         : oj_ums

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 08/10/2022 23:03:34
*/

CREATE DATABASE IF NOT EXISTS oj_ums DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
USE oj_ums;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ums_discuss
-- ----------------------------
DROP TABLE IF EXISTS `ums_discuss`;
CREATE TABLE `ums_discuss`  (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `author_id` bigint(20) NULL DEFAULT NULL,
                                `content` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
                                `gmt_create` datetime(0) NULL DEFAULT NULL,
                                `favor_num` int(11) NULL DEFAULT NULL,
                                `gmt_modified` datetime(0) NULL DEFAULT NULL,
                                `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_discuss
-- ----------------------------

-- ----------------------------
-- Table structure for ums_reply_discuss
-- ----------------------------
DROP TABLE IF EXISTS `ums_reply_discuss`;
CREATE TABLE `ums_reply_discuss`  (
                                      `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                      `u_id` bigint(20) NULL DEFAULT NULL,
                                      `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                      `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                      `gmt_create` datetime(0) NULL DEFAULT NULL,
                                      `gmt_modified` datetime(0) NULL DEFAULT NULL,
                                      `favor_num` int(11) NULL DEFAULT NULL,
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_reply_discuss
-- ----------------------------

-- ----------------------------
-- Table structure for ums_submission
-- ----------------------------
DROP TABLE IF EXISTS `ums_submission`;
CREATE TABLE `ums_submission`  (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `user_id` bigint(20) NULL DEFAULT NULL,
                                   `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户',
                                   `question_id` bigint(20) NULL DEFAULT NULL,
                                   `question_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目',
                                   `result_code` int(11) NULL DEFAULT NULL COMMENT '判题结果状态',
                                   `result_info` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '响应详细信息数据',
                                   `result_msg` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '判题结果信息',
                                   `time_used` double NULL DEFAULT NULL COMMENT '时间占用',
                                   `memory_used` double NULL DEFAULT NULL COMMENT '内存占用',
                                   `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '时间',
                                   `language` int(1) NULL DEFAULT NULL COMMENT '提交语言',
                                   `from_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                   `source` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '用户源代码',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   INDEX `nickname_idx`(`nickname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_submission
-- ----------------------------
INSERT INTO `ums_submission` VALUES (7, 1, 'wzf', 1, 'A+B Problem', 0, '[{\"input\":\"99 1\",\"memoryUsed\":22848,\"output\":\"100\",\"status\":0,\"tempOut\":\"100\",\"timeUsed\":97},{\"input\":\"99 1\",\"memoryUsed\":22864,\"output\":\"100\",\"status\":0,\"tempOut\":\"100\",\"timeUsed\":82},{\"input\":\"99 1\",\"memoryUsed\":22856,\"output\":\"100\",\"status\":0,\"tempOut\":\"100\",\"timeUsed\":80}]', 'Accept', 97, 22856, '2022-04-05 20:46:01', 0, NULL, 'import java.util.Scanner;\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        int a = 0, b = 0;\n        while (sc.hasNextInt()) {\n            a = sc.nextInt();\n            b = sc.nextInt();\n        }\n        System.out.print(b + a);\n    }\n}');
INSERT INTO `ums_submission` VALUES (10, 1, 'wzf', 1, 'A+B Problem', 1, '[{\"input\":\"99 1\",\"memoryUsed\":4572,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":13},{\"input\":\"99 1\",\"memoryUsed\":4572,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":11},{\"input\":\"99 1\",\"memoryUsed\":4572,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":11}]', 'Presentation Error', 13, 4572, '2022-04-18 13:43:17', 8, NULL, 'a,b=map(int, raw_input().split())\nprint(a+b)');
INSERT INTO `ums_submission` VALUES (13, 1, 'wzf', 1, 'A+B Problem', 6, '# command-line-arguments\n/tmp/OnlineJudgeWorkspace/1650261268200/main.go:2:1: syntax error: non-declaration statement outside function body', 'Compile Error', NULL, NULL, '2022-04-18 13:54:28', 6, NULL, 'package mainNimport\n\"fmt\"\nfunc main() {\nvar a, b int\nfmt.Scanf(\"%d%d\", &a, &b)\nfmt.Println(a+b)\n}');
INSERT INTO `ums_submission` VALUES (14, 1, 'wzf', 1, 'A+B Problem', 1, '[{\"input\":\"99 1\",\"memoryUsed\":1084,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":0},{\"input\":\"99 1\",\"memoryUsed\":1084,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":1},{\"input\":\"99 1\",\"memoryUsed\":1084,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":1}]', 'Presentation Error', 1, 1084, '2022-04-18 13:57:17', 6, NULL, 'package main\nimport\n\"fmt\"\nfunc main() {\nvar a, b int\nfmt.Scanf(\"%d%d\", &a, &b)\nfmt.Println(a+b)\n}');
INSERT INTO `ums_submission` VALUES (15, 1, 'wzf', 1, 'A+B Problem', 6, 'Cannot run program \"python3\": error=2, 没有那个文件或目录', 'Compile Error', NULL, NULL, '2022-06-22 20:56:13', 9, NULL, 'a,b=map(int, input().split())print(a+b)');
INSERT INTO `ums_submission` VALUES (16, 1, 'wzf', 1, 'A+B Problem', 6, 'Cannot run program \"python3\": error=2, 没有那个文件或目录', 'Compile Error', NULL, NULL, '2022-06-22 21:00:50', 9, NULL, 'a,b=map(int, input().split())print(a+b)');
INSERT INTO `ums_submission` VALUES (17, 1, 'wzf', 1, 'A+B Problem', 0, '[{\"input\":\"99 1\",\"memoryUsed\":22896,\"output\":\"100\",\"status\":0,\"tempOut\":\"100\",\"timeUsed\":110},{\"input\":\"99 1\",\"memoryUsed\":22856,\"output\":\"100\",\"status\":0,\"tempOut\":\"100\",\"timeUsed\":80},{\"input\":\"99 1\",\"memoryUsed\":22848,\"output\":\"100\",\"status\":0,\"tempOut\":\"100\",\"timeUsed\":84}]', 'Accept', 110, 22848, '2022-06-22 21:02:32', 0, NULL, 'import java.util.Scanner;\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        int a = 0, b = 0;\n        while (sc.hasNextInt()) {\n            a = sc.nextInt();\n            b = sc.nextInt();\n        }\n        System.out.print(b + a);\n    }\n}');
INSERT INTO `ums_submission` VALUES (18, 1, 'wzf', 1, 'A+B Problem', 6, '/tmp/OnlineJudgeWorkspace/1655903133903/main.c: 在函数‘main’中:\n/tmp/OnlineJudgeWorkspace/1655903133903/main.c:5:1: 错误：缺少结尾的 \" 字符\n printf(\"%d\n ^\n/tmp/OnlineJudgeWorkspace/1655903133903/main.c:6:1: 错误：缺少结尾的 \" 字符\n \",a+b);\n ^\n/tmp/OnlineJudgeWorkspace/1655903133903/main.c:7:1: 错误：expected expression before ‘return’\n return 0;}\n ^\n/tmp/OnlineJudgeWorkspace/1655903133903/main.c:7:10: 错误：expected ‘;’ before ‘}’ token\n return 0;}\n          ^', 'Compile Error', NULL, NULL, '2022-06-22 21:05:34', 2, NULL, '#include <stdio.h>\nint main(){\nint a,b;\nscanf(\"%d %d\",&a, &b);\nprintf(\"%d\n\",a+b);\nreturn 0;}');
INSERT INTO `ums_submission` VALUES (19, 1, 'wzf', 1, 'A+B Problem', 0, '[{\"input\":\"99 1\",\"memoryUsed\":22856,\"output\":\"100\",\"status\":0,\"tempOut\":\"100\",\"timeUsed\":86},{\"input\":\"99 1\",\"memoryUsed\":22856,\"output\":\"100\",\"status\":0,\"tempOut\":\"100\",\"timeUsed\":84},{\"input\":\"99 1\",\"memoryUsed\":22860,\"output\":\"100\",\"status\":0,\"tempOut\":\"100\",\"timeUsed\":81}]', 'Accept', 86, 22860, '2022-06-22 21:13:07', 0, NULL, 'import java.util.Scanner;\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        int a = 0, b = 0;\n        while (sc.hasNextInt()) {\n            a = sc.nextInt();\n            b = sc.nextInt();\n        }\n        System.out.print(b + a);\n    }\n}');
INSERT INTO `ums_submission` VALUES (20, 1, 'wzf', 1, 'A+B Problem', 6, 'Cannot run program \"python3\": error=2, 没有那个文件或目录', 'Compile Error', NULL, NULL, '2022-06-22 21:22:13', 9, NULL, 'a,b=map(int, input().split())\nprint(a+b)');
INSERT INTO `ums_submission` VALUES (21, 1, 'wzf', 1, 'A+B Problem', 6, 'Cannot run program \"python3\": error=2, 没有那个文件或目录', 'Compile Error', NULL, NULL, '2022-06-22 21:25:25', 9, NULL, 'a,b=map(int, raw_input().split())\nprint(a+b)');
INSERT INTO `ums_submission` VALUES (22, 1, 'wzf', 1, 'A+B Problem', 1, '[{\"input\":\"99 1\",\"memoryUsed\":4572,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":13},{\"input\":\"99 1\",\"memoryUsed\":4568,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":9},{\"input\":\"99 1\",\"memoryUsed\":4572,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":12}]', 'Presentation Error', 13, 4572, '2022-06-22 21:26:45', 8, NULL, 'a,b=map(int, raw_input().split())\nprint(a+b)');
INSERT INTO `ums_submission` VALUES (23, 1, 'wzf', 1, 'A+B Problem', 1, '[{\"input\":\"99 1\",\"memoryUsed\":4568,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":11},{\"input\":\"99 1\",\"memoryUsed\":4572,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":11},{\"input\":\"99 1\",\"memoryUsed\":4572,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":11}]', 'Presentation Error', 11, 4572, '2022-06-22 21:29:15', 8, NULL, 'a,b=map(int, raw_input().split())\nprint a+b');
INSERT INTO `ums_submission` VALUES (24, 1, 'wzf', 1, 'A+B Problem', 1, '[{\"input\":\"99 1\",\"memoryUsed\":4572,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":13},{\"input\":\"99 1\",\"memoryUsed\":4572,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":10},{\"input\":\"99 1\",\"memoryUsed\":4576,\"output\":\"100\",\"status\":1,\"tempOut\":\"100\\n\",\"timeUsed\":10}]', 'Presentation Error', 13, 4576, '2022-06-22 21:30:46', 8, NULL, 'a,b=map(int, raw_input().split())\nprint a+b,');
INSERT INTO `ums_submission` VALUES (25, 1, 'wzf', 1, 'A+B Problem', 4, '[{\"input\":\"99 1\",\"memoryUsed\":4568,\"output\":\"100\",\"status\":4,\"tempOut\":\"a+b\\n\",\"timeUsed\":28},{\"input\":\"99 1\",\"memoryUsed\":4572,\"output\":\"100\",\"status\":4,\"tempOut\":\"a+b\\n\",\"timeUsed\":12},{\"input\":\"99 1\",\"memoryUsed\":4568,\"output\":\"100\",\"status\":4,\"tempOut\":\"a+b\\n\",\"timeUsed\":10}]', 'Wrong Answer', 28, 4568, '2022-06-22 21:31:31', 8, NULL, 'a,b=map(int, raw_input().split())\nprint \"a+b\",');

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`  (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                             `avatar_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                             `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                             `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                             `user_desc` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
                             `user_role` int(11) NULL DEFAULT 0,
                             `sex` int(11) NULL DEFAULT 3 COMMENT '0男,1女',
                             `gmt_create` datetime(0) NULL DEFAULT NULL,
                             `gmt_modified` datetime(0) NULL DEFAULT NULL,
                             `social_id` bigint(20) NULL DEFAULT NULL,
                             `access_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                             `prohabit_status` int(11) NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE,
                             INDEX `nickname_idx`(`nickname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES (1, 'cheerful~', NULL, 'wangzhifu@stu.sicau.edu.cn', '$2a$10$cJYd4GSy3hnqShCvuKZxk.GmBepWJUJcuwmpZLV9kIKtO5iYyVwvi', NULL, 0, 3, '2022-03-31 21:43:41', '2022-03-31 21:43:41', NULL, NULL, 0);
INSERT INTO `ums_user` VALUES (2, 'cheerfulwzf', 'https://portrait.gitee.com/uploads/avatars/user/2750/8250086_cheerfulwzf_1646656917.png', NULL, NULL, NULL, 0, 3, '2022-03-31 21:57:15', '2022-03-31 21:57:15', 8250086, NULL, 0);

-- ----------------------------
-- Table structure for ums_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_login_log`;
CREATE TABLE `ums_user_login_log`  (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                       `u_id` bigint(20) NULL DEFAULT NULL,
                                       `gmt_create` datetime(0) NULL DEFAULT NULL,
                                       `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                       `city` varchar(61) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_user_login_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;


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

CREATE DATABASE IF NOT EXISTS oj_qms DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
USE oj_qms;

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

CREATE DATABASE IF NOT EXISTS oj_cms DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
USE oj_cms;


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
