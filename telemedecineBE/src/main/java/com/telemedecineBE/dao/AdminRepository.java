package com.telemedecineBE.dao;

import com.telemedecineBE.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
public interface AdminRepository extends JpaRepository<Admin, Serializable> {
    Admin findByEmail(String email);
    Admin findById(Integer id);
    Boolean existsByEmail(String email);
    Boolean existsByCellphone(String cellphone);
    Boolean existsById(Integer id);
    void deleteByEmail(String email);
    void deleteById(Integer id);

}
