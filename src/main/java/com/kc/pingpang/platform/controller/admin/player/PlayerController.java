package com.kc.pingpang.platform.controller.admin.player;

import com.kc.pingpang.platform.data.mapper.PlayerMapper;
import com.kc.pingpang.platform.data.model.Player;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping("/services/rs/admin/user")
public class PlayerController {

    @Resource
    private PlayerMapper playerMapper;

    @RequestMapping("/list")
    public Player test() {

        Player player = playerMapper.selectPlayerById(1);

        return player;
    }

}
