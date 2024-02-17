package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementAddressRepository extends
        JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {
}
