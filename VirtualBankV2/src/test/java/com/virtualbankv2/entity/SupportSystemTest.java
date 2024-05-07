package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupportSystemTest {

    @Test
    public void testSupportSystemCreation() {
        String faqID = "001";
        String question = "What is a virtual bank?";
        String answer = "A virtual bank is a bank that operates exclusively online without traditional physical branch networks.";
        String guide = "Visit our website to learn more.";
        String contactInfo = "Contact us at support@virtualbankv2.com";

        SupportSystem support = new SupportSystem(faqID, question, answer, guide, contactInfo);

        assertEquals(faqID, support.getFaqID(), "FAQ ID should match the expected value");
        assertEquals(question, support.getQuestion(), "Question should match the expected value");
        assertEquals(answer, support.getAnswer(), "Answer should match the expected value");
        assertEquals(guide, support.getGuide(), "Guide should match the expected value");
        assertEquals(contactInfo, support.getContactInfo(), "Contact Info should match the expected value");
    }
}