package com.virtualbankv2.boundary;

import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;

/**
 * This class tests the functionality of the ContactUsPage class.
 * It includes tests for initialization, color representation, and return button.
 *
 * @version 1.0
 * @since 2024-04-20
 * @author Ji Zheng
 */

public class ContactUsPageTest {

    @Test
    public void testContactUsPageInitialization() {
        ContactUsPage contactUsPage = new ContactUsPage();
        assertNotNull(contactUsPage);
        assertTrue(contactUsPage.isVisible());
        assertEquals("Virtual Bank", contactUsPage.getTitle());
        assertNotNull(contactUsPage.getContentPane());
    }

    @Test
    public void testColorHex() throws Exception {
        ContactUsPage contactUsPage = new ContactUsPage();
        Color bg = contactUsPage.getContentPane().getBackground();
        String colorHex = getColorHex(contactUsPage, bg);
        assertNotNull(colorHex);
    }

    @Test
    public void testReturnButton() {
        ContactUsPage contactUsPage = new ContactUsPage();
        assertNotNull(contactUsPage.getContentPane());
        assertEquals(5, contactUsPage.getContentPane().getComponentCount());
    }

    private String getColorHex(ContactUsPage contactUsPage, Color color) throws Exception {
        java.lang.reflect.Method method = ContactUsPage.class.getDeclaredMethod("getColorHex", Color.class);
        method.setAccessible(true);
        return (String) method.invoke(contactUsPage, color);
    }
}
