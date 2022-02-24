package com.telemedecineBE.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.telemedecineBE.entities.Habilitations;

public interface HabilitationsRepository 
					extends JpaRepository<Habilitations, Serializable>{
	
	public Habilitations findByNameHabilitation(String nameHabilitation);

	public Habilitations findById(Integer id);

	public Boolean existsByNameHabilitation(String nameHabilitiation);

	public Boolean existsById(Integer id);

	public void deleteByNameHabilitation(String nameHabilitation);

	public void deleteById(Integer id);
}
