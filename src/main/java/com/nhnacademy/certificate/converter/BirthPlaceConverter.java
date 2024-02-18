package com.nhnacademy.certificate.converter;

import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.util.EnumUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BirthPlaceConverter implements AttributeConverter<Resident.BirthPlace, String> {
    @Override
    public String convertToDatabaseColumn(Resident.BirthPlace attribute) {
        return EnumUtils.convertToString(attribute);
    }

    @Override
    public Resident.BirthPlace convertToEntityAttribute(String dbData) {
        return EnumUtils.convertToEnum(Resident.BirthPlace.class, dbData);
    }
}
