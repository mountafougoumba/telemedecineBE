package com.telemedecineBE.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.telemedecineBE.web.UserController;
import lombok.*;

@Entity
@Table(name = "PATIENT",
		uniqueConstraints = @UniqueConstraint(columnNames={"EMAIL", "PHONE"}))
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Patient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	@Column(name="FIRST_NAME")
	private String Fname;
	@Column(name="LAST_NAME")
    private String Lname;
	@Column(name="DOB")
    private String dob;
	@Column(name="PHONE")
    private String phone;
	@Column(name="EMAIL")
    private String email;
    //private String BP;
	@Column(name="IS_INSURED")
    private Boolean isInsured;
	@Column(name="STATE")
    private Integer state=1;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="medicalHistoryId")
	private List<MedicalHistory> medicalHistory = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "insuranceID")
    private List<Insurance> insurance;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressID")
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userID")
	@JsonBackReference
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appointmentID")
	@JsonManagedReference
	private List<Appointment> appointments;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="prescriptionId")
	private List<Priscriptions> prescriptions;

	public Patient(String fname, String lname, String phone, String email) {
		Fname = fname;
		Lname = lname;
		this.phone = phone;
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getIsInsured(){ return isInsured; };

	public void setIsInsured(boolean isInsured) { this.isInsured = isInsured; }

}
