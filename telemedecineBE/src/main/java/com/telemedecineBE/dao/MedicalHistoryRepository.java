package com.telemedecineBE.dao;

import com.telemedecineBE.entities.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Serializable> {
    public MedicalHistory findByName(String name);

    public MedicalHistory findById(Integer id);

    public Boolean existsByName(String name);

    public Boolean existsById(Integer id);

    public void deleteByName(String name);

    public void deleteById(Integer id);
}
