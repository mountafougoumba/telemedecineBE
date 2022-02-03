package com.telemedecineBE.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telemedecineBE.entities.Consultation;
import com.telemedecineBE.entities.TypeConsultation;


public interface ConsultationRepository 
						extends JpaRepository<Consultation, Serializable>{
	
	public List<Consultation> findByDiagnostic(String diagnostic);
	
	public List<Consultation> findByDateConsultation(Date date);
	
	
	public List<Consultation> findByTypeConsultation(TypeConsultation typeconsultation);
	
	public List<Consultation> findByTotalPercu(Double montant);
	
	
}
