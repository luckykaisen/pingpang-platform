package com.kc.pingpang.platform.data.model;

import java.util.HashMap;
import java.util.Map;

public enum Group {

    GROUP_1(1, "第一组"),
    GROUP_2(2, "第二组"),
    GROUP_3(3, "第三组"),
    GROUP_4(4, "第四组"),
    GROUP_5(5, "第五组"),
    GROUP_6(6, "第六组"),
    GROUP_7(7, "第七组"),
    GROUP_8(8, "第八组"),
    GROUP_9(9, "第九组"),
    GROUP_10(10, "第十组"),
    GROUP_11(11, "第十一组"),
    GROUP_12(12, "第十二组"),
    GROUP_13(13, "第十三组"),
    GROUP_14(14, "第十四组"),
    GROUP_15(15, "第十五组"),
    GROUP_16(16, "第十六组"),
    GROUP_17(17, "第十七组"),
    GROUP_18(18, "第十八组"),
    GROUP_19(19, "第十九组"),
    GROUP_20(20, "第二十组"),
    GROUP_21(21, "第二十一组"),
    GROUP_22(22, "第二十二组"),
    GROUP_23(23, "第二十三组"),
    GROUP_24(24, "第二十四组"),
    GROUP_25(25, "第二十五组"),
    GROUP_26(26, "第二十六组"),
    GROUP_27(27, "第二十七组"),
    GROUP_28(28, "第二十八组"),
    GROUP_29(29, "第二十九组"),
    GROUP_30(30, "第三十组"),
    GROUP_31(31, "第三十一组"),
    GROUP_32(32, "第三十二组"),
    GROUP_33(33, "第三十三组"),
    GROUP_34(34, "第三十四组"),
    GROUP_35(35, "第三十五组"),
    GROUP_36(36, "第三十六组");

    private static Map<Integer, Group> id2Enum = new HashMap<>();
    private static Map<String, Group> name2Enum = new HashMap<>();

    static {
        for (Group record : Group.values()) {
            id2Enum.put(record.getId(), record);
            name2Enum.put(record.getName(), record);
        }
    }

    private int id;
    private String name;

    Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Group fromId(Integer id) {
        return id2Enum.get(id);
    }

    public static Group fromName(String name) {
        return name2Enum.get(name);
    }

}
