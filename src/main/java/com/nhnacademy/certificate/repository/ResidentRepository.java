package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
}
