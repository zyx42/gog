package com.eugenarium.notification.persistence.service;

import com.eugenarium.notification.persistence.domain.NotificationType;
import com.eugenarium.notification.persistence.domain.Recipient;

import java.util.List;

public interface RecipientService {

    Recipient findByAccountName(String accountName);

    List<Recipient> findReadyToNotify(NotificationType type);

    Recipient save(String accountName, Recipient recipient);

    void markNotified(NotificationType type, Recipient recipient);
}
