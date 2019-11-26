package com.eugenarium.statistics.persistence.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Length(min = 1, max = 20)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "overall_reps")
    private BigDecimal overallReps;

    @NotNull
    @Column(name = "period")
    private TimePeriod period;

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
