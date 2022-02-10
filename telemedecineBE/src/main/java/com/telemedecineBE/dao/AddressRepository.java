package com.telemedecineBE.dao;

import com.telemedecineBE.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
public interface AddressRepository extends JpaRepository<Address, Serializable> {

    public Address findById(Long id);

    public Address findByLine(String line);

    public Boolean existsById(Long id);

    public Boolean existsByLine(String Line);

    public void deleteById(Long id);

    public void deleteByLine(String line);
}
