package com.kc.pingpang.platform.service.competition;

import com.kc.pingpang.platform.data.filter.CompetitionFilter;
import com.kc.pingpang.platform.data.mapper.CompetitionMapper;
import com.kc.pingpang.platform.data.mapper.PlayerMapper;
import com.kc.pingpang.platform.data.model.*;
import com.kc.pingpang.platform.freamwork.db.filter.PagingData;
import com.kc.pingpang.platform.freamwork.db.filter.PagingResult;
import com.kc.pingpang.platform.freamwork.db.filter.SearchResult;
import com.kc.pingpang.platform.service.competition.api.ICompetitionService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class CompetitionService implements ICompetitionService {

    @Value("${system.storage.path}")
    private String storagePath;

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private PlayerMapper playerMapper;

    @Override
    public SearchResult<Competition> searchCompetitionByFilter(CompetitionFilter filter) {
        SearchResult<Competition> result = new SearchResult<>();

        List<Competition> list = competitionMapper.selectCompetitionByFilter(filter);
        result.setResult(list);

        PagingData pagingData = filter.getPagingData();
        if (filter.isPaged() && pagingData != null) {
            int recordNumber = competitionMapper.countCompetitionByFilter(filter);

            PagingResult pagingResult = new PagingResult();

            pagingResult.setRecordNumber(recordNumber);
            pagingResult.setPageSize(pagingData.getPageSize());
            pagingResult.setPageNumber(pagingData.getPageNumber());

            result.setPaged(true);
            result.setPagingResult(pagingResult);
        }

        return result;
    }

    @Override
    @Transactional
    public void joinCompetition(String playerName, Competition competition) {

        Player player = playerMapper.selectPlayerByName(playerName);

        if (player == null) {
            player = new Player();
            player.setName(playerName);

            playerMapper.insertPlayer(player);
        }

        CompetitionPlayer competitionPlayer = new CompetitionPlayer();
        competitionPlayer.setPlayerName(player.getName());
        competitionPlayer.setPlayerId(player.getId());
        competitionPlayer.setCompetitionId(competition.getId());

        competitionMapper.insertCompetitionPlayer(competitionPlayer);
    }

    @Override
    @Transactional
    public void deleteCompetitionPlayer(CompetitionPlayer competitionPlayer) {

        competitionMapper.deleteCompetitionGroupPlayerByCompetitionIdAndPlayerId(
                competitionPlayer.getCompetitionId(), competitionPlayer.getPlayerId());

        competitionMapper.deleteCompetitionPlayer(competitionPlayer.getId());
    }

    @Override
    @Transactional
    public void deleteCompetitionGroup(Integer groupId) {
        competitionMapper.deleteCompetitionGroupPlayerByGroupId(groupId);
        competitionMapper.deleteCompetitionGroup(groupId);
    }

    @Override
    @Transactional
    public void addCompetitionGroup(Integer id) {

        CompetitionGroup lastGroup = competitionMapper.selectLastCompetitionGroupByCompetitionId(id);

        CompetitionGroup group = new CompetitionGroup();
        group.setCompetitionId(id);

        if (lastGroup == null) {
            group.setName(Group.GROUP_1.getName());
        } else {
            Group nextGroup = Group.fromId(Group.fromName(lastGroup.getName()).getId() + 1);

            if (nextGroup != null) {
                group.setName(nextGroup.getName());
            }
        }

        competitionMapper.insertCompetitionGroup(group);
    }

    @Override
    public String downloadGroupRoundRobinExcel(Integer id) throws Exception {

        Competition competition = competitionMapper.selectCompetitionById(id);

        Workbook workbook = new XSSFWorkbook();

        CellStyle headCellStyle = workbook.createCellStyle();
        headCellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
        Font headFont = workbook.createFont();
        headFont.setFontName("仿宋_GB2312");
        headFont.setBold(true);//粗体显⽰
        headFont.setFontHeightInPoints((short) 20);  //字体⼤⼩
        headCellStyle.setFont(headFont);//选择需要⽤到的字体格式

        CellStyle contentCellStyle = workbook.createCellStyle();
        contentCellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
        Font contentFont = workbook.createFont();
        contentFont.setFontName("仿宋_GB2312");
        contentFont.setBold(true);//粗体显⽰
        contentFont.setFontHeightInPoints((short) 21);  //字体⼤⼩
        contentCellStyle.setFont(contentFont);//选择需要⽤到的字体格式
        contentCellStyle.setBorderLeft(BorderStyle.THIN);
        contentCellStyle.setBorderRight(BorderStyle.THIN);
        contentCellStyle.setBorderTop(BorderStyle.THIN);
        contentCellStyle.setBorderBottom(BorderStyle.THIN);

        CellStyle emptyCellStyle = workbook.createCellStyle();
        emptyCellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
        emptyCellStyle.setBorderLeft(BorderStyle.THIN);
        emptyCellStyle.setBorderRight(BorderStyle.THIN);
        emptyCellStyle.setBorderTop(BorderStyle.THIN);
        emptyCellStyle.setBorderBottom(BorderStyle.THIN);

        //2.创建一个工作表
        XSSFSheet sheet = (XSSFSheet) workbook.createSheet("小组循环");

        for (int i = 0; i < competition.getGroups().size(); i++) {

            CompetitionGroup group = competition.getGroups().get(i);
            List<CompetitionGroupPlayer> players = group.getPlayers();

            int nameSize = group.getPlayers().size();
            if (nameSize == 0) {
                continue;
            }

            int rowIndex = i * (nameSize + 7);

            Row headRow = sheet.createRow(rowIndex);
            headRow.setHeightInPoints(30);

            CellRangeAddress headCellAddresses = new CellRangeAddress(rowIndex, rowIndex, 0, nameSize + 2);
            sheet.addMergedRegion(headCellAddresses);
            Cell headCell = headRow.createCell(0);
            headCell.setCellValue(group.getName() + "循环比赛用表");
            headCell.setCellStyle(headCellStyle);

            RegionUtil.setBorderBottom(BorderStyle.THIN, headCellAddresses, sheet); // 下边框
            RegionUtil.setBorderLeft(BorderStyle.THIN, headCellAddresses, sheet); // 左边框
            RegionUtil.setBorderRight(BorderStyle.THIN, headCellAddresses, sheet); // 有边框
            RegionUtil.setBorderTop(BorderStyle.THIN, headCellAddresses, sheet); // 上边框

            // 空白
            CellRangeAddress emptyCellAddresses = new CellRangeAddress(rowIndex + 1, rowIndex + 2, 0, 1);
            sheet.addMergedRegion(emptyCellAddresses);

            RegionUtil.setBorderBottom(BorderStyle.THIN, emptyCellAddresses, sheet); // 下边框
            RegionUtil.setBorderLeft(BorderStyle.THIN, emptyCellAddresses, sheet); // 左边框
            RegionUtil.setBorderRight(BorderStyle.THIN, emptyCellAddresses, sheet); // 有边框
            RegionUtil.setBorderTop(BorderStyle.THIN, emptyCellAddresses, sheet); // 上边框

            Row numRow = sheet.createRow(rowIndex + 1);
            Row nameRow = sheet.createRow(rowIndex + 2);

            numRow.setHeightInPoints(40);
            nameRow.setHeightInPoints(40);

            Cell emptyContentCell = numRow.createCell(0);
            emptyContentCell.setCellValue("x");
            emptyContentCell.setCellStyle(headCellStyle);

            for (int j = 0; j < nameSize; j++) {

                String playerName = players.get(j).getPlayer().getName();

                // 横向序号
                Cell numCell = numRow.createCell(j + 2);
                numCell.setCellValue(j + 1);
                numCell.setCellStyle(contentCellStyle);

                // 横向姓名
                Cell nameCell = nameRow.createCell(j + 2);
                nameCell.setCellValue(playerName);
                nameCell.setCellStyle(contentCellStyle);
                sheet.setColumnWidth(j + 2, 4000);

                // 左侧序号
                Row row = sheet.createRow(rowIndex + j + 3);
                row.setHeightInPoints(40);
                Cell leftNumCell = row.createCell(0);
                leftNumCell.setCellValue(j + 1);
                leftNumCell.setCellStyle(contentCellStyle);

                // 左侧姓名
                Cell leftNameCell = row.createCell(1);
                leftNameCell.setCellValue(playerName);
                leftNameCell.setCellStyle(contentCellStyle);

                sheet.setColumnWidth(1, 4000);

                for (int x = 0; x < nameSize + 1; x++) {
                    Cell emptyCell = row.createCell(x + 2);
                    emptyCell.setCellStyle(emptyCellStyle);
                }

                drawLineArrow(workbook, sheet, rowIndex + j + 3, rowIndex + j + 4, j + 2, j + 3);
            }

            Cell lastNameCell = nameRow.createCell(2 + nameSize);
            lastNameCell.setCellStyle(emptyCellStyle);

            Cell resultCell = numRow.createCell(2 + nameSize);
            resultCell.setCellValue("比赛结果");
            resultCell.setCellStyle(emptyCellStyle);
        }

        String path = storagePath + "/excel/小组循环-" + System.currentTimeMillis() + ".xlsx";
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        fileOutputStream.close();

        return path;
    }

    private static void drawLineArrow(Workbook workbook, XSSFSheet sheet, int row1, int row2, int col1, int col2) {

        CreationHelper helper = workbook.getCreationHelper();
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();

        // 设置斜线的开始位置
        anchor.setCol1(col1);
        anchor.setRow1(row1);

        // 设置斜线的结束位置
        anchor.setCol2(col2);
        anchor.setRow2(row2);

        XSSFSimpleShape shape = drawing.createSimpleShape((XSSFClientAnchor) anchor);
        shape.setShapeType(ShapeTypes.LINE);
        shape.setLineWidth(0.5);
        shape.setLineStyle(0);
        shape.setLineStyleColor(0, 0, 0);
    }
}
