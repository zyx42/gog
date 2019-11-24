package com.eugenarium.statistics.converter;

import com.eugenarium.statistics.persistence.domain.statistics.DataPoint;
import com.eugenarium.statistics.persistence.domain.statistics.DataPointId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataPointIdReaderConverter implements Converter<Object, DataPointId> {

    @Override
    public DataPointId convert(Entity object) {
    }
}
