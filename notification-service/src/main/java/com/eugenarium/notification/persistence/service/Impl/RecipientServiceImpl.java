package com.eugenarium.notification.persistence.service.Impl;

import com.eugenarium.notification.persistence.domain.NotificationType;
import com.eugenarium.notification.persistence.domain.Recipient;
import com.eugenarium.notification.persistence.repository.RecipientRepository;
import com.eugenarium.notification.persistence.service.RecipientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecipientServiceImpl implements RecipientService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final RecipientRepository repository;

    public RecipientServiceImpl(RecipientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Recipient findByAccountName(String accountName) {
        Assert.hasLength(accountName);
        return repository.findByAccountName(accountName);
    }

    @Override
    public Recipient save(String accountName, Recipient recipient) {

        recipient.setAccountName(accountName);
        recipient.getScheduledNotifications().values()
                .forEach(settings -> {
                    if (settings.getLastNotified() == null) {
                        settings.setLastNotified(LocalDate.now());
                    }
                });

        repository.save(recipient);

        LOGGER.info("recipient {} settings has been updated", recipient);

        return recipient;
    }

    @Override
    public List<Recipient> findReadyToNotify(NotificationType type) {
        switch (type) {
            case BACKUP:
                return repository.findReadyForBackup();
            case REMIND:
                return repository.findReadyForRemind();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void markNotified(NotificationType type, Recipient recipient) {
        recipient.getScheduledNotifications().get(type).setLastNotified(LocalDate.now());
        repository.save(recipient);
    }
}
