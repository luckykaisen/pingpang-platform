package com.kc.pingpang.platform.controller.admin.competition.api;

public class JoinCompetitionRequest {

    private Integer id;
    private Integer playerId;
    private Boolean dinner;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Boolean getDinner() {
        return dinner;
    }

    public void setDinner(Boolean dinner) {
        this.dinner = dinner;
    }
}
