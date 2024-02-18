package com.nhnacademy.certificate.converter;

import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.util.EnumUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DeathPlaceConverter implements AttributeConverter<Resident.DeathPlace, String> {
    @Override
    public String convertToDatabaseColumn(Resident.DeathPlace attribute) {
        return EnumUtils.convertToString(attribute);
    }

    @Override
    public Resident.DeathPlace convertToEntityAttribute(String dbData) {
        return EnumUtils.convertToEnum(Resident.DeathPlace.class, dbData);
    }
}
