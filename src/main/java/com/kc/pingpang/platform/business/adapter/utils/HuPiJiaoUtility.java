package com.kc.pingpang.platform.business.adapter.utils;

import com.alibaba.fastjson.JSON;
import com.kc.pingpang.platform.business.adapter.api.OrderNotifyRequest;
import com.kc.pingpang.platform.freamwork.utils.MD5Utility;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.net.URLDecoder;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class HuPiJiaoUtility {

    private static final String RAND_BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String sign(TreeMap<String, Object> params, String appSecret) {

        if (!CollectionUtils.isEmpty(params) || StringUtils.isNotBlank(appSecret)) {

            StringBuilder sb = new StringBuilder();

            String sep = "";
            for (Map.Entry<String, Object> entry : params.entrySet()) {

                String key = entry.getKey();
                Object value = entry.getValue();

                if (key != null && value != null) {

                    if (!"hash".equals(key)) {
                        sb.append(sep).append(key).append("=").append(value);
                        sep = "&";
                    }
                }
            }

            sb.append(appSecret);

            return MD5Utility.sign(sb.toString(), "utf-8");
        }

        return null;
    }

    public static String randomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(RAND_BASE.length());
            sb.append(RAND_BASE.charAt(number));
        }
        return sb.toString();
    }

    public static OrderNotifyRequest parseNotifyRequest(String content) throws Exception {

        String decode = URLDecoder.decode(content, "utf-8");

        TreeMap<String, Object> map = new TreeMap<>();

        for (String s : decode.split("&")) {

            String[] split = s.split("=");

            map.put(split[0], split[1]);
        }

        return JSON.parseObject(JSON.toJSONString(map), OrderNotifyRequest.class);
    }

    public static boolean verifySign(String content, String appSecret) throws Exception {

        if (StringUtils.isBlank(content)) {
            return false;
        }

        String hash = null;
        String decode = URLDecoder.decode(content, "utf-8");

        TreeMap<String, Object> map = new TreeMap<>();

        for (String s : decode.split("&")) {

            String[] split = s.split("=");


            if (!split[0].equals("hash")) {
                map.put(split[0], split[1]);
            } else {
                hash = split[1];
            }
        }

        String sign = sign(map, appSecret);

        assert sign != null;
        return sign.equals(hash);
    }
}
