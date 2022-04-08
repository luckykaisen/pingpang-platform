package com.kc.pingpang.platform.controller.competition.api;

import com.kc.pingpang.platform.data.filter.CompetitionFilter;
import com.kc.pingpang.platform.freamwork.http.api.api.PagingRequest;

public class SearchCompetitionRequest extends PagingRequest {

    private String name;

    public CompetitionFilter toFilter() {

        CompetitionFilter filter = new CompetitionFilter();
        filter.setName(name);
        filter.initPagingAndOrdering(this);

        return filter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
