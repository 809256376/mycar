SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for Vehicle
-- ----------------------------
DROP TABLE IF EXISTS `Vehicle`;
CREATE TABLE `Vehicle` (
  `id`              INT(10) NOT NULL AUTO_INCREMENT,
  `viid`            INT(10) NOT NULL COMMENT '车系ID',
  `number`          VARCHAR(20) NOT NULL UNIQUE COMMENT '车牌',
  `sid`             INT(10) NOT NULL COMMENT '当前所在门店',
  `status`          INT(10) NOT NULL DEFAULT 0 COMMENT '车辆的状态',
  `begin`           DATETIME DEFAULT NULL COMMENT '租车或者修车的开始时间',
  `end`             DATETIME DEFAULT NULL COMMENT '租车或者修车的结束时间',
  `create_time`     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

INSERT `Vehicle` (viid,number,sid,status) VALUES(1,"京A10001",1,0);