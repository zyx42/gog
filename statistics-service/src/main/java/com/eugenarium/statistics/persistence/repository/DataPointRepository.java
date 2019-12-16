package com.eugenarium.statistics.persistence.repository;

import com.eugenarium.statistics.persistence.domain.statistics.DataPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataPointRepository extends CrudRepository<DataPoint, Long> {

    List<DataPoint> findByAccount(String account);
}
