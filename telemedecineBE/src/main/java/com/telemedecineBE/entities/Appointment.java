package com.telemedecineBE.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.telemedecineBE.enumeration.AppointmentType;
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
	private AppointmentType appointmentType;
	private Integer state;

	@OneToOne
	private Requests appointmentRequest;

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
		if(dateScheduled.charAt(0) != 'T' && dateScheduled.charAt(dateScheduled.length() - 1) != 'T'){
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			this.schedule = LocalDateTime.parse(dateScheduled, dateTimeFormatter);
		}
		this.dateScheduled = dateScheduled;
		this.purpose = purpose;
		this.doctor = doctor;
	}

	public Appointment(String dateScheduled, Patient patient, Doctor doctor, AppointmentType appointmentType){
		super();
		if(dateScheduled.charAt(0) != 'T' && dateScheduled.charAt(dateScheduled.length() - 1) != 'T'){
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			this.schedule = LocalDateTime.parse(dateScheduled, dateTimeFormatter);
		}
		this.dateScheduled = dateScheduled;
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentType = appointmentType;
	}

	public Appointment(String dateScheduled, String purpose, Patient patient, Doctor doctor, AppointmentType appointmentType){
		super();
		if(dateScheduled.charAt(0) != 'T' && dateScheduled.charAt(dateScheduled.length() - 1) != 'T'){
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			this.schedule = LocalDateTime.parse(dateScheduled, dateTimeFormatter);
		}
		this.dateScheduled = dateScheduled;
		this.purpose = purpose;
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentType = appointmentType;
	}

	public Appointment(LocalDateTime dateScheduled, String purpose, Patient patient, Doctor doctor, AppointmentType appointmentType){
		super();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		this.schedule = LocalDateTime.parse(dateScheduled.toString(), dateTimeFormatter);
		this.dateScheduled = dateScheduled.toString();
		this.purpose = purpose;
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentType = appointmentType;
	}

	public Appointment(String dateScheduled){
		if(dateScheduled.charAt(0) != 'T' && dateScheduled.charAt(dateScheduled.length() - 1) != 'T'){
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			this.schedule = LocalDateTime.parse(dateScheduled, dateTimeFormatter);
		}
		this.dateScheduled = dateScheduled;
	}

	public void setDateScheduled(String dateScheduled){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		this.schedule = LocalDateTime.parse(dateScheduled, dateTimeFormatter);
		this.dateScheduled = dateScheduled;
	}

	public void setDate(String dateScheduled){
		String hour = String.valueOf(schedule.getHour());
		String min = String.valueOf(schedule.getMinute());
		dateScheduled = dateScheduled + hour+ ":" + min;
		System.out.println(dateScheduled);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		this.schedule = LocalDateTime.parse(dateScheduled, dateTimeFormatter);
		this.dateScheduled = dateScheduled;
	}

	public void setTime(String dateScheduled){
		String year = String.valueOf(schedule.getYear());
		String month = String.valueOf(schedule.getMonth());
		String day = String.valueOf(schedule.getDayOfMonth());
		dateScheduled = year + "-" + month + "-" + day + "-" + dateScheduled;
		System.out.println(dateScheduled);
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
				+ ", state=" + state + ", doctor= " + doctor + "]";
	}

}
