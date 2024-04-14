package com.virtualbankv1;

import java.io.*;
import java.util.*;

public class Reader {

    // 读取User.csv文件并存储到变量中
    public List<User> readUsers(String filePath) {
        List<String[]> rawData = readCSV(filePath);
        List<User> users = new ArrayList<>();
        for (String[] data : rawData) {
            User user = new User(data[0], data[1]); // 假设User类有一个构造函数接受Username和Password
            users.add(user);
        }
        return users;
    }

    // 读取Account.csv文件并存储到变量中
    public List<Account> readAccounts(String filePath) {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // 跳过标题行
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Account account = new Account(values[0], values[1], values[2], values[3], Double.parseDouble(values[4]), values[5]);
                accounts.add(account);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // 读取Goals.csv文件并存储到变量中
    public List<Goal> readGoals(String filePath) {
        List<String[]> rawData = readCSV(filePath);
        List<Goal> goals = new ArrayList<>();
        for (String[] data : rawData) {
            Goal goal = new Goal(data[0], data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]), data[4]); // 假设Goal类有一个构造函数接受所有属性
            goals.add(goal);
        }
        return goals;
    }

    // 读取Tasks.csv文件并存储到变量中
    public List<Task> readTasks(String filePath) {
        List<String[]> rawData = readCSV(filePath);
        List<Task> tasks = new ArrayList<>();
        for (String[] data : rawData) {
            Task task = new Task(data[0], data[1], Double.parseDouble(data[2]), Boolean.parseBoolean(data[3]), Integer.parseInt(data[4])); // 假设Task类有一个构造函数接受所有属性
            tasks.add(task);
        }
        return tasks;
    }

    // 读取Support.csv文件并存储到变量中
    public List<SupportSystem> readSupport(String filePath) {
        List<String[]> rawData = readCSV(filePath);
        List<SupportSystem> supports = new ArrayList<>();
        for (String[] data : rawData) {
            SupportSystem support = new SupportSystem(data[0], data[1], data[2], data[3], data[4]); // 假设Support类有一个构造函数接受所有属性
            supports.add(support);
        }
        return supports;
    }

    // 读取Transactions.csv文件并存储到变量中
    public List<Transactions> readTransactions(String filePath) {
        List<String[]> rawData = readCSV(filePath);
        List<Transactions> transactions = new ArrayList<>();
        for (String[] data : rawData) {
            Transactions transaction = new Transactions(data[0], data[1], data[2], data[3], data[4]); // 假设Transactions类有一个构造函数接受所有属性
            transactions.add(transaction);
        }
        return transactions;
    }

    // 通用的CSV读取方法
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
}
