package com.nhnacademy.certificate.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.nhnacademy.certificate.converter.BirthPlaceConverter;
import com.nhnacademy.certificate.converter.CodeType;
import com.nhnacademy.certificate.converter.DeathPlaceConverter;
import com.nhnacademy.certificate.converter.GenderConverter;
import com.nhnacademy.certificate.util.EnumUtils;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resident")
public class Resident {
    @Id
    @Column(name = "resident_serial_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ResidentSerialNumber;

    private String name;

    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber;

    @Column(name = "gender_code")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code")
    @Convert(converter = BirthPlaceConverter.class)
    private BirthPlace birthPlace;

    @Column(name = "registration_base_address")
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code")
    @Convert(converter = DeathPlaceConverter.class)
    private DeathPlace deathPlaceCode;

    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    @Getter
    @ToString
    @RequiredArgsConstructor
    public enum Gender implements CodeType {
        MALE("남"), FEMALE("여");
        private final String code;

        @JsonCreator
        public static Gender from(String gender) {
            return EnumUtils.convertToEnum(Gender.class, gender);
        }
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    public enum BirthPlace implements CodeType {
        HOME("자택"), HOSPITAL("병원"), ETC("기타");
        private final String code;

        @JsonCreator
        public static BirthPlace from(String birthPlace) {
            return EnumUtils.convertToEnum(BirthPlace.class, birthPlace);
        }
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    public enum DeathPlace implements CodeType {
        HOUSING("주택"),
        MEDICAL_FACILITY("의료기관"),
        SOCIAL_WELFARE_FACILITY("사회복지시설"),
        INDUSTRIAL_SITE("산업장"),
        PUBLIC_FACILITY("공공시설"),
        ROAD("도로"),
        COMMERCIAL_FACILITY("상업서비스시설"),
        FARM("농장"),
        HOSPITAL_TRANSFER("병원이송중사망"),
        OTHER("기타");
        private final String code;

        @JsonCreator
        public static DeathPlace from(String deathPlace) {
            return EnumUtils.convertToEnum(DeathPlace.class, deathPlace);
        }
    }
}
