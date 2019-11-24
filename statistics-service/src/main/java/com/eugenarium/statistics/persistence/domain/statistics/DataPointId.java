package com.eugenarium.statistics.persistence.domain.statistics;

import java.io.Serializable;
import java.time.LocalDate;

public class DataPointId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String account;

    private LocalDate date;

    public DataPointId(String account, LocalDate date) {
        this.account = account;
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "DataPointId{" +
                "account='" + account + '\'' +
                ", date=" + date +
                '}';
    }
}
