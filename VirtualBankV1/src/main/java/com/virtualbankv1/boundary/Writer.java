package com.virtualbankv1.boundary;

import com.virtualbankv1.entity.*;
import java.io.*;
import java.util.*;

public class Writer {
    public void writeAccounts(String filePath, List<Account> data) {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("AccountID,AccountType,Username,Password,Balance,Status");
            for (Account account : data) {
                bw.newLine();
                bw.write(account.getAccountID() + "," +
                        account.getAccountType() + "," +
                        account.getUsername() + "," +
                        account.getPassword() + "," +
                        account.getBalance() + "," +
                        account.getStatus());
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeGoals(String filePath, List<Goal> data) {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("GoalID,Description,TargetAmount,CurrentAmount,ChildUsername");
            for (Goal goal : data) {
                bw.newLine();
                bw.write(goal.getGoalID() + "," +
                        goal.getDescription() + "," +
                        goal.getTargetAmount() + "," +
                        goal.getCurrentAmount() + "," +
                        goal.getChildUsername());
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTasks(String filePath, List<Task> data) {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("TaskID,Description,Reward,IsCompleted,Counter");
            for (Task task : data) {
                bw.newLine();
                bw.write(task.getTaskID() + "," +
                        task.getDescription() + "," +
                        task.getReward() + "," +
                        task.isCompleted() + "," +
                        task.getCounter());
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTransactions(String filePath, List<Transaction> data) {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("TransactionID,UserFrom,UserTo,Amount");
            for (Transaction transaction: data) {
                bw.newLine();
                bw.write(transaction.getTransactionID() + "," +
                        transaction.getUserFrom() + "," +
                        transaction.getUserTo() + "," +
                        transaction.getAmount());
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeUsers(String filePath, List<User> data) {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Username,Password");
            for (User user : data) {
                bw.newLine();
                bw.write(user.getUsername() + "," + user.getPassword());
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
