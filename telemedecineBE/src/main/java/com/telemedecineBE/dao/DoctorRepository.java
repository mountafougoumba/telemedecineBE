package com.telemedecineBE.dao;

import com.telemedecineBE.entities.Appointment;
import com.telemedecineBE.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface DoctorRepository  extends JpaRepository<Doctor, Serializable> {
    @Query("select d from Doctor d")
    public List<Doctor> findAllDoctors();
    public Doctor findByEmail(String email);
}
