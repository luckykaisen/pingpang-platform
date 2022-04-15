package com.kc.pingpang.platform.controller.admin.player.api;

import com.kc.pingpang.platform.data.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerVO {

    private Integer id;
    private String name;

    public static List<PlayerVO> toVOs(List<Player> players) {

        List<PlayerVO> vos = new ArrayList<>();
        for (Player player : players) {
            vos.add(toVO(player));
        }

        return vos;
    }

    public static PlayerVO toVO(Player player) {

        PlayerVO vo = new PlayerVO();
        vo.setId(player.getId());
        vo.setName(player.getName());

        return vo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
