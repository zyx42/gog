package com.eugenarium.notification.persistence.repository;

import com.eugenarium.notification.persistence.domain.Recipient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientRepository extends CrudRepository<Recipient, String> {

    Recipient findByAccountName(String accountName);

    @Query("")
    List<Recipient> findReadyForBackup();

    @Query("")
    List<Recipient> findReadyForRemind();
}
