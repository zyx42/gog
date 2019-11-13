package com.eugenarium.account.persistence.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @Id
    private String name;

    private LocalDateTime lastSeen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }
}
