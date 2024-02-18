package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.dto.ResidentRequest;
import com.nhnacademy.certificate.repository.ResidentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ResidentServiceTest {
    ResidentService residentService;
    private ResidentRepository residentRepository;

    @BeforeEach
    void setUp() {
        residentRepository = mock(ResidentRepository.class);
        residentService = new ResidentService(residentRepository);
    }

    @Test
    void saveResident() {
        residentService.saveResident(new ResidentRequest());
        verify(residentRepository, times(1)).save(any());
    }
}