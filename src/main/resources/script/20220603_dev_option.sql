
USE pingpang_all;

ALTER TABLE `t_competition`
    ADD `sign_up_options` varchar(36) DEFAULT NULL AFTER `description`;

ALTER TABLE `t_competition_player`
    ADD `is_dinner` char(1) NOT NULL AFTER `player_name`;