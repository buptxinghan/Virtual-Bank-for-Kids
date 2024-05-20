package com.virtualbankv2.control;

import com.virtualbankv2.boundary.AccountOverviewUI;
import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class AccountOverviewPageTest {

    private AccountOverviewUIStub uiStub;
    private AccountOverviewPage accountOverviewPage;

    @Before
    public void setUp() {
        uiStub = new AccountOverviewUIStub();
        accountOverviewPage = new AccountOverviewPage();
        accountOverviewPage.ui = uiStub;
    }

    @Test
    public void testAccountOverviewPage_UserExists() {
        User user = new User("testUser", "password");
        VirtualBankApplication.currentUser = user;
        List<User> users = new ArrayList<>();
        users.add(user);
        Reader.users = users;

        Account account = new Account("testUser", "Active", "firstName", "password", 1000.0, "Checking");
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        Reader.accounts = accounts;

        accountOverviewPage = new AccountOverviewPage();

        assertTrue(uiStub.isUpdatePageCalled());
        assertEquals(account, uiStub.getUpdatedAccount());
    }

    @Test
    public void testAccountOverviewPage_UserDoesNotExist() {
        User user = new User("testUser", "password");
        VirtualBankApplication.currentUser = user;
        List<User> users = new ArrayList<>();
        Reader.users = users;

        accountOverviewPage = new AccountOverviewPage();

        assertFalse(uiStub.isUpdatePageCalled());
    }

    private static class AccountOverviewUIStub extends AccountOverviewUI {
        private boolean updatePageCalled = false;
        private Account updatedAccount;

        @Override
        public void updatePage(Account account) {
            updatePageCalled = true;
            updatedAccount = account;
        }

        public boolean isUpdatePageCalled() {
            return updatePageCalled;
        }

        public Account getUpdatedAccount() {
            return updatedAccount;
        }
    }
}