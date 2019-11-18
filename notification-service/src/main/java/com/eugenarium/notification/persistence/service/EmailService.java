package com.eugenarium.notification.persistence.service;

import com.eugenarium.notification.persistence.domain.NotificationType;
import com.eugenarium.notification.persistence.domain.Recipient;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    void send(NotificationType type, Recipient recipient, String attachment)
            throws MessagingException, IOException;
}
