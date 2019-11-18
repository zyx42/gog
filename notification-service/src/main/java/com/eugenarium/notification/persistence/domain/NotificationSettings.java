package com.eugenarium.notification.persistence.domain;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NotificationSettings {

    @NotNull
    private Boolean active;

    @NotNull
    private Frequency frequency;

    private LocalDate lastNotified;

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
