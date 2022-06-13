package com.kc.pingpang.platform.controller.console.competition.api;

import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.data.model.CompetitionOption;
import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;
import com.kc.pingpang.platform.freamwork.utils.DateTimeUtility;

import java.util.List;
import java.util.stream.Collectors;

public class GetCompetitionDetailResponse extends ServiceResponse {

    private Integer id;
    private String name;
    private String description;
    private Integer participantLimit;

    private String signUpPrice;
    private String dinnerPrice;
    private String date;
    private List<Integer> signUpOptionIds;
    private List<CompetitionPlayerVO> players;

    public GetCompetitionDetailResponse(Competition competition) {

        this.id = competition.getId();
        this.name = competition.getName();
        this.description = competition.getDescription();
        this.date = DateTimeUtility.formatYYYYMMDD(competition.getDate());
        this.participantLimit = competition.getParticipantLimit();
        this.players = CompetitionPlayerVO.toVOs(competition.getCompetitionPlayers());
        this.signUpOptionIds = competition.getSignUpOptionList().stream().map(CompetitionOption::getId).collect(Collectors.toList());
        this.signUpPrice = competition.getSignUpPrice().toString();
        this.dinnerPrice = competition.getDinnerPrice().toString();
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
