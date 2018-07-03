/*
 Navicat Premium Data Transfer

 Source Server         : db_candy_block18
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : 35.194.212.125
 Source Database       : db_tracking

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : utf-8

 Date: 07/03/2018 13:47:43 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_bigBossAddr`
-- ----------------------------
DROP TABLE IF EXISTS `t_bigBossAddr`;
CREATE TABLE `t_bigBossAddr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `privateTokenAdd` longtext,
  `tokenNum` double NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_bigBossAddr`
-- ----------------------------
BEGIN;
INSERT INTO `t_bigBossAddr` VALUES ('1', null, '0x79650799e7899a802cb96c0bc33a6a8d4ce4936c', '0', null), ('2', null, '0xbc46d9961a3932f7d6b64abfdec80c1816c4b835', '0', null), ('3', null, '0x82d3a142ddd44d2bd29a683f0691fbead3bccc44', '0', null), ('4', null, '0x69dfcf370c5ba8959acebd0bfa229a0608961a6d', '0', null), ('5', null, '0xbeb6fdf4ef6ceb975157be43cbe0047b248a8922', '0', null), ('6', null, '0x81e74a3ea4bab2277aa3b941e9d9f37b08ac5374', '0', null), ('7', null, '0x1a0c31837edb132a9312841b9527e6307db13509', '0', null), ('8', null, '0x0089659f609933d16a5cd6c2be1a5dca1abe24ad', '0', null), ('9', null, '0x626a14ec8bb0d92692ef704f19968c20727dede1', '0', null), ('10', null, '0xf41861f194e7ba8de95144a89e0c6ed16ee0b3a0', '0', null), ('11', null, '0xbc647aad10114b89564c0a7aabe542bd0cf2c5af', '0', null), ('12', null, '0x584b44853680ee34a0f337b712a8f66d816df151', '0', null), ('13', null, '0x9b20dabcec77f6289113e61893f7beefaeb1990a', '0', null), ('14', null, '0xec985525f3f22c7fa9c9ff4a49e37589a76d86d4', '0', null), ('15', null, '0x399f9a95305114efacb91d1d6c02cbe234dd36af', '0', null), ('16', null, '0xedc502b12ced7e16ce21749e7161f9ed22bfca53', '0', null), ('17', null, '0x351d5ea36941861d0c03fdfb24a8c2cb106e068b', '0', null), ('18', null, '0xc5faadd1206ca91d9f8dd015b3498affad9a58bc', '0', null), ('19', null, '0x0b76544f6c413a555f309bf76260d1e02377c02a', '0', null), ('20', null, '0x4212fea9fec90236ecc51e41e2096b16ceb84555', '0', null), ('21', null, '0x6aba1623ea906d1164cbb007e764ebde2514a2ba', '0', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
