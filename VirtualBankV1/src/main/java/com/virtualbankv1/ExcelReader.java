package com.virtualbankv1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public static void main(String[] args) {
        // 存储从Excel文件读取的数据
        List<User> users = new ArrayList<>();
        List<com.virtualbankv1.Transaction> transactions = new ArrayList<>();
        // ... 其他列表

        try (FileInputStream inputStream = new FileInputStream("VirtualBankData.xlsx")) {
            Workbook workbook = new XSSFWorkbook(inputStream);

            // 读取账户信息
            Sheet accountsSheet = workbook.getSheet("Accounts");
            for (Row row : accountsSheet) {
                if (row.getRowNum() == 0) continue; // 跳过标题行
                User user = new User();
               user.setAccountId((int) row.getCell(0).getNumericCellValue());
               user.setUsername(row.getCell(1).getStringCellValue());
               user.setType(row.getCell(2).getStringCellValue());
               user.setBalance(row.getCell(3).getNumericCellValue());
               user.setStatus(row.getCell(4).getStringCellValue());
                users.add(user);
            }

            // 读取交易记录
            Sheet transactionsSheet = workbook.getSheet("Transactions");
            for (Row row : transactionsSheet) {
                if (row.getRowNum() == 0) continue; // 跳过标题行
                Transaction transaction = new Transaction();
                transaction.setTransactionId((int) row.getCell(0).getNumericCellValue());
                transaction.setAccountId((int) row.getCell(1).getNumericCellValue());
                transaction.setType(row.getCell(2).getStringCellValue());
                transaction.setAmount(row.getCell(3).getNumericCellValue());
                transaction.setDate(row.getCell(4).getDateCellValue());
                transactions.add(transaction);
            }

            // ... 读取其他列表

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 打印读取的数据（示例）
        for (User user : users) {
            System.out.println("Account ID: " + user.getAccountId());
            // ... 打印其他属性
        }
        // ... 打印其他列表的数据
    }
}
