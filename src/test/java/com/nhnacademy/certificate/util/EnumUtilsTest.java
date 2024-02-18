package com.nhnacademy.certificate.util;

import com.nhnacademy.certificate.entity.Resident;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EnumUtilsTest {
    @Test
    void convertToEnum() {
        assertThat(EnumUtils.convertToEnum(Resident.Gender.class, "남"))
                .isEqualTo(Resident.Gender.MALE);
    }

    @Test
    void convertToString() {
        assertThat(EnumUtils.convertToString(Resident.Gender.MALE))
                .isEqualTo("남");
    }
}
