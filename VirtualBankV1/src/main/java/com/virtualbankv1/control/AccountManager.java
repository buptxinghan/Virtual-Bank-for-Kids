package com.virtualbankv1.control;
// Account management interface class

import com.virtualbankv1.boundary.AccountInformationPage;
import com.virtualbankv1.boundary.TransactionHistoryPage;
import com.virtualbankv1.boundary.TransactionPage;
import com.virtualbankv1.boundary.Writer;
import com.virtualbankv1.entity.Account;
import com.virtualbankv1.entity.Transaction;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.virtualbankv1.boundary.Reader.accounts;
import static com.virtualbankv1.boundary.Reader.transactions;

public class AccountManager {

    Writer writer = new Writer();

    public void displayAccountInformation(Account account) {
        new AccountInformationPage(account);
    }

    public Account getAccountById(List<Account> accounts, String accountID) {
        for (Account account : accounts) {
            if (account.getAccountID().equals(accountID)) {
                return account; // 找到匹配的accountID，返回对应的Account对象
            }
        }
        return null; // 如果没有找到，返回null
    }

    public boolean validatePassword(String password, Account account) {
        return password.equals(account.getPassword());
    }

    public void transferIn(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Transaction transaction = new Transaction(new TransactionManager().getFormatTransactionID(), "System", account.getAccountID(), amount,  dateFormatter.format(LocalDate.now()), "Transfer In");
        transactions.add(transaction);
        writer.writeAccounts(accounts);
        writer.writeSingleTransaction(transaction);
    }

    public boolean withdraw(Account account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            Transaction transaction = new Transaction(new TransactionManager().getFormatTransactionID(), "System", account.getAccountID(), amount,  dateFormatter.format(LocalDate.now()), "Withdraw");
            transactions.add(transaction);
            writer.writeAccounts(accounts);
            writer.writeSingleTransaction(transaction);
            return true;
        }
        else {
            return false;
        }
    }

    public void freezeAccount(Account account) {
        account.setStatus("Frozen");
    }

    public void unfreezeAccount(Account account) {
        account.setStatus("Active");
    }

    public void deleteAccount(Account account) {
        account.setStatus("Deleted");
    }

    public boolean isFrozen(Account account) {
        return account.getStatus().equals("Frozen");
    }

    public boolean isDeleted(Account account) {
        return account.getStatus().equals("Deleted");
    }

    // 为交易按钮添加动作监听器
    public void addTransactionListenerToButton(JButton button, String actionCommand, Account account, JLabel accountBalanceLabel) {
        button.setActionCommand(actionCommand);
        button.addActionListener(e ->  {

            if (validateAccount(account)) return;

            String input = JOptionPane.showInputDialog(null, "请输入金额：", "交易", JOptionPane.PLAIN_MESSAGE);
            if (input != null && !input.isEmpty()) {
                try {
                    double amount = Double.parseDouble(input);
                    if ("withdraw".equals(e.getActionCommand())) {
                        // 调用取款方法
                        if(withdraw(account, amount)) {
                            //格式化balance
                            DecimalFormat df = new DecimalFormat("#,##0.00");
                            String formattedBalance = df.format(account.getBalance());
                            accountBalanceLabel.setText("<html><font color='Red' style='font-size: 20px;'>" + formattedBalance + "</font></html>");
                        }
                        else {
                            // 余额不足，弹出提示窗口
                            JOptionPane.showMessageDialog(null, "余额不足", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if ("transferIn".equals(e.getActionCommand())) {
                        // 调用存款方法
                        transferIn(account, amount);
                        DecimalFormat df = new DecimalFormat("#,##0.00");
                        String formattedBalance = df.format(account.getBalance());
                        accountBalanceLabel.setText("<html><font color='Red' style='font-size: 20px;'>" + formattedBalance + "</font></html>");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "请输入有效的金额", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // 为Confirmation按钮添加动作监听器
    public void addConfirmationListenerToButton(JButton button, String actionCommand, Account account, JLabel accountStatusLabel, JLabel accountBalanceLabel, JLabel accountTypeLabel, JLabel accountIDLabel, JLabel accountUsernameLabel) {
        button.setActionCommand(actionCommand);
        button.addActionListener(e ->  {
            int confirmed = JOptionPane.showConfirmDialog(null, "您确定要执行此操作吗？", "确认操作", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                if ("delete account".equals(e.getActionCommand())) {
                    // 确认删除账户后的逻辑
                    deleteAccount(account); // 将账户状态改为 Deleted
                    accountStatusLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getStatus() + "</font></html>");
                    account.setBalance(0.00);
                    accountBalanceLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getBalance() + "</font></html>");
                    account.setAccountType("---");
                    accountTypeLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getAccountType() + "</font></html>");
                    account.setUsername("---");
                    accountUsernameLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getUsername() + "</font></html>");
                    account.setPassword("---");
                    account.setAccountID("---");
                    accountIDLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getAccountID() + "</font></html>");
                } else if ("freeze account".equals(e.getActionCommand())) {
                    // 确认冻结账户后的逻辑
                    freezeAccount(account); // 将账户状态改为 Frozen
                    accountStatusLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getStatus() + "</font></html>");
                } else if ("unfreeze account".equals(e.getActionCommand())) {
                    // 确认解冻账户后的逻辑
                    unfreezeAccount(account); // 将账户状态改为 Active
                    accountStatusLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getStatus() + "</font></html>");
                }
                writer.writeAccounts(accounts);
            }
        });
    }

    // 为Confirmation按钮添加动作监听器
    public void addTransferListenerToButton(JButton button, String actionCommand, Account account, Frame frame) {
        button.setActionCommand(actionCommand);
        button.addActionListener(e ->  {

            if (validateAccount(account)) return;

            frame.dispose();
            new TransactionPage(account);
        });
    }

    public  void  addHistoryListenerToButton(JButton button, String actionCommand, Account account, Frame frame) {
        button.setActionCommand(actionCommand);
        button.addActionListener(e ->  {

            if (validateAccount(account)) return;

            frame.dispose();
            new TransactionHistoryPage(account);
        });
    }

    private boolean validateAccount(Account account) {
        // 检查账户状态
        if (isFrozen(account) || isDeleted(account)) {
            JOptionPane.showMessageDialog(null, "账户状态异常，无法进行交易", "错误", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        String password = JOptionPane.showInputDialog(null, "请输入密码：", "身份验证", JOptionPane.PLAIN_MESSAGE);
        if (!validatePassword(password, account)) {
            JOptionPane.showMessageDialog(null, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

}

