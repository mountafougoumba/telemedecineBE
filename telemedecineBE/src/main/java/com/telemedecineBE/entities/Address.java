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
import lombok.*;


@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
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

	@Column(name="STREET_ADDRESS")
	private String streetAddress;

	@Column(name="CITY")
	private String city;

	@Column(name="US_STATE")
	private String usState;

	//These caused the 415 error.
	/* @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
	 @JsonBackReference(value="patient-address")
	 private Patient patient;

	 @OneToOne(cascade = CascadeType.ALL,mappedBy = "address")
	 @JsonBackReference(value="doctor-address")
	 private Doctor doctor; */

	public Address(String zipcode, String streetAddress, String city, String usState) {
		super();
		this.zipcode = zipcode;
		this.streetAddress=streetAddress;
		this.city=city;
		this.usState=usState;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	}
