package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.dto.RelationshipRequest;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import com.nhnacademy.certificate.service.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents/{serialNumber}/relationship")
public class FamilyRelationshipRestController {
    private final FamilyRelationshipService familyRelationshipService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerFamilyRelationship(@PathVariable Integer serialNumber,
                                           @Valid @RequestBody RelationshipRequest request) {
        request.setSerialNumber(serialNumber);
        familyRelationshipService.saveFamilyRelationship(request);
    }

    @PutMapping("/{familySerialNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFamilyRelationship(@PathVariable Integer serialNumber,
                                         @PathVariable Integer familySerialNumber,
                                         @Valid @RequestBody RelationshipRequest request) {
        request.setSerialNumber(serialNumber);
        request.setFamilySerialNumber(familySerialNumber);
        familyRelationshipService.updateFamilyRelationship(request);
    }

    @DeleteMapping("/{familySerialNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFamilyRelationship(@PathVariable Integer serialNumber,
                                         @PathVariable Integer familySerialNumber) {
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();
        pk.setBaseResidentSerialNumber(serialNumber);
        pk.setFamilyResidentSerialNumber(familySerialNumber);

        familyRelationshipService.deleteFamilyRelationship(pk);
    }
}
