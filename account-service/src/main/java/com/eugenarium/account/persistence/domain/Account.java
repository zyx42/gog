package com.eugenarium.account.persistence.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "accounts", schema = "gog")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_seen")
    private LocalDateTime lastSeen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
