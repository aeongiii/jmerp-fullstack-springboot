package com.example.demo.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class AC_ExcelService {

    // AC_Bond 대신 T를 사용할 수 있습니다.
    public <T> void exportToExcel(List<T> entityList, HttpServletResponse response) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            // 엔티티 클래스의 필드를 검색하여 테이블 헤더 생성
            Row headerRow = sheet.createRow(0);
            Field[] fields = entityList.get(0).getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(fields[i].getName());
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

            // 엑셀 파일을 HttpServletResponse를 통해 브라우저로 전송
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=yourEntities.xlsx");

            workbook.write(response.getOutputStream());
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
            // 예외 처리를 원하는 대로 구현하세요.
        }
    }
}

