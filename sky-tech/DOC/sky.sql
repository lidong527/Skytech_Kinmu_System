/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : sky

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 08/02/2021 21:40:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for m_account
-- ----------------------------
DROP TABLE IF EXISTS `m_account`;
CREATE TABLE `m_account` (
  `USER_MAIL` varchar(255) NOT NULL COMMENT 'アカウントのユーザー名',
  `PASSWORD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'アカウントのパスワード',
  `PASS_SEC` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '暗号化したのアカウントのパスワード',
  `INITIALIZATION_FLG` bit(1) DEFAULT NULL COMMENT '初期化状態がどうかの判断フラグ',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '作成時間',
  `CREATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '作成者',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新時間',
  `UPDATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新者',
  `UPDATE_CONT` int DEFAULT NULL COMMENT '更新回数',
  PRIMARY KEY (`USER_MAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of m_account
-- ----------------------------
BEGIN;
INSERT INTO `m_account` VALUES ('1@1.co', '12345678', 'L5e6zSxztsHmfxYbVccCqQ==', b'0', '2020-06-10 17:12:00', '1', '2020-06-10 17:12:00', '1', 1);
INSERT INTO `m_account` VALUES ('2@2.co', '12345678', 'L5e6zSxztsHmfxYbVccCqQ==', b'0', '2020-06-10 17:12:00', '1', '2020-06-10 17:12:00', '1', 1);
INSERT INTO `m_account` VALUES ('3@3.co', '12345678', 'L5e6zSxztsHmfxYbVccCqQ==', b'0', '2020-10-20 16:14:04', '1', '2020-10-20 16:14:10', '1', 1);
INSERT INTO `m_account` VALUES ('6@6.co', '12345678', 'L5e6zSxztsHmfxYbVccCqQ==', b'0', '2020-10-20 16:14:04', '1', '2020-10-20 16:14:10', '1', 1);
INSERT INTO `m_account` VALUES ('7@7', '123', 'stCLTORGAxcNGBw60PYLMQ==', b'1', '2020-06-10 17:12:00', '1', '2020-06-10 17:12:00', '1', 1);
INSERT INTO `m_account` VALUES ('admin@admin', '123', 'stCLTORGAxcNGBw60PYLMQ==', b'1', '2020-06-10 17:12:00', '1', '2020-06-10 17:12:00', '1', 1);
INSERT INTO `m_account` VALUES ('admin@mail.com', '123', 'stCLTORGAxcNGBw60PYLMQ==', b'0', '2020-06-10 17:12:00', '1', '2020-06-10 17:12:00', '1', 1);
INSERT INTO `m_account` VALUES ('staff@mail.com', '12345678', 'L5e6zSxztsHmfxYbVccCqQ==', b'0', '2020-06-10 17:12:00', '1', '2020-06-10 17:12:00', '1', 1);
INSERT INTO `m_account` VALUES ('staff1@mail.com', '12345678', 'L5e6zSxztsHmfxYbVccCqQ==', b'0', '2020-06-10 17:12:00', '1', '2020-06-10 17:12:00', '1', 1);
COMMIT;

-- ----------------------------
-- Table structure for m_code
-- ----------------------------
DROP TABLE IF EXISTS `m_code`;
CREATE TABLE `m_code` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CODE_ID` varchar(255) NOT NULL COMMENT 'コードID',
  `CODE_DES` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'コード解説',
  `CODE_CONTENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'コード内容',
  `CODE_CONTENT2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'コード内容2',
  `CODE_CONTENT3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'コード内容3',
  `CODE_CONTENT4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'コード内容4',
  `CODE_CONTENT5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'コード内容5',
  `DELETE_FLG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '癈棄フラグ',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '作成時間',
  `CREATE_USER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作成者',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新時間',
  `UPDATE_USER` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `UPDATE_CONT` int DEFAULT NULL COMMENT '更新回数',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of m_code
-- ----------------------------
BEGIN;
INSERT INTO `m_code` VALUES (1, 'STAFF_STATUS', 'STAFF_STATUS', '1', '待機中', '', '', '', '0', '2020-10-20 12:22:37', '', '2020-10-20 12:23:35', '', 1);
INSERT INTO `m_code` VALUES (2, 'STAFF_STATUS', 'STAFF_STATUS', '2', '現場中', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (3, 'STAFF_STATUS', 'STAFF_STATUS', '3', '休暇中', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (4, 'STAFF_STATUS', 'STAFF_STATUS', '4', '離職', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (5, 'FILE_PATH_KINMU', 'FILE_PATH_KINMU', '/Users/ting/Documents/java/kinmu/', '', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (6, 'FILE_PATH_KOUTSU', 'FILE_PATH_KOUTSU', '/Users/ting/Documents/java/koutu/', '', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (7, 'FILE_PATH_TATEKAE', 'FILE_PATH_TATEKAE', '/Users/ting/Documents/java/tatekae/', '', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (8, 'PERM_STATUS', '承認状態', '1', '<font style=\"color:red\">未提出</font>', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (9, 'PERM_BG', '権限番号', '0', '社員', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (10, 'PERM_BG', '権限番号', '1', '組長', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (11, 'PERM_BG', '権限番号', '2', '業務', 'JAVA', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (12, 'PERM_BG', '権限番号', '3', '業務', 'SAP', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (13, 'PERM_BG', '権限番号', '4', '財務', 'ALL', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (14, 'PERM_STATUS', '承認状態', '2', '<font style=\"color:blue\">提出済み</font>', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (15, 'PERM_STATUS', '承認状態', '3', '<font style=\"color:green\">承認済み</font>', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (16, 'PERM_STATUS', '承認状態', '0', '<font style=\"color:black\">なし</font>', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (17, 'IT_TYPE', '業務タイプ', 'JAVA', '', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (18, 'IT_TYPE', '業務タイプ', 'SAP', '', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (19, 'BUTTON_TYPE', 'ボタンタイプ', '社員の作成', 'javascript:this.form.action=\'/addStaff\'', '', '', '', '0', '2020-10-20 12:22:00', '', '2020-10-20 12:22:00', '', 1);
INSERT INTO `m_code` VALUES (20, 'CONTRACT_TYPE', '契約タイプ', '0', '個人事業', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (21, 'CONTRACT_TYPE', '契約タイプ', '1', '契約社員', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (22, 'CONTRACT_TYPE', '契約タイプ', '2', '正社員', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for m_kinmu
-- ----------------------------
DROP TABLE IF EXISTS `m_kinmu`;
CREATE TABLE `m_kinmu` (
  `KINMU_ID` bigint NOT NULL AUTO_INCREMENT COMMENT '勤務表ID',
  `STAFF_BG` varchar(15) NOT NULL COMMENT '社員ID',
  `UP_TIME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '提出時間',
  `USED_TIME` date DEFAULT NULL COMMENT '利用期間',
  `GENBAN_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '現場名称',
  `STANDARD_TIME` bigint NOT NULL COMMENT '基準時間',
  `UPPER_TIME` bigint DEFAULT NULL COMMENT '精算時間',
  `WORK_TIME` double DEFAULT NULL COMMENT '仕事時間',
  `ZANGYOU_TIME` double DEFAULT NULL COMMENT '残業時間',
  `FILE_URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `FILE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ファイル名前',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '作成時間',
  `CREATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作成者',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新時間',
  `UPDATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `UPDATE_CONT` int DEFAULT NULL COMMENT '更新回数',
  `START_TIME` date NOT NULL COMMENT '作业开始时间',
  `END_TIME` date NOT NULL COMMENT '作业结束时间',
  `KINMU_YEAR_MONTH` date DEFAULT NULL COMMENT '数据日期',
  `GENBAN_ADDRESS` varchar(255) DEFAULT NULL COMMENT '現場address',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`KINMU_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of m_kinmu
-- ----------------------------
BEGIN;
INSERT INTO `m_kinmu` VALUES (1, '4', '1', '2021-01-01', '1', 1, 1, 1, 1, '/Users/ting/Documents/java/kinmu/6_2020年10月.JPEG', '6_2020年10月.JPEG', '2020-10-22 23:05:48', '1', '2021-02-08 20:26:25', '1', 18, '2020-10-22', '2020-10-22', '2020-11-01', 'abc', 'ooo');
INSERT INTO `m_kinmu` VALUES (2, '2', '2020年10月1日', '2020-11-01', 'qq', 111, 111, 111, 0, '/Users/ting/Documents/java/kinmu/2_null.xls', '2_null.xls', '2020-11-06 19:57:43', 'KINMU_SYS', '2020-11-08 17:18:44', '1', 3, '2020-11-05', '2020-11-06', NULL, NULL, NULL);
INSERT INTO `m_kinmu` VALUES (3, '2', '2020年10月1日', '2020-10-01', 'qq', 111, 111, 111, 0, '/Users/ting/Documents/java/kinmu/2_null.xls', '2_null.xls', '2020-11-06 19:57:43', 'KINMU_SYS', '2020-11-10 23:09:51', '1', 6, '2020-11-05', '2020-11-06', NULL, NULL, NULL);
INSERT INTO `m_kinmu` VALUES (4, '4', '1', '2021-02-01', '1', 1, 1, 1, 1, '1', '1', '2020-10-22 23:05:48', '1', '2020-11-11 17:02:24', '1', 18, '2020-10-22', '2020-10-22', '2020-11-01', 'abc', 'ooo');
INSERT INTO `m_kinmu` VALUES (5, '6', '2020年10月1日', '2020-11-01', 'ceshi', 222, 234, 123, -99, '/Users/ting/Documents/java/kinmu/6_2020年10月.JPEG', '6_2020年10月.JPEG', '2020-11-11 16:17:33', 'KINMU_SYS', '2020-11-12 22:08:08', 'KINMU_SYS', 13, '2020-11-04', '2020-11-11', NULL, 'abef', 'fff');
INSERT INTO `m_kinmu` VALUES (6, '8', '2020年11月1日', '2020-11-01', '岩本町', 130, 160, 196.4166717529297, 36.41667175292969, '/Users/ting/Documents/java/kinmu/8_2020年11月.xlsx', '8_2020年11月.xlsx', '2020-11-26 14:38:14', 'KINMU_SYS', '2020-12-07 21:17:29', 'KINMU_SYS', 4, '2020-11-02', '2020-11-26', NULL, 'abc', 'fff');
INSERT INTO `m_kinmu` VALUES (7, '9', '2020年11月1日', '2020-11-01', '', 120, 160, 123, 0, NULL, NULL, '2020-12-03 15:40:32', 'KINMU_SYS', '2020-12-03 16:00:37', 'KINMU_SYS', 4, '2020-12-01', '2020-12-03', NULL, 'sss', 'fff');
COMMIT;

-- ----------------------------
-- Table structure for m_koutu
-- ----------------------------
DROP TABLE IF EXISTS `m_koutu`;
CREATE TABLE `m_koutu` (
  `KOUTU_ID` bigint NOT NULL AUTO_INCREMENT COMMENT '交通ID',
  `STAFF_BG` varchar(15) NOT NULL COMMENT '社員ID',
  `UP_TIME` varchar(255) DEFAULT NULL COMMENT '提出時間',
  `START_ST` varchar(255) DEFAULT NULL COMMENT '始発駅',
  `END_ST` varchar(255) DEFAULT NULL COMMENT '終点駅',
  `USED_MONEY_ALL` double(255,2) DEFAULT NULL COMMENT '総金額',
  `USED_MONEY_ETC` double(255,2) DEFAULT NULL COMMENT 'ファイル総金額',
  `USED_MONEY_TSUKIN` double(255,2) DEFAULT NULL COMMENT '通勤金額',
  `USED_TIME` date DEFAULT NULL COMMENT '利用日付',
  `FILE_NAME` varchar(255) DEFAULT NULL COMMENT 'ファイル名前',
  `FILE_URL` varchar(255) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(255) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `UPDATE_CONT` int DEFAULT NULL,
  PRIMARY KEY (`KOUTU_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of m_koutu
-- ----------------------------
BEGIN;
INSERT INTO `m_koutu` VALUES (7, '2', '2020年11月6日', 'fff', 'www', 123.00, 111.00, 222.00, '2020-10-01', '2_2020年10月.xls', NULL, '2020-11-06 20:31:50', 'KOUTU_SYS', '2020-11-10 23:10:01', '1', 3);
INSERT INTO `m_koutu` VALUES (8, '4', '2021年1月1日', '饿', '的www', 2.00, 8990.00, 333.00, '2021-01-01', '4_2020年10月.JPEG', '/Users/ting/Documents/java/koutu/4_2020年10月.JPEG', '2020-11-06 20:31:50', 'KOUTU_SYS', '2021-02-08 21:33:38', '1', 5);
INSERT INTO `m_koutu` VALUES (9, '3', '2020年11月10日', 'fff', 'qwe', 123.00, 111.00, 12.00, '2020-10-01', NULL, NULL, '2020-11-10 20:18:52', 'KOUTU_SYS', '2020-11-11 17:01:02', '1', 1);
INSERT INTO `m_koutu` VALUES (10, '6', '2020年11月11日', 'a', 'b', 444.00, 111.00, 333.00, '2020-09-01', '6_2020年10月.xls', NULL, '2020-11-11 16:11:55', 'KOUTU_SYS', '2020-11-11 16:11:55', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (12, '6', '2020年10月1日', '啊啊啊啊啊啊啊啊啊', 'www', 1500.00, 700.00, 800.00, '2020-11-01', '6_2020年10月.xls', '/Users/ting/Documents/java/koutu/6_2020年10月.xls', '2020-11-12 16:01:15', 'KOUTU_SYS', '2020-11-13 16:02:21', 'KOUTU_SYS', 9);
INSERT INTO `m_koutu` VALUES (13, '8', '2020年10月1日', '', '', 1500.00, 700.00, 800.00, '2020-10-01', '8_2020年10月.xls', '/Users/ting/Documents/java/koutu/8_2020年10月.xls', '2020-11-13 15:38:47', 'KOUTU_SYS', '2020-11-13 15:38:47', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (14, '8', '2020年11月1日', 'asf', '是啥', 8990.00, 8990.00, 200.00, '2020-11-01', '8_2020年11月.xls', '/Users/ting/Documents/java/koutu/8_2020年11月.xls', '2020-11-26 14:40:21', 'KOUTU_SYS', '2020-12-11 02:50:13', 'KOUTU_SYS', 5);
COMMIT;

-- ----------------------------
-- Table structure for m_staff
-- ----------------------------
DROP TABLE IF EXISTS `m_staff`;
CREATE TABLE `m_staff` (
  `STAFF_BG` varchar(15) NOT NULL COMMENT '社員番号',
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `STAFF_NAME` varchar(255) DEFAULT NULL COMMENT '社員名称',
  `STAFF_MAILADDRESS` varchar(255) NOT NULL COMMENT '社員メールアドレス',
  `STAFF_SEX` varchar(255) DEFAULT NULL COMMENT '社員性別',
  `PERMISSION_BG` int NOT NULL COMMENT '社員権限番号',
  `STAFF_STATUS` int DEFAULT NULL COMMENT '社員の現在状態',
  `STANDARD_TIME` bigint DEFAULT NULL COMMENT '基準時間',
  `UPPER_TIME` bigint DEFAULT NULL COMMENT '精算時間',
  `GROUP_ID` int DEFAULT NULL COMMENT '社員の組番号',
  `GENBAN_NAME` varchar(255) DEFAULT NULL COMMENT '現場名称',
  `IT_TYPE` varchar(255) DEFAULT NULL COMMENT '業務タイプ',
  `LM_KINMU_STATUS` int NOT NULL COMMENT '先月勤务表是否提出,0未提出',
  `LM_KOUTU_STATUS` int NOT NULL COMMENT '先月交通表是否提出,0为未提出',
  `LM_TATEKAE_STATUS` int NOT NULL COMMENT '先月立替金是否提出,0为未提出',
  `TM_KINMU_STATUS` int NOT NULL COMMENT '本月勤务表是否提出,0未提出',
  `TM_KOUTU_STATUS` int NOT NULL COMMENT '本月交通表是否提出,0未提出',
  `TM_TATEKAE_STATUS` int NOT NULL COMMENT '本月立替金表是否提出,0未提出',
  `CREATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `UPDATE_CONT` int DEFAULT NULL,
  `IS_LEADER` bit(10) DEFAULT NULL,
  `LEADER_ID` bigint DEFAULT NULL,
  `GENBAN_ADDRESS` varchar(255) DEFAULT NULL COMMENT '现场地址',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `RESIGN_FLAG` int DEFAULT NULL COMMENT '退職マーク,退職:0',
  `RESIGN_TYPE` varchar(255) DEFAULT NULL COMMENT '退職の種類',
  `RESIGN_TIME` date DEFAULT NULL COMMENT '退職時間',
  `RESIGN_REASON` varchar(255) DEFAULT NULL COMMENT '退職理由',
  `CONTRACT_TYPE` int NOT NULL COMMENT '契约状态,0个人事业组,1契约社员,2正社员',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of m_staff
-- ----------------------------
BEGIN;
INSERT INTO `m_staff` VALUES ('1', 1, 'admin', 'admin@mail.com', '女', 4, 1, 140, 140, 1, '品川', 'JAVA', 0, 0, 0, 0, 0, 2, '1', '2020-10-20 13:33:44', '2020-10-20 13:33:47', '0\r', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 1);
INSERT INTO `m_staff` VALUES ('2', 2, '123', '1@1.co', '男', 0, 2, 123, 140, 1, 'abg', 'JAVA', 1, 1, 0, 2, 2, 0, '1', '2020-10-20 13:33:44', '2020-10-20 13:33:47', '0\r', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 2);
INSERT INTO `m_staff` VALUES ('3', 3, 'test2', '2@2.co', '男', 1, 2, 123, 140, 1, 'abg', 'JAVA', 0, 1, 0, 0, 2, 2, '1', '2020-10-20 13:33:44', '2020-10-20 13:33:47', '0\r', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 0);
INSERT INTO `m_staff` VALUES ('4', 4, '4fan', '3@3.co', '男', 3, 1, 140, 140, 1, '', 'JAVA', 0, 0, 0, 2, 2, 1, '1', '2020-10-20 13:33:44', '2020-10-20 13:33:47', '0\r', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 2);
INSERT INTO `m_staff` VALUES ('5', 5, 'admin2', 'admin@admin', '女', 4, 1, 140, 140, 1, '品川', 'JAVA', 0, 0, 0, 0, 0, 2, '1', '2020-10-20 13:33:44', '2020-10-20 13:33:47', '0\r', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 0);
INSERT INTO `m_staff` VALUES ('6', 6, '测试', '6@6.co', '女', 3, 2, 222, 234, 1, 'ceshi', 'JAVA', 0, 0, 0, 1, 1, 2, '1', '2020-10-20 13:33:44', '2020-10-20 13:33:47', '0\r', 1, NULL, NULL, 'xinmu', '          wwwww          ', 1, NULL, NULL, NULL, 1);
INSERT INTO `m_staff` VALUES ('7', 7, 'test', '7@7', '女', 0, NULL, NULL, NULL, 1, '', 'JAVA', 0, 0, 0, 0, 0, 0, '1', '2020-10-20 13:33:44', '2020-10-20 13:33:47', '0\r', 1, NULL, NULL, '', '', 1, NULL, NULL, NULL, 1);
INSERT INTO `m_staff` VALUES ('8', 8, 'cun', 'staff@mail.com', '男', 0, 2, 130, 160, 1, '岩本町', 'JAVA', 0, 0, 0, 1, 1, 1, '1', '2020-10-20 13:33:44', '2020-10-20 13:33:47', '0\r', 1, NULL, NULL, 'shfafd', '11', 1, NULL, NULL, NULL, 2);
INSERT INTO `m_staff` VALUES ('9', 9, 'abb', 'staff1@mail.com', '男', 0, 2, 120, 160, 1, '', 'JAVA', 0, 0, 0, 1, 0, 0, '1', '2020-10-20 13:33:44', '2020-10-20 13:33:47', '0\r', 1, NULL, NULL, '', '', 1, NULL, NULL, NULL, 2);
COMMIT;

-- ----------------------------
-- Table structure for m_sys_manager
-- ----------------------------
DROP TABLE IF EXISTS `m_sys_manager`;
CREATE TABLE `m_sys_manager` (
  `GYOMU_DATE` date NOT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`GYOMU_DATE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of m_sys_manager
-- ----------------------------
BEGIN;
INSERT INTO `m_sys_manager` VALUES ('2020-09-01', '2020-10-23 16:52:49');
INSERT INTO `m_sys_manager` VALUES ('2020-10-01', '2020-10-23 16:52:49');
INSERT INTO `m_sys_manager` VALUES ('2020-11-01', '2020-10-23 16:52:49');
COMMIT;

-- ----------------------------
-- Table structure for m_tatekae
-- ----------------------------
DROP TABLE IF EXISTS `m_tatekae`;
CREATE TABLE `m_tatekae` (
  `TATEKAE_ID` bigint NOT NULL AUTO_INCREMENT COMMENT '立替金ID',
  `STAFF_BG` varchar(15) NOT NULL COMMENT '社員ID',
  `UP_TIME` varchar(255) DEFAULT NULL COMMENT '提出時間',
  `USED_PRICE` double(255,0) DEFAULT NULL COMMENT '金額',
  `USED_TIME` date DEFAULT NULL COMMENT '利用期間',
  `FILE_NAME` varchar(255) DEFAULT NULL COMMENT 'ファイル名前',
  `SUB_COMPANY` varchar(255) DEFAULT NULL COMMENT '会社名',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '作成時間',
  `CREATE_USER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作成者',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新時間',
  `UPDATE_USER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `UPDATE_CONT` int DEFAULT NULL COMMENT '更新回数',
  `FILE_URL` varchar(255) DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`TATEKAE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of m_tatekae
-- ----------------------------
BEGIN;
INSERT INTO `m_tatekae` VALUES (1, '2', '222', 5, '2020-11-01', NULL, '他天天', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_tatekae` VALUES (2, '6', '2020年11月12日', 333, '2020-10-01', '6_2020年10月.xlsx', 'ABC', '2020-11-11 16:26:06', 'TATEKAE_SYS', '2020-11-13 15:58:53', '1', 16, '/Users/ting/Documents/java/tatekae/6_2020年10月.xlsx');
INSERT INTO `m_tatekae` VALUES (3, '4', '2020年11月13日', 1000, '2020-10-01', '4_2020年10月.JPEG', 'ABC', '2020-11-13 03:39:05', 'TATEKAE_SYS', '2020-11-13 03:39:05', 'TATEKAE_SYS', 0, '/Users/ting/Documents/java/tatekae/4_2020年10月.JPEG');
INSERT INTO `m_tatekae` VALUES (4, '8', '2020年11月13日', 1000, '2020-10-01', '8_2020年10月.HEIC', 'ABC', '2020-11-13 15:40:49', 'TATEKAE_SYS', '2020-11-13 15:40:49', 'TATEKAE_SYS', 0, '/Users/ting/Documents/java/tatekae/8_2020年10月.HEIC');
INSERT INTO `m_tatekae` VALUES (5, '8', '2020年11月26日', 5543, '2020-11-01', '8_2020年11月.xlsx', '', '2020-11-26 14:55:50', 'TATEKAE_SYS', '2020-11-26 14:55:50', 'TATEKAE_SYS', 0, '/Users/ting/Documents/java/tatekae/8_2020年11月.xlsx');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
