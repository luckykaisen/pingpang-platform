package com.kc.pingpang.platform.controller.admin.competition.api;

import com.kc.pingpang.platform.data.model.CompetitionGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionGroupVO {

    private Integer id;
    private String name;
    private List<CompetitionGroupPlayerVO> players;

    public static List<CompetitionGroupVO> toVOs(List<CompetitionGroup> groups) {
        List<CompetitionGroupVO> vos = new ArrayList<>();
        groups = groups.stream().
                sorted(Comparator.comparing(CompetitionGroup::getId)).collect(Collectors.toList());
        for (CompetitionGroup group : groups) {
            vos.add(toVO(group));
        }

        return vos;
    }

    public static CompetitionGroupVO toVO(CompetitionGroup group) {

        CompetitionGroupVO vo = new CompetitionGroupVO();
        vo.setId(group.getId());
        vo.setName(group.getName());
        vo.setPlayers(CompetitionGroupPlayerVO.toVOs(group.getPlayers()));

        return vo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CompetitionGroupPlayerVO> getPlayers() {
        return players;
    }

    public void setPlayers(List<CompetitionGroupPlayerVO> players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
