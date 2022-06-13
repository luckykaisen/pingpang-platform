
USE pingpang_all;

CREATE TABLE `t_order_status` (
    `id` int(10) unsigned NOT NULL,
    `name` varchar(32) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

INSERT INTO `t_order_status` VALUES (1, '待支付'),(2, '已付款'),(3, '已取消');

CREATE TABLE `t_order` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `order_number` varchar(32) NOT NULL,
    `title` varchar(64) NOT NULL,
    `player_name` varchar(64) NOT NULL,
    `is_dinner` char(1) NOT NULL DEFAULT 'N',
    `amount` decimal(18,2) unsigned NOT NULL,
    `goods_url` varchar(256) DEFAULT NULL,
    `pay_url` varchar(256) DEFAULT NULL,
    `qrcode_url` varchar(256) DEFAULT NULL,
    `pay_time` datetime DEFAULT NULL,
    `cancel_time` datetime DEFAULT NULL,
    `create_time` datetime NOT NULL,
    `update_time` datetime NOT NULL,
    `competition_id` int(11) unsigned NOT NULL,
    `status_id` int(11) unsigned NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_order_order_number` (`order_number`) USING BTREE,
    KEY `idx_order_competition` (`competition_id`) USING BTREE,
    KEY `idx_order_status_id` (`status_id`) USING BTREE,
    CONSTRAINT `fk_order_competition` FOREIGN KEY (`competition_id`) REFERENCES `t_competition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_status_id` FOREIGN KEY (`status_id`) REFERENCES `t_order_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

ALTER TABLE `t_competition`
    ADD `sign_up_price` decimal(18,2) unsigned NOT NULL AFTER `participant_limit`,
    ADD `dinner_price` decimal(18,2) unsigned NOT NULL AFTER `sign_up_price`;

UPDATE `t_competition` SET `sign_up_price` = 40, `dinner_price` = 50 WHERE `id` = 1;

