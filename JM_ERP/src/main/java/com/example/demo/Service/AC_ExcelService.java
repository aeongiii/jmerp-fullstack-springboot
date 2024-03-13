package com.example.demo.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class AC_ExcelService {

    public <T> void exportToExcel(List<T> entityList, HttpServletResponse response) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            // 헤더 스타일 설정
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            // 엔티티 클래스의 필드를 검색하여 테이블 헤더 생성
            Row headerRow = sheet.createRow(0);
            Field[] fields = entityList.get(0).getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(fields[i].getName());
                cell.setCellStyle(headerStyle); // 헤더 스타일 적용
            }

            // 데이터 삽입
            int rowNum = 1;
            for (T entity : entityList) {
                Row row = sheet.createRow(rowNum++);
                int cellNum = 0;
                for (Field field : fields) {
                    field.setAccessible(true); // private 필드에 접근하기 위해 설정
                    Object value = field.get(entity);
                    Cell cell = row.createCell(cellNum++);
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }

            // 열 너비 조정
            for (int i = 0; i < fields.length; i++) {
                // 각 셀의 내용에 따라 최대 폭 설정
                for (int j = 0; j < sheet.getLastRowNum(); j++) {
                    Row row = sheet.getRow(j);
                    if (row != null) {
                        Cell cell = row.getCell(i);
                        if (cell != null && cell.getCellType() == CellType.STRING) {
                            String value = cell.getStringCellValue();
                            int length = calculateWidth(value);
                            int currentWidth = sheet.getColumnWidth(i);
                            int minWidth = 256 * length + 100;
                            if (currentWidth < minWidth) {
                                sheet.setColumnWidth(i, minWidth);
                            }
                        }
                    }
                }
            }

            // 엑셀 파일을 HttpServletResponse를 통해 브라우저로 전송
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=yourEntities.xlsx");

            workbook.write(response.getOutputStream());
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
            // 예외 처리를 원하는 대로 구현하세요.
        }
    }

    private int calculateWidth(String str) {
        int width = 0;
        for (char c : str.toCharArray()) {
            if (c >= 0xAC00 && c <= 0xD7AF) { // 한글 범위에 있는 문자인 경우
                width += 2; // 한글은 2칸 차지
            } else {
                width += 1; // 영문 및 숫자 등은 1칸 차지
            }
        }
        return width;
    }
}