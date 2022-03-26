
USE pingpang_all;

CREATE TABLE `t_player` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(64) NOT NULL,
    `create_time` datetime NOT NULL,
    `update_time` datetime NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_competition` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(64) NOT NULL,
    `description` varchar(64) NOT NULL,
    `participant_limit` int(10) NOT NULL,
    `date` date NOT NULL,
    `create_time` datetime NOT NULL,
    `update_time` datetime NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_competition_player` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `player_id` int(10) NOT NULL,
    `competition_id` int(10) NOT NULL,
    `create_time` datetime NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_competition_group` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(16) NOT NULL,
    `create_time` datetime NOT NULL,
    `update_time` datetime NOT NULL,
    `competition_id` int(10) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_competition_group_competition` (`competition_id`),
    CONSTRAINT `fk_competition_group_competition` FOREIGN KEY (`competition_id`) REFERENCES `t_competition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_competition_group_player` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `create_time` datetime NOT NULL,
    `update_time` datetime NOT NULL,
    `player_id` int(10) NOT NULL,
    `group_id` int(10) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_competition_group_competition` (`competition_id`),
    CONSTRAINT `fk_competition_group_competition` FOREIGN KEY (`competition_id`) REFERENCES `t_competition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;