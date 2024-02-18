package com.nhnacademy.certificate.util;

import com.nhnacademy.certificate.converter.CodeType;

import java.util.Arrays;
import java.util.Objects;

public class EnumUtils {
    private EnumUtils() {
        // util class
    }

    public static <T extends Enum<T> & CodeType> T convertToEnum(Class<T> enumType, String code) {
        if (Objects.isNull(code)) {
            return null;
        }
        return Arrays.stream(enumType.getEnumConstants())
                .filter(t -> t.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 타입이 없습니다."));
    }

    public static <T extends Enum<T> & CodeType> String convertToString(T attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getCode();
    }
}
