package com.polarbookshop.catalogservice.persistence;

import javax.persistence.AttributeConverter;
import java.time.Year;

public class YearAttributeConverter implements AttributeConverter<Year, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Year attribute) {
        return attribute.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Integer dbData) {
        return Year.of(dbData);
    }
}
