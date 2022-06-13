package com.kc.pingpang.platform.data.model;

import com.kc.pingpang.platform.freamwork.model.IdInterface;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus implements IdInterface {

    PENDING_PAYMENT (1, "待支付"),
    PAID (2, "已付款"),

    CANCEL (3, "已取消");

    private static Map<Integer, OrderStatus> id2Enum = new HashMap<>();
    private static Map<String, OrderStatus> name2Enum = new HashMap<>();

    static {
        for (OrderStatus record : OrderStatus.values()) {
            id2Enum.put(record.getId(), record);
            name2Enum.put(record.getName(), record);
        }
    }

    private int id;
    private String name;

    OrderStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static OrderStatus fromId(Integer id) {
        return id2Enum.get(id);
    }

    public static OrderStatus fromName(String name) {
        return name2Enum.get(name);
    }


}
