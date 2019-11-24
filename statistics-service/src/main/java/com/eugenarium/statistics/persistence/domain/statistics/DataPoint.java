package com.eugenarium.statistics.persistence.domain.statistics;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "datapoints")
public class DataPoint implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    private Long id;

    private Set<ExerciseMetric> exercises;

    private Set<MeasurementMetric> measurements;

    private Map<StatisticMetric, BigDecimal> statistics;

    private String account;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ExerciseMetric> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseMetric> exercises) {
        this.exercises = exercises;
    }

    public Set<MeasurementMetric> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Set<MeasurementMetric> measurements) {
        this.measurements = measurements;
    }

    public Map<StatisticMetric, BigDecimal> getStatistics() {
        return statistics;
    }

    public void setStatistics(Map<StatisticMetric, BigDecimal> statistics) {
        this.statistics = statistics;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
