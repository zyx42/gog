package com.eugenarium.notification.persistence.domain;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Entity
@Table(name = "recipients", schema = "gog")
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_name")
    private String accountName;

    @NotNull
    @Email
    @Column(name = "email")
    private String email;

    @Valid
    @ElementCollection(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "recipient_id", referencedColumnName = "id")})
    @OneToMany(targetEntity = NotificationSettings.class, fetch = FetchType.LAZY)
    @MapKey(name = "notification_type")
    private Map<NotificationType, NotificationSettings> scheduledNotifications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<NotificationType, NotificationSettings> getScheduledNotifications() {
        return scheduledNotifications;
    }

    public void setScheduledNotifications(Map<NotificationType, NotificationSettings> scheduledNotifications) {
        this.scheduledNotifications = scheduledNotifications;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                    "accountName='" + accountName + '\'' +
                    ", email='" + email + '\'' +
                    '}';
    }
}
