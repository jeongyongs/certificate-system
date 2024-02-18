package com.nhnacademy.certificate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhnacademy.certificate.entity.Resident;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class RegisterResidentRequest {
    @NotEmpty(message = "이름을 입력하세요.")
    private String name;

    @NotEmpty(message = "주민등록번호를 입력하세요.")
    private String residentRegistrationNumber;

    @JsonProperty("genderCode")
    @NotNull(message = "성별을 입력하세요.")
    private Resident.Gender gender;

    @NotNull(message = "생년월일을 입력하세요.")
    private LocalDateTime birthDate;

    @JsonProperty("birthPlaceCode")
    @NotNull(message = "출생장소를 입력하세요.")
    private Resident.BirthPlace birthPlace;

    @NotEmpty(message = "등록기준지를 입력하세요.")
    private String registrationBaseAddress;

    private LocalDateTime deathDate;

    @JsonProperty("deathPlaceCode")
    private Resident.DeathPlace deathPlace;

    private String deathPlaceAddress;
}
