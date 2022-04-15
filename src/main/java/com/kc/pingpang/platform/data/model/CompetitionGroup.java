package com.kc.pingpang.platform.data.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CompetitionGroup implements Serializable {

    private Integer id;
    private String name;
    private Date createTime;
    private Date updateTime;
    private Integer competitionId;

    private List<CompetitionGroupPlayer> players;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public List<CompetitionGroupPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<CompetitionGroupPlayer> players) {
        this.players = players;
    }
}
