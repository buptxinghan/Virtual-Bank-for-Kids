package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Writer;
import com.virtualbankv2.entity.Transaction;
import com.virtualbankv2.entity.Account;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.transactions;

public class TransactionManager {

    private AccountManager accountManager = new AccountManager();

    public boolean transfer(Account sourceAccount, Account targetAccount, double amount, String description) {
        if (accountManager.withdraw(sourceAccount, amount)) {
            if (accountManager.isFrozen(targetAccount) || accountManager.isDeleted(targetAccount)) {
                return false; // Account status is abnormal
            }
            accountManager.transferIn(targetAccount, amount);
            recordTransaction(sourceAccount, targetAccount, amount, description);
            return true;
        } else {
            return false; // Insufficient balance
        }
    }

    private void recordTransaction(Account sourceAccount, Account targetAccount, double amount, String description) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Transaction transaction = new Transaction(
                "00" + getTransactionID(),
                sourceAccount.getAccountID(),
                targetAccount.getAccountID(),
                amount,
                dateFormatter.format(LocalDate.now()),
                description
        );
        transactions.add(transaction);

        Writer writer = new Writer();
        writer.writeSingleTransaction(transaction);
    }

    private int getTransactionID() {
        return (transactions.size() + 1);
    }

    public String getFormatTransactionID() {
        return String.format("%04d", getTransactionID());
    }

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
