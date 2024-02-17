package com.nhnacademy.certificate.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class YnConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return Boolean.TRUE.equals(attribute) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return dbData.equals("Y");
    }
}
