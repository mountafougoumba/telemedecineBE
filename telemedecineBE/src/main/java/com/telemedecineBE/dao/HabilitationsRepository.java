package com.telemedecineBE.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telemedecineBE.entities.Habilitations;

import javax.transaction.Transactional;

@Transactional
public interface HabilitationsRepository 
					extends JpaRepository<Habilitations, Serializable>{
	
	public Habilitations findByNameHabilitation(String nameHabilitation);

	public Habilitations findById(Integer id);

	public Boolean existsByNameHabilitation(String nameHabilitiation);

	public Boolean existsById(Integer id);

	public void deleteByNameHabilitation(String nameHabilitation);

	public void deleteById(Integer id);

}
