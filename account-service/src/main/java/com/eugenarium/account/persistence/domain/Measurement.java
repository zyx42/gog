package com.eugenarium.account.persistence.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurements", schema = "gog")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Length(min = 1, max = 20)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "girth")
    private BigDecimal girth;

    @NotNull
    @Column(name = "point_in_time")
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
