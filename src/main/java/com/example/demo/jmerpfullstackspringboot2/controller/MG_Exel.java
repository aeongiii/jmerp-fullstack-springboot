package com.example.demo.jmerpfullstackspringboot2.controller;

import com.example.demo.jmerpfullstackspringboot2.entity.Mg_AccountMG_Entity;
import com.example.demo.jmerpfullstackspringboot2.service.MG_ExleService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MG_Exel {

    @Autowired
    private MG_ExleService mgexle;

    public MG_Exel(MG_ExleService mgexle) {
        this.mgexle = mgexle;
    }

    @PostMapping("/MG/exeldown")
    public void exelDown(HttpServletResponse response) throws IOException {
        List<Mg_AccountMG_Entity> accEnti = mgexle.getaccountInfo();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("AccountInfo");
//시트 생성 & 이름 정함
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 3);
        sheet.addMergedRegion(region);

        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        CellStyle borderstyle = workbook.createCellStyle();
        borderstyle.setAlignment(HorizontalAlignment.CENTER);
        borderstyle.setVerticalAlignment(VerticalAlignment.CENTER);
        borderstyle.setBorderRight(BorderStyle.THICK);

        // 스타일 끝

        Row headrow = sheet.createRow(0);
        Cell headcell = headrow.createCell(0);
        headcell.setCellStyle(borderstyle);
        headcell.setCellValue("거래처 조회 목록");
//		메인 헤드

        Row headerRow = sheet.createRow(1);

        Cell cell1 = headerRow.createCell(0);
        cell1.setCellValue("사업자 번호");
        cell1.setCellStyle(style);

        Cell cell2 = headerRow.createCell(1);
        cell2.setCellValue("거래처 전화번호");
        cell2.setCellStyle(style);

        Cell cell3 = headerRow.createCell(2);
        cell3.setCellValue("거래처 이름");
        cell3.setCellStyle(style);

        Cell cell4 = headerRow.createCell(3);
        cell4.setCellValue("거래처 담당자");
        cell4.setCellStyle(style);
//		헤드 마무리

        int rowNum = 2;

        for (Mg_AccountMG_Entity entity : accEnti) {
            Row row = sheet.createRow(rowNum++);
            Cell rowCell = row.createCell(0);
            Cell rowCell1 = row.createCell(1);
            Cell rowCell2 = row.createCell(2);
            Cell rowCell3 = row.createCell(3);

            rowCell.setCellStyle(style);
            rowCell.setCellValue(entity.getAccountCode());
            rowCell1.setCellStyle(style);
            rowCell1.setCellValue(entity.getAccountNum());
            rowCell2.setCellStyle(style);
            rowCell2.setCellValue(entity.getAccountName());
            rowCell3.setCellStyle(style);
            rowCell3.setCellValue(entity.getAccountManager());
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.setColumnWidth(2, 12 * 256);
        sheet.setColumnWidth(3, 15 * 256);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=accounts.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
    }

}
