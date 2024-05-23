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

/**
 * This class handles the interest calculation and updating process.
 *
 * @version 2.0
 * @since 2024-05-14
 * @author Zhenghan Zhong
 */
public class InterestController {
    /**
     * Static interest rate for calculating interest.
     */
    private static final double INTEREST_RATE = 0.002;

    /**
     * Writer instance for writing interest data.
     */
    Writer writer = new Writer();

    /**
     * InterestMessagePage instance for displaying interest messages.
     */
    InterestMessagePage interestMessagePage = new InterestMessagePage();

    /**
     * Flag indicating if an update has been made.
     */
    private Boolean isUpdate = false;

    /**
     * Date formatter for formatting dates as "yyyy/MM/dd".
     */
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    /**
     * Current date formatted as "yyyy/MM/dd".
     */
    String currentDate = dateFormatter.format(LocalDate.now());

    /**
     * AccountManager instance for managing account operations.
     */
    AccountManager accountManager = new AccountManager();

    /**
     * Calculates and applies interest to the user's saving accounts.
     */
    public void getInterest() {
        String lastUpdate = getLastUpdateByUser();
        if (!lastUpdate.equals(currentDate)) {
            int times = calculateDaysBetween(currentDate, lastUpdate);
            List<Account> tempAccounts = getSavingAccounts();
            List<String> accountInterests = new ArrayList<>();
            for (Account tempAccount : tempAccounts) {
                double interestAmount = tempAccount.getBalance() * INTEREST_RATE * times;
                accountManager.transferIn(tempAccount, interestAmount, "Interest income");
                tempAccount.setBalance(tempAccount.getBalance() + interestAmount);
                // Assuming interestAmount is a double
                accountInterests.add("<html><div style='text-align:center;'>Account ID: " + tempAccount.getAccountID() + " - Interest: <span style='color:red;'>" + String.format("%.2f", interestAmount) + "</span></div></html>");
                isUpdate = true;
            }
            writer.writeInterest(interests);
            if (isUpdate) {
                interestMessagePage.showInterestWindow(accountInterests);
            }
        }
    }

    /**
     * Retrieves the last update date for the current user.
     *
     * @return the last update date as a string.
     */
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

    /**
     * Retrieves all saving accounts for the current user.
     *
     * @return a list of saving accounts.
     */
    public List<Account> getSavingAccounts() {
        List<Account> tempAccounts = new ArrayList<>();
        for (Account tempAccount : accounts) {
            if (tempAccount.getAccountType().equals("Saving Account") && tempAccount.getUsername().equals(currentUser.getUsername())) {
                tempAccounts.add(tempAccount);
            }
        }
        return tempAccounts;
    }

    /**
     * Calculates the number of days between two dates.
     *
     * @param dateStr1 the first date as a string.
     * @param dateStr2 the second date as a string.
     * @return the number of days between the two dates.
     */
    public int calculateDaysBetween(String dateStr1, String dateStr2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date1 = LocalDate.parse(dateStr1, formatter);
        LocalDate date2 = LocalDate.parse(dateStr2, formatter);
        return (int) ChronoUnit.DAYS.between(date2, date1);
    }

    /**
     * Returns whether an update has been made.
     *
     * @return true if an update has been made, false otherwise.
     */
    public Boolean getIsUpdate() {
        return isUpdate;
    }
}
