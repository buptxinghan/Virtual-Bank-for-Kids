package com.virtualbankv1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter {

    public static void main(String[] args) {
        // 创建一个新的工作簿
        Workbook workbook = new XSSFWorkbook();

        // 创建一个工作表
        Sheet sheet = workbook.createSheet("Accounts");

        // 创建标题行
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("AccountID");
        headerRow.createCell(1).setCellValue("Username");
        headerRow.createCell(2).setCellValue("AccountType");
        headerRow.createCell(3).setCellValue("Balance");
        headerRow.createCell(4).setCellValue("Status");

        // 添加数据行（示例）
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(1);
        dataRow.createCell(1).setCellValue("User1");
        dataRow.createCell(2).setCellValue("Current");
        dataRow.createCell(3).setCellValue(1000.00);
        dataRow.createCell(4).setCellValue("Active");

        // 将工作簿写入文件
        try (FileOutputStream outputStream = new FileOutputStream("VirtualBankData.xlsx")) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

