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
	private Integer id;
	private String officeName;
	private String specialty;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "doctor", fetch = FetchType.LAZY)
	@JsonManagedReference(value = "doctor-address")
	private Address officeAddress;

	@OneToMany(mappedBy="doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "doctor-appointments")
	private List<Appointment> appointments;

	public Doctor(String fname, String lname, String officeName, String specialty, String userpassword, String email,
				  String cellphone) {
		super(fname, lname, userpassword, UserType.DOCTOR, email, cellphone);
		this.officeName = officeName;
		this.specialty = specialty;
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
