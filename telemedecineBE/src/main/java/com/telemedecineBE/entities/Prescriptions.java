package com.telemedecineBE.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;

import javax.persistence.*;


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
	@ManyToOne
	@JoinColumn(name = "patientID")
	@JsonBackReference(value = "patient-prescriptions")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name="doctorID")
	@JsonBackReference(value = "doctor-requested-medications")
	private Doctor doctor;

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

	public Prescriptions(String name, String dosages, String description, Patient patient){
		this.name = name;
		this.dosages = dosages;
		this.description = description;
		this.patient = patient;
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
	public void setPatient(Patient patient) {this.patient = patient; }
	public Patient getPatient(){return this.patient;}

	@Override
	public String toString() {
		return "Prescriptions [id=" + id + ", dosages=" + dosages + ", description=" + description + "]";
	}
	

}
