package com.telemedecineBE.entities;

import java.util.ArrayList;
import java.util.List;
import com.telemedecineBE.Security.AES;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.telemedecineBE.enumeration.UserType;
import lombok.*;

@Entity
@Table(name = "PATIENT",
		uniqueConstraints = @UniqueConstraint(columnNames={"EMAIL", "PHONE"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Patient extends User{

	private final String secretKey = "total_user_secret";

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PATIENT_ID")
	private Integer id;
	/*
	@Column(name="FIRST_NAME")
	private String Fname;
	@Column(name="LAST_NAME")
    private String Lname;
	@Column(name="PHONE")
    private String phone;
	@Column(name="EMAIL")
    private String email;
	 */

	@Column(name="DOB")
    private String dob;
	@Column(name="IS_INSURED")
    private Boolean isInsured = false;
	@Column(name="STATE")
    private Integer state=1;

	@OneToMany(cascade=CascadeType.ALL)
	@JsonManagedReference(value="patient-medical-history")
	private List<MedicalHistory> medicalHistory = new ArrayList<>();

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "patient-insurance")
	private List<Insurance> insurance;

	@OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference("patient-address")
	private Address address;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "patient-appointments")
	private List<Appointment> appointments;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "patient-prescriptions")
	private List<Prescriptions> prescriptions;

	@ManyToMany
	@JoinColumn(name="doctorId")
	private List<Doctor> doctors;

	@OneToMany(mappedBy = "requestingPatient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference("patient-request")
	private List<Requests> requests;

	@ElementCollection
	private List<Integer> reportIds;

	public Patient(String fname, String lname, String email, String cellphone, String userpassword){
		super(fname, lname, userpassword, UserType.PATIENT, email, cellphone);
	}

	public Patient(String fname, String lname, String email, String cellphone, String dob, String userpassword){
		super(fname, lname, userpassword, UserType.PATIENT, email, cellphone);
		this.dob = dob;
	}

	public Patient(String fname, String lname, String email, String cellphone, Address address, String userpassword){
		super(fname, lname, userpassword, UserType.PATIENT, email, cellphone);
		this.address = address;
	}

	public Doctor addDoctor(Doctor doctor){
		if(!this.doctors.contains(doctor)){
			this.doctors.add(doctor);
			return doctor;
		} else {
			return null;
		}
	}

	@Override
	public String toString (){
		return "Patient [id= " + id + ", fname= " + getFname() + ", lname= " + getLname() +
				", username= " + getUserName() + ", userType= " + getUserType().getType() +
				", email= " + getEmail() + ", cellphone= " + getCellphone() + ", dob= " + dob +
				", isInsured= " + isInsured + ", habilitations= " + getHabilitations() + ", medicalHistory= " +
				medicalHistory + ", insurance= " + insurance + ", address= " + address + ", appointments= " +
				appointments + ", prescriptions= " + prescriptions + ", state= " + state + "]";
	}

	public void encryptUserData() {
		/**
		 * Encrypting the following:
		 * first name
		 * last name
		 * email
		 * username
		 * phone
		 * dob
		 */
		this.setFname(AES.encrypt(this.getFname(), secretKey));
		this.setLname(AES.encrypt(this.getLname(), secretKey));
		this.setEmail(AES.encrypt(this.getEmail(), secretKey));
		this.setUserName(AES.encrypt(this.getUserName(), secretKey));
		this.setCellphone(AES.encrypt(this.getCellphone(), secretKey));
		this.setDob(AES.encrypt(this.getDob(), secretKey));
	}

	public void decryptUserData() {
		this.setFname(AES.decrypt(this.getFname(), secretKey));
		this.setLname(AES.decrypt(this.getLname(), secretKey));
		this.setEmail(AES.decrypt(this.getEmail(), secretKey));
		this.setUserName(AES.decrypt(this.getUserName(), secretKey));
		this.setCellphone(AES.decrypt(this.getCellphone(), secretKey));
		this.setDob(AES.decrypt(this.getDob(), secretKey));
	}
}
