package com.virtualbankv2.boundary;

import com.virtualbankv2.control.InterestController;
import com.virtualbankv2.entity.*;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.virtualbankv2.boundary.Reader.*;

/**
 * The Writer class is responsible for writing various data entities to CSV files.
 * It handles the conversion of data from objects to a comma-separated text format
 * that can be easily read and written to a file. This class is part of the boundary
 * layer, facilitating persistent storage of application data.
 * Each method in this class corresponds to a specific data entity and is responsible
 * for writing that entity's data to an associated CSV file. The class utilizes
 * java.io.FileWriter and BufferedWriter to write the data, and java.text.DecimalFormat
 * and java.time.format.DateTimeFormatter to format the data appropriately before writing.
 * Exception handling is implemented to catch and log any IOExceptions that may occur
 * during the file writing process. Additionally, methods are provided to write single
 * entries to the CSV files.
 *
 * @author Feng Shiyu
 * @since 1.0
 */

public class Writer {

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

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
            bw.write("GoalName,Description,TargetAmount,CurrentAmount,Username,startDate,endDate");
            for (Goal goal : data) {
                bw.newLine();
                bw.write(goal.getGoalName() + "," +
                        goal.getDescription() + "," +
                        decimalFormat.format(goal.getTargetAmount()) + "," +
                        decimalFormat.format(goal.getCurrentAmount()) + "," +
                        goal.getUsername());
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
     * Writes interest data to a CSV file.
     *
     * @param interests The list of interests to be written.
     */
    public void writeInterest(List<Interest> interests) {
        try {
            FileWriter fw = new FileWriter("src/Data/Interest.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Username,LastUpdate");
            for (Interest interest : interests) {
                bw.newLine();
                bw.write(interest.getUsername() + "," + interest.getLastUpdate());
            }
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a single interest to the user CSV file.
     *
     * @param interest The interest to be written.
     */
    public void writeSingleInterest(Interest interest) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Data/Interest.csv", true)))) {
            interests.add(interest);
            out.println(interest.getUsername() + "," + dateFormatter.format(LocalDate.now()));
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
            tasks.add(tempTask);
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
                            tempGoal.getUsername()
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
