package com.kc.pingpang.platform.controller.console.order.api;

import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;

public class CreateOrderResponse extends ServiceResponse {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
