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

 Date: 08/06/2020 10:41:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pay_record
-- ----------------------------
DROP TABLE IF EXISTS `pay_record`;
CREATE TABLE `pay_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pay_amount` int(11) DEFAULT NULL COMMENT '支出金额',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `create_time` timestamp(0) DEFAULT NULL COMMENT '创建时间',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建者id',
  `is_del` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pay_record_type
-- ----------------------------
DROP TABLE IF EXISTS `pay_record_type`;
CREATE TABLE `pay_record_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_record_id` int(11) DEFAULT NULL COMMENT '支付记录id',
  `pay_type_id` int(11) DEFAULT NULL COMMENT '支付类型id',
  `is_del` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付记录-支出类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pay_type
-- ----------------------------
DROP TABLE IF EXISTS `pay_type`;
CREATE TABLE `pay_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型名',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` timestamp(0) DEFAULT NULL COMMENT '创建时间',
  `is_del` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支出类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` tinyint(3) NOT NULL AUTO_INCREMENT,
  `parent_id` tinyint(3) NOT NULL COMMENT '父菜单id（一级菜单的父id默认为0）',
  `name` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单中文名称',
  `enname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单英文名称',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'url地址(用于区别菜单路由及加载页面组件)',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标样式',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态 0禁用,1启动',
  `is_del` bit(1) DEFAULT NULL COMMENT '是否已删除',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建者id',
  `create_time` timestamp(0) DEFAULT NULL COMMENT '创建时间',
  `updater_id` int(11) DEFAULT NULL COMMENT '更新者id',
  `update_time` timestamp(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '首页', 'home', '/home', 'form', 1, b'0', '首页', 0, '2019-12-17 10:14:07', 0, '2019-12-17 10:14:07');
INSERT INTO `sys_menu` VALUES (2, 0, '系统设置', 'systemSettings', '/settings', 'form', 1, b'0', '系统设置', 0, '2019-12-18 07:15:33', 0, '2019-12-18 07:15:33');
INSERT INTO `sys_menu` VALUES (3, 3, '权限管理', '嘿嘿呵呵', '/permission1', 'form', 1, b'0', '嘿嘿呵呵', 0, '2019-12-18 07:19:25', 0, '2019-12-18 07:19:25');
INSERT INTO `sys_menu` VALUES (4, 2, '用户管理', '嘿嘿呵呵1', '/users', 'form', 1, b'0', '嘿嘿呵呵', 0, '2019-12-18 07:20:56', 1, '2020-05-09 23:05:05');
INSERT INTO `sys_menu` VALUES (5, 3, '角色管理', '2', '/roles', 'form', 1, b'0', '2', 0, '2019-12-18 07:22:14', 0, '2019-12-18 07:22:14');
INSERT INTO `sys_menu` VALUES (6, 2, '数据字典', '2', '/6', 'form', 1, b'0', '2', 0, '2019-12-18 07:22:14', 0, '2019-12-18 07:22:14');
INSERT INTO `sys_menu` VALUES (7, 2, '课程类型', '嘿嘿呵呵', '/7', 'form', 1, b'0', '嘿嘿呵呵', 0, '2019-12-18 07:20:56', 0, '2019-12-18 07:20:56');
INSERT INTO `sys_menu` VALUES (8, 9, '课程列表', '2', '/list', 'form', 0, b'0', '2', 0, '2019-12-18 07:22:14', 0, '2019-12-18 07:22:14');
INSERT INTO `sys_menu` VALUES (9, 0, '课程管理', '2', '/9', 'form', 1, b'0', '2', 0, '2019-12-18 07:22:14', 0, '2019-12-18 07:22:14');
INSERT INTO `sys_menu` VALUES (10, 2, '菜单管理', 'menu', '/menus', 'form', 1, b'0', '2', 0, '2019-12-18 07:22:14', 1, '2020-05-10 00:35:33');
INSERT INTO `sys_menu` VALUES (11, 0, '权限管理', 'permission', '/permission', 'form', 1, b'0', '2', 0, '2019-12-18 07:22:14', 0, '2019-12-18 07:22:14');
INSERT INTO `sys_menu` VALUES (15, 11, '999', '999', '99', '99', 1, b'1', '999', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_id` tinyint(3) DEFAULT NULL COMMENT '权限对应的菜单id（授予角色权限时，要同步将菜单权限授予给角色）',
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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, NULL, '删除用户', '/users/{id}', 'DELETE', 1, '权限测试', NULL, NULL, NULL, NULL, b'0');
INSERT INTO `sys_permission` VALUES (2, NULL, '新增用户', '/users', 'POST', 1, '权限测试', NULL, NULL, NULL, NULL, b'0');
INSERT INTO `sys_permission` VALUES (3, NULL, '查询用户', '/users', 'GET', 1, '权限测试', NULL, NULL, NULL, NULL, b'0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'ROLE_ADMIN', 1, '管理员权限', NULL, NULL, NULL, NULL, b'0');
INSERT INTO `sys_role` VALUES (2, '普通用户', 'ROLE_USER', 1, '普通用户权限', NULL, NULL, NULL, NULL, b'0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 角色-菜单' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 角色-权限' ROW_FORMAT = Dynamic;

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
  `avatar` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '管理员001', '头像2', 'admin', 'admin', '1', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, b'0');
INSERT INTO `sys_user` VALUES (2, '普通用户', '头像1', 'user', 'user', '1', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, b'0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统 用户-角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1, NULL, NULL, NULL, b'0');
INSERT INTO `sys_user_role` VALUES (2, 2, 2, NULL, NULL, NULL, b'0');

SET FOREIGN_KEY_CHECKS = 1;
