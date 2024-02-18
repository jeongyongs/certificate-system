package com.nhnacademy.certificate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.certificate.dto.RelationshipRequest;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import com.nhnacademy.certificate.service.FamilyRelationshipService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FamilyRelationshipRestControllerTest {
    MockMvc mockMvc;
    @Mock
    FamilyRelationshipService familyRelationshipService;
    @InjectMocks
    FamilyRelationshipRestController familyRelationshipRestController;
    static ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(familyRelationshipRestController)
                .build();
    }

    static Stream<Arguments> requestData() {
        return Stream.of(
                Arguments.of(1, 2, "father", FamilyRelationship.Relationship.FATHER),
                Arguments.of(3, 4, "MOTHER", FamilyRelationship.Relationship.MOTHER),
                Arguments.of(5, 2, "자녀", FamilyRelationship.Relationship.CHILD)
        );
    }

    @ParameterizedTest
    @MethodSource("requestData")
    void registerFamilyRelationship(Integer serialNumber,
                                    Integer familySerialNumber,
                                    String relationshipString,
                                    FamilyRelationship.Relationship relationship) throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("familySerialNumber", String.valueOf(familySerialNumber));
        request.put("relationship", relationshipString);

        String content = objectMapper.writeValueAsString(request);
        System.out.println(content);

        ArgumentCaptor<RelationshipRequest> relationshipRequestCaptor = ArgumentCaptor.forClass(RelationshipRequest.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(String.format("/residents/%d/relationship", serialNumber))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated());
        Mockito.verify(familyRelationshipService, Mockito.times(1))
                .saveFamilyRelationship(relationshipRequestCaptor.capture());
        Assertions.assertThat(relationshipRequestCaptor.getValue().getSerialNumber()).isEqualTo(serialNumber);
        Assertions.assertThat(relationshipRequestCaptor.getValue().getFamilySerialNumber()).isEqualTo(familySerialNumber);
        Assertions.assertThat(relationshipRequestCaptor.getValue().getRelationship()).isEqualTo(relationship);
    }

    @ParameterizedTest
    @MethodSource("requestData")
    void updateFamilyRelationship(Integer serialNumber,
                                  Integer familySerialNumber,
                                  String relationshipString,
                                  FamilyRelationship.Relationship relationship) throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("relationship", relationshipString);

        String content = objectMapper.writeValueAsString(request);
        System.out.println(content);

        ArgumentCaptor<RelationshipRequest> relationshipRequestCaptor = ArgumentCaptor.forClass(RelationshipRequest.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .put(String.format("/residents/%d/relationship/%d", serialNumber, familySerialNumber))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isNoContent());
        Mockito.verify(familyRelationshipService, Mockito.times(1))
                .updateFamilyRelationship(relationshipRequestCaptor.capture());
        Assertions.assertThat(relationshipRequestCaptor.getValue().getSerialNumber()).isEqualTo(serialNumber);
        Assertions.assertThat(relationshipRequestCaptor.getValue().getFamilySerialNumber()).isEqualTo(familySerialNumber);
        Assertions.assertThat(relationshipRequestCaptor.getValue().getRelationship()).isEqualTo(relationship);
    }

    @ParameterizedTest
    @MethodSource("requestData")
    void deleteFamilyRelationship(Integer serialNumber,
                                  Integer familySerialNumber) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(
                        String.format("/residents/%d/relationship/%d",
                                serialNumber,
                                familySerialNumber)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<FamilyRelationship.Pk> pkArgumentCaptor = ArgumentCaptor.forClass(FamilyRelationship.Pk.class);

        Mockito.verify(familyRelationshipService, Mockito.times(1))
                .deleteFamilyRelationship(pkArgumentCaptor.capture());
        Assertions.assertThat(pkArgumentCaptor.getValue().getBaseResidentSerialNumber()).isEqualTo(serialNumber);
        Assertions.assertThat(pkArgumentCaptor.getValue().getFamilyResidentSerialNumber()).isEqualTo(familySerialNumber);
    }
}
