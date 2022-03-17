package com.telemedecineBE.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "APPOINTMENT")
@Getter
@Setter
public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@JsonFormat(pattern = "uuuu-MM-dd HH:mm")
	private LocalDateTime schedule;
	private String dateScheduled;
	private String purpose;
	@ManyToOne
	@JoinColumn(name="doctorID")
	@JsonBackReference(value = "doctor-appointments")
	private Doctor doctor;
	@ManyToOne
	@JoinColumn(name = "patientID")
	@JsonBackReference(value = "patient-appointments")
	private Patient patient;
	private Integer state;

	public Appointment()
	{
		super();
	}

	public Appointment(String dateScheduled, String purpose) {
		super();
		this.dateScheduled = dateScheduled;
		this.purpose = purpose;
	}

	public Appointment(String dateScheduled, String purpose, Doctor doctor){
		super();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		this.schedule = LocalDateTime.parse(dateScheduled, dateTimeFormatter);
		this.dateScheduled = dateScheduled;
		this.purpose = purpose;
		this.doctor = doctor;
	}

	public Appointment(String dateScheduled, Doctor doctor){
		super();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		this.schedule = LocalDateTime.parse(dateScheduled, dateTimeFormatter);
		this.dateScheduled = dateScheduled;
		this.doctor = doctor;
	}

	public Appointment(String dateScheduled, String purpose, Patient patient, Doctor doctor){
		super();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		this.schedule = LocalDateTime.parse(dateScheduled, dateTimeFormatter);
		this.dateScheduled = dateScheduled;
		this.purpose = purpose;
		this.patient = patient;
		this.doctor = doctor;
	}

	public Appointment(LocalDateTime dateScheduled, String purpose, Patient patient, Doctor doctor){
		super();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		this.schedule = LocalDateTime.parse(dateScheduled.toString(), dateTimeFormatter);
		this.dateScheduled = dateScheduled.toString();
		this.purpose = purpose;
		this.patient = patient;
		this.doctor = doctor;
	}

	public void setDateScheduled(String dateScheduled){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		this.schedule = LocalDateTime.parse(dateScheduled, dateTimeFormatter);
		this.dateScheduled = dateScheduled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", scheduled" + schedule.toString() + ", purpose=" + purpose
				+ ", state=" + state + ", doctor=Dr." + doctor.getLname() + "]";
	}

}
