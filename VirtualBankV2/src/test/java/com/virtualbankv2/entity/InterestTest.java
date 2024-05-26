package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the Interest class.
 * It includes tests for getting and setting the username and last update date.
 *
 * @version 1.0
 * @since 2024-05-10
 * @author Ji Zheng
 */
public class InterestTest {

    /**
     * Tests the getLastUpdate method of the Interest class.
     */
    @Test
    public void testGetLastUpdate() {
        String username = "testUser";
        String lastUpdate = "2024-05-20";
        Interest interest = new Interest(username, lastUpdate);
        String retrievedLastUpdate = interest.getLastUpdate();

        assertEquals(lastUpdate, retrievedLastUpdate);
    }

    /**
     * Tests the setLastUpdate method of the Interest class.
     */
    @Test
    public void testSetLastUpdate() {
        String username = "testUser";
        String lastUpdate = "2024-05-20";
        Interest interest = new Interest(username, "");
        interest.setLastUpdate(lastUpdate);

        assertEquals(lastUpdate, interest.getLastUpdate());
    }

    /**
     * Tests the getUsername method of the Interest class.
     */
    @Test
    public void testGetUsername() {
        String username = "testUser";
        String lastUpdate = "2024-05-20";
        Interest interest = new Interest(username, lastUpdate);
        String retrievedUsername = interest.getUsername();

        assertEquals(username, retrievedUsername);
    }

    /**
     * Tests the setUsername method of the Interest class.
     */
    @Test
    public void testSetUsername() {
        String username = "testUser";
        String lastUpdate = "2024-05-20";
        Interest interest = new Interest("", lastUpdate);
        interest.setUsername(username);

        assertEquals(username, interest.getUsername());
    }
}
