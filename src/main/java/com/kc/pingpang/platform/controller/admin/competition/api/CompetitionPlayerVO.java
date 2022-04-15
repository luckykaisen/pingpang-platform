package com.kc.pingpang.platform.controller.admin.competition.api;

import com.kc.pingpang.platform.data.model.CompetitionPlayer;

import java.util.ArrayList;
import java.util.List;

public class CompetitionPlayerVO {

    private Integer id;
    private String name;

    public static List<CompetitionPlayerVO> toVOs(List<CompetitionPlayer> competitionPlayers) {

        List<CompetitionPlayerVO> vos = new ArrayList<>();
        for (CompetitionPlayer competitionPlayer : competitionPlayers) {

            CompetitionPlayerVO vo = new CompetitionPlayerVO();
            vo.setId(competitionPlayer.getId());
            vo.setName(competitionPlayer.getPlayerName());

            vos.add(vo);
        }

        return vos;
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
