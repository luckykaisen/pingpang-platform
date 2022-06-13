package com.kc.pingpang.platform.business.adapter;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kc.pingpang.platform.business.adapter.api.PayResponse;
import com.kc.pingpang.platform.business.adapter.utils.HuPiJiaoUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.TreeMap;

@Component
public class HuPiJiaoAdapter {

    @Value("${hupijiao.pay.appid}")
    private String appid;

    @Value("${hupijiao.pay.secret}")
    private String secret;

    @Value("${hupijiao.pay.notify.url}")
    private String notifyUrl;

    private static final String PAY_URL = "https://pay.xunhunet.com/payment/do.html";

    public PayResponse pay(String orderNumber, String title, BigDecimal amount, String goodsUrl) {

        JSONObject plugin = new JSONObject();
        plugin.put("orderNumber", orderNumber);

        TreeMap<String, Object> params = new TreeMap<>();
        params.put("version", "1.1");
        params.put("appid", appid);
        params.put("trade_order_id", orderNumber);
        params.put("total_fee", amount);
        params.put("title", title);
        params.put("time", System.currentTimeMillis()/100);
        params.put("notify_url", notifyUrl);
        params.put("return_url", goodsUrl);
        params.put("callback_url", goodsUrl);
        params.put("nonce_str", HuPiJiaoUtility.randomString(32));
        params.put("type", "WAP");
        params.put("wap_url", "https://www.sysm.ltd");
        params.put("wap_name", "森杨晟茂乒乓球俱乐部");

        String sign = HuPiJiaoUtility.sign(params, secret);

        params.put("hash", sign);

        String response = HttpUtil.post(PAY_URL, params);

        return JSON.parseObject(response, PayResponse.class);
    }
}
