package com.telemedecineBE.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telemedecineBE.entities.Appointment;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Serializable>{

    @Query("select a from Appointment a")
    public List<Appointment> findAllApps();
}