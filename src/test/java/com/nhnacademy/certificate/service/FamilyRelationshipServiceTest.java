package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.repository.FamilyRelationshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FamilyRelationshipServiceTest {
    @Mock
    FamilyRelationshipRepository familyRelationshipRepository;
    @InjectMocks
    FamilyRelationshipService familyRelationshipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveFamilyRelationship() {
        familyRelationshipService.saveFamilyRelationship(null);
    }

    @Test
    void updateFamilyRelationship() {
        familyRelationshipService.updateFamilyRelationship(null);
    }

    @Test
    void deleteFamilyRelationship() {
        familyRelationshipService.deleteFamilyRelationship(null);
    }
}
