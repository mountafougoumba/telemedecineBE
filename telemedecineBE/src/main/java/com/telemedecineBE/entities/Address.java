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
public class Address implements Serializable{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String zipcode;

	private String streetAddress;

	private String city;

	private String usState;

	@OneToOne
	@JoinColumn(name="patientID")
	@JsonBackReference("patient-address")
	private Patient patient;

	@OneToOne
	@JoinColumn(name="doctorID")
	@JsonBackReference("doctor-address")
	private Doctor doctor;

	public Address(String zipcode, String streetAddress, String city, String usState) {
		super();
		this.zipcode = zipcode;
		this.streetAddress=streetAddress;
		this.city=city;
		this.usState=usState;
	}

	public Address(String zipcode, String streetAddress, String city, String usState, Patient patient) {
		super();
		this.zipcode = zipcode;
		this.streetAddress=streetAddress;
		this.city=city;
		this.usState=usState;
		this.patient = patient;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", zipcode='" + zipcode + '\'' +
				", streetAddress='" + streetAddress + '\'' +
				", city='" + city + '\'' +
				", usState='" + usState + '\'' +
				'}';
	}
}
