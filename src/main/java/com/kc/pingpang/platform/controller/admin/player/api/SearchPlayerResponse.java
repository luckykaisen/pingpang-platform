package com.kc.pingpang.platform.controller.admin.player.api;

import com.kc.pingpang.platform.freamwork.http.api.api.PagingResponse;

import java.util.List;

public class SearchPlayerResponse extends PagingResponse {

    private List<PlayerVO> list;

    public List<PlayerVO> getList() {
        return list;
    }

    public void setList(List<PlayerVO> list) {
        this.list = list;
    }
}
