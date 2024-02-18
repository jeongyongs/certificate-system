package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.dto.ResidentRequest;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.NoSuchResidentException;
import com.nhnacademy.certificate.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

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

    @Transactional
    public void updateResident(ResidentRequest request) {
        Integer residentSerialNumber = request.getResidentSerialNumber();

        Resident resident = residentRepository
                .findById(residentSerialNumber)
                .orElseThrow(() -> new NoSuchResidentException(residentSerialNumber));

        Arrays.stream(request.getClass().getMethods())
                .filter(method -> method.getName().startsWith("get") && !method.getName().equals("getClass"))
                .forEach(method -> test(method, request, resident));

        residentRepository.save(resident);
    }

    private void test(Method method, ResidentRequest request, Resident resident) {
        try {
            Object object = method.invoke(request);
            if (Objects.isNull(object)) {
                return;
            }
            resident.getClass()
                    .getMethod("set" + method.getName().substring(3), object.getClass())
                    .invoke(resident, object);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignore) {
            // ignore
        }
    }
}
