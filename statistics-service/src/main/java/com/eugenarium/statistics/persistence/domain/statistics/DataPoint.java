package com.eugenarium.statistics.persistence.domain.statistics;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "datapoints")
public class DataPoint {

    @Id
    private DataPointId id;

    private Set<ExerciseMetric> exercises;

    private Set<MeasurementMetric> measurements;

    private Map<StatisticMetric, BigDecimal> statistics;

    public DataPointId getId() {
        return id;
    }

    public void setId(DataPointId id) {
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
}
