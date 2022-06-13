package com.kc.pingpang.platform.business.adapter.api;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

public class OrderNotifyRequest {

    @JSONField(name = "trade_order_id")
    private String tradeOrderId;

    @JSONField(name = "total_fee")
    private BigDecimal totalFee;

    @JSONField(name = "transaction_id")
    private String transactionId;

    @JSONField(name = "open_order_id")
    private String openOrderId;

    @JSONField(name = "order_title")
    private String orderTitle;

    private String status;
    private String plugins;
    private String appid;
    private String time;

    @JSONField(name = "nonce_str")
    private String nonceStr;
    private String hash;

    public String getTradeOrderId() {
        return tradeOrderId;
    }

    public void setTradeOrderId(String tradeOrderId) {
        this.tradeOrderId = tradeOrderId;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOpenOrderId() {
        return openOrderId;
    }

    public void setOpenOrderId(String openOrderId) {
        this.openOrderId = openOrderId;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlugins() {
        return plugins;
    }

    public void setPlugins(String plugins) {
        this.plugins = plugins;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
