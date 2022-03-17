package com.telemedecineBE.entities;

import java.io.Serializable;

import javax.persistence.*;

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

	@OneToOne
	@JsonBackReference(value="patient-address")
	@JoinColumn(name="patientID")
	 private Patient patient;

	 @OneToOne
	 @JsonBackReference(value="doctor-address")
	 @JoinColumn(name="doctorID")
	 private Doctor doctor;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
