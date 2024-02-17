package com.nhnacademy.certificate.entity;

import com.nhnacademy.certificate.converter.YnConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {
    @EmbeddedId
    private Pk pk;

    @Column(name = "house_movement_address")
    private String houseMovementAddress;

    @Column(name = "last_address_yn")
    @Convert(converter = YnConverter.class)
    private Boolean lastAddress;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @Data
    @Embeddable
    @NoArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "household_serial_number")
        private Integer householdSerialNumber;

        @Column(name = "house_movement_report_date")
        private LocalDate houseMovementReportDate;
    }
}
