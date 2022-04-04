package com.telemedecineBE.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telemedecineBE.entities.Patient;

import javax.transaction.Transactional;

@Transactional
public interface PatientRepository 
	
				extends JpaRepository<Patient, Serializable>{
	
	public Patient findByCellphone(String phone);
	
	public Patient findByEmail(String email);


	public Patient findById(Integer id);
	
	public Boolean existsByCellphone(String phone);

	public Boolean existsByEmail(String email);

	public Boolean existsById(Integer id);

	public void deleteByCellphone(String tel);

	public void deleteByEmail(String email);

	public void deleteById(Integer id);


}
