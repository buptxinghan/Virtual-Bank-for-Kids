package com.virtualbankv1.boundary;

import com.virtualbankv1.entity.*;
import static com.virtualbankv1.control.VirtualBankApplication.*;

import java.io.*;
import java.util.*;

/**
 * The Reader class provides methods to read data from CSV files and populate lists of various entities,
 * such as users, accounts, transactions, tasks, goals, and support information.
 */
public class Reader {
    /** List to store all user account information. */
    public static List<User> users;

    /** List to store all account information. */
    public static List<Account> accounts;

    /** List to store all transaction records of accounts. */
    public static List<Transaction> transactions;

    /** List to store all tasks set by parents. */
    public static List<Task> tasks;

    /** List to store all long-term goals set by users. */
    public static List<Goal> goals;

    /** List to store user support and help information. */
    public static List<SupportSystem> support; // 提供用户支持和帮助信息

    /**
     * Constructs a Reader object and initializes lists by reading data from CSV files.
     * Reads data for users, accounts, transactions, tasks, goals, and support information.
     */
    public Reader() {
        users = this.readUsers("src/Data/Users.csv");
        accounts = this.readAccounts("src/Data/Accounts.csv");
        transactions = this.readTransactions("src/Data/Transactions.csv");
        tasks = this.readTasks("src/Data/Tasks.csv");
        goals = this.readGoals("src/Data/Goals.csv");
        support = this.readSupport("src/Data/Support.csv");
    }

    /**
     * Reads user data from a CSV file and populates a list of User objects.
     *
     * @param filePath The path to the CSV file containing user data.
     * @return A list of User objects populated with data from the CSV file.
     */
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

    /**
     * Reads account data from a CSV file and populates a list of Account objects.
     *
     * @param filePath The path to the CSV file containing account data.
     * @return A list of Account objects populated with data from the CSV file.
     */
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

    /**
     * Reads goal data from a CSV file and populates a list of Goal objects.
     *
     * @param filePath The path to the CSV file containing goal data.
     * @return A list of Goal objects populated with data from the CSV file.
     */
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

    /**
     * Reads task data from a CSV file and populates a list of Task objects.
     *
     * @param filePath The path to the CSV file containing task data.
     * @return A list of Task objects populated with data from the CSV file.
     */
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
                        Integer.parseInt(values[4]),// Counter
                        values[5],
                        values[6],
                        values[7]
                );
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Reads support information from a CSV file and populates a list of SupportSystem objects.
     *
     * @param filePath The path to the CSV file containing support information.
     * @return A list of SupportSystem objects populated with data from the CSV file.
     */
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

    /**
     * Reads transaction data from a CSV file and populates a list of Transaction objects.
     *
     * @param filePath The path to the CSV file containing transaction data.
     * @return A list of Transaction objects populated with data from the CSV file.
     */
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
                        Double.parseDouble(values[3]), // Amount
                        values[4], // Date
                        values[5]  // Description
                );
                transactions.add(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

}
