package com.eugenarium.statistics.persistence.service.Impl;

import com.eugenarium.statistics.persistence.domain.Account;
import com.eugenarium.statistics.persistence.domain.Exercise;
import com.eugenarium.statistics.persistence.domain.Measurement;
import com.eugenarium.statistics.persistence.domain.statistics.*;
import com.eugenarium.statistics.persistence.repository.DataPointRepository;
import com.eugenarium.statistics.persistence.service.StatisticsService;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final DataPointRepository repository;

    public StatisticsServiceImpl(DataPointRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DataPoint> findByAccountName(String accountName) {
        Assert.hasLength(accountName);
        return repository.findByIdAccount(accountName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataPoint save(String accountName, Account account) {

        Instant instant = LocalDate.now().atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant();

        DataPointId pointId = new DataPointId(accountName, LocalDate.from(instant));

        Set<ExerciseMetric> exercises = account.getExercises().stream()
                .map(this::createExerciseMetric)
                .collect(Collectors.toSet());

        Set<MeasurementMetric> measurements = account.getMeasurements().stream()
                .map(this::createMeasurementMetric)
                .collect(Collectors.toSet());

        Map<StatisticMetric, BigDecimal> statistics = createStatisticMetrics(exercises, measurements);

        DataPoint dataPoint = new DataPoint();
        dataPoint.setId(pointId);
        dataPoint.setExercises(exercises);
        dataPoint.setMeasurements(measurements);
        dataPoint.setStatistics(statistics);

        LOGGER.debug("new datapoint has been created: {}", pointId);

        return repository.save(dataPoint);
    }

    private Map<StatisticMetric, BigDecimal> createStatisticMetrics(Set<ExerciseMetric> exercises, Set<MeasurementMetric> measurements) {

        BigDecimal exerciseReps = exercises.stream()
                .map(ExerciseMetric::getOverallReps)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal measurementSum = measurements.stream()
                .map(MeasurementMetric::getGirth)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return ImmutableMap.of(
                StatisticMetric.EXERCISES_REPS, exerciseReps,
                StatisticMetric.MEASUREMENTS_SUM, measurementSum
        );
    }

    private ExerciseMetric createExerciseMetric(Exercise exercise) {

        BigDecimal amount = exercise.getOverallReps()
                .divide(exercise.getPeriod().getBaseRatio(), RoundingMode.FLOOR);

        return new ExerciseMetric(exercise.getName(), amount);
    }

    private MeasurementMetric createMeasurementMetric(Measurement measurement) {

        BigDecimal sum = measurement.getGirth();

        return new MeasurementMetric(measurement.getName(), sum);
    }
}
