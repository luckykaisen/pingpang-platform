package com.kc.pingpang.platform.controller.admin.competition.api;

import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.freamwork.utils.DateTimeUtility;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.List;

public class CreateCompetitionRequest {

    private String name;
    private List<Integer> signUpOptionIds;
    private String description;
    private String signUpPrice;
    private String dinnerPrice;
    private String date;
    private Integer participantLimit;

    public Competition toCompetition() throws Exception {

        Competition competition = new Competition();
        competition.setSignUpOptions(StringUtils.join(signUpOptionIds, ","));
        competition.setName(name);
        competition.setDescription(description);
        competition.setDate(DateTimeUtility.parseYYYYMMDD(date));
        competition.setParticipantLimit(participantLimit);
        competition.setSignUpPrice(new BigDecimal(signUpPrice));
        competition.setDinnerPrice(new BigDecimal(dinnerPrice));

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
