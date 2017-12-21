/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : goods

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 21/12/2017 09:56:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permit` int(11) DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collectelog
-- ----------------------------
DROP TABLE IF EXISTS `collectelog`;
CREATE TABLE `collectelog`  (
  `goods_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `goods_id`) USING BTREE,
  INDEX `FK_4jj2h6purd7edun3cy60sn8tn`(`goods_id`) USING BTREE,
  CONSTRAINT `FK_4jj2h6purd7edun3cy60sn8tn` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_b1wtafeb87wxtfjm4oaymuogd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dialog
-- ----------------------------
DROP TABLE IF EXISTS `dialog`;
CREATE TABLE `dialog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `noReadA` int(11) DEFAULT NULL,
  `noReadB` int(11) DEFAULT NULL,
  `roleA_id` bigint(20) DEFAULT NULL,
  `roleB_id` bigint(20) DEFAULT NULL,
  `date` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_hi8ti4f0j36hph6lbsigtdjye`(`roleA_id`) USING BTREE,
  INDEX `FK_t77eic8q8cnrkrn3nd0tao03f`(`roleB_id`) USING BTREE,
  CONSTRAINT `FK_hi8ti4f0j36hph6lbsigtdjye` FOREIGN KEY (`roleA_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_t77eic8q8cnrkrn3nd0tao03f` FOREIGN KEY (`roleB_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dialogcontent
-- ----------------------------
DROP TABLE IF EXISTS `dialogcontent`;
CREATE TABLE `dialogcontent`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(0) DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_4kpa08v1yap9in650dbhm1nys`(`content_id`) USING BTREE,
  CONSTRAINT `FK_4kpa08v1yap9in650dbhm1nys` FOREIGN KEY (`content_id`) REFERENCES `dialog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `originalPrice` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `uploadTime` datetime(0) DEFAULT NULL,
  `classification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `pageViews` bigint(20) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `collectedCount` bigint(20) DEFAULT NULL,
  `onlineOrOffline` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_lfqyk5pfpuap7pk3s8exrtd62`(`owner_id`) USING BTREE,
  CONSTRAINT `FK_lfqyk5pfpuap7pk3s8exrtd62` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goodsimage
-- ----------------------------
DROP TABLE IF EXISTS `goodsimage`;
CREATE TABLE `goodsimage`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `imageUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tr5uum2xjj8ex6cb8e438oedk`(`goods_id`) USING BTREE,
  CONSTRAINT `FK_tr5uum2xjj8ex6cb8e438oedk` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reqcode
-- ----------------------------
DROP TABLE IF EXISTS `reqcode`;
CREATE TABLE `reqcode`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `reqCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `date` bigint(20) DEFAULT NULL,
  `verification` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for suggestion
-- ----------------------------
DROP TABLE IF EXISTS `suggestion`;
CREATE TABLE `suggestion`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fromName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `date` datetime(0) DEFAULT NULL,
  `isRead` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for undercollectedlog
-- ----------------------------
DROP TABLE IF EXISTS `undercollectedlog`;
CREATE TABLE `undercollectedlog`  (
  `goods_id` bigint(20) NOT NULL,
  `collected_id` bigint(20) NOT NULL,
  PRIMARY KEY (`collected_id`, `goods_id`) USING BTREE,
  INDEX `FK_fbalr9y6jyy5ct03wtb9j5tv5`(`goods_id`) USING BTREE,
  CONSTRAINT `FK_fbalr9y6jyy5ct03wtb9j5tv5` FOREIGN KEY (`goods_id`) REFERENCES `undershelfgoods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_jjq7battnd5wy7giq6v7a4egk` FOREIGN KEY (`collected_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for undershelfgoods
-- ----------------------------
DROP TABLE IF EXISTS `undershelfgoods`;
CREATE TABLE `undershelfgoods`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `uploadTime` datetime(0) DEFAULT NULL,
  `classification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `underShelfTime` datetime(0) DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `imageUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_b0no46eb140s22ppy3akk5v02`(`owner_id`) USING BTREE,
  CONSTRAINT `FK_b0no46eb140s22ppy3akk5v02` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `headImageUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `weixin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for verifylog
-- ----------------------------
DROP TABLE IF EXISTS `verifylog`;
CREATE TABLE `verifylog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(0) DEFAULT NULL,
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goodsName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `admin_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_4kriuh5pn0x6pep2ogkfb6v30`(`admin_id`) USING BTREE,
  CONSTRAINT `FK_4kriuh5pn0x6pep2ogkfb6v30` FOREIGN KEY (`admin_id`) REFERENCES `administrator` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Procedure structure for proc_adder
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_adder`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_adder`(IN a int, IN b int, OUT sum int)
BEGIN
    #Routine body goes here...

    DECLARE c int;
    if a > 1 then set a = 5; 
    end if;
  
    if b is null then set b = 0;
    end if;

    set sum  = a + b;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
