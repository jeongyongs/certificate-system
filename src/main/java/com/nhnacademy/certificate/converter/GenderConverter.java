package com.nhnacademy.certificate.converter;

import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.util.EnumUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Resident.Gender, String> {
    @Override
    public String convertToDatabaseColumn(Resident.Gender attribute) {
        return EnumUtils.convertToString(attribute);
    }

    @Override
    public Resident.Gender convertToEntityAttribute(String dbData) {
        return EnumUtils.convertToEnum(Resident.Gender.class, dbData);
    }
}
