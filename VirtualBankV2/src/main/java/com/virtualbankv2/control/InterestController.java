package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.boundary.Writer;
import com.virtualbankv2.boundary.InterestMessagePage;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.Interest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.*;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;

public class InterestController {
    Writer writer = new Writer();
    InterestMessagePage interestMessagePage = new InterestMessagePage();
    private Boolean isUpdate = false;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    String currentDate = dateFormatter.format(LocalDate.now());
    AccountManager accountManager = new AccountManager();

    public void getInterest() {
        Double interestRate = 0.002;
        String lastUpdate = getLastUpdateByUser();
        if (!lastUpdate.equals(currentDate)) {
            int times = calculateDaysBetween(currentDate, lastUpdate);
            List<Account> tempAccounts = getSavingAccounts();
            List<String> accountInterests = new ArrayList<>();
            for (Account tempAccount : tempAccounts) {
                Double interestAmount = tempAccount.getBalance() * interestRate * times;
                accountManager.transferIn(tempAccount, interestAmount, "Interest income");
                tempAccount.setBalance(tempAccount.getBalance() + interestAmount);
                // Assuming interestAmount is a double
                accountInterests.add("Account ID: " + tempAccount.getAccountID() + " - Interest: " + String.format("%.2f", interestAmount));
                isUpdate = true;
            }
            writer.writeInterest(interests);
            if (isUpdate) {
                interestMessagePage.showInterestWindow(accountInterests);
            }
        }
    }

    public String getLastUpdateByUser() {
        for (Interest tempInterest : interests) {
            if (tempInterest.getUsername().equals(currentUser.getUsername())) {
                String lastUpdate = tempInterest.getLastUpdate();
                tempInterest.setLastUpdate(currentDate);
                return lastUpdate;
            }
        }
        return null;
    }

    public List<Account> getSavingAccounts() {
        List<Account> tempAccounts = new ArrayList<>();
        for (Account tempAccount : accounts) {
            if (tempAccount.getAccountType().equals("Saving Account") && tempAccount.getUsername().equals(currentUser.getUsername())) {
                tempAccounts.add(tempAccount);
            }
        }
        return tempAccounts;
    }

    public int calculateDaysBetween(String dateStr1, String dateStr2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date1 = LocalDate.parse(dateStr1, formatter);
        LocalDate date2 = LocalDate.parse(dateStr2, formatter);
        return (int) ChronoUnit.DAYS.between(date2, date1);
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public static void main(String[] args) {
        new Reader();
        currentUser = users.get(1);
        new InterestController().getInterest();
    }
}
