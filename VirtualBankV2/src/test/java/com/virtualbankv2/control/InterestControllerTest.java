package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.boundary.Writer;
import com.virtualbankv2.boundary.InterestMessagePage;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.Interest;
import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.accounts;
import static com.virtualbankv2.boundary.Reader.interests;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
import static org.junit.jupiter.api.Assertions.*;

public class InterestControllerTest {

    private InterestController interestController;
    private User user;

    @BeforeEach
    void setUp() {
        new Reader();
        new VirtualBankApplication();
        interestController = new InterestController();
        user = new User("testUser", "password");
        currentUser = user;

        accounts = new ArrayList<>();
        interests = new ArrayList<>();

        accounts.add(new Account("123456", "Saving Account", "testUser", "password", 1000.0, "Active"));
        interests.add(new Interest("testUser", "2024/01/01"));
    }

    @Test
    void testCalculateDaysBetween() {
        String dateStr1 = "2024/05/14";
        String dateStr2 = "2024/01/01";
        int daysBetween = interestController.calculateDaysBetween(dateStr1, dateStr2);
        assertEquals(134, daysBetween);
    }

    @Test
    void testGetLastUpdateByUser() {
        String lastUpdate = interestController.getLastUpdateByUser();
        assertEquals("2024/01/01", lastUpdate);
        assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), interests.get(0).getLastUpdate());
    }

    @Test
    void testGetSavingAccounts() {
        List<Account> savingAccounts = interestController.getSavingAccounts();
        assertEquals(1, savingAccounts.size());
        assertEquals("Saving Account", savingAccounts.get(0).getAccountType());
    }
}
