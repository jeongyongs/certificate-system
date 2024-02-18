package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.dto.ResidentRequest;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResidentService {
    private final ResidentRepository residentRepository;

    @Transactional
    public void saveResident(ResidentRequest request) {
        residentRepository.save(
                Resident.builder()
                        .name(request.getName())
                        .residentRegistrationNumber(request.getResidentRegistrationNumber())
                        .gender(request.getGender())
                        .birthDate(request.getBirthDate())
                        .birthPlace(request.getBirthPlace())
                        .registrationBaseAddress(request.getRegistrationBaseAddress())
                        .deathDate(request.getDeathDate())
                        .deathPlaceCode(request.getDeathPlace())
                        .deathPlaceAddress(request.getDeathPlaceAddress())
                        .build()
        );
    }
}
