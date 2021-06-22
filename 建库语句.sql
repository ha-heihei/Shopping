/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : shopping

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 18/07/2020 22:06:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `nownum` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sellerid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `seller` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('2fFCe8co6dQA4ql', 'W08DqRCZ0uJD7eq.jpg', '安踏运动鞋', '279', '998', '最新款安踏运动鞋', '0', '2020-07-14 18:55:37', 'bLhVvyT7Xo1zvex', '商户', '1');
INSERT INTO `goods` VALUES ('5zNV5gb7kjRQRA9', 'tsceTl8upas9ehO.jpg', '情侣短袖', '39', '999', '最新款情侣短袖', '2', '2020-07-14 18:54:23', 'bLhVvyT7Xo1zvex', '商户', '1');
INSERT INTO `goods` VALUES ('9Cjz2hl78g5jm2c', 'mDwmL9L8Ol4N3n0.jpg', '运动内衣', '199', '997', '健身房运动透气女士内衣', '0', '2020-07-14 18:58:26', 'bLhVvyT7Xo1zvex', '商户', '1');
INSERT INTO `goods` VALUES ('WXfBtwEPtoqm94l', '9iJcl3aiJCHfpjh.jpg', '休闲套装', '78', '999', '休闲套装，爆款', '1', '2020-07-14 18:56:44', 'bLhVvyT7Xo1zvex', '商户', '1');
INSERT INTO `goods` VALUES ('d6ugiFSVw7fvehP', 'IYeGpoaxcHf01tK.jpg', '居家睡衣', '65', '997', '居家可爱女生睡衣', '1', '2020-07-14 18:59:36', 'bLhVvyT7Xo1zvex', '商户', '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `goods` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `goodsid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sum` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tradedate` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `buyer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `buyerid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `seller` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sellerid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `buyerlocation` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `buyerphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sellerlocation` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sellerphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tradestatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `buyerstatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sellerstatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('Ww5pujSeembfAqe', '运动内衣', '9Cjz2hl78g5jm2c', 'mDwmL9L8Ol4N3n0.jpg', '199', '2', '398.0', '2020-07-14 19:01:44', '李浩', 'fhSmGMsOjdOroB2', '商户', 'bLhVvyT7Xo1zvex', '河南', '12345678901', '石家庄', '12390987641', '2', '0', '0');
INSERT INTO `orders` VALUES ('n8Uc8Y0ddE2rVxf', '居家睡衣', 'd6ugiFSVw7fvehP', 'IYeGpoaxcHf01tK.jpg', '65', '2', '130.0', '2020-07-14 19:01:52', '李浩', 'fhSmGMsOjdOroB2', '商户', 'bLhVvyT7Xo1zvex', '河南', '12345678901', '石家庄', '12390987641', '1', '0', '0');
INSERT INTO `orders` VALUES ('s2huo6x474k0lu5', '安踏运动鞋', '2fFCe8co6dQA4ql', 'W08DqRCZ0uJD7eq.jpg', '279', '1', '279.0', '2020-07-14 19:01:29', '李浩', 'fhSmGMsOjdOroB2', '商户', 'bLhVvyT7Xo1zvex', '河南', '12345678901', '石家庄', '12390987641', '0', '0', '0');

-- ----------------------------
-- Table structure for shoptrolley
-- ----------------------------
DROP TABLE IF EXISTS `shoptrolley`;
CREATE TABLE `shoptrolley`  (
  `goodsid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `goods` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sum` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `seller` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `buyerid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shoptrolley
-- ----------------------------
INSERT INTO `shoptrolley` VALUES ('2fFCe8co6dQA4ql', '安踏运动鞋', 'W08DqRCZ0uJD7eq.jpg', '279', '3', '837.0', '最新款安踏运动鞋', '商户', 'fhSmGMsOjdOroB2');
INSERT INTO `shoptrolley` VALUES ('9Cjz2hl78g5jm2c', '运动内衣', 'mDwmL9L8Ol4N3n0.jpg', '199', '2', '398.0', '健身房运动透气女士内衣', '商户', 'fhSmGMsOjdOroB2');
INSERT INTO `shoptrolley` VALUES ('d6ugiFSVw7fvehP', '居家睡衣', 'IYeGpoaxcHf01tK.jpg', '65', '1', '65.0', '居家可爱女生睡衣', '商户', 'fhSmGMsOjdOroB2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `onlinestatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', 'admin', '123', '男', '3', '外太空', '15733230698', '2020-07-12 17:34:04', '999999999999999999999999999999', 'admin.jpg', '0');
INSERT INTO `user` VALUES ('bLhVvyT7Xo1zvex', '商户', '123', '男', '2', '石家庄', '12390987641', '2020-07-13 11:34:53', '4243', 'bLhVvyT7Xo1zvex.jpg', '0');
INSERT INTO `user` VALUES ('cCw0c62ZiycP4c0', '老王', '123', '男', '2', '云南', '12345678903', '2020-07-12 16:48:50', '20', '6.jpg', '0');
INSERT INTO `user` VALUES ('fhSmGMsOjdOroB2', '李浩', '123', '男', '1', '河南', '12345678901', '2020-07-15 17:34:04', '8897.0', 'fhSmGMsOjdOroB2.jpg', '0');

SET FOREIGN_KEY_CHECKS = 1;
