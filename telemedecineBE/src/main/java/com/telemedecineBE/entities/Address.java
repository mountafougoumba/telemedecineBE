package com.telemedecineBE.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "ZIPCODE")
	private String zipcode;
	
	@Column(name = "LINE",length = 255)
	private String line;

	//These caused the 415 error.
	/* @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
	 @JsonBackReference(value="patient-address")
	 private Patient patient;

	 @OneToOne(cascade = CascadeType.ALL,mappedBy = "address")
	 @JsonBackReference(value="doctor-address")
	 private Doctor doctor; */

	public Address(String zipcode, String line) {
		super();
		this.zipcode = zipcode;
		this.line = line;
	}


	public Long getId() {
		return id;
	}

/*
	public Patient getPatient() {
		return patient;
	}


	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}
*/

	public void setId(Long id) {
		this.id = id;
	}

	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getLine() {
		return line;
	}


	public void setLine(String line) {
		this.line = line;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		return "Address [id=" + id + ", zipcode=" + zipcode + ", line=" + line + "]";
	}


	}
