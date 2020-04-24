/*
 Navicat Premium Data Transfer

 Source Server         : 47.106.198.29
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 47.106.198.29:3306
 Source Schema         : base_api

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 24/04/2020 10:47:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'url',
  `method` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求类型 \r\nGET,POST,PUT,DELETE,OPTIONS,HEAD,TRACE,CONNECT',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态 0禁用,1启动',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建者id',
  `create_time` timestamp(0) DEFAULT NULL COMMENT '创建时间',
  `updater_id` int(11) DEFAULT NULL COMMENT '更新者id',
  `update_time` timestamp(0) DEFAULT NULL COMMENT '更新时间',
  `is_del` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '权限测试', '/users/{id}', 'DELETE', 1, '权限测试', NULL, NULL, NULL, NULL, b'0');
INSERT INTO `sys_permission` VALUES (2, '权限测试', '/users', 'POST', 1, '权限测试', NULL, NULL, NULL, NULL, b'0');
INSERT INTO `sys_permission` VALUES (3, '权限测试', '/users', 'GET', 1, '权限测试', NULL, NULL, NULL, NULL, b'0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ch_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称 中',
  `en_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称 英',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态 0禁用,1启动',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建者id',
  `create_time` timestamp(0) DEFAULT NULL COMMENT '创建时间',
  `updater_id` int(11) DEFAULT NULL COMMENT '更新者id',
  `update_time` timestamp(0) DEFAULT NULL COMMENT '更新时间',
  `is_del` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'ROLE_ADMIN', 1, '管理员权限', NULL, NULL, NULL, NULL, b'0');
INSERT INTO `sys_role` VALUES (2, '普通用户', 'ROLE_USER', 1, '普通用户权限', NULL, NULL, NULL, NULL, b'0');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建者id',
  `create_time` timestamp(0) DEFAULT NULL COMMENT '创建时间',
  `is_del` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 角色-权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 1, NULL, NULL, NULL, b'0');
INSERT INTO `sys_role_permission` VALUES (2, 2, 3, NULL, NULL, NULL, b'0');
INSERT INTO `sys_role_permission` VALUES (3, 1, 2, NULL, NULL, NULL, b'0');
INSERT INTO `sys_role_permission` VALUES (4, 1, 3, NULL, NULL, NULL, b'0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nick_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称（显示用户名）',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名（登录用户名）',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `salt` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐值',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `email` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态 0禁用,1启动',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建者id',
  `create_time` timestamp(0) DEFAULT NULL COMMENT '创建时间',
  `updater_id` int(11) DEFAULT NULL COMMENT '更新者id',
  `update_time` timestamp(0) DEFAULT NULL COMMENT '更新时间',
  `is_del` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '管理员001', 'admin', 'admin', '1', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, b'0');
INSERT INTO `sys_user` VALUES (2, '普通用户', 'user', 'user', '1', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, b'0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建者id',
  `create_time` timestamp(0) DEFAULT NULL COMMENT '创建时间',
  `is_del` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 用户-角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1, NULL, NULL, NULL, b'0');
INSERT INTO `sys_user_role` VALUES (2, 2, 2, NULL, NULL, NULL, b'0');

SET FOREIGN_KEY_CHECKS = 1;
