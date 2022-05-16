package com.kc.pingpang.platform.data.model;

import java.util.HashMap;
import java.util.Map;

public enum GroupAgainstType {

    TYPE_1 (1, "小组循环"),
    TYPE_2 (2, "小组顺序循环");

    private static Map<Integer, GroupAgainstType> id2Enum = new HashMap<>();
    private static Map<String, GroupAgainstType> name2Enum = new HashMap<>();

    static {
        for (GroupAgainstType record : GroupAgainstType.values()) {
            id2Enum.put(record.getId(), record);
            name2Enum.put(record.getName(), record);
        }
    }

    private int id;
    private String name;

    GroupAgainstType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static GroupAgainstType fromId(Integer id) {
        return id2Enum.get(id);
    }

    public static GroupAgainstType fromName(String name) {
        return name2Enum.get(name);
    }


}
