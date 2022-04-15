package com.kc.pingpang.platform.controller.admin.resource.api;


import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;

import java.util.ArrayList;
import java.util.List;

public class DefaultResourceResponse extends ServiceResponse {
    private static final long serialVersionUID = 1L;

    private List<DefaultResourceVO> list = new ArrayList<>();

    public List<DefaultResourceVO> getList() {
        return list;
    }

    public void setList(List<DefaultResourceVO> list) {
        this.list = list;
    }
}
