/*
Navicat MySQL Data Transfer

Source Server         : jpetstore
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : medicalsystem

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-09-18 18:44:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admininfo
-- ----------------------------
DROP TABLE IF EXISTS `admininfo`;
CREATE TABLE `admininfo` (
  `adminAccount` int(11) NOT NULL,
  `adminPassword` varchar(100) NOT NULL,
  `phonenumber` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`adminAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admininfo
-- ----------------------------
INSERT INTO `admininfo` VALUES ('123456', '123456', '18956778818');

-- ----------------------------
-- Table structure for departmentinfo
-- ----------------------------
DROP TABLE IF EXISTS `departmentinfo`;
CREATE TABLE `departmentinfo` (
  `depId` int(11) NOT NULL,
  `depName` varchar(20) DEFAULT NULL,
  `depDoctorNumber` int(11) DEFAULT NULL,
  `depBedNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`depId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of departmentinfo
-- ----------------------------
INSERT INTO `departmentinfo` VALUES ('1', '急诊科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('2', '儿科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('3', '眼科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('4', '口腔科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('5', '皮肤科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('6', '妇产科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('7', '内分泌科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('8', '骨科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('9', '肝胆外科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('10', '泌尿外科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('11', '耳鼻喉科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('12', '心血管内科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('13', '神经内科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('14', '肛肠外科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('15', '乳腺甲状腺外科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('16', '整形、激光美容外科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('17', '神经外科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('18', '心胸外科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('19', '消化内科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('20', '呼吸内科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('21', '肿瘤科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('22', '中医科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('23', '肾内科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('24', '检验科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('25', '病理科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('26', '放射科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('27', '药剂科', '5', '20');
INSERT INTO `departmentinfo` VALUES ('28', '麻醉科', '5', '20');

-- ----------------------------
-- Table structure for doctorinfo
-- ----------------------------
DROP TABLE IF EXISTS `doctorinfo`;
CREATE TABLE `doctorinfo` (
  `docId` int(11) NOT NULL,
  `docName` varchar(20) NOT NULL,
  `docGender` char(2) NOT NULL,
  `docAge` int(11) DEFAULT NULL,
  `docPwd` varchar(100) NOT NULL,
  `docDepartment` varchar(50) NOT NULL,
  `docRank` varchar(20) NOT NULL,
  `docTel` char(11) DEFAULT NULL,
  `onDuty` char(8) DEFAULT NULL,
  `offDuty` char(8) DEFAULT NULL,
  `phonenumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`docId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of doctorinfo
-- ----------------------------
INSERT INTO `doctorinfo` VALUES ('10000', '刘英杰', '男', '34', '1', '儿科', '住院医师', '17325204148', '8:00', '16:30', '');
INSERT INTO `doctorinfo` VALUES ('10001', '崔扬波', '男', '45', '123456', '眼科', '主治医师', '18211231636', '8:30', '17:00', '');
INSERT INTO `doctorinfo` VALUES ('10002', '张子铭', '男', '52', '123456', '耳鼻喉科', '副主任医师', '19312125252', '7:00', '16:30', '');
INSERT INTO `doctorinfo` VALUES ('10003', '罗哲翰', '男', '47', '123456', '神经内科', '主任医师', '18232541649', '7:00', '17:30', '');
INSERT INTO `doctorinfo` VALUES ('10004', '梁浩南', '男', '37', '123456', '肛肠外科', '主治医师', '18623075802', '7:00', '18:00', '');
INSERT INTO `doctorinfo` VALUES ('10005', '刘彦豪', '男', '51', '123456', '急诊科', '主任医师', '19328285345', '9:00', '17:30', '');
INSERT INTO `doctorinfo` VALUES ('10006', '陈宏杰', '男', '62', '123456', '中医科', '主任医师', '18412412419', '9:00', '16:00', '');
INSERT INTO `doctorinfo` VALUES ('10007', '钱博森', '男', '31', '123456', '口腔科', '住院医师', '15325239688', '7:00', '17:30', '');
INSERT INTO `doctorinfo` VALUES ('10008', '李邵元', '男', '24', '123456', '骨科', '住院医师', '18934633423', '8:00', '17:30', '');
INSERT INTO `doctorinfo` VALUES ('10009', '梁文斌', '男', '51', '123456', '泌尿外科', '副主任医师', '19434634234', '7:30', '17:00', '');
INSERT INTO `doctorinfo` VALUES ('10010', '蒋熙诚', '男', '40', '123456', '心血管内科', '副主任医师', '19523542342', '8:00', '18:00', '');
INSERT INTO `doctorinfo` VALUES ('10011', '董望舒', '男', '38', '123456', '肿瘤科', '主治医师', '18521431234', '7:30', '17:00', '');
INSERT INTO `doctorinfo` VALUES ('10012', '罗贝尔', '男', '19', '123456', '骨科', '主任医师', '15892161729', '7:00', '18:00', '');
INSERT INTO `doctorinfo` VALUES ('10013', '吴文昊', '男', '40', '123456', '肾内科', '副主任医师', '18599232254', '7:30', '17:00', '');
INSERT INTO `doctorinfo` VALUES ('10014', '赵飞龙', '男', '46', '123456', '肝胆外科', '主治医师', '13921344421', '8:00', '17:30', '18956778815');
INSERT INTO `doctorinfo` VALUES ('10015', '欧阳雨婕', '女', '18', '123456', '麻醉科', '主任医师', '18221423551', '7:00', '18:00', '');
INSERT INTO `doctorinfo` VALUES ('10016', '李瑞欣', '女', '23', '123456', '检验科', '副主任医师', '19202035102', '8:00', '17:30', '');
INSERT INTO `doctorinfo` VALUES ('10017', '姚文进', '男', '20', '123456', '肿瘤科', '住院医师', '17521402351', '9:00', '16:30', '');
INSERT INTO `doctorinfo` VALUES ('10018', '刘艾琳', '女', '28', '123456', '急诊科', '副主任医师', '18329021512', '8:00', '17:30', '');
INSERT INTO `doctorinfo` VALUES ('10019', ' 周海玲', '女', '40', '123456', '妇产科', '主任医师', '19412235032', '7:30', '17:00', '');
INSERT INTO `doctorinfo` VALUES ('10020', '李雪茹', '女', '29', '123456', '皮肤科', '主任医师', '19422523002', '7:00', '17:30', '');
INSERT INTO `doctorinfo` VALUES ('10021', '祝瑾萱', '女', '25', '123456', '消化内科', '主治医师', '13534436259', '7:40', '18:00', '');
INSERT INTO `doctorinfo` VALUES ('10022', '梁雪飞', '女', '33', '123456', '儿科', '住院医师', '13634424124', '8:00', '18:00', '');
INSERT INTO `doctorinfo` VALUES ('10023', '赵惠宁', '女', '34', '123456', '儿科', '主治医师', '18952224214', '7:30', '18:00', '');
INSERT INTO `doctorinfo` VALUES ('10025', '周永怡', '女', '24', '123456', '儿科', '副主任医师', '17482949125', '7:00', '17:00', '');
INSERT INTO `doctorinfo` VALUES ('10026', '王思涵', '女', '37', '123456', '肾内科', '住院医师', '18332504939', '8:00', '18:00', '');
INSERT INTO `doctorinfo` VALUES ('10027', '刘丽娇', '女', '23', '123456', '眼科', '主治医师', '19323050232', '7:30', '18:00', '');
INSERT INTO `doctorinfo` VALUES ('10028', '张雯珊', '女', '24', '123456', '眼科', '主任医师', '18331250230', '7:00', '17:00', '');
INSERT INTO `doctorinfo` VALUES ('10029', '唐明', '男', '30', '123456', '急诊科', '主任医师', '12345678999', '7:00', '13:25', '');
INSERT INTO `doctorinfo` VALUES ('10030', '张明天', '男', '25', '123456', '急诊科', '副主任医师', '12345874566', '7:00', '18:25', '');

-- ----------------------------
-- Table structure for medicalrecordsinfo
-- ----------------------------
DROP TABLE IF EXISTS `medicalrecordsinfo`;
CREATE TABLE `medicalrecordsinfo` (
  `recordId` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` char(10) NOT NULL,
  `curDepartment` varchar(50) NOT NULL,
  `curDoctor` varchar(20) NOT NULL,
  `patDemands` varchar(200) NOT NULL,
  `patId1` int(11) NOT NULL,
  `Result` varchar(200) NOT NULL,
  `patConsumption` double DEFAULT NULL,
  `isInHospital` char(2) NOT NULL,
  `inDate` char(10) DEFAULT NULL,
  `outDate` char(10) NOT NULL,
  `roomId` varchar(20) DEFAULT NULL,
  `bedId` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of medicalrecordsinfo
-- ----------------------------
INSERT INTO `medicalrecordsinfo` VALUES ('1', '2019-09-04', '儿科', '刘英杰', '反复咳嗽、咳痰、十余年，加重伴发热一周。', '1', '感冒、上呼吸道感染', '49.5', '否', '无', '无', '无', '无');
INSERT INTO `medicalrecordsinfo` VALUES ('2', '2019-09-04', '儿科', '刘英杰', '咳嗽、咳痰、高热、乏力', '2', '感冒', '28', '否', '无', '无', '无', '无');
INSERT INTO `medicalrecordsinfo` VALUES ('3', '2019-09-04', '眼科', '刘英杰', '反复上腹部隐痛3年，加重3个月。', '3', '反复上腹部隐痛3年，加重3个月。', '46.5', '否', '无', '无', '无', '无');

-- ----------------------------
-- Table structure for medicineinfo
-- ----------------------------
DROP TABLE IF EXISTS `medicineinfo`;
CREATE TABLE `medicineinfo` (
  `medNumber` int(11) NOT NULL,
  `medName` varchar(50) NOT NULL,
  `medPrice` double NOT NULL,
  `medCategory` varchar(20) NOT NULL,
  `medStore` int(11) DEFAULT NULL,
  PRIMARY KEY (`medNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of medicineinfo
-- ----------------------------
INSERT INTO `medicineinfo` VALUES ('1', '连花清瘟胶囊', '14', '中成药', '1');
INSERT INTO `medicineinfo` VALUES ('2', '小儿肺热咳喘颗粒', '14.5', '中成药', '1');
INSERT INTO `medicineinfo` VALUES ('3', '感冒灵颗粒', '10.2', '中成药', '14');
INSERT INTO `medicineinfo` VALUES ('4', '荆防颗粒', '10', '中成药', '17');
INSERT INTO `medicineinfo` VALUES ('5', '清热解毒口服液', '10', '中成药', '18');
INSERT INTO `medicineinfo` VALUES ('6', '四季感冒片', '8', '中成药', '19');
INSERT INTO `medicineinfo` VALUES ('7', '复方板蓝根颗粒', '6.4', '中成药', '20');
INSERT INTO `medicineinfo` VALUES ('8', '布洛芬颗粒', '10', '西药', '16');
INSERT INTO `medicineinfo` VALUES ('9', '小儿复方氨酚烷胺片', '7.4', '西药', '20');
INSERT INTO `medicineinfo` VALUES ('10', '小儿消积止咳颗粒', '10', '西药', '20');
INSERT INTO `medicineinfo` VALUES ('11', '尼克(氨苯伪麻片)', '15', '西药', '20');
INSERT INTO `medicineinfo` VALUES ('12', '诺氟沙星乳膏', '3', '西药', '20');
INSERT INTO `medicineinfo` VALUES ('13', '阿奇霉素胶囊', '10', '西药', '20');
INSERT INTO `medicineinfo` VALUES ('14', '罗红霉素片', '14', '西药', '19');
INSERT INTO `medicineinfo` VALUES ('15', '阿莫西林克拉维酸钾颗粒', '14.9', '西药', '20');
INSERT INTO `medicineinfo` VALUES ('16', '头孢克肟分散片', '11', '西药', '20');
INSERT INTO `medicineinfo` VALUES ('17', '阿莫西林颗粒', '6.3', '西药', '20');
INSERT INTO `medicineinfo` VALUES ('18', '猴耳环消炎片', '8.7', '中成药', '20');
INSERT INTO `medicineinfo` VALUES ('19', '慢严舒柠', '15.5', '中成药', '20');
INSERT INTO `medicineinfo` VALUES ('20', '炎可宁片', '13', '中成药', '20');
INSERT INTO `medicineinfo` VALUES ('21', '炎宁胶囊', '15.5', '中成药', '19');
INSERT INTO `medicineinfo` VALUES ('22', '炎立消胶囊', '10.5', '中成药', '20');
INSERT INTO `medicineinfo` VALUES ('23', '邦奇', '17', '西药', '18');
INSERT INTO `medicineinfo` VALUES ('24', '芬必得', '25', '西药', '20');
INSERT INTO `medicineinfo` VALUES ('25', '小儿氨酚黄那敏颗粒', '6.7', '西药', '20');
INSERT INTO `medicineinfo` VALUES ('26', '银贝止咳颗粒', '23', '中成药', '20');
INSERT INTO `medicineinfo` VALUES ('27', '鼻炎康片', '15.3', '中成药', '20');
INSERT INTO `medicineinfo` VALUES ('28', '知柏地黄丸', '12.5', '中成药', '19');
INSERT INTO `medicineinfo` VALUES ('29', '头孢拉定胶囊', '8.5', '西药', '20');
INSERT INTO `medicineinfo` VALUES ('30', '舒瑞特', '23', '西药', '20');

-- ----------------------------
-- Table structure for medicinelist
-- ----------------------------
DROP TABLE IF EXISTS `medicinelist`;
CREATE TABLE `medicinelist` (
  `medicineListNumber` int(11) NOT NULL AUTO_INCREMENT,
  `medicalRecordsNumber` int(11) DEFAULT NULL,
  `medicineNumber` int(11) DEFAULT NULL,
  `medicineAmount` varchar(20) DEFAULT NULL,
  `medicineUsage` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`medicineListNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of medicinelist
-- ----------------------------
INSERT INTO `medicinelist` VALUES ('1', '1', '8', '2瓶/盒', '一日1次，一次1粒');
INSERT INTO `medicinelist` VALUES ('2', '1', '14', '1瓶/盒', '一日3次，一次3粒');
INSERT INTO `medicinelist` VALUES ('3', '1', '21', '1瓶/盒', '一日3次，一次1粒');
INSERT INTO `medicinelist` VALUES ('4', '2', '6', '1瓶/盒', '一日3次，一次4片');
INSERT INTO `medicinelist` VALUES ('5', '2', '5', '2瓶/盒', '一日3次，一次1支');
INSERT INTO `medicinelist` VALUES ('6', '3', '23', '2瓶/盒', '一天3次，一次1粒');
INSERT INTO `medicinelist` VALUES ('7', '3', '28', '1瓶/盒', '一天3次，一次10粒');
INSERT INTO `medicinelist` VALUES ('40', null, null, null, null);

-- ----------------------------
-- Table structure for patientinfo
-- ----------------------------
DROP TABLE IF EXISTS `patientinfo`;
CREATE TABLE `patientinfo` (
  `patId` int(11) NOT NULL,
  `patName` varchar(20) NOT NULL,
  `patGender` char(2) NOT NULL,
  `patAge` int(11) NOT NULL,
  `patPwd` varchar(100) NOT NULL,
  `patDeposit` float NOT NULL,
  `patDate` char(10) NOT NULL,
  `patTel` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phonenumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`patId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patientinfo
-- ----------------------------
INSERT INTO `patientinfo` VALUES ('1', 'ywj', 'm', '23', '123', '-1989.7', '2019-01-02', '12345612345', '18956778818');
INSERT INTO `patientinfo` VALUES ('2', '三毛', '男', '23', '123', '-46.5', '2019-21-02', '12312312312', '18956778819');
INSERT INTO `patientinfo` VALUES ('3', '11', '男', '23', '123', '0', '2019-24-02', '12312312312', '18956778817');
INSERT INTO `patientinfo` VALUES ('4', '123', '男', '12', '124', '0', '2019-41-02', '12214124124', '18956778812');
INSERT INTO `patientinfo` VALUES ('5', '罗贝尔', '男', '20', '1234', '0', '2019-09-02', '11111111111', '18956778811');
INSERT INTO `patientinfo` VALUES ('6', '自驾游', '男', '56', '123', '0', '2019-09-02', '12312312311', '18956778822');
INSERT INTO `patientinfo` VALUES ('7', '姚文进', '男', '23', '123', '0', '2019-09-02', '123', '18956778898');

-- ----------------------------
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge` (
  `rid` int(11) NOT NULL,
  `patid` int(11) DEFAULT NULL,
  `date` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` float(255,0) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=MyISAM AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of recharge
-- ----------------------------
INSERT INTO `recharge` VALUES ('100', '100', '1', '1', '1');
INSERT INTO `recharge` VALUES ('101', '99', '99', '99', '1');
INSERT INTO `recharge` VALUES ('102', '1000', '2020-9-11', '55', '1');
INSERT INTO `recharge` VALUES ('103', '1', '2020-09-17', '15', '1');
INSERT INTO `recharge` VALUES ('104', '1', '2020-09-17', '100', '1');
INSERT INTO `recharge` VALUES ('105', '1', '2020-09-17', '100', '1');
INSERT INTO `recharge` VALUES ('106', '1', '2020-09-17', '100', '1');
INSERT INTO `recharge` VALUES ('107', '1', '2020-09-17', '100', '1');
INSERT INTO `recharge` VALUES ('108', '1', '2020-09-17', '100', '1');
INSERT INTO `recharge` VALUES ('109', '1', '2020-09-18', '100', '1');
INSERT INTO `recharge` VALUES ('110', '1', '2020-09-18', '111', '0');
INSERT INTO `recharge` VALUES ('111', '1', '2020-09-18', '344', '0');
INSERT INTO `recharge` VALUES ('112', '1', '2020-09-18', '1145', '0');
INSERT INTO `recharge` VALUES ('113', '1', '2020-09-18', '444', '2');
INSERT INTO `recharge` VALUES ('114', '1', '2020-09-18', '55', '0');
INSERT INTO `recharge` VALUES ('115', '1', '2020-09-18', '66', '2');
INSERT INTO `recharge` VALUES ('116', '1', '2020-09-18', '33', '0');

-- ----------------------------
-- Table structure for workinfo
-- ----------------------------
DROP TABLE IF EXISTS `workinfo`;
CREATE TABLE `workinfo` (
  `WorkNumber` int(11) NOT NULL,
  `depNumber` int(11) DEFAULT NULL,
  `Monday` varchar(20) DEFAULT NULL,
  `Tuesday` varchar(20) DEFAULT NULL,
  `Wensday` varchar(20) DEFAULT NULL,
  `Thursday` varchar(20) DEFAULT NULL,
  `Friday` varchar(20) DEFAULT NULL,
  `Saturday` varchar(20) DEFAULT NULL,
  `Sunday` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`WorkNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workinfo
-- ----------------------------
INSERT INTO `workinfo` VALUES ('10000', '2', '上午', '下午', '晚上', '休息', '晚上', '休息', '晚上');
INSERT INTO `workinfo` VALUES ('10022', '2', '下午', '晚上', '休息', '晚上', '休息', '下午', '上午');
INSERT INTO `workinfo` VALUES ('10023', '2', '晚上', '休息', '上午', '下午', '上午', '晚上', '休息');
INSERT INTO `workinfo` VALUES ('10025', '2', '休息', '上午', '下午', '上午', '下午', '上午', '下午');
