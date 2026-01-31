package com.example.parser;

import com.example.parser.dto.AmsroDto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    public static void writeToExcel(List<AmsroDto> data, String fileName) {
        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Amsros");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Name");
            header.createCell(1).setCellValue("INN");
            header.createCell(2).setCellValue("OGRN");
            header.createCell(3).setCellValue("Status");
            header.createCell(4).setCellValue("Is Active");
            header.createCell(5).setCellValue("Arbitr Manager Count");
            header.createCell(6).setCellValue("Date Of Registration");
            header.createCell(7).setCellValue("GUID");

            int rowNum = 1;
            for (AmsroDto dto : data) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(dto.getName());
                row.createCell(1).setCellValue(dto.getInn());
                row.createCell(2).setCellValue(dto.getOgrn());
                row.createCell(3).setCellValue(dto.getStatus());
                row.createCell(4).setCellValue(dto.getIsActive() != null && dto.getIsActive());
                row.createCell(5).setCellValue(dto.getArbitrManagerCount() != null ? dto.getArbitrManagerCount() : 0);
                row.createCell(6).setCellValue(dto.getDateOfRegistration() != null
                        ? dto.getDateOfRegistration().toString()
                        : "");
                row.createCell(7).setCellValue(dto.getGuid());
            }

            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
            }

            System.out.println("Excel file created: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
