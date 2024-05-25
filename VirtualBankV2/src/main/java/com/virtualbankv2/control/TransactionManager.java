package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Writer;
import com.virtualbankv2.entity.Transaction;
import com.virtualbankv2.entity.Account;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
//        transactions.add(transaction);

        Writer writer = new Writer();
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
}
