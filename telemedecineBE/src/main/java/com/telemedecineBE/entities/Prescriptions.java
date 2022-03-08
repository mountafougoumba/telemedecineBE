package com.telemedecineBE.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PRESCRIPTIONS")
public class Prescriptions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String dosages;
	private String description;
	private String name;
	public Prescriptions() {
		// TODO Auto-generated constructor stub
	}
	
	public Prescriptions(Integer id, String dosages, String description) {
		super();
		this.id = id;
		this.dosages = dosages;
		this.description = description;
	}

	public Prescriptions(String name, String dosages, String description) {
		this.name = name;
		this.dosages = dosages;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "Prescriptions [id=" + id + ", dosages=" + dosages + ", description=" + description + "]";
	}
	

}
