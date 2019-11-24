package com.eugenarium.statistics.persistence.domain.statistics;

import java.math.BigDecimal;
import java.util.Objects;

public class MeasurementMetric {

    private String name;

    private BigDecimal girth;

    public MeasurementMetric(String name, BigDecimal girth) {
        this.name = name;
        this.girth = girth;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getGirth() {
        return girth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementMetric that = (MeasurementMetric) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
