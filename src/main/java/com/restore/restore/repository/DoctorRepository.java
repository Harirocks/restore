package com.restore.restore.repository;

import com.restore.restore.domain.DoctorStatus;
import com.restore.restore.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByStatus(DoctorStatus status);
}

