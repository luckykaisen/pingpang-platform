package com.kc.pingpang.platform.data.model;

import java.util.HashMap;
import java.util.Map;

public enum CompetitionOption {

    DINNER (1, "是否聚餐");

    private static Map<Integer, CompetitionOption> id2Enum = new HashMap<>();
    private static Map<String, CompetitionOption> name2Enum = new HashMap<>();

    static {
        for (CompetitionOption record : CompetitionOption.values()) {
            id2Enum.put(record.getId(), record);
            name2Enum.put(record.getName(), record);
        }
    }

    private int id;
    private String name;

    CompetitionOption(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static CompetitionOption fromId(Integer id) {
        return id2Enum.get(id);
    }

    public static CompetitionOption fromName(String name) {
        return name2Enum.get(name);
    }


}
