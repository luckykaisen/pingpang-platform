package com.kc.pingpang.platform.controller.admin.competition.api;

public class JoinCompetitionRequest {

    private Integer id;
    private Integer playerId;

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
}
