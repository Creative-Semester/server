package com.sejong.creativesemester.common.config.format.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoundToSecondDigit implements Converter<Double,Double> {
    @Override
    public Double convert(Double num) {
        return Math.round(num * 100) / 100.0;
    }
}
