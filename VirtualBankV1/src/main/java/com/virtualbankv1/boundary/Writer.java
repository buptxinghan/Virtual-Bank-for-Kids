package com.virtualbankv1.boundary;

import com.virtualbankv1.entity.*;
import java.io.*;
import java.util.*;

import static com.virtualbankv1.boundary.Reader.accounts;
import static com.virtualbankv1.boundary.Reader.users;
import static com.virtualbankv1.boundary.Reader.tasks;
import static com.virtualbankv1.boundary.Reader.goals;
import static com.virtualbankv1.boundary.Reader.transactions;

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
            bw.write("TransactionID,AccountFrom,AccountTo,Amount");
            for (Transaction transaction: data) {
                bw.newLine();
                bw.write(transaction.getTransactionID() + "," +
                        transaction.getAccountFrom() + "," +
                        transaction.getAccountTo() + "," +
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
                            tempAccount.getBalance() + "," +
                            tempAccount.getStatus()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSingleTask(Task tempTask) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Data/Tasks.csv", true)))) {
            tasks.add(tempTask);
            out.println(
                            tempTask.getTaskID() + "," +
                            tempTask.getDescription() + "," +
                            tempTask.getReward() + "," +
                            "no" + "," +
                            tempTask.getCounter()
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
                            tempGoal.getTargetAmount() + "," +
                            tempGoal.getCurrentAmount() + "," +
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
                            tempTransaction.getAmount()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
