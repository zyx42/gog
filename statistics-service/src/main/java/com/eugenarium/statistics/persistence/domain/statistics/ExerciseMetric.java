package com.eugenarium.statistics.persistence.domain.statistics;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "exercise_metrics")
public class ExerciseMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "overall_reps")
    private BigDecimal overallReps;

    public ExerciseMetric(String name, BigDecimal overallReps) {
        this.name = name;
        this.overallReps = overallReps;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getOverallReps() {
        return overallReps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseMetric that = (ExerciseMetric) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
