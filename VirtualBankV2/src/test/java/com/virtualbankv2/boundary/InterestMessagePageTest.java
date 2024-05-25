package com.virtualbankv2.boundary;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the InterestMessagePage class.
 * It includes tests for the showInterestWindow method.
 *
 * @version 1.0
 * @since 2024-05-05
 * @author Ji Zheng
 */

public class InterestMessagePageTest {

    @Test
    public void testShowInterestWindow() {
        InterestMessagePage interestMessagePage = new InterestMessagePage();
        List<String> accountInterests = Arrays.asList("Interest 1", "Interest 2", "Interest 3");
        interestMessagePage.showInterestWindow(accountInterests);
        assertTrue(hasInterestWindow());
    }

    @Test
    public void testShowInterestWindowWithEmptyList() {
        InterestMessagePage interestMessagePage = new InterestMessagePage();
        interestMessagePage.showInterestWindow(Arrays.asList());
        assertTrue(hasInterestWindow());
    }

    private boolean hasInterestWindow() {
        Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            if (frame instanceof JFrame) {
                JFrame jFrame = (JFrame) frame;
                if ("Interest Display".equals(jFrame.getTitle())) {
                    return true;
                }
            }
        }
        return false;
    }
}
