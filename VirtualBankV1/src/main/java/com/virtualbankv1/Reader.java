package com.virtualbankv1;

import java.io.*;
import java.util.*;

public class Reader {

    public List<User> readUsers(String filePath) {
        List<String[]> rawData = readCSV(filePath);
        List<User> users = new ArrayList<>();
        for (String[] data : rawData) {
            if (data[0].equals("Username")) { // 跳过标题行
                continue;
            }
            User user = new User(
                    data[0], //Username
                    data[1]  //Password
            ); // 创建User对象
            users.add(user);
        }
        return users;
    }

    public List<Account> readAccounts(String filePath) {
        List<String[]> rawData = readCSV(filePath);
        List<Account> accounts = new ArrayList<>();
        for (String[] data : rawData) {
            if (data[0].equals("AccountID")) { // 跳过标题行
                continue;
            }
            Account account = new Account(
                    data[0], // AccountID
                    data[1], // AccountType
                    data[2], // Username
                    data[3], // password
                    Double.parseDouble(data[4]), // Balance
                    data[5]  // Status
            );
            accounts.add(account);
        }
        return accounts;
    }

    public List<Goal> readGoals(String filePath) {
        List<String[]> rawData = readCSV(filePath);
        List<Goal> goals = new ArrayList<>();
        for (String[] data : rawData) {
            if (data[0].equals("GoalID")) { // 跳过标题行
                continue;
            }
            Goal goal = new Goal(
                    data[0], // GoalID
                    data[1], // Description
                    Double.parseDouble(data[2]), // TargetAmount
                    Double.parseDouble(data[3]), // CurrentAmount
                    data[4]  // Status
            );
            goals.add(goal);
        }
        return goals;
    }

    public List<Task> readTasks(String filePath) {
        List<String[]> rawData = readCSV(filePath);
        List<Task> tasks = new ArrayList<>();
        for (String[] data : rawData) {
            if (data[0].equals("TaskID")) { // 跳过标题行
                continue;
            }
            Task task = new Task(
                    data[0], // TaskID
                    data[1], // Description
                    Double.parseDouble(data[2]), // Reward
                    Boolean.parseBoolean(data[3]), // IsCompleted
                    Integer.parseInt(data[4]) // Counter
            );
            tasks.add(task);
        }
        return tasks;
    }

    public List<SupportSystem> readSupport(String filePath) {
        List<String[]> rawData = readCSV(filePath);
        List<SupportSystem> supports = new ArrayList<>();
        for (String[] data : rawData) {
            if (data[0].equals("SupportID")) { // 跳过标题行
                continue;
            }
            SupportSystem support = new SupportSystem(
                    data[0], // FAQ ID
                    data[1], // Question
                    data[2], // Answer
                    data[3], // Guide
                    data[4]  // ContactInfo
            );
            supports.add(support);
        }
        return supports;
    }

    public List<Transaction> readTransactions(String filePath) {
        List<String[]> rawData = readCSV(filePath);
        List<Transaction> transactions = new ArrayList<>();
        for (String[] data : rawData) {
            if (data[0].equals("TransactionID")) { // 跳过标题行
                continue;
            }
            Transaction transaction = new Transaction(
                    data[0], // TransactionID
                    data[1], // UserFrom,,
                    data[2], // UserTo
                    Double.parseDouble(data[3]) // Amount
            );
            transactions.add(transaction);
        }
        return transactions;
    }


    // 通用的CSV读取方法 //需修改
    public List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        Reader reader = new Reader();
        List<Account> accounts =  reader.readAccounts("src/main/Data/User.csv");

        // 测试系统功能
    }
}
