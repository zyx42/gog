package com.eugenarium.notification.persistence.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "notification_settings", schema = "gog")
public class NotificationSettings {

    @Id
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "active")
    private Boolean active;

    @NotNull
    @Column(name = "frequency")
    private Frequency frequency;

    @Column(name = "last_notified")
    private LocalDate lastNotified;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType notificationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private Recipient recipient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public LocalDate getLastNotified() {
        return lastNotified;
    }

    public void setLastNotified(LocalDate lastNotified) {
        this.lastNotified = lastNotified;
    }
}
