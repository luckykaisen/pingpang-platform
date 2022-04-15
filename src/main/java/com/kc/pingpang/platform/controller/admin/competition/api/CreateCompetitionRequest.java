package com.kc.pingpang.platform.controller.admin.competition.api;

import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.freamwork.utils.DateTimeUtility;

public class CreateCompetitionRequest {

    private String name;
    private String description;
    private String date;
    private Integer participantLimit;

    public Competition toCompetition() throws Exception {

        Competition competition = new Competition();
        competition.setName(name);
        competition.setDescription(description);
        competition.setDate(DateTimeUtility.parseYYYYMMDD(date));
        competition.setParticipantLimit(participantLimit);

        return competition;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(Integer participantLimit) {
        this.participantLimit = participantLimit;
    }
}
