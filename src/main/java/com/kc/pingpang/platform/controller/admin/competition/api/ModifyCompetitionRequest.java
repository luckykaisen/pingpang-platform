package com.kc.pingpang.platform.controller.admin.competition.api;

import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.freamwork.utils.DateTimeUtility;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.List;

public class ModifyCompetitionRequest {

    private Integer id;
    private String name;
    private List<Integer> signUpOptionIds;
    private String description;
    private String date;
    private Integer participantLimit;
    private String signUpPrice;
    private String dinnerPrice;

    public Competition toCompetition() throws Exception {

        Competition competition = new Competition();
        competition.setId(id);
        competition.setName(name);
        competition.setDescription(description);
        competition.setDate(DateTimeUtility.parseYYYYMMDD(date));
        competition.setParticipantLimit(participantLimit);
        competition.setSignUpPrice(new BigDecimal(signUpPrice));
        competition.setDinnerPrice(new BigDecimal(dinnerPrice));

        if (CollectionUtils.isNotEmpty(signUpOptionIds)) {
            competition.setSignUpOptions(StringUtils.join(signUpOptionIds, ","));
        } else {
            competition.setNullSignUpOptions(true);
        }

        return competition;
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
