package com.eugenarium.notification.persistence.domain;

public enum NotificationType {

    BACKUP("subject", "text", "attachment"),
    REMIND("subject", "text", null);

    private String subject;
    private String text;
    private String attachment;

    NotificationType(String subject, String text, String attachment) {
        this.subject = subject;
        this.text = text;
        this.attachment = attachment;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getAttachment() {
        return attachment;
    }
}
