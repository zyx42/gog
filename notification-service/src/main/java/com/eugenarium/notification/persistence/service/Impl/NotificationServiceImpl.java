package com.eugenarium.notification.persistence.service.Impl;

import com.eugenarium.notification.client.AccountServiceClient;
import com.eugenarium.notification.persistence.domain.NotificationType;
import com.eugenarium.notification.persistence.domain.Recipient;
import com.eugenarium.notification.persistence.service.EmailService;
import com.eugenarium.notification.persistence.service.NotificationService;
import com.eugenarium.notification.persistence.service.RecipientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AccountServiceClient client;

    private final RecipientService recipientService;

    private final EmailService emailService;

    public NotificationServiceImpl(AccountServiceClient client, RecipientService recipientService, EmailService emailService) {
        this.client = client;
        this.recipientService = recipientService;
        this.emailService = emailService;
    }

    @Override
    @Scheduled(cron = "${backup.cron}")
    public void sendBackupNotifications() {

        final NotificationType type = NotificationType.BACKUP;

        List<Recipient> recipients = recipientService.findReadyToNotify(type);
        LOGGER.info("found {} recipients for backup notification", recipients.size());

        recipients.forEach(recipient -> CompletableFuture.runAsync(() -> {
            try {
                String attachment = client.getAccount(recipient.getAccountName());
                emailService.send(type, recipient, attachment);
                recipientService.markNotified(type, recipient);
            } catch (Throwable t) {
                LOGGER.error("an error during backup notification for {}", recipient, t);
            }
        }));
    }

    @Override
    @Scheduled(cron = "${remind.cron}")
    public void sendRemindNotifications() {

        final NotificationType type = NotificationType.REMIND;

        List<Recipient> recipients = recipientService.findReadyToNotify(type);
        LOGGER.info("found {} recipients for remind notification", recipients.size());

        recipients.forEach(recipient -> CompletableFuture.runAsync(() -> {
            try {
                emailService.send(type, recipient, null);
                recipientService.markNotified(type, recipient);
            } catch (Throwable t) {
                LOGGER.error("an error during remind notification for {}", recipient, t);
            }
        }));
    }
}
