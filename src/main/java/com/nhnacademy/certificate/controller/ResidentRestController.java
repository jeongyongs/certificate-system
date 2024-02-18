package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.dto.RegisterResidentRequest;
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
    public void registerResident(@Valid @RequestBody RegisterResidentRequest request) {
        residentService.saveResident(request);
    }
}
