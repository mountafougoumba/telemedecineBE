package com.telemedecineBE.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.telemedecineBE.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "DOCTOR",
		uniqueConstraints = @UniqueConstraint(columnNames={"EMAIL", "PHONE"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Doctor extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="DOCTOR_ID")
	private Integer id;
	private String officeName;
	private String specialty;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "doctor", fetch = FetchType.LAZY)
	@JsonManagedReference(value = "doctor-address")
	private Address officeAddress;

	@OneToMany(mappedBy="doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "doctor-appointments")
	private List<Appointment> appointments;

	@ManyToMany(mappedBy="doctors", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Patient> patients;

	@OneToMany(mappedBy="doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference("doctor-request")
	private List<Requests> requestedPrescriptions;

	@OneToMany(mappedBy="doctorPrescribed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "doctor-prescriptions")
	private List<Prescriptions> prescribedPrescriptions;

	public Doctor(String fname, String lname, String officeName, String specialty, String userpassword, String email,
				  String cellphone) {
		super(fname, lname, userpassword, UserType.DOCTOR, email, cellphone);
		this.officeName = officeName;
		this.specialty = specialty;
	}

	public Patient addPatient(Patient patient){
		if(!this.patients.contains(patient)){
			this.patients.add(patient);
			return patient;
		} else {
			return null;
		}
	}

	public void removePatient(Patient patient){
		if(this.patients.contains(patient)) {
			this.patients.remove(patient);
		}
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Doctor [ID=" + id + ", fname=" + getFname() + ", lname=" + getLname() + ", office=" + officeName + ", specialty="
				+ specialty + ", email=" + getEmail() + ", phone=" + getCellphone() + "]";
	}
	

}
