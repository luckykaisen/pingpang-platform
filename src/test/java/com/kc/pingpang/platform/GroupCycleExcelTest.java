package com.kc.pingpang.platform;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;

import java.util.ArrayList;
import java.util.List;

public class GroupCycleExcelTest {

//    @Test
    public void fill() {
        String templateFileName = "E:/顺序循环模板.xlsx";

        // 方案1 根据对象填充
        String fileName = "E:/顺序循环模板A.xlsx";

        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量

        List<FillData> list = new ArrayList<>();
        list.add(new FillData("预约"));
        list.add(new FillData("张三"));
        list.add(new FillData("张三封"));
        list.add(new FillData("不不不"));
        list.add(new FillData("张三"));
        list.add(new FillData("啊啊啊"));
        list.add(new FillData("张三"));
        list.add(new FillData("张三"));
        list.add(new FillData("里思想"));

        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        excelWriter.fill(list, fillConfig, writeSheet);

        excelWriter.finish();
    }

    static class FillData {

        private String name;

        public FillData() {
        }

        public FillData(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
