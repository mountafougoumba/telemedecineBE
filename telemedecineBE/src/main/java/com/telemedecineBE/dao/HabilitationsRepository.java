package com.telemedecineBE.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telemedecineBE.entities.Habilitations;

public interface HabilitationsRepository 
					extends JpaRepository<Habilitations, Serializable>{
	
	//public List<Habilitations> findByNameHabilitation(String nomhabilitation);

}
