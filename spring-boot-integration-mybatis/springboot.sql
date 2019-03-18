/*
 Navicat Premium Data Transfer

 Source Server         : 虚拟机
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : XXXXXXX:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 18/03/2019 09:32:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_age` int(255) NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '张三', 24, '济南', '15063359060', '2019-03-15 10:55:40');
INSERT INTO `tb_user` VALUES (2, '李四', 24, '济南', '15063359001', '2019-03-15 10:54:40');
INSERT INTO `tb_user` VALUES (3, '赵武', 24, '济南', '15063359002', '2019-03-15 10:53:40');
INSERT INTO `tb_user` VALUES (4, '用户101', 25, '济南', '15063359003', '2019-03-15 10:52:40');
INSERT INTO `tb_user` VALUES (5, '用户102', 25, '济南', '15063359004', '2019-03-15 10:51:40');
INSERT INTO `tb_user` VALUES (6, '用户103', 26, '济南', '15063359005', '2019-03-15 10:49:40');
INSERT INTO `tb_user` VALUES (7, '用户104', 28, '济南', '15063359006', '2019-03-15 10:45:40');
INSERT INTO `tb_user` VALUES (8, '用户105', 34, '济南', '15063359061', '2019-03-15 10:35:40');
INSERT INTO `tb_user` VALUES (9, '用户106', 32, '济南', '15063359062', '2019-03-15 10:25:40');
INSERT INTO `tb_user` VALUES (10, '用户107', 30, '济南', '15063359063', '2019-03-15 10:15:40');
INSERT INTO `tb_user` VALUES (11, '用户108', 29, '济南', '15063359064', '2019-03-15 10:05:40');

SET FOREIGN_KEY_CHECKS = 1;
