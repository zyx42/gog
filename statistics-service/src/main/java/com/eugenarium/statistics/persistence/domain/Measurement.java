package com.eugenarium.statistics.persistence.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurement {

    @NotNull
    @Length(min = 1, max = 20)
    private String name;

    @NotNull
    private BigDecimal girth;

    @NotNull
    private LocalDateTime pointInTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getGirth() {
        return girth;
    }

    public void setGirth(BigDecimal girth) {
        this.girth = girth;
    }

    public LocalDateTime getPointInTime() {
        return pointInTime;
    }

    public void setPointInTime(LocalDateTime pointInTime) {
        this.pointInTime = pointInTime;
    }
}
