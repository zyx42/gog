package com.eugenarium.notification.persistence.service;

public interface NotificationService {

    void sendBackupNotifications();

    void sendRemindNotifications();
}
