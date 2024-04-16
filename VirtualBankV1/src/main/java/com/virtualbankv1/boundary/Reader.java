package com.virtualbankv1.boundary;

import com.virtualbankv1.entity.*;

import java.io.*;
import java.util.*;

public class Reader {

    public List<User> readUsers(String filePath) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // 跳过标题行
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                User user = new User(
                        values[0], // Username
                        values[1]  // Password
                );
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }


    public List<Account> readAccounts(String filePath) {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // 跳过标题行
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Account account = new Account(
                        values[0], // AccountID
                        values[1], // AccountType
                        values[2], // Username
                        values[3], // Password
                        Double.parseDouble(values[4]), // Balance
                        values[5]  // Status
                );
                accounts.add(account);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public List<Goal> readGoals(String filePath) {
        List<Goal> goals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // 跳过标题行
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Goal goal = new Goal(
                        values[0], // GoalID
                        values[1], // Description
                        Double.parseDouble(values[2]), // TargetAmount
                        Double.parseDouble(values[3]), // CurrentAmount
                        values[4]  // Status
                );
                goals.add(goal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goals;
    }

    public List<Task> readTasks(String filePath) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // 跳过标题行
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Task task = new Task(
                        values[0], // TaskID
                        values[1], // Description
                        Double.parseDouble(values[2]), // Reward
                        Boolean.parseBoolean(values[3]), // IsCompleted
                        Integer.parseInt(values[4]) // Counter
                );
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public List<SupportSystem> readSupport(String filePath) {
        List<SupportSystem> supports = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // 跳过标题行
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                SupportSystem support = new SupportSystem(
                        values[0], // FAQ ID
                        values[1], // Question
                        values[2], // Answer
                        values[3], // Guide
                        values[4]  // ContactInfo
                );
                supports.add(support);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return supports;
    }

    public List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // 跳过标题行
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Transaction transaction = new Transaction(
                        values[0], // TransactionID
                        values[1], // UserFrom
                        values[2], // UserTo
                        Double.parseDouble(values[3]) // Amount
                );
                transactions.add(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
