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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PATIENT")
@Getter
@Setter
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
    private Boolean estAssure;
    private String datePremiereConsultation;
    private Integer state=1;
    
	
	public Patient(Integer id, String fname, String lname, String dateNaissance, String tel, String email, String bP,
				   Boolean estAssure, String datePremiereConsultation, Integer state,
				   List<MedicalHistory> antecedantMedicaux, Insurance insurance) {
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
		this.antecedantMedicaux = antecedantMedicaux;
		this.insurance = insurance;
	}


	public Patient(String fname, String lname,
				   String dateNaissance, String tel,
				   String email, Boolean estAssure,
				   Address address) {
		Fname = fname;
		Lname = lname;
		this.dateNaissance = dateNaissance;
		this.tel = tel;
		this.email = email;
		this.estAssure = estAssure;
		this.address = address;
	}

	public Patient(String fname, String lname,
				   String dateNaissance, String tel,
				   String email, Boolean estAssure,
				   Insurance insurance, Address address) {
		Fname = fname;
		Lname = lname;
		this.dateNaissance = dateNaissance;
		this.tel = tel;
		this.email = email;
		this.estAssure = estAssure;
		this.insurance = insurance;
		this.address = address;
	}

	@ManyToMany(cascade = {
	        CascadeType.PERSIST, CascadeType.MERGE})
	    @JoinTable(name = "PatientAntecedantMedicaux",
	            joinColumns = @JoinColumn(name = "patientID"),
	            inverseJoinColumns = @JoinColumn(name = "AntecedantMedicauxID")
	    )
	    private List<MedicalHistory> antecedantMedicaux = new ArrayList<>();
	
	@ManyToOne(cascade =  {
			CascadeType.PERSIST, CascadeType.MERGE
	})
    @JoinColumn(name = "insuranceID")
    private Insurance insurance;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressID")
   // @JsonBackReference
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userID")
	//@JsonBackReference
	private User user;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "appointmentID")
	//@JsonBackReference
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

	public List<MedicalHistory> getAntecedantMedicaux() {
		return antecedantMedicaux;
	}

	public void setAntecedantMedicaux(List<MedicalHistory> antecedantMedicaux) {
		this.antecedantMedicaux = antecedantMedicaux;
	}

	public void addAntecedantMedicaux(MedicalHistory medicalHistory){
		this.antecedantMedicaux.add(medicalHistory);
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance assurance) {
		this.insurance = assurance;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "Patient [id=" + id + ", Fname=" + Fname + ", Lname=" + Lname + ", dateNaissance=" + dateNaissance
				+ ", tel=" + tel + ", email=" + email + ", BP=" + BP + ", estAssure=" + estAssure
				+ ", datePremiereConsultation=" + datePremiereConsultation + ", state=" + state
				+ ", antecedantMedicaux=" + antecedantMedicaux + ", insurance=" + insurance + "]";
	}

	

}
