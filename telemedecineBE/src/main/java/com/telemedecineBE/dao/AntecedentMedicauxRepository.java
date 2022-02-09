package com.telemedecineBE.dao;

import com.telemedecineBE.entities.AntecedentMedicaux;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
public interface AntecedentMedicauxRepository extends JpaRepository<AntecedentMedicaux, Serializable> {
    public AntecedentMedicaux findByNom(String name);

    public AntecedentMedicaux findById(Integer id);

    public Boolean existsByNom(String name);

    public Boolean existsById(Integer id);

    public void deleteByNom(String name);

    public void deleteById(Integer id);
}
