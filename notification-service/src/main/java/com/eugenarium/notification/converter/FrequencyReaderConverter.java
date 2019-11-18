package com.eugenarium.notification.converter;

import com.eugenarium.notification.persistence.domain.Frequency;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FrequencyReaderConverter implements Converter<Integer, Frequency> {

    @Override
    public Frequency convert(Integer days) {
        return Frequency.withDays(days);
    }
}
