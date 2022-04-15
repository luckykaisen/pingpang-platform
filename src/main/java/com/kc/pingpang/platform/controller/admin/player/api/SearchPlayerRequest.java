package com.kc.pingpang.platform.controller.admin.player.api;

import com.kc.pingpang.platform.data.filter.PlayerFilter;
import com.kc.pingpang.platform.freamwork.http.api.api.PagingRequest;

public class SearchPlayerRequest extends PagingRequest {

    private String name;

    public PlayerFilter toFilter() {

        PlayerFilter filter = new PlayerFilter();
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
