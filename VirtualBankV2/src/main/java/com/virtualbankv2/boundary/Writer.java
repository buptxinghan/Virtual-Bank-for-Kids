package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.*;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.virtualbankv2.boundary.Reader.accounts;
import static com.virtualbankv2.boundary.Reader.users;
import static com.virtualbankv2.boundary.Reader.goals;
import static com.virtualbankv2.boundary.Reader.transactions;

/**
 * Writes data to CSV files.
 */
public class Writer {

    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    /**
     * Writes account data to a CSV file.
     *
     * @param data The list of accounts to be written.
     */
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

    /**
     * Writes goal data to a CSV file.
     *
     * @param data The list of goals to be written.
     */
    public void writeGoals(List<Goal> data) {
        try {
            FileWriter fw = new FileWriter("src/Data/Goals.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("GoalName,Description,TargetAmount,CurrentAmount,Username,startDate,endDate,targetAccount");
            for (Goal goal : data) {
                bw.newLine();
                bw.write(goal.getGoalName() + "," +
                        goal.getDescription() + "," +
                        decimalFormat.format(goal.getTargetAmount()) + "," +
                        decimalFormat.format(goal.getCurrentAmount()) + "," +
                        goal.getUsername() + "," +
                        goal.getStartDate() + "," +
                        goal.getEndDate());
            }
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes task data to a CSV file.
     *
     * @param data The list of tasks to be written.
     */
    public void writeTasks(List<Task> data) {
        try {
            FileWriter fw = new FileWriter("src/Data/Tasks.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("TaskID,Description,Reward,IsCompleted,Counter,Start,End,Title,UserName");
            for (Task task : data) {
                bw.newLine();
                bw.write(task.getTaskID() + "," +
                        task.getDescription() + "," +
                        decimalFormat.format(task.getReward()) + "," +
                        task.isCompleted() + "," +
                        task.getCounter()+","+
                        task.getStart()+","+
                        task.getEnd()+","+
                        task.getTitle()+","+
                        task.getUserName());
            }
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes transaction data to a CSV file.
     *
     * @param data The list of transactions to be written.
     */
    public void writeTransactions(List<Transaction> data) {
        try {
            FileWriter fw = new FileWriter("src/Data/Transactions.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("TransactionID,AccountFrom,AccountTo,Amount,Date,Description");
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

    /**
     * Writes user data to a CSV file.
     *
     * @param data The list of users to be written.
     */
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

    /**
     * Writes a single user to the user CSV file.
     *
     * @param tempUser The user to be written.
     */
    public void writeSingleUser(User tempUser) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Data/Users.csv", true)))) {
            users.add(tempUser);
            out.println(tempUser.getUsername() + "," + tempUser.getPassword() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a single account to the account CSV file.
     *
     * @param tempAccount The account to be written.
     */
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

    /**
     * Writes a single task to the task CSV file.
     *
     * @param tempTask The task to be written.
     */
    public void writeSingleTask(Task tempTask) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Data/Tasks.csv", true)))) {
           // tasks.add(tempTask);
            out.println(
                    tempTask.getTaskID() + "," +
                            tempTask.getDescription() + "," +
                            decimalFormat.format(tempTask.getReward()) + "," +
                            tempTask.isCompleted() + "," +
                            tempTask.getCounter()+","+
                            tempTask.getStart()+","+
                            tempTask.getEnd()+","+
                            tempTask.getTitle()+","+
                            tempTask.getUserName()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a single goal to the goal CSV file.
     *
     * @param tempGoal The goal to be written.
     */
    public void writeSingleGoal(Goal tempGoal) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Data/Goals.csv", true)))) {
            goals.add(tempGoal);
            out.println(
                    tempGoal.getGoalName() + "," +
                            tempGoal.getDescription() + "," +
                            decimalFormat.format(tempGoal.getTargetAmount()) + "," +
                            decimalFormat.format(tempGoal.getCurrentAmount()) + "," +
                            tempGoal.getUsername() + "," +
                            tempGoal.getStartDate() + "," +
                            tempGoal.getEndDate()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a single transaction to the transaction CSV file.
     *
     * @param tempTransaction The transaction to be written.
     */
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
