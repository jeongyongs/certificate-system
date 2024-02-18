package com.nhnacademy.certificate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.service.ResidentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ResidentRestControllerTest {
    MockMvc mockMvc;
    @Mock
    ResidentService residentService;
    @InjectMocks
    ResidentRestController residentRestController;
    AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(residentRestController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void registerResident() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("name", "이름");
        request.put("residentRegistrationNumber", "주민등록번호");
        request.put("genderCode", Resident.Gender.MALE.getCode());
        request.put("birthDate", LocalDateTime.now().toString());
        request.put("birthPlaceCode", Resident.BirthPlace.HOSPITAL.getCode());
        request.put("registrationBaseAddress", "등록기준지");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String content = objectMapper.writeValueAsString(request);
        System.out.println(content);

        mockMvc.perform(post("/residents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated());
        Mockito.verify(residentService, Mockito.times(1))
                .saveResident(Mockito.any());
    }
}
