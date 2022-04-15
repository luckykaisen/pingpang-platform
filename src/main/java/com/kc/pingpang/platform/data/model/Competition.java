package com.kc.pingpang.platform.data.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Competition implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private Integer participantLimit;
    private Date date;
    private Date createTime;
    private Date updateTime;

    private List<CompetitionPlayer> competitionPlayers;
    private List<CompetitionGroup> groups;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(Integer participantLimit) {
        this.participantLimit = participantLimit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public List<CompetitionPlayer> getCompetitionPlayers() {
        return competitionPlayers;
    }

    public void setCompetitionPlayers(List<CompetitionPlayer> competitionPlayers) {
        this.competitionPlayers = competitionPlayers;
    }

    public List<CompetitionGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<CompetitionGroup> groups) {
        this.groups = groups;
    }
}
