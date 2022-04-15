package com.kc.pingpang.platform.controller.admin.competition.api;

import com.kc.pingpang.platform.data.model.CompetitionGroupPlayer;

import java.util.ArrayList;
import java.util.List;

public class CompetitionGroupPlayerVO {

    private Integer id;
    private String name;

    public static List<CompetitionGroupPlayerVO> toVOs(List<CompetitionGroupPlayer> groupPlayers) {
        List<CompetitionGroupPlayerVO> vos = new ArrayList<>();
        for (CompetitionGroupPlayer groupPlayer : groupPlayers) {
            vos.add(toVO(groupPlayer));
        }

        return vos;
    }

    public static CompetitionGroupPlayerVO toVO(CompetitionGroupPlayer groupPlayer) {

        CompetitionGroupPlayerVO vo = new CompetitionGroupPlayerVO();
        vo.setId(groupPlayer.getId());
        vo.setName(groupPlayer.getPlayer().getName());

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
