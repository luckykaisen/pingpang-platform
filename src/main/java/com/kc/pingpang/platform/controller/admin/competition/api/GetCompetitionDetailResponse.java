package com.kc.pingpang.platform.controller.admin.competition.api;

import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;

import java.util.List;

public class GetCompetitionDetailResponse extends ServiceResponse {

    private Integer id;
    private String name;
    private String description;
    private Integer participantLimit;
    private String signUpPrice;
    private String dinnerPrice;
    private String date;
    private String createTime;
    private String updateTime;
    private List<Integer> signUpOptionIds;
    private List<CompetitionPlayerVO> players;
    private List<CompetitionGroupVO> groups;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<CompetitionPlayerVO> getPlayers() {
        return players;
    }

    public void setPlayers(List<CompetitionPlayerVO> players) {
        this.players = players;
    }

    public List<CompetitionGroupVO> getGroups() {
        return groups;
    }

    public void setGroups(List<CompetitionGroupVO> groups) {
        this.groups = groups;
    }

    public List<Integer> getSignUpOptionIds() {
        return signUpOptionIds;
    }

    public void setSignUpOptionIds(List<Integer> signUpOptionIds) {
        this.signUpOptionIds = signUpOptionIds;
    }

    public String getSignUpPrice() {
        return signUpPrice;
    }

    public void setSignUpPrice(String signUpPrice) {
        this.signUpPrice = signUpPrice;
    }

    public String getDinnerPrice() {
        return dinnerPrice;
    }

    public void setDinnerPrice(String dinnerPrice) {
        this.dinnerPrice = dinnerPrice;
    }
}
