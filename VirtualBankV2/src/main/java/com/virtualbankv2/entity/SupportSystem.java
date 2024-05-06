package com.virtualbankv2.entity;
// 支持界面类
public class SupportSystem {
    // 获取支持信息
    private String faqID;
    private String question;
    private String answer;
    private String guide;
    private String contactInfo;

    public SupportSystem(String faqID, String question, String answer, String guide, String contactInfo) {
        this.faqID = faqID;
        this.question = question;
        this.answer = answer;
        this.guide = guide;
        this.contactInfo = contactInfo;
    }
}
