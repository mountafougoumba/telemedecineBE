package com.telemedecineBE.dao;

import com.telemedecineBE.entities.Prescriptions;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
public interface PrescriptionRepository extends JpaRepository<Prescriptions, Serializable> {

    public Prescriptions findById(Integer id);

    public Boolean existsById(Integer id);

    public void deleteById(Integer id);
}
