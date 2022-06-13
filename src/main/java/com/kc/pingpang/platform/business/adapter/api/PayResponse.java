package com.kc.pingpang.platform.business.adapter.api;

import com.alibaba.fastjson.annotation.JSONField;

public class PayResponse {

    private Integer oderid;

    @JSONField(name = "url_qrcode")
    private String urlQrcode;

    private String url;

    private String hash;

    private Integer errcode;
    private String errmsg;

    public boolean isSuccess() {
        if (errcode != null && errcode == 0) {
            return true;
        }

        return false;
    }

    public Integer getOderid() {
        return oderid;
    }

    public void setOderid(Integer oderid) {
        this.oderid = oderid;
    }

    public String getUrlQrcode() {
        return urlQrcode;
    }

    public void setUrlQrcode(String urlQrcode) {
        this.urlQrcode = urlQrcode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
