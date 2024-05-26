package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Reader;
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

/**
 * This class tests the functionality of the InterestController class.
 * It includes tests for calculating days between dates, retrieving last update by user,
 * and getting saving accounts.
 *
 * @version 1.0
 * @since 2024-05-01
 * @author Ji Zheng
 */
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

    /**
     * Tests the calculateDaysBetween method of InterestController.
     */
    @Test
    void testCalculateDaysBetween() {
        String dateStr1 = "2024/05/14";
        String dateStr2 = "2024/01/01";
        int daysBetween = interestController.calculateDaysBetween(dateStr1, dateStr2);
        assertEquals(134, daysBetween);
    }

    /**
     * Tests the getLastUpdateByUser method of InterestController.
     */
    @Test
    void testGetLastUpdateByUser() {
        String lastUpdate = interestController.getLastUpdateByUser();
        assertEquals("2024/01/01", lastUpdate);
        assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), interests.get(0).getLastUpdate());
    }

    /**
     * Tests the getSavingAccounts method of InterestController.
     */
    @Test
    void testGetSavingAccounts() {
        List<Account> savingAccounts = interestController.getSavingAccounts();
        assertEquals(1, savingAccounts.size());
        assertEquals("Saving Account", savingAccounts.get(0).getAccountType());
    }
}
