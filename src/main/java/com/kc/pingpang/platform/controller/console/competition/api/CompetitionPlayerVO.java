package com.kc.pingpang.platform.controller.console.competition.api;

import com.kc.pingpang.platform.data.model.CompetitionPlayer;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionPlayerVO {

    private String name;

    public static List<CompetitionPlayerVO> toVOs(List<CompetitionPlayer> competitionPlayers) {

        List<CompetitionPlayerVO> vos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(competitionPlayers)) {

            List<CompetitionPlayer> list = competitionPlayers.stream().
                    sorted(Comparator.comparing(CompetitionPlayer::getId).reversed()).collect(Collectors.toList());

            for (CompetitionPlayer competitionPlayer : list) {

                CompetitionPlayerVO vo = new CompetitionPlayerVO();
                vo.setName(competitionPlayer.getPlayerName());

                vos.add(vo);
            }
        }

        return vos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
