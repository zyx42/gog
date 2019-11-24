package com.eugenarium.statistics.persistence.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "exercises")
public class Exercise {

    @NotNull
    @Length(min = 1, max = 20)
    private String name;

    @NotNull
    private BigDecimal overallReps;

    @NotNull
    private TimePeriod period;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOverallReps() {
        return overallReps;
    }

    public void setOverallReps(BigDecimal overallReps) {
        this.overallReps = overallReps;
    }

    public TimePeriod getPeriod() {
        return period;
    }

    public void setPeriod(TimePeriod period) {
        this.period = period;
    }
}
