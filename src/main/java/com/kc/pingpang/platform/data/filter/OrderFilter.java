package com.kc.pingpang.platform.data.filter;

import com.kc.pingpang.platform.data.model.OrderStatus;
import com.kc.pingpang.platform.freamwork.model.db.filter.SearchFilter;

import java.util.List;

public class OrderFilter extends SearchFilter {

    private Integer competitionId;
    private List<OrderStatus> statuses;

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public List<OrderStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<OrderStatus> statuses) {
        this.statuses = statuses;
    }
}
