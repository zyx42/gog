package com.eugenarium.statistics.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "accounts_statistics")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Valid
    @OneToMany(mappedBy = "account_id")
    private List<Measurement> measurements;

    @NotNull
    @Valid
    @OneToMany(mappedBy = "account_id")
    private List<Exercise> exercises;

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
