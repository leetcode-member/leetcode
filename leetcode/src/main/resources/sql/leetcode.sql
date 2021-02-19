/*
 Navicat Premium Data Transfer

 Source Server         : 数据库.106
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.43.106:3306
 Source Schema         : leetcode

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 05/02/2021 22:49:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_answer
-- ----------------------------
DROP TABLE IF EXISTS `sys_answer`;
CREATE TABLE `sys_answer`  (
  `answer_id` bigint(20) NOT NULL COMMENT ' 题解id',
  `question_id` int(11) NOT NULL COMMENT '题目id',
  `user_id` int(11) NOT NULL COMMENT ' 用户id，发布题解的人',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 题解标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题解正文',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 题解封面图片url',
  `create_time` datetime(0) NOT NULL COMMENT '发表日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT ' 修改日期',
  `view` int(11) NULL DEFAULT NULL COMMENT '浏览量',
  `thumbup` int(11) NULL DEFAULT NULL COMMENT ' 点赞量',
  `comment` int(11) NULL DEFAULT NULL COMMENT ' 评论数',
  `deleted` int(1) NULL DEFAULT NULL COMMENT ' 逻辑删除',
  PRIMARY KEY (`answer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_answer
-- ----------------------------

-- ----------------------------
-- Table structure for sys_answers_tags
-- ----------------------------
DROP TABLE IF EXISTS `sys_answers_tags`;
CREATE TABLE `sys_answers_tags`  (
  `answer_id` bigint(20) NOT NULL COMMENT '题解的id',
  `tag_id` int(11) NOT NULL COMMENT '标签的id',
  PRIMARY KEY (`answer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_answers_tags
-- ----------------------------

-- ----------------------------
-- Table structure for sys_comment
-- ----------------------------
DROP TABLE IF EXISTS `sys_comment`;
CREATE TABLE `sys_comment`  (
  `
comment_id` bigint(20) NOT NULL COMMENT '评论id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `user_id` int(11) NOT NULL COMMENT '评论人ID',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT ' 评论父（目标）ID（目标类型通过 type类型确定）',
  `comment_time` datetime(0) NULL DEFAULT NULL COMMENT '评论日期',
  `thumbup` int(11) NULL DEFAULT NULL COMMENT ' 点赞数',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 评论类型可选（question,comment,answer）',
  `deleted` int(1) NULL DEFAULT NULL COMMENT ' 逻辑删除',
  PRIMARY KEY (`
comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_comment
-- ----------------------------

-- ----------------------------
-- Table structure for sys_commit
-- ----------------------------
DROP TABLE IF EXISTS `sys_commit`;
CREATE TABLE `sys_commit`  (
  `commit_id` bigint(20) NOT NULL COMMENT '主键id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '提交用户id',
  `question_id` int(11) NULL DEFAULT NULL COMMENT '提交题目id',
  `commit_time` datetime(0) NULL DEFAULT NULL COMMENT '提交题目时间(time)',
  `commit_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提交结果（有几种类型需要确定）',
  `commit_code` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '提交的代码',
  `runtime` int(11) NULL DEFAULT NULL COMMENT '运行时间(time)毫秒',
  `memory` int(11) NULL DEFAULT NULL COMMENT '内存消耗bit',
  `deleted` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`commit_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_commit
-- ----------------------------

-- ----------------------------
-- Table structure for sys_list
-- ----------------------------
DROP TABLE IF EXISTS `sys_list`;
CREATE TABLE `sys_list`  (
  `list_id` bigint(20) NOT NULL COMMENT ' list id',
  `list_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列表名称',
  `deleted` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`list_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_list
-- ----------------------------
INSERT INTO `sys_list` VALUES (1357250087251021826, 'LeetCode热题HOT 100', 0);
INSERT INTO `sys_list` VALUES (1357250088299597825, 'LeetCode精选数牛i...', 0);
INSERT INTO `sys_list` VALUES (1357250088307986433, 'LeetCode精选算法200题', 0);
INSERT INTO `sys_list` VALUES (1357250088307986434, '力扣杯–竞赛合集', 0);
INSERT INTO `sys_list` VALUES (1357250088307986435, '腾讯精选练习50题', 0);

-- ----------------------------
-- Table structure for sys_question
-- ----------------------------
DROP TABLE IF EXISTS `sys_question`;
CREATE TABLE `sys_question`  (
  `question_id` bigint(20) NOT NULL COMMENT 'question id',
  `question_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 题目的序号',
  `answer_num` int(11) NULL DEFAULT NULL COMMENT ' 解题数',
  `difficulty` int(1) NULL DEFAULT NULL COMMENT '难度(0:easy,1:mid,2:diff)',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题目名字',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '题目正文',
  `list_id` int(11) NULL DEFAULT NULL COMMENT '题目类型(属于哪个列表)',
  `tag_id` int(11) NULL DEFAULT NULL COMMENT '题目标签(栈\\队列\\数组)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发表日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改日期',
  `thumbup` int(11) NULL DEFAULT NULL COMMENT '点赞数',
  `comment_num` int(11) NULL DEFAULT NULL COMMENT '评论数',
  `submit_num` int(11) NULL DEFAULT NULL COMMENT '提交次数',
  `pass_num` int(11) NULL DEFAULT NULL COMMENT '通过次数',
  `initial_code` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '一道题的初始代码',
  `initial_test_case` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '一道题的初始测试用例',
  `commit_test_case` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '一道题的用于评测的测试用例',
  `correct_code` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '题目对应的正确算法',
  `deleted` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_question
-- ----------------------------

-- ----------------------------
-- Table structure for sys_questions_lists
-- ----------------------------
DROP TABLE IF EXISTS `sys_questions_lists`;
CREATE TABLE `sys_questions_lists`  (
  `question_id` bigint(20) NOT NULL COMMENT ' question id',
  `list_id` bigint(20) NULL DEFAULT NULL COMMENT 'list（列表）id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_questions_lists
-- ----------------------------

-- ----------------------------
-- Table structure for sys_questions_tags
-- ----------------------------
DROP TABLE IF EXISTS `sys_questions_tags`;
CREATE TABLE `sys_questions_tags`  (
  `question_id` bigint(20) NOT NULL COMMENT 'question id',
  `tag_id` bigint(20) NOT NULL COMMENT ' tag 标签id',
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_questions_tags
-- ----------------------------

-- ----------------------------
-- Table structure for sys_tag
-- ----------------------------
DROP TABLE IF EXISTS `sys_tag`;
CREATE TABLE `sys_tag`  (
  `tag_id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `deleted` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tag
-- ----------------------------
INSERT INTO `sys_tag` VALUES (1357245099137261570, '栈', 0);
INSERT INTO `sys_tag` VALUES (1357245425361821697, '堆', 0);
INSERT INTO `sys_tag` VALUES (1357248635765985282, '贪心算法', 0);
INSERT INTO `sys_tag` VALUES (1357248637145911297, '排序', 0);
INSERT INTO `sys_tag` VALUES (1357248637145911298, '位运算', 0);
INSERT INTO `sys_tag` VALUES (1357248637145911299, '树', 0);
INSERT INTO `sys_tag` VALUES (1357248637154299906, '深度优先搜索', 0);
INSERT INTO `sys_tag` VALUES (1357248637154299907, '广度优先搜索', 0);
INSERT INTO `sys_tag` VALUES (1357248637162688514, '并查集', 0);
INSERT INTO `sys_tag` VALUES (1357248637162688515, '图', 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL COMMENT 'user id',
  `user_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户角色,springsecurity规定：ROLE_USER,ROLE_ADMIN',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 别名',
  `sex` int(1) NOT NULL COMMENT ' 性别(1男,0女)',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像图片地址',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 邮箱地址',
  `num_simp` int(11) NULL DEFAULT NULL COMMENT ' 做过的简单题的数量',
  `num_mid` int(11) NULL DEFAULT NULL COMMENT ' 做过的中等题的数量',
  `num_diff` int(11) NULL DEFAULT NULL COMMENT ' 做过的复杂题的数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT ' 账号信息更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT ' 乐观锁',
  `deleted` int(1) NULL DEFAULT NULL COMMENT ' 逻辑删除',
  PRIMARY KEY (`user_id`, `sex`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1357259291638530049, 'ROLE_ADMIN', '13388888888', 'jkljkl', '靓仔1号', 1, 'http://fdsafd.fasf.png', 'fdasfd@leetcode.com', 34, 22, 11, '2021-02-04 17:26:46', '2021-02-04 17:26:46', NULL, 0);
INSERT INTO `sys_user` VALUES (1357259569548955649, 'ROLE_USER', '13388888888', 'jkljkl', '用户2号', 1, 'http://fdsafd.fasf.png', 'fdasfd@leetcode.com', 34, 22, 11, '2021-02-04 17:27:53', '2021-02-04 17:27:53', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
