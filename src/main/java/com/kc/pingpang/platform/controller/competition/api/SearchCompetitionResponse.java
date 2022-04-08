package com.kc.pingpang.platform.controller.competition.api;

import com.kc.pingpang.platform.freamwork.http.api.api.PagingResponse;

import java.util.List;

public class SearchCompetitionResponse extends PagingResponse {

    private List<CompetitionVO> list;

    public List<CompetitionVO> getList() {
        return list;
    }

    public void setList(List<CompetitionVO> list) {
        this.list = list;
    }
}
