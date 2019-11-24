package com.eugenarium.notification.persistence.repository;

import com.eugenarium.notification.persistence.domain.Recipient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientRepository extends CrudRepository<Recipient, String> {

    Recipient findByAccountName(String accountName);

    @Query("SELECT gog.recipients FROM gog.recipients WHERE scheduledNotifications.BACKUP.lastNotified < (CURRENT_DATE -" +
            "INTERVAL 'scheduledNotifications.BACKUP.frequency days')")
    List<Recipient> findReadyForBackup();

    @Query("SELECT gog.recipients FROM gog.recipients WHERE scheduledNotifications.REMIND.lastNotified < (CURRENT_DATE -" +
            "INTERVAL 'scheduledNotifications.REMIND.frequency days')")
    List<Recipient> findReadyForRemind();
}
