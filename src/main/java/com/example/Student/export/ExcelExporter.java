package com.example.Student.export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExporter {

    public ByteArrayInputStream exportToExcel(String sheetName, List<? extends ExcelExportable> data) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(sheetName);

            // Header
            if (!data.isEmpty()) {
                Row headerRow = sheet.createRow(0);
                List<String> headers = data.get(0).getHeaders();
                for (int i = 0; i < headers.size(); i++) {
                    headerRow.createCell(i).setCellValue(headers.get(i));
                }
            }

            // Rows
            int rowIdx = 1;
            for (ExcelExportable item : data) {
                Row row = sheet.createRow(rowIdx++);
                List<String> rowData = item.getRowData();
                for (int i = 0; i < rowData.size(); i++) {
                    row.createCell(i).setCellValue(rowData.get(i));
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
