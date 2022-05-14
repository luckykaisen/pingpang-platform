package com.kc.pingpang.platform.freamwork.http.api.api;

public class DownloadServiceResponse extends ServiceResponse {

    private String url;

    public DownloadServiceResponse() {
    }

    public DownloadServiceResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
