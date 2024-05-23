package com.virtualbankv2.entity;

/**
 * The SupportSystem class represents the support information in the system,
 * including FAQs, guides, and contact information for support.
 *
 * @version 1.0
 * @since 2024-04-13
 * @author Zhenghan Zhong
 */
public class SupportSystem {
    /**
     * The ID of the FAQ entry.
     */
    private final String faqID;

    /**
     * The question part of the FAQ entry.
     */
    private final String question;

    /**
     * The answer part of the FAQ entry.
     */
    private final String answer;

    /**
     * The guide related to the FAQ entry.
     */
    private final String guide;

    /**
     * The contact information for further support.
     */
    private final String contactInfo;

    /**
     * Constructs a new SupportSystem object with the specified FAQ ID, question, answer, guide, and contact information.
     *
     * @param faqID       the ID of the FAQ entry
     * @param question    the question part of the FAQ entry
     * @param answer      the answer part of the FAQ entry
     * @param guide       the guide related to the FAQ entry
     * @param contactInfo the contact information for further support
     */
    public SupportSystem(String faqID, String question, String answer, String guide, String contactInfo) {
        this.faqID = faqID;
        this.question = question;
        this.answer = answer;
        this.guide = guide;
        this.contactInfo = contactInfo;
    }

    /**
     * Returns the ID of the FAQ entry.
     *
     * @return the ID of the FAQ entry
     */
    public String getFaqID() {
        return faqID;
    }

    /**
     * Returns the question part of the FAQ entry.
     *
     * @return the question part of the FAQ entry
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns the answer part of the FAQ entry.
     *
     * @return the answer part of the FAQ entry
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Returns the guide related to the FAQ entry.
     *
     * @return the guide related to the FAQ entry
     */
    public String getGuide() {
        return guide;
    }

    /**
     * Returns the contact information for further support.
     *
     * @return the contact information for further support
     */
    public String getContactInfo() {
        return contactInfo;
    }
}
