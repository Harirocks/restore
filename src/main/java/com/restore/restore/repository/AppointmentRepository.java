package com.restore.restore.repository;

import com.restore.restore.entity.Appointment;
import com.restore.restore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctor(User doctor);
    List<Appointment> findByUser(User user);
}

