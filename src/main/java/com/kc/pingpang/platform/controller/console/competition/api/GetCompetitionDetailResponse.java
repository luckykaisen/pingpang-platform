package com.kc.pingpang.platform.controller.console.competition.api;

import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;
import com.kc.pingpang.platform.freamwork.utils.DateTimeUtility;

import java.util.List;

public class GetCompetitionDetailResponse extends ServiceResponse {

    private Integer id;
    private String name;
    private String description;
    private Integer participantLimit;
    private String date;
    private List<CompetitionPlayerVO> players;

    public GetCompetitionDetailResponse(Competition competition) {

        this.id = competition.getId();
        this.name = competition.getName();
        this.description = competition.getDescription();
        this.date = DateTimeUtility.formatYYYYMMDD(competition.getDate());
        this.participantLimit = competition.getParticipantLimit();
        this.players = CompetitionPlayerVO.toVOs(competition.getCompetitionPlayers());
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

    public List<CompetitionPlayerVO> getPlayers() {
        return players;
    }

    public void setPlayers(List<CompetitionPlayerVO> players) {
        this.players = players;
    }

    public Integer getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(Integer participantLimit) {
        this.participantLimit = participantLimit;
    }
}
