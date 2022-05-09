
USE pingpang_all;

CREATE TABLE `t_admin_account` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `login_name` varchar(16) NOT NULL,
    `password` varchar(128) NOT NULL,
    `salt` varchar(128) NOT NULL,
    `create_time` datetime NOT NULL,
    `update_time` datetime NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_admin_account_login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
