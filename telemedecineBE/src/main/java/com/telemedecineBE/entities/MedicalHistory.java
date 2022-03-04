package com.telemedecineBE.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "MEDICALHISTORY",
uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
@Getter
@Setter
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
	@Column(name="STATE")
	private Integer state = 1;
	@Column(name="DESCRIPTION")
	private String description;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
