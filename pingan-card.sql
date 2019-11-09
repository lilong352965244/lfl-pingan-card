/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : 192.168.157.128:3306
 Source Schema         : pingan-card

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 08/11/2019 17:10:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_images
-- ----------------------------
DROP TABLE IF EXISTS `tb_images`;
CREATE TABLE `tb_images`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `person_id` int(20) NOT NULL COMMENT 'person表主键id',
  `images_url` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址，最多10个图片',
  `img_description` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片描述',
  `image_number` int(10) NOT NULL COMMENT '图片个数,默认10张',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `last_update` datetime(0) NOT NULL COMMENT '最后修改时间',
  `create_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_person
-- ----------------------------
DROP TABLE IF EXISTS `tb_person`;
CREATE TABLE `tb_person`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(20) NOT NULL COMMENT '用户id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户名字',
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户联系方式',
  `person_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户手机号',
  `enable_status` tinyint(4) NOT NULL COMMENT '0：过期；1：审核中；2：审核通过',
  `money` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '充值金额：单位分',
  `level` tinyint(4) NOT NULL COMMENT '会员级别:0：普通会员，1：超级管理员',
  `expire_time` datetime(0) NOT NULL COMMENT '有效截止时间',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key_name`(`username`) USING BTREE COMMENT '索引搜索'
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '李四', '123456', '123', 1, 0, 0, '2019-11-04 19:35:57', '2019-11-04 19:35:57', '2019-11-04 19:35:57', NULL);
INSERT INTO `tb_user` VALUES (2, '李四1', '123456', '312', 1, 0, 0, '2019-11-04 19:37:20', '2019-11-04 19:37:20', '2019-11-04 19:37:20', NULL);
INSERT INTO `tb_user` VALUES (3, '李四2', '123456', '15670616655', 1, 0, 0, '2019-11-07 16:26:37', '2019-11-07 16:26:37', '2019-11-07 16:26:37', NULL);
INSERT INTO `tb_user` VALUES (4, '王五', '123456', '15670616654', 1, 0, 0, '2019-11-07 16:28:09', '2019-11-07 16:28:09', '2019-11-07 16:28:09', NULL);
INSERT INTO `tb_user` VALUES (5, '156', '123456', '15670616555', 1, 0, 0, '2019-11-07 17:30:51', '2019-11-07 17:30:51', '2019-11-07 17:30:51', NULL);

SET FOREIGN_KEY_CHECKS = 1;
