package com.virtualbankv1.boundary;

import com.virtualbankv1.entity.*;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.virtualbankv1.boundary.Reader.accounts;
import static com.virtualbankv1.boundary.Reader.users;
import static com.virtualbankv1.boundary.Reader.tasks;
import static com.virtualbankv1.boundary.Reader.goals;
import static com.virtualbankv1.boundary.Reader.transactions;

public class Writer {

    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); // 保留两位小数
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public void writeAccounts(List<Account> data) {
        try {
            FileWriter fw = new FileWriter("src/Data/Accounts.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("AccountID,AccountType,Username,Password,Balance,Status");
            for (Account account : data) {
                bw.newLine();
                bw.write(account.getAccountID() + "," +
                        account.getAccountType() + "," +
                        account.getUsername() + "," +
                        account.getPassword() + "," +
                        decimalFormat.format(account.getBalance()) + "," +
                        account.getStatus());
            }
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeGoals(List<Goal> data) {
        try {
            FileWriter fw = new FileWriter("src/Data/Goals.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("GoalID,Description,TargetAmount,CurrentAmount,ChildUsername");
            for (Goal goal : data) {
                bw.newLine();
                bw.write(goal.getGoalID() + "," +
                        goal.getDescription() + "," +
                        decimalFormat.format(goal.getTargetAmount()) + "," +
                        decimalFormat.format(goal.getCurrentAmount()) + "," +
                        goal.getChildUsername());
            }
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTasks(List<Task> data) {
        try {
            FileWriter fw = new FileWriter("src/Data/Tasks.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("TaskID,Description,Reward,IsCompleted,Counter,Start,End,Title");
            for (Task task : data) {
                bw.newLine();
                bw.write(task.getTaskID() + "," +
                        task.getDescription() + "," +
                        decimalFormat.format(task.getReward()) + "," +
                        task.isCompleted() + "," +
                        task.getCounter()+","+
                        task.getStart()+","+
                        task.getEnd()+","+
                        task.getTitle());
            }
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTransactions(List<Transaction> data) {
        try {
            FileWriter fw = new FileWriter("src/Data/Transactions.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("TransactionID,AccountFrom,AccountTo,Amount,Date");
            for (Transaction transaction: data) {
                bw.newLine();
                bw.write(transaction.getTransactionID() + "," +
                        transaction.getAccountFrom() + "," +
                        transaction.getAccountTo() + "," +
                        decimalFormat.format(transaction.getAmount()) + "," +
                        dateFormatter.format(LocalDate.now()) + "," +
                        transaction.getDescription());
            }
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeUsers(List<User> data) {
        try {
            FileWriter fw = new FileWriter("src/Data/Users.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Username,Password");
            for (User user : data) {
                bw.newLine();
                bw.write(user.getUsername() + "," + user.getPassword());
            }
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSingleUser(User tempUser) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Data/Users.csv", true)))) {
            users.add(tempUser);
            out.println(tempUser.getUsername() + "," + tempUser.getPassword() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSingleAccount(Account tempAccount) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Data/Accounts.csv", true)))) {
            accounts.add(tempAccount);
            out.println(
                            tempAccount.getAccountID() + "," +
                            tempAccount.getAccountType() + "," +
                            tempAccount.getUsername() + "," +
                            tempAccount.getPassword() + "," +
                            decimalFormat.format(tempAccount.getBalance()) + "," +
                            tempAccount.getStatus()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSingleTask(Task tempTask) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Data/Tasks.csv", true)))) {
            //tasks.add(tempTask);
            out.println(
                            tempTask.getTaskID() + "," +
                            tempTask.getDescription() + "," +
                            decimalFormat.format(tempTask.getReward()) + "," +
                            tempTask.isCompleted() + "," +
                            tempTask.getCounter()+","+
                            tempTask.getStart()+","+
                            tempTask.getEnd()+","+
                            tempTask.getTitle()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSingleGoal(Goal tempGoal) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Data/Goals.csv", true)))) {
            goals.add(tempGoal);
            out.println(
                    tempGoal.getGoalID() + "," +
                            tempGoal.getDescription() + "," +
                            decimalFormat.format(tempGoal.getTargetAmount()) + "," +
                            decimalFormat.format(tempGoal.getCurrentAmount()) + "," +
                            tempGoal.getChildUsername()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSingleTransaction(Transaction tempTransaction) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Data/Transactions.csv", true)))) {
            transactions.add(tempTransaction);
            out.println(
                    tempTransaction.getTransactionID() + "," +
                            tempTransaction.getAccountFrom() + "," +
                            tempTransaction.getAccountTo() + "," +
                            decimalFormat.format(tempTransaction.getAmount()) + "," +
                            dateFormatter.format(LocalDate.now()) + "," +
                            tempTransaction.getDescription()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
