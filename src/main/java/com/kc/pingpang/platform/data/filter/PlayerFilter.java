package com.kc.pingpang.platform.data.filter;

import com.kc.pingpang.platform.freamwork.db.filter.SearchFilter;
import org.apache.commons.lang.StringUtils;

public class PlayerFilter extends SearchFilter {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotBlank(name)) {
            this.name = "%" + name + "%";
        }
    }
}
