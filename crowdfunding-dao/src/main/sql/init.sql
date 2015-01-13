
DROP DATABASE IF EXISTS `crowdfunding`;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crowdfunding` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `crowdfunding`;

GRANT ALL PRIVILEGES ON crowdfunding.* TO 'crowdfunding'@'%' identified by "crowdfunding" WITH GRANT OPTION;

flush privileges;

drop table if exists user;
create table user(
    id bigint not null auto_increment,
    name varchar(100) not null,
    age int not null,
    gender varchar(5) not null,
    backup TEXT,
    create_time datetime not null,
    update_time timestamp not null,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

drop table if exists sys_role_authoritie_mapping;

drop table if exists sys_user_role_mapping;

drop table if exists sys_authoritie;

-- drop index Index_1 on sys_role;

drop table if exists sys_role;

-- drop index Index_1 on sys_user;

drop table if exists sys_user;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_authoritie
-- ----------------------------
CREATE TABLE `sys_authoritie` (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_authoritie
-- ----------------------------
INSERT INTO `sys_authoritie` VALUES ('1', 'add', '/stc/add.do');
INSERT INTO `sys_authoritie` VALUES ('2', 'save', '/stc/save.do');
INSERT INTO `sys_authoritie` VALUES ('3', 'update', '/stc/update.do');
INSERT INTO `sys_authoritie` VALUES ('4', 'delete', '/stc/delete.do');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `Index_1` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin');
INSERT INTO `sys_role` VALUES ('3', 'custom');
INSERT INTO `sys_role` VALUES ('2', 'user');

-- ----------------------------
-- Table structure for sys_role_authoritie_mapping
-- ----------------------------
CREATE TABLE `sys_role_authoritie_mapping` (
  `ra_mapping_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `auth_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ra_mapping_id`),
  KEY `FK_Reference_3` (`role_id`),
  KEY `FK_Reference_4` (`auth_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`auth_id`) REFERENCES `sys_authoritie` (`auth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_authoritie_mapping
-- ----------------------------
INSERT INTO `sys_role_authoritie_mapping` VALUES ('1', '1', '1');
INSERT INTO `sys_role_authoritie_mapping` VALUES ('2', '2', '2');
INSERT INTO `sys_role_authoritie_mapping` VALUES ('3', '2', '3');
INSERT INTO `sys_role_authoritie_mapping` VALUES ('5', '2', '4');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `Index_1` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'pandy', '31a6a915d2ce9ac21305a06872582cca', 'pandy');
INSERT INTO `sys_user` VALUES ('2', 'pyzheng', '52da327171aaa6813acc027fc242513d', 'pyzheng');
INSERT INTO `sys_user` VALUES ('3', 'test', '889255f1c9c8f12a353be255f78a848b', 'rest');

-- ----------------------------
-- Table structure for sys_user_role_mapping
-- ----------------------------
CREATE TABLE `sys_user_role_mapping` (
  `ur_mapping_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ur_mapping_id`),
  KEY `FK_Reference_1` (`user_id`),
  KEY `FK_Reference_2` (`role_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role_mapping
-- ----------------------------
INSERT INTO `sys_user_role_mapping` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role_mapping` VALUES ('2', '2', '2');
INSERT INTO `sys_user_role_mapping` VALUES ('3', '3', '3');


