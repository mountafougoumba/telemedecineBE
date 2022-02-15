package com.telemedecineBE.entities;

import lombok.ToString;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "MEDICAL_HISTORY")
@ToString
public class MedicalHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name="NAME")
	private String name;
	@Column(name="DOCTOR_DIAGNOSED")
	private String doctorDiagnosed;
	@Column(name="DATE_DIAGNOSED")
	private String dateDiagnosed;
	@Column(name="DESCRIPTION_NOTES")
	private String description;
	@Column(name="STATE")
	private Integer state = 1;

	public MedicalHistory() {
		super();
	}

	public MedicalHistory(String name, String doctorDiagnosed,
						  String dateDiagnosed) {
		super();
		this.name = name;
		this.doctorDiagnosed = doctorDiagnosed;
		this.dateDiagnosed = dateDiagnosed;
	}

	public MedicalHistory(String name, String doctorDiagnosed, String dateDiagnosed, String description) {
		this.name = name;
		this.doctorDiagnosed = doctorDiagnosed;
		this.dateDiagnosed = dateDiagnosed;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nomAntecedant) {
		this.name = nomAntecedant;
	}

	public String getDoctorDiagnosed() {
		return doctorDiagnosed;
	}

	public void setDoctorDiagnosed(String medecinTraitant) {
		this.doctorDiagnosed = medecinTraitant;
	}

	public String getDateDiagnosed() {
		return dateDiagnosed;
	}

	public void setDateDiagnosed(String dateSurvenance) {
		this.dateDiagnosed = dateSurvenance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
