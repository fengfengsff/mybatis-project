CREATE TABLE `user` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '逻辑自增主键',
    `name` varchar(30) NOT NULL DEFAULT '' COMMENT '姓名',
    `age` int(3) unsigned NOT NULL DEFAULT '0' COMMENT '年龄',
    `sex` tinyint(2) unsigned zerofill NOT NULL DEFAULT '00' COMMENT '性别',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

