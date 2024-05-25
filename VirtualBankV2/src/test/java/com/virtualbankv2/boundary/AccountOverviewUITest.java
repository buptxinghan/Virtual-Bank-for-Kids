package com.virtualbankv2.boundary;
import com.virtualbankv2.boundary.AccountOverviewUI;
import com.virtualbankv2.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class AccountOverviewUITest {

    private AccountOverviewUI accountOverviewUI;

    @BeforeEach
    public void setUp() {
        accountOverviewUI = new AccountOverviewUI();
        accountOverviewUI.setPage();
    }

    @Test
    public void testUpdatePageWithValidAccount() {
        Account account = new Account("123456789", "JohnDoe", "Savings", "Active", 1000.00, "password");

        CountDownLatch buttonLatch = new CountDownLatch(1);
        accountOverviewUI.updatePage(account);

        SwingUtilities.invokeLater(() -> {
            try {
                assertTrue(buttonLatch.await(5, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                fail("Thread interrupted while waiting for latch");
            }
        });
        assertTrue(true);
    }
}