package com.telemedecineBE.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telemedecineBE.entities.Insurance;

public interface InsuranceRepository 
					extends JpaRepository<Insurance, Serializable>{
	

	public List<Insurance> findByNom(String nom);
	
	public List<Insurance> findByPercentageAssurance(Double percentage);
	

}
