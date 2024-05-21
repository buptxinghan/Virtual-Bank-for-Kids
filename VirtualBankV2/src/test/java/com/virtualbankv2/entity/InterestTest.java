package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.virtualbankv2.entity.Interest;

public class InterestTest {

    @Test
    public void testGetLastUpdate() {
        String username = "testUser";
        String lastUpdate = "2024-05-20";
        Interest interest = new Interest(username, lastUpdate);
        String retrievedLastUpdate = interest.getLastUpdate();

        assertEquals(lastUpdate, retrievedLastUpdate);
    }

    @Test
    public void testSetLastUpdate() {
        String username = "testUser";
        String lastUpdate = "2024-05-20";
        Interest interest = new Interest(username, "");
        interest.setLastUpdate(lastUpdate);

        assertEquals(lastUpdate, interest.getLastUpdate());
    }

    @Test
    public void testGetUsername() {
        String username = "testUser";
        String lastUpdate = "2024-05-20";
        Interest interest = new Interest(username, lastUpdate);
        String retrievedUsername = interest.getUsername();

        assertEquals(username, retrievedUsername);
    }

    @Test
    public void testSetUsername() {
        String username = "testUser";
        String lastUpdate = "2024-05-20";
        Interest interest = new Interest("", lastUpdate);
        interest.setUsername(username);

        assertEquals(username, interest.getUsername());
    }
}