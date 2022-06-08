package com.kc.pingpang.platform.freamwork.utils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtility {

    public static List<String> split(String str) {

        if (StringUtils.isNotBlank(str)) {
            return Arrays.asList(str.split(","));
        } else {
            return new ArrayList<>();
        }
    }

    public static String splice(String ... args) {

        StringBuilder sb = new StringBuilder();
        if (args != null && args.length > 0) {
            for (String str : args) {
                if (StringUtils.isNotBlank(str)) {
                    sb.append(str);
                }
            }
        }

        return sb.toString();
    }
}
