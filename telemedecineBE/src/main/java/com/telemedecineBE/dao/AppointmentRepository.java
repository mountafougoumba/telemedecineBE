package com.telemedecineBE.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.telemedecineBE.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import com.telemedecineBE.entities.Appointment;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Serializable>{

    @Query("select a from Appointment a")
    public List<Appointment> findAllApps();

    public Boolean existsByDateScheduledAndPatient(String dateScheduled, Patient patient);

}
