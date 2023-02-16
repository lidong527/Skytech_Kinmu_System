/*
 Navicat Premium Data Transfer

 Source Server         : servers
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 192.168.10.102:3306
 Source Schema         : skytech

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 30/10/2020 15:03:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for m_account
-- ----------------------------
DROP TABLE IF EXISTS `m_account`;
CREATE TABLE `m_account`  (
  `USER_MAIL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'アカウントのユーザー名',
  `PASSWORD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'アカウントのパスワード',
  `PASS_SEC` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '暗号化したのアカウントのパスワード',
  `INITIALIZATION_FLG` bit(1) NULL DEFAULT NULL COMMENT '初期化状態がどうかの判断フラグ',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '作成時間',
  `CREATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作成者',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新時間',
  `UPDATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `UPDATE_CONT` int(0) NULL DEFAULT NULL COMMENT '更新回数',
  PRIMARY KEY (`USER_MAIL`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_account
-- ----------------------------
INSERT INTO `m_account` VALUES ('00@00', '11111111', 'JOeIjstsRn4YfY/iUSj/8Q==', b'0', '2020-10-21 11:07:56', 'ADD_STAFF_SYS', '2020-10-21 11:07:56', 'ADD_STAFF_SYS', 0);
INSERT INTO `m_account` VALUES ('1@1', '11111111', 'JOeIjstsRn4YfY/iUSj/8Q==', b'1', '2020-06-10 00:00:00', 'USER', '2020-08-07 00:00:00', 'USer', 0);
INSERT INTO `m_account` VALUES ('123', 'VGG1a9bhr0', 'e9im0LIbDbE8QbQmrRzZWA==', b'1', '2020-10-29 15:22:14', 'ADD_STAFF_SYS', '2020-10-29 15:22:14', 'ADD_STAFF_SYS', 0);
INSERT INTO `m_account` VALUES ('2@2', '11111111', 'JOeIjstsRn4YfY/iUSj/8Q==', b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_account` VALUES ('5@5', '12345678', 'L5e6zSxztsHmfxYbVccCqQ==', b'0', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for m_code
-- ----------------------------
DROP TABLE IF EXISTS `m_code`;
CREATE TABLE `m_code`  (
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `CODE_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'コードID',
  `CODE_DES` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'コード解説',
  `CODE_CONTENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'コード内容',
  `CODE_CONTENT2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'コード内容2',
  `CODE_CONTENT3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'コード内容3',
  `CODE_CONTENT4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'コード内容4',
  `CODE_CONTENT5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'コード内容5',
  `DELETE_FLG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '癈棄フラグ',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '作成時間',
  `CREATE_USER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成者',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新時間',
  `UPDATE_USER` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `UPDATE_CONT` int(0) NULL DEFAULT NULL COMMENT '更新回数',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_code
-- ----------------------------
INSERT INTO `m_code` VALUES (1, 'STAFF_STATUS', 'STAFF_STATUS', '1', '待機中', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (2, 'STAFF_STATUS', 'STAFF_STATUS', '2', '現場中', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (3, 'STAFF_STATUS', 'STAFF_STATUS', '3', '休暇中', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (4, 'STAFF_STATUS', 'STAFF_STATUS', '4', '離職', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (5, 'FILE_PATH_KINMU', 'FILE_PATH_KINMU', 'D:/File/Kinmu/', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (6, 'FILE_PATH_KOUTSU', 'FILE_PATH_KOUTSU', 'D:/File/Koutsu/', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (7, 'FILE_PATH_TATE', 'FILE_PATH_TATE', 'D:/File/Tate/', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (8, 'PERM_STATUS', '承認状態', '1', '<font style=\"color:red\">未提出</font>', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (9, 'PERM_BG', '権限番号', '0', '社員', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (10, 'PERM_BG', '権限番号', '1', '組長', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (11, 'PERM_BG', '権限番号', '2', '業務', 'JAVA', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (12, 'PERM_BG', '権限番号', '3', '業務', 'SAP', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (13, 'PERM_BG', '権限番号', '4', '財務', 'ALL', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (14, 'PERM_STATUS', '承認状態', '2', '<font style=\"color:blue\">提出済み</font>', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (15, 'PERM_STATUS', '承認状態', '3', '<font style=\"color:green\">承認済み</font>', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (16, 'PERM_STATUS', '承認状態', '0', '<font style=\"color:black\">なし</font>', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (17, 'IT_TYPE', '業務タイプ', 'JAVA', NULL, NULL, NULL, '', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (18, 'IT_TYPE', '業務タイプ', 'SAP', NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `m_code` VALUES (19, 'BUTTON_TYPE', 'ボタンタイプ', '社員の作成', 'javascript:this.form.action=\'/addStaff\'', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for m_kinmu
-- ----------------------------
DROP TABLE IF EXISTS `m_kinmu`;
CREATE TABLE `m_kinmu`  (
  `KINMU_ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '勤務表ID',
  `STAFF_BG` bigint(0) NOT NULL COMMENT '社員ID',
  `UP_TIME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '提出時間',
  `USED_TIME` date NULL DEFAULT NULL COMMENT '利用期間',
  `GENBAN_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '現場名称',
  `STANDARD_TIME` bigint(0) NOT NULL COMMENT '基準時間',
  `UPPER_TIME` bigint(0) NULL DEFAULT NULL COMMENT '精算時間',
  `WORK_TIME` double NULL DEFAULT NULL COMMENT '仕事時間',
  `ZANGYOU_TIME` double NULL DEFAULT NULL COMMENT '残業時間',
  `FILE_URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `FILE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ファイル名前',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '作成時間',
  `CREATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作成者',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新時間',
  `UPDATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `UPDATE_CONT` int(0) NULL DEFAULT NULL COMMENT '更新回数',
  `START_TIME` date NOT NULL,
  `END_TIME` date NOT NULL,
  `GENBAN_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '現場address',
  `REMARKS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '備考',
  PRIMARY KEY (`KINMU_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 168 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_kinmu
-- ----------------------------
INSERT INTO `m_kinmu` VALUES (167, 123, '2020年10月28日', '2020-10-01', 'sky-tech', 140, 180, 167, 0, NULL, NULL, '2020-10-28 17:13:51', 'KINMU_SYS', '2020-10-28 17:13:51', 'KINMU_SYS', 0, '2020-10-14', '2020-10-08', '住所', '備考');

-- ----------------------------
-- Table structure for m_koutu
-- ----------------------------
DROP TABLE IF EXISTS `m_koutu`;
CREATE TABLE `m_koutu`  (
  `KOUTU_ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '交通ID',
  `STAFF_BG` bigint(0) NOT NULL COMMENT '社員ID',
  `UP_TIME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '提出時間',
  `START_ST` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '始発駅',
  `END_ST` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '終点駅',
  `USED_MONEY_ALL` double(255, 0) NULL DEFAULT NULL COMMENT '総金額',
  `USED_MONEY_ETC` double(255, 0) NULL DEFAULT NULL COMMENT 'ファイル総金額',
  `USED_MONEY_TSUKIN` double(255, 0) NULL DEFAULT NULL COMMENT '通勤金額',
  `USED_TIME` date NULL DEFAULT NULL COMMENT '利用日付',
  `FILE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ファイル名前',
  `FILE_URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `CREATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  `UPDATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `UPDATE_CONT` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`KOUTU_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_koutu
-- ----------------------------
INSERT INTO `m_koutu` VALUES (2, 123, '2020年10月29日', '', '', 1500, 700, 800, '2020-01-01', '123_2020年1月.xls', NULL, '2020-10-29 14:13:07', 'KOUTU_SYS', '2020-10-29 14:13:07', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (3, 123, '2020年10月29日', '二分法', 'v给屡屡', 1500, 700, 800, '2020-02-01', '123_2020年2月.xls', NULL, '2020-10-29 14:17:42', 'KOUTU_SYS', '2020-10-29 14:17:42', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (4, 123, '2020年10月29日', '1', '1', 1500, 700, 800, '2020-03-01', '123_2020年3月.xls', NULL, '2020-10-29 14:18:59', 'KOUTU_SYS', '2020-10-29 14:18:59', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (5, 123, '2020年10月29日', '1', '1', 1500, 700, 800, '2020-04-01', '123_2020年4月.xls', NULL, '2020-10-29 14:20:37', 'KOUTU_SYS', '2020-10-29 14:20:37', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (6, 123, '2020年10月29日', '1', '333', 1500, 700, 800, '2020-05-01', '123_2020年5月.xls', NULL, '2020-10-29 14:24:39', 'KOUTU_SYS', '2020-10-29 14:24:39', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (7, 123, '2020年10月29日', '', '', 1500, 700, 800, '2020-09-01', '123_2020年9月.xls', NULL, '2020-10-29 14:41:18', 'KOUTU_SYS', '2020-10-29 14:41:18', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (8, 123, '2020年10月29日', '123', '321', 1500, 700, 800, '2020-08-01', '123_2020年8月.xls', NULL, '2020-10-29 15:16:22', 'KOUTU_SYS', '2020-10-29 15:16:22', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (9, 123, '2020年10月29日', '', '', 1500, 700, 800, '2020-10-01', '123_2020年10月.xls', NULL, '2020-10-29 15:20:26', 'KOUTU_SYS', '2020-10-29 15:20:26', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (10, 102, '2020年10月29日', '', '', 1500, 700, 800, '2020-10-01', '102_2020年10月.xls', NULL, '2020-10-29 15:23:53', 'KOUTU_SYS', '2020-10-29 15:23:53', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (11, 102, '2020年10月29日', '', '', 1500, 700, 800, '2020-11-01', '102_2020年10月.xls', NULL, '2020-10-29 15:35:06', 'KOUTU_SYS', '2020-10-29 15:35:06', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (12, 102, '2020年10月29日', '', '', 1500, 700, 800, '2020-11-01', '102_2020年10月.xls', NULL, '2020-10-29 15:35:55', 'KOUTU_SYS', '2020-10-29 15:35:55', 'KOUTU_SYS', 0);
INSERT INTO `m_koutu` VALUES (13, 102, '2020年10月29日', '二分法', '1', 1500, 700, 800, '2020-11-01', NULL, NULL, '2020-10-29 15:36:04', 'KOUTU_SYS', '2020-10-29 15:36:04', 'KOUTU_SYS', 0);

-- ----------------------------
-- Table structure for m_staff
-- ----------------------------
DROP TABLE IF EXISTS `m_staff`;
CREATE TABLE `m_staff`  (
  `STAFF_BG` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社員番号',
  `ID` bigint(0) NOT NULL AUTO_INCREMENT,
  `STAFF_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社員名称',
  `STAFF_MAILADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社員メールアドレス',
  `STAFF_SEX` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社員性別',
  `PERMISSION_BG` int(0) NOT NULL COMMENT '社員権限番号',
  `STAFF_STATUS` int(0) NULL DEFAULT NULL COMMENT '社員の現在状態',
  `STANDARD_TIME` bigint(0) NULL DEFAULT NULL COMMENT '基準時間',
  `UPPER_TIME` bigint(0) NULL DEFAULT NULL COMMENT '精算時間',
  `GROUP_ID` int(0) NULL DEFAULT NULL COMMENT '社員の組番号',
  `GENBAN_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '現場名称',
  `IT_TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '業務タイプ',
  `LM_KINMU_STATUS` int(0) NULL DEFAULT NULL COMMENT '先月勤?表是否提出,0未提出',
  `LM_KOUTU_STATUS` int(0) NULL DEFAULT NULL COMMENT '先月交通表是否提出,0?未提出',
  `LM_TATEKAE_STATUS` int(0) NULL DEFAULT NULL COMMENT '先月立替金是否提出,0?未提出',
  `TM_KINMU_STATUS` int(0) NULL DEFAULT NULL COMMENT '本月勤?表是否提出,0未提出',
  `TM_KOUTU_STATUS` int(0) NULL DEFAULT NULL COMMENT '本月交通表是否提出,0未提出',
  `TM_TATEKAE_STATUS` int(0) NULL DEFAULT NULL COMMENT '本月立替金表是否提出,0未提出',
  `CREATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  `UPDATE_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `UPDATE_CONT` int(0) NULL DEFAULT NULL,
  `IS_LEADER` bit(10) NULL DEFAULT NULL,
  `LEADER_ID` bigint(0) NULL DEFAULT NULL,
  `GENBAN_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '現場address',
  `REMARKS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '備考',
  `RESIGN_FLAG` int(0) NULL DEFAULT NULL COMMENT '退職マーク,退職:0',
  `RESIGN_TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '退職の種類',
  `RESIGN_TIME` date NULL DEFAULT NULL COMMENT '退職時間',
  `RESIGN_REASON` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '退職理由',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_staff
-- ----------------------------
INSERT INTO `m_staff` VALUES ('123', 1, 'zsw', '5@5', '女', 1, 1, 160, 200, NULL, 'test', 'JAVA', 1, 1, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL);
INSERT INTO `m_staff` VALUES ('20201019', 2, '1111', '2@2', '男', 4, 1, 140, 180, NULL, 'test', NULL, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, null, NULL);
INSERT INTO `m_staff` VALUES ('2016081701', 6, '李東', '1@1.co', '男', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, null, NULL);
INSERT INTO `m_staff` VALUES ('2020020202', 7, '周テイテイ', '2@2.co', '女', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, null, NULL);
INSERT INTO `m_staff` VALUES ('2018102501', 8, NULL, '3@3.co', '女', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, null, NULL);
INSERT INTO `m_staff` VALUES ('2020020801', 9, '劉洋', '6@6', '男', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, null, NULL);
INSERT INTO `m_staff` VALUES ('102', 12, 'liuy', '00@00', '男', 4, 1, NULL, NULL, NULL, '', '', 0, 1, 0, 0, 0, 0, '', NULL, NULL, '', 0, NULL, NULL, '', '          ', NULL, NULL, null, NULL);

-- ----------------------------
-- Table structure for m_sys_manager
-- ----------------------------
DROP TABLE IF EXISTS `m_sys_manager`;
CREATE TABLE `m_sys_manager`  (
  `GYOMU_DATE` date NOT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`GYOMU_DATE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_sys_manager
-- ----------------------------
INSERT INTO `m_sys_manager` VALUES ('2020-09-01', '2020-10-23 16:52:49');
INSERT INTO `m_sys_manager` VALUES ('2020-10-01', '2020-10-28 18:45:38');

-- ----------------------------
-- Table structure for m_tatekae
-- ----------------------------
DROP TABLE IF EXISTS `m_tatekae`;
CREATE TABLE `m_tatekae`  (
  `TATEKAE_ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '立替金ID',
  `STAFF_BG` bigint(0) NOT NULL COMMENT '社員ID',
  `UP_TIME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '提出時間',
  `USED_PRICE` double(255, 0) NULL DEFAULT NULL COMMENT '金額',
  `USED_TIME` date NULL DEFAULT NULL COMMENT '利用期間',
  `FILE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ファイル名前',
  `SUB_COMPANY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会社名',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '作成時間',
  `CREATE_USER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成者',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新時間',
  `UPDATE_USER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `UPDATE_CONT` int(0) NULL DEFAULT NULL COMMENT '更新回数',
  PRIMARY KEY (`TATEKAE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Procedure structure for System_Monitor
-- ----------------------------
DROP PROCEDURE IF EXISTS `System_Monitor`;
delimiter ;;
CREATE DEFINER=`test`@`192.168.10.%` PROCEDURE `System_Monitor`()
BEGIN
	SELECT max(GYOMU_DATE) INTO @GYOMU_DATE
	FROM m_sys_manager;
	
  IF (@GYOMU_DATE is null) THEN
		begin
			insert into m_sys_manager
			(GYOMU_DATE, UPDATE_TIME)
			values
			(ADDDate(ADDDATE(LAST_DAY(now()), INTERVAL 1 DAY), INTERVAL -1 MONTH), SYSDATE());
		end;
	ELSE
		begin
			if(MONTH(now()) <> MONTH(@GYOMU_DATE)) and (Day(NOW())>15) THEN
					begin
						insert into m_sys_manager
						(GYOMU_DATE, UPDATE_TIME)
						values
						(ADDDate(ADDDATE(LAST_DAY(now()), INTERVAL 1 DAY), INTERVAL -1 MONTH), SYSDATE());
						
						update m_staff
						set TM_KINMU_STATUS=0
						   ,TM_KOUTU_STATUS=0
							 ,TM_TATEKAE_STATUS=0;
							 
					end;
			end IF;
		end;
	end IF;
END
;;
delimiter ;

-- ----------------------------
-- Event structure for SYS_Monitor
-- ----------------------------
DROP EVENT IF EXISTS `SYS_Monitor`;
delimiter ;;
CREATE DEFINER = `test`@`192.168.10.%` EVENT `SYS_Monitor`
ON SCHEDULE
EVERY '1' DAY STARTS '2020-10-23 18:45:24'
ON COMPLETION PRESERVE
DO CALL System_Monitor()
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
