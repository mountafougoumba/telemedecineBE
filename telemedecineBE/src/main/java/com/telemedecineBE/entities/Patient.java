package com.telemedecineBE.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "PATIENT")
public class Patient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	private String Fname;
    private String Lname;
    private String dateNaissance;
    private String tel;
    private String email;
    private String BP;
    private boolean estAssure;
    private String datePremiereConsultation;
    private Integer state=1;
    
	
	

	public Patient(Integer id, String fname, String lname, String dateNaissance, String tel, String email, String bP,
			boolean estAssure, String datePremiereConsultation, Integer state, List<MedicalHistory> medicalhistory,
			Insurance assurance, Address address, User user, Appointment appointment) {
		super();
		this.id = id;
		Fname = fname;
		Lname = lname;
		this.dateNaissance = dateNaissance;
		this.tel = tel;
		this.email = email;
		BP = bP;
		this.estAssure = estAssure;
		this.datePremiereConsultation = datePremiereConsultation;
		this.state = state;
		this.medicalhistory = medicalhistory;
		this.assurance = assurance;
		this.address = address;
		this.user = user;
		this.appointment = appointment;
	}



	@ManyToMany(cascade = {
	        CascadeType.PERSIST, CascadeType.MERGE})
	    @JoinTable(name = "medicalhistory",
	            joinColumns = @JoinColumn(name = "patientID"),
	            inverseJoinColumns = @JoinColumn(name = "MedicalHistory")
	    )
	    private List<MedicalHistory> medicalhistory = new ArrayList<>();
	
	@ManyToOne
    @JoinColumn(name = "assuranceID")
    private Insurance assurance;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressID")
    @JsonBackReference
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userID")
	@JsonBackReference
	private User user;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "appointmentID")
	@JsonBackReference
	private Appointment appointment;

	public Patient() {
		super();
	}

	

	public Integer getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}



	public List<MedicalHistory> getMedicalhistory() {
		return medicalhistory;
	}



	public void setMedicalhistory(List<MedicalHistory> medicalhistory) {
		this.medicalhistory = medicalhistory;
	}



	public Appointment getAppointment() {
		return appointment;
	}



	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBP() {
		return BP;
	}

	public void setBP(String bP) {
		BP = bP;
	}

	public boolean isEstAssure() {
		return estAssure;
	}

	public void setEstAssure(boolean estAssure) {
		this.estAssure = estAssure;
	}

	public String getDatePremiereConsultation() {
		return datePremiereConsultation;
	}

	public void setDatePremiereConsultation(String datePremiereConsultation) {
		this.datePremiereConsultation = datePremiereConsultation;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}



	public Insurance getAssurance() {
		return assurance;
	}

	public void setAssurance(Insurance assurance) {
		this.assurance = assurance;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

}
