package com.telemedecineBE.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telemedecineBE.entities.AntecedentMedicaux;
import com.telemedecineBE.entities.Patient;

public interface PatientRepository 
	
				extends JpaRepository<Patient, Serializable>{
	
	public Patient findByTel(String cellephone);
	
	public Patient findByEmail(String email);
	
	public Patient findByAntecedantMedicaux(List<AntecedentMedicaux> antecedantList);
	
	public List<Patient> findByDatePremiereConsultation(Date date);
	
	@Query("select p from Patient p where p.estAssure=true")
	public List<Patient> findByEstAssure();
	
	@Query("select p from Patient p where p.Lname  =:x AND p.Fname =:y")
	public Patient chercherPatient(@Param("x")String lnom,@Param("y")String fnom);


}
