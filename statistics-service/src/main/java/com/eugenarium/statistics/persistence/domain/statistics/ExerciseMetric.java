package com.eugenarium.statistics.persistence.domain.statistics;

import java.math.BigDecimal;
import java.util.Objects;

public class ExerciseMetric {

    private String name;

    private BigDecimal overallReps;

    public ExerciseMetric(String name, BigDecimal overallReps) {
        this.name = name;
        this.overallReps = overallReps;
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
