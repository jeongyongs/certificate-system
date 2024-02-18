package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.dto.ResidentRequest;
import com.nhnacademy.certificate.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class ResidentRestController {
    private final ResidentService residentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerResident(@Valid @RequestBody ResidentRequest request) {
        residentService.saveResident(request);
    }

    @PutMapping("/{serialNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateResident(@PathVariable Integer serialNumber, @Valid @RequestBody ResidentRequest request) {
        request.setResidentSerialNumber(serialNumber);
        residentService.updateResident(request);
    }
}
