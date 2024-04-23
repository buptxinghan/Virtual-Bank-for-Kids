package com.virtualbankv1.control;
// Account management interface class

import com.virtualbankv1.boundary.AccountInformationPage;
import com.virtualbankv1.boundary.TransactionHistoryPage;
import com.virtualbankv1.boundary.TransactionPage;
import com.virtualbankv1.entity.Account;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class AccountManager {

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

    public void transferIn(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
    }

    public boolean withdraw(Account account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
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

            // 检查账户状态
            if (isFrozen(account) || isDeleted(account)) {
                JOptionPane.showMessageDialog(null, "账户状态异常，无法进行交易", "错误", JOptionPane.ERROR_MESSAGE);
                return; // 账户状态异常，中断操作
            }

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
            }
        });
    }

    // 为Confirmation按钮添加动作监听器
    public void addTransferListenerToButton(JButton button, String actionCommand, Account account, Frame frame) {
        button.setActionCommand(actionCommand);
        button.addActionListener(e ->  {
            // 检查账户状态
            if (isFrozen(account) || isDeleted(account)) {
                JOptionPane.showMessageDialog(null, "账户状态异常，无法进行交易", "错误", JOptionPane.ERROR_MESSAGE);
                return; // 账户状态异常，中断操作
            }

            frame.dispose();
            new TransactionPage(account);
        });
    }

    public  void  addHistoryListenerToButton(JButton button, String actionCommand, Account account, Frame frame) {
        button.setActionCommand(actionCommand);
        button.addActionListener(e ->  {
            // 检查账户状态
            if (isFrozen(account) || isDeleted(account)) {
                JOptionPane.showMessageDialog(null, "账户状态异常，无法进行交易", "错误", JOptionPane.ERROR_MESSAGE);
                return; // 账户状态异常，中断操作
            }

            frame.dispose();
            new TransactionHistoryPage(account);
        });
    }

}

