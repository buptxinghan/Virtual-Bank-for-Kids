package com.virtualbankv2.control;
// Account management interface class

import com.virtualbankv2.boundary.AccountInformationPage;
import com.virtualbankv2.boundary.TransactionHistoryPage;
import com.virtualbankv2.boundary.TransactionPage;
import com.virtualbankv2.boundary.Writer;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.Transaction;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.accounts;
import static com.virtualbankv2.boundary.Reader.transactions;

/**
 * Manages account-related operations.
 */
public class AccountManager {

    private final Writer writer = new Writer();

    /**
     * Displays account information.
     *
     * @param account The account to display information for.
     */
    public void displayAccountInformation(Account account) {
        new AccountInformationPage(account);
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param accounts  The list of accounts to search.
     * @param accountID The ID of the account to retrieve.
     * @return The account with the specified ID, or null if not found.
     */
    public Account getAccountById(List<Account> accounts, String accountID) {
        for (Account account : accounts) {
            if (account.getAccountID().equals(accountID)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Validates a password for an account.
     *
     * @param password The password to validate.
     * @param account  The account to validate the password for.
     * @return True if the password is valid, otherwise false.
     */
    public boolean validatePassword(String password, Account account) {
        return password.equals(account.getPassword());
    }

    /**
     * Transfers funds into an account.
     *
     * @param account The account to transfer funds into.
     * @param amount  The amount of funds to transfer.
     */
    public void transferIn(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Transaction transaction = new Transaction(new TransactionManager().getFormatTransactionID(), "System", account.getAccountID(), amount,  dateFormatter.format(LocalDate.now()), "Transfer In");
        writer.writeAccounts(accounts);
        writer.writeSingleTransaction(transaction);
    }

    /**
     * Withdraws funds from an account.
     *
     * @param account The account to withdraw funds from.
     * @param amount  The amount of funds to withdraw.
     * @return True if the withdrawal is successful, otherwise false.
     */
    public boolean withdraw(Account account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            Transaction transaction = new Transaction(new TransactionManager().getFormatTransactionID(), "System", account.getAccountID(), amount,  dateFormatter.format(LocalDate.now()), "Withdraw");
            writer.writeAccounts(accounts);
            writer.writeSingleTransaction(transaction);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Freezes an account.
     *
     * @param account The account to freeze.
     */
    public void freezeAccount(Account account) {
        account.setStatus("Frozen");
        writer.writeAccounts(accounts);
    }

    /**
     * Unfreezes an account.
     *
     * @param account The account to unfreeze.
     */
    public void unfreezeAccount(Account account) {
        account.setStatus("Active");
        writer.writeAccounts(accounts);
    }

    /**
     * Deletes an account.
     *
     * @param account The account to delete.
     */
    public void deleteAccount(Account account) {
        account.setStatus("Deleted");
        account.setBalance(0.00);
        account.setAccountType("---");
        account.setUsername("---");
        account.setPassword("---");
        account.setAccountID("---");
        writer.writeAccounts(accounts);
    }

    /**
     * Checks if an account is frozen.
     *
     * @param account The account to check.
     * @return True if the account is frozen, otherwise false.
     */
    public boolean isFrozen(Account account) {
        return account.getStatus().equals("Frozen");
    }

    /**
     * Checks if an account is deleted.
     *
     * @param account The account to check.
     * @return True if the account is deleted, otherwise false.
     */
    public boolean isDeleted(Account account) {
        return account.getStatus().equals("Deleted");
    }

    /**
     * Adds a transaction listener to a button.
     *
     * @param button            The button to add the listener to.
     * @param actionCommand     The action command for the button.
     * @param account           The account associated with the button.
     * @param accountBalanceLabel The label to display the account balance.
     */
    public void addTransactionListenerToButton(JButton button, String actionCommand, Account account, JLabel accountBalanceLabel) {
        button.setActionCommand(actionCommand);
        button.addActionListener(e ->  {

            if (validateAccount(account)) return;

            String input = JOptionPane.showInputDialog(null, "Please enter the amount:", "Transaction", JOptionPane.PLAIN_MESSAGE);
            if (input != null && !input.isEmpty()) {
                try {
                    double amount = Double.parseDouble(input);
                    if ("withdraw".equals(e.getActionCommand())) {
                        if(withdraw(account, amount)) {
                            DecimalFormat df = new DecimalFormat("#,##0.00");
                            String formattedBalance = df.format(account.getBalance());
                            accountBalanceLabel.setText("<html><font color='Red' style='font-size: 20px;'>" + formattedBalance + "</font></html>");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Not sufficient funds", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if ("transferIn".equals(e.getActionCommand())) {
                        transferIn(account, amount);
                        DecimalFormat df = new DecimalFormat("#,##0.00");
                        String formattedBalance = df.format(account.getBalance());
                        accountBalanceLabel.setText("<html><font color='Red' style='font-size: 20px;'>" + formattedBalance + "</font></html>");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Adds a confirmation listener to a button.
     *
     * @param button             The button to add the listener to.
     * @param actionCommand      The action command for the button.
     * @param account            The account associated with the button.
     * @param accountStatusLabel The label to display the account status.
     * @param accountBalanceLabel The label to display the account balance.
     * @param accountTypeLabel   The label to display the account type.
     * @param accountIDLabel     The label to display the account ID.
     * @param accountUsernameLabel The label to display the account username.
     */
    public void addConfirmationListenerToButton(JButton button, String actionCommand, Account account, JLabel accountStatusLabel, JLabel accountBalanceLabel, JLabel accountTypeLabel, JLabel accountIDLabel, JLabel accountUsernameLabel) {
        button.setActionCommand(actionCommand);
        button.addActionListener(e ->  {
            int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to do this?", "Confirm operation", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                if ("delete account".equals(e.getActionCommand())) {
                    deleteAccount(account);
                    accountStatusLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getStatus() + "</font></html>");
                    accountBalanceLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getBalance() + "</font></html>");
                    accountTypeLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getAccountType() + "</font></html>");
                    accountUsernameLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getUsername() + "</font></html>");
                    accountIDLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getAccountID() + "</font></html>");
                } else if ("freeze account".equals(e.getActionCommand())) {
                    freezeAccount(account);
                    accountStatusLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getStatus() + "</font></html>");
                } else if ("unfreeze account".equals(e.getActionCommand())) {
                    unfreezeAccount(account);
                    accountStatusLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getStatus() + "</font></html>");
                }
            }
        });
    }

    /**
     * Adds a transfer listener to a button.
     *
     * @param button The button to add the listener to.
     * @param actionCommand The action command for the button.
     * @param account The account associated with the button.
     * @param frame The frame to close.
     */
    public void addTransferListenerToButton(JButton button, String actionCommand, Account account, Frame frame) {
        button.setActionCommand(actionCommand);
        button.addActionListener(e ->  {

            if (validateAccount(account)) return;

            frame.dispose();
            new TransactionPage(account);
        });
    }

    /**
     * Adds a history listener to a button.
     *
     * @param button The button to add the listener to.
     * @param actionCommand The action command for the button.
     * @param account The account associated with the button.
     * @param frame The frame to close.
     */
    public  void  addHistoryListenerToButton(JButton button, String actionCommand, Account account, Frame frame) {
        button.setActionCommand(actionCommand);
        button.addActionListener(e ->  {

            if (validateAccount(account)) return;

            frame.dispose();
            new TransactionHistoryPage(account);
        });
    }

    /**
     * Validates the account status and password.
     *
     * @param account The account to validate.
     * @return True if validation fails, otherwise false.
     */
    private boolean validateAccount(Account account) {
        if (isFrozen(account) || isDeleted(account)) {
            JOptionPane.showMessageDialog(null, "The account status is abnormal and cannot be traded", "Error!", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        String password = JOptionPane.showInputDialog(null, "Enter your password:", "Authentication", JOptionPane.PLAIN_MESSAGE);
        if (!validatePassword(password, account)) {
            JOptionPane.showMessageDialog(null, "Wrong password", "Error!", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

}

