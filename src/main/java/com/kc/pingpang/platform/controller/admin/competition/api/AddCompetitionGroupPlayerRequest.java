package com.kc.pingpang.platform.controller.admin.competition.api;

public class AddCompetitionGroupPlayerRequest {

    private Integer playerId;
    private Integer groupId;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
