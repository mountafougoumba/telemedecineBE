package com.telemedecineBE.dao;

import com.telemedecineBE.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public interface AddressRepository extends JpaRepository<Address, Serializable> {

    public Address findById(Long id);

    public Address findByStreetAddress(String line);

    public List<Address> findAddressesByCity(String city);

    public List<Address> findAddressesByZipcode(String zipcode);

    public List<Address> findAddressesByUsState(String usState);

    public Boolean existsById(Long id);

    public Boolean existsByStreetAddress(String Line);

    public Boolean existsByCity(String city);

    public Boolean existsByZipcode(String zipcode);

    public Boolean existsByUsState(String usState);

    public void deleteById(Long id);

    public void deleteByStreetAddress(String line);
}
