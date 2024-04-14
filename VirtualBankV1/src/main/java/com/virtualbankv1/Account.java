package com.virtualbankv1;

public class Account {
    private String accountType;
    private String paymentPassword;
    public Account(){}
    public Account(String paymentPassword, String accountType) {
        this.setAccountType(accountType);
        this.setPaymentPassword(paymentPassword);
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }
}