package com.telemedecineBE.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telemedecineBE.entities.Insurance;

import javax.transaction.Transactional;

@Transactional
public interface InsuranceRepository 
					extends JpaRepository<Insurance, Serializable>{

	public Insurance findByNom(String nom);

	public Insurance findById(Integer id);

	public Boolean existsByNom(String nom);

	public Boolean existsById(Integer id);

	public void deleteByNom(String nom);

	public void deleteById(Integer id);
}
