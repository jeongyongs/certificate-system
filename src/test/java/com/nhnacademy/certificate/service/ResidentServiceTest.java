package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.dto.ResidentRequest;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.repository.ResidentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ResidentServiceTest {
    @Mock
    ResidentRepository residentRepository;
    @InjectMocks
    ResidentService residentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveResident() {
        residentService.saveResident(new ResidentRequest());
        verify(residentRepository, times(1)).save(any());
    }

    @ParameterizedTest
    @MethodSource("names")
    void updateResident(String name, LocalDateTime time) {
        when(residentRepository.findById(any())).thenReturn(Optional.of(new Resident()));
        ArgumentCaptor<Resident> residentCaptor = ArgumentCaptor.forClass(Resident.class);

        ResidentRequest request = new ResidentRequest();
        request.setName(name);
        request.setBirthDate(time);

        residentService.updateResident(request);

        verify(residentRepository, times(1)).save(residentCaptor.capture());
        Assertions.assertThat(residentCaptor.getValue().getName()).isEqualTo(name);
        Assertions.assertThat(residentCaptor.getValue().getBirthDate()).isEqualTo(time);
    }

    static Stream<Arguments> names() {
        return Stream.of(
                Arguments.of("이름", LocalDateTime.now()),
                Arguments.of("테스트", LocalDateTime.now())
        );
    }
}
