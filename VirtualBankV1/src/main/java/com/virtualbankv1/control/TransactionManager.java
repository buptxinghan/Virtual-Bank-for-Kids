package com.virtualbankv1.control;

import com.virtualbankv1.entity.Transaction;

import static com.virtualbankv1.boundary.Reader.transactions;

public class TransactionManager {

    public String getTransactionID() {
        return String.valueOf(transactions.size() + 1);
    }
}
