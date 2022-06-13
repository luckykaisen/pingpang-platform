package com.kc.pingpang.platform.business.service.business.excel.group.cycle;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.kc.pingpang.platform.data.model.CompetitionGroup;
import com.kc.pingpang.platform.data.model.CompetitionGroupPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GroupCycleExcel {

    private final String storagePath;
    private List<CompetitionGroup> groups;

    public GroupCycleExcel(String storagePath, List<CompetitionGroup> groups) {
        this.storagePath = storagePath;
        this.groups = groups;
    }

    public String generate() {

        String fileName = storagePath + "/excel/小组顺序循环-" + System.currentTimeMillis() + ".xlsx";
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(storagePath + "/excel/template/顺序循环模板.xlsx").build();

        if (groups.size() > 20) {
            groups = groups.subList(0, 20);
        }

        for (int i = 0; i < groups.size(); i++) {

            CompetitionGroup group = groups.get(i);

            List<FillData> list = new ArrayList<>();

            if (group.getPlayers().size() == 0) {
                continue;
            }

            for (CompetitionGroupPlayer player : group.getPlayers()) {
                list.add(new FillData(player.getPlayer().getName(), group.getName()));
            }

            WriteSheet writeSheet = EasyExcel.writerSheet(i).build();
            FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
            excelWriter.fill(list, fillConfig, writeSheet);
        }

        excelWriter.finish();

        return fileName;
    }

    static class FillData {

        private String groupName;
        private String name;

        public FillData() {
        }

        public FillData(String name, String groupName) {
            this.name = name;
            this.groupName = groupName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }
    }
}
