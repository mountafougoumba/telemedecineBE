package com.telemedecineBE.dao;

import com.telemedecineBE.entities.Priscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
public interface PrescriptionRepository extends JpaRepository<Priscriptions, Serializable> {

    public Priscriptions findById(Integer id);

    public Boolean existsById(Integer id);

    public void deleteById(Integer id);
}
