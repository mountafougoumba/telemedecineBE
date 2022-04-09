package com.telemedecineBE.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;
import javax.swing.text.DateFormatter;

@Entity
@Table(name = "MEDICALHISTORY")
@Getter
@Setter
public class MedicalHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String doctorDiagnosed;
	@JsonFormat(pattern = "uuuu-MM-dd")
	private LocalDate dateDiagnosed;
	private Integer state = 1;
	private String description;

	@ManyToOne
	@JoinColumn(name="patientID")
	@JsonBackReference(value = "patient-medical-history")
	private Patient patient;

	public MedicalHistory() {
		super();
	}

	public MedicalHistory(String name, String doctorDiagnosed,
						  String dateDiagnosed) {
		super();
		this.name = name;
		this.doctorDiagnosed = doctorDiagnosed;
		DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		this.dateDiagnosed = LocalDate.parse(dateDiagnosed, dateFormatter);
	}

	public MedicalHistory(String name, String doctorDiagnosed,
						  String dateDiagnosed, String description) {
		super();
		this.name = name;
		this.doctorDiagnosed = doctorDiagnosed;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		this.dateDiagnosed = LocalDate.parse(dateDiagnosed, dateTimeFormatter);
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MedicalHistory{" +
				"id=" + id +
				", name='" + name + '\'' +
				", doctorDiagnosed='" + doctorDiagnosed + '\'' +
				", dateDiagnosed='" + dateDiagnosed + '\'' +
				", state=" + state +
				", description='" + description + '\'' +
				'}';
	}
}
