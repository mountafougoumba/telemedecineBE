package com.telemedecineBE.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
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
	
	@Column(name = "STREET_ADDRESS",length = 255)
	private String streetAddress;

	@Column(name = "CITY")
	private String city;

	@Column(name = "US_STATE")
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
		this.streetAddress = streetAddress;
		this.city = city;
		this.usState = usState;
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

	public String getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(String line) {
		this.streetAddress = line;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUsState() {
		return usState;
	}

	public void setUsState(String usState) {
		this.usState = usState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Address)) return false;
		Address address = (Address) o;
		return getZipcode().equals(address.getZipcode()) && getStreetAddress().equals(address.getStreetAddress()) &&
				getCity().equals(address.getCity()) && getUsState().equals(address.getUsState());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getZipcode(), getStreetAddress(), getCity(), getUsState());
	}

	}
