package com.nhnacademy.certificate.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.nhnacademy.certificate.converter.CodeType;
import com.nhnacademy.certificate.util.EnumUtils;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "family_relationship")
public class FamilyRelationship {
    @EmbeddedId
    private Pk pk;

    @Column(name = "family_relationship_code")
    private Relationship familyRelationship;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number")
    private Resident baseResident;

    @MapsId("familyResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "family_resident_serial_number")
    private Resident familyResident;

    @Data
    @Embeddable
    @NoArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "base_resident_serial_number")
        private Integer baseResidentSerialNumber;

        @Column(name = "family_resident_serial_number")
        private Integer familyResidentSerialNumber;
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    public enum Relationship implements CodeType {
        FATHER("부"),
        MOTHER("모"),
        SPOUSE("배우자"),
        CHILD("자녀");

        private final String code;

        @JsonCreator
        public static Relationship from(String sub) {
            try {
                return Relationship.valueOf(sub.toUpperCase());
            } catch (IllegalArgumentException ignore) {
                return EnumUtils.convertToEnum(Relationship.class, sub);
            }
        }
    }
}
