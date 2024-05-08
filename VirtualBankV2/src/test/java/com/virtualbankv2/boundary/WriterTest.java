package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {

    private Writer writer;
    private List<Account> accounts;
    private List<Goal> goals;
    private List<Task> tasks;
    private List<Transaction> transactions;
    private List<User> users;

    @BeforeEach
    void setUp() {
        writer = new Writer();
        accounts = new ArrayList<>();
        goals = new ArrayList<>();
        tasks = new ArrayList<>();
        transactions = new ArrayList<>();
        users = new ArrayList<>();
        users.add(new User("testuser", "testpassword"));
        accounts.add(new Account("ACC001", "Savings", "testuser", "password", 1000.0, true));
        goals.add(new Goal("Buy a Car", "Saving for a car", 5000.0, 2000.0, "testuser", LocalDate.now(), LocalDate.now().plusYears(1)));
        tasks.add(new Task("Task001", "Do something", 100.0, false, 0, LocalDate.now(), LocalDate.now().plusDays(7), "Task Title", "testuser"));
        transactions.add(new Transaction("TR001", "ACC001", "ACC002", 500.0, LocalDate.now(), "Payment for services"));
    }

    @Test
    void testWriteAccounts() {
        writer.writeAccounts(accounts);
        assertTrue(Files.exists(Paths.get("src/Data/Accounts.csv")));
    }

    @Test
    void testWriteSingleAccount() {
        Account tempAccount = new Account("ACC002", "Checking", "testuser", "password", 2000.0, true);
        writer.writeSingleAccount(tempAccount);
        assertTrue(Files.exists(Paths.get("src/Data/Accounts.csv")));
    }

    @Test
    void testWriteGoals() {
        writer.writeGoals(goals);
        assertTrue(Files.exists(Paths.get("src/Data/Goals.csv")));
    }

    @Test
    void testWriteSingleGoal() {
        Goal tempGoal = new Goal("Vacation", "Saving for vacation", 3000.0, 1000.0, "testuser", LocalDate.now(), LocalDate.now().plusMonths(6));
        writer.writeSingleGoal(tempGoal);
        assertTrue(Files.exists(Paths.get("src/Data/Goals.csv")));
    }

    @Test
    void testWriteTasks() {
        writer.writeTasks(tasks);
        assertTrue(Files.exists(Paths.get("src/Data/Tasks.csv")));
        // 检查写入的任务文件内容，确保完成状态为字符串而不是布尔值
        try {
            String tasksContent = Files.readString(Paths.get("src/Data/Tasks.csv"));
            assertNotNull(tasksContent);
            assertTrue(tasksContent.contains("Task001,Do something,100.00,false")); // 这里完成状态已经是字符串了
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteSingleTask() {
        Task tempTask = new Task("Task002", "Exercise daily", 50.0, false, 0, LocalDate.now(), LocalDate.now().plusDays(30), "Fitness Goal", "testuser");
        writer.writeSingleTask(tempTask);
        assertTrue(Files.exists(Paths.get("src/Data/Tasks.csv")));
        // 检查写入的任务文件内容，确保完成状态为字符串而不是布尔值
        try {
            String tasksContent = Files.readString(Paths.get("src/Data/Tasks.csv"));
            assertNotNull(tasksContent);
            assertTrue(tasksContent.contains("Task002,Exercise daily,50.00,false")); // 这里完成状态已经是字符串了
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteTransactions() {
        writer.writeTransactions(transactions);
        assertTrue(Files.exists(Paths.get("src/Data/Transactions.csv")));
    }

    @Test
    void testWriteSingleTransaction() {
        Transaction tempTransaction = new Transaction("TR002", "ACC002", "ACC001", 300.0, LocalDate.now(), "Payment received");
        writer.writeSingleTransaction(tempTransaction);
        assertTrue(Files.exists(Paths.get("src/Data/Transactions.csv")));
    }

    @Test
    void testWriteUsers() {
        writer.writeUsers(users);
        assertTrue(Files.exists(Paths.get("src/Data/Users.csv")));
    }

    @Test
    void testWriteSingleUser() {
        User tempUser = new User("newuser", "newpassword");
        writer.writeSingleUser(tempUser);
        assertTrue(Files.exists(Paths.get("src/Data/Users.csv")));
    }

}
