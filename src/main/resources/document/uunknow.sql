/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50742
 Source Host           : localhost:3306
 Source Schema         : mall

 Target Server Type    : MySQL
 Target Server Version : 50742
 File Encoding         : 65001

 Date: 01/09/2023 16:04:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for uunknow
-- ----------------------------
DROP TABLE IF EXISTS `uunknow`;
CREATE TABLE `uunknow`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `openId` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `danci` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `hanyi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `des` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `changdu` int(10) DEFAULT NULL,
  `date` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `hal_danci_index`(`openId`, `danci`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of uunknow
-- ----------------------------
INSERT INTO `uunknow` VALUES ('05b7b9acb2a7410ca4fbbf2453b52713', 'oYLEe5Zu8OAS74ElfdEQAQHsga88', 'ox', 'n. 牛；公牛', NULL, 2, '2023-09-01 15:32:26');
INSERT INTO `uunknow` VALUES ('3876914c05303b88c382e01c08d8382b', 'oYLEe5Zu8OAS74ElfdEQAQHsga88', 'ah', 'int. 啊；呀 n. (Ah)人名；(中)亚(广东话·威妥玛)；(缅)阿', NULL, 2, '2023-09-01 15:32:25');
INSERT INTO `uunknow` VALUES ('40e3a6cbd1cad14648016d4e3565b7e3', 'oYLEe5Zu8OAS74ElfdEQAQHsga88', 'Dr', 'abbr. 速度三角形定位法（dead reckoning）；数据记录器（Data Recorder）', NULL, 2, '2023-09-01 15:32:26');
INSERT INTO `uunknow` VALUES ('52420842c18daf8a02c0430e7ebade0e', 'oYLEe5Zu8OAS74ElfdEQAQHsga88', 'PM', 'abbr. （拉）下午（Post Meridiem，等于afternoon）；调相（Phase Modulation）', NULL, 2, '2023-09-01 15:32:26');
INSERT INTO `uunknow` VALUES ('afef8563f353ce48c99f0841de69fafc', 'oYLEe5Zu8OAS74ElfdEQAQHsga88', 'pub', 'n. 酒馆；客栈 n. (Pub)人名；(匈)普布', NULL, 3, '2023-09-01 15:32:26');
INSERT INTO `uunknow` VALUES ('b9dec76212ddd5499ce5842b128961f4', 'oYLEe5Zu8OAS74ElfdEQAQHsga88', 'rod', 'n. 棒；惩罚；枝条；权力 n. (Rod)人名；(英、法、德、捷、希)罗德；(阿拉伯)鲁德；(老)罗', NULL, 3, '2023-09-01 15:32:27');
INSERT INTO `uunknow` VALUES ('eb624ab01749fe0dabe826f73378022e', 'oYLEe5Zu8OAS74ElfdEQAQHsga88', 'de', 'abbr. 判定元件（Decision Element） n. (De)人名；(朝)大', NULL, 2, '2023-09-01 15:32:25');

SET FOREIGN_KEY_CHECKS = 1;
