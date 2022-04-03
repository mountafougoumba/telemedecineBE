package com.telemedecineBE.dao;

import com.telemedecineBE.entities.Appointment;
import com.telemedecineBE.entities.Patient;
import com.telemedecineBE.entities.Requests;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public interface RequestRepository extends JpaRepository<Requests, Serializable> {
    public Requests findById(Integer id);
    public List<Requests> findByRequestingPatient(Patient patient);
    public Requests findByAppointmentRequest(Appointment appointment);
    public Boolean existsById(Integer id);
    public void deleteById(Integer id);

}
