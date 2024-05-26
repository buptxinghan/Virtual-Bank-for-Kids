package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Writer;
import com.virtualbankv2.entity.Transaction;
import com.virtualbankv2.entity.Account;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.accounts;
import static com.virtualbankv2.boundary.Reader.transactions;

/**
 * This class handles the logic for transferring money between accounts
 * and recording the transactions.
 *
 * @version 3.0
 * @since 2024-04-24
 * @author Zhenghan Zhong
 */
public class TransactionManager {

    /**
     * AccountManager instance for managing account operations.
     */
    private final AccountManager accountManager = new AccountManager();

    /**
     * Transfers money from a source account to a target account.
     *
     * @param sourceAccount The account from which money is withdrawn.
     * @param targetAccount The account to which money is deposited.
     * @param amount The amount of money to transfer.
     * @param description The description of the transaction.
     * @return true if the transfer is successful, false otherwise.
     */
    public boolean transfer(Account sourceAccount, Account targetAccount, double amount, String description) {
        if (sourceAccount.getBalance() >= amount) {
            if (accountManager.isFrozen(targetAccount) || accountManager.isDeleted(targetAccount)) {
                return false; // Account status is abnormal
            }
            targetAccount.setBalance(targetAccount.getBalance() + amount);
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            recordTransaction(sourceAccount, targetAccount, amount, description);
            return true;
        } else {
            return false; // Insufficient balance
        }
    }

    /**
     * Records a transaction in the system.
     *
     * @param sourceAccount The account from which money was withdrawn.
     * @param targetAccount The account to which money was deposited.
     * @param amount The amount of money transferred.
     * @param description The description of the transaction.
     */
    private void recordTransaction(Account sourceAccount, Account targetAccount, double amount, String description) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Transaction transaction = new Transaction(
                "000" + getTransactionID(),
                sourceAccount.getAccountID(),
                targetAccount.getAccountID(),
                amount,
                dateFormatter.format(LocalDate.now()),
                description
        );
        Writer writer = new Writer();
        writer.writeAccounts(accounts);
        writer.writeSingleTransaction(transaction);
    }

    /**
     * Generates a new transaction ID based on the number of existing transactions.
     *
     * @return The new transaction ID as an integer.
     */
    private int getTransactionID() {
        return (transactions.size() + 1);
    }

    /**
     * Generates a formatted transaction ID.
     *
     * @return The formatted transaction ID as a string.
     */
    public String getFormatTransactionID() {
        return String.format("%04d", getTransactionID());
    }

    /**
     * Filters transactions to find those involving a specific account.
     *
     * @param account The account for which transactions are to be filtered.
     * @return A list of transactions involving the specified account.
     */
    public List<Transaction> filterTransactionsByAccount(Account account) {
        List<Transaction> tempTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAccountTo().equals(account.getAccountID()) || transaction.getAccountFrom().equals(account.getAccountID())) {
                tempTransactions.add(transaction);
            }
        }
        return tempTransactions;
    }

    /**
     * Filters transactions by the provided date.
     *
     * @param year            The year to filter by.
     * @param month           The month to filter by.
     * @param day             The day to filter by.
     * @param tempTransactions The list of transactions to filter.
     * @return List<Transaction> The filtered list of transactions.
     */
    public List<Transaction> filterTransactionsByDate(String year, String month, String day, List<Transaction> tempTransactions) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction tempTransaction : tempTransactions) {
            String[] dateParts = tempTransaction.getDate().split("/"); // Split by '/'

            if (dateParts.length == 3) {
                String transactionYear = dateParts[0];
                String transactionMonth = dateParts[1];
                String transactionDay = dateParts[2];

                if (year.isEmpty() || year.equals(transactionYear)) {
                    if (month.isEmpty() || month.equals(transactionMonth)) {
                        if (day.isEmpty() || day.equals(transactionDay)) {
                            filteredTransactions.add(tempTransaction);
                        }
                    }
                }
            }
        }

        return filteredTransactions;
    }
}
