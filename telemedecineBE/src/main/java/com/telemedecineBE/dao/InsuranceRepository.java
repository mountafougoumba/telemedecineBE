package com.telemedecineBE.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telemedecineBE.entities.Insurance;

import javax.transaction.Transactional;

@Transactional
public interface InsuranceRepository 
					extends JpaRepository<Insurance, Serializable>{

	public Insurance findByName(String nom);

	public Insurance findById(Integer id);

	public Boolean existsByName(String nom);

	public Boolean existsById(Integer id);

	public void deleteByName(String nom);

	public void deleteById(Integer id);
}
