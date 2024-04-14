package com.virtualbankv1;

public class BankAccount {
    private String accountType;
    private String paymentPassword;
    public BankAccount(){}
    public BankAccount(String paymentPassword,String accountType) {
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