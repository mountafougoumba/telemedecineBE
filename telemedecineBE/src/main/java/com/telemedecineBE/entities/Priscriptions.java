package com.telemedecineBE.entities;

import java.io.Serializable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "PRESCRIPTIONS")
public class Priscriptions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String dosages;
	private String description;
	public Priscriptions() {
		// TODO Auto-generated constructor stub
	}
	
	public Priscriptions(Integer id, String dosages, String description) {
		super();
		this.id = id;
		this.dosages = dosages;
		this.description = description;
	}

	public Priscriptions(String dosages, String description) {
		this.dosages = dosages;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDosages() {
		return dosages;
	}
	public void setDosages(String dosages) {
		this.dosages = dosages;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Priscriptions [id=" + id + ", dosages=" + dosages + ", description=" + description + "]";
	}
	

}
