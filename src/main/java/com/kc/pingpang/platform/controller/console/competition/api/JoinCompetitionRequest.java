package com.kc.pingpang.platform.controller.console.competition.api;

public class JoinCompetitionRequest {

    private Integer id;
    private String name;
    private Boolean dinner;

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

    public Boolean getDinner() {
        return dinner;
    }

    public void setDinner(Boolean dinner) {
        this.dinner = dinner;
    }
}
