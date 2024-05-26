package com.virtualbankv2.control;

import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the GoalManager class.
 * It includes tests for removing completed goals from the goals file.
 *
 * @version 1.0
 * @since 2024-04-20
 * @author Ji Zheng
 */
public class GoalManagerTest {

    private GoalManager goalManager;
    private User user;

    @BeforeEach
    void setUp() throws Exception {
        goalManager = new GoalManager();
        user = new User("testUser", "password");
        currentUser = user;

        // Create a temporary goals file for testing
        String[] goals = {
                "Goal1,Description,1000,500,testUser",
                "Goal2,Description,2000,2500,testUser",
                "Goal3,Description,1500,1500,testUser",
                "Goal4,Description,3000,1000,anotherUser"
        };
        Path tempFilePath = Path.of("src/Data/Goals.csv");
        Files.createDirectories(tempFilePath.getParent());
        Files.write(tempFilePath, String.join("\n", goals).getBytes());
    }

    /**
     * Tests the removeGoalIfComplete method of GoalManager.
     */
    @Test
    void testRemoveGoalIfComplete() throws IOException {
        goalManager.removeGoalIfComplete();

        Path tempFilePath = Path.of("src/Data/Goals.csv");
        List<String> lines = Files.readAllLines(tempFilePath);

        assertEquals(2, lines.size());
        assertTrue(lines.contains("Goal1,Description,1000,500,testUser"));
        assertTrue(lines.contains("Goal4,Description,3000,1000,anotherUser"));
        assertFalse(lines.contains("Goal2,Description,2000,2500,testUser"));
        assertFalse(lines.contains("Goal3,Description,1500,1500,testUser"));
    }
}
