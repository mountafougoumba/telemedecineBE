package com.telemedecineBE.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "APPOINTMENT")
public class Appointment implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date dateRDV;
    private String heureRDV;
    private String objectRDV;
    private Integer state;
    @ManyToOne
    @JoinColumn(name = "patientID")
    private Patient patient;
	public Appointment( Date dateRDV,  String heureRDV,  String objectRDV) {
		super();
		this.dateRDV = dateRDV;
		this.heureRDV = heureRDV;
		this.objectRDV = objectRDV;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDateRDV() {
		return dateRDV;
	}
	public void setDateRDV(Date dateRDV) {
		this.dateRDV = dateRDV;
	}
	public String getHeureRDV() {
		return heureRDV;
	}
	public void setHeureRDV(String heureRDV) {
		this.heureRDV = heureRDV;
	}
	public String getObjectRDV() {
		return objectRDV;
	}
	public void setObjectRDV(String objectRDV) {
		this.objectRDV = objectRDV;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", dateRDV=" + dateRDV + ", heureRDV=" + heureRDV + ", objectRDV=" + objectRDV
				+ ", state=" + state + ", patient=" + patient + "]";
	}
    

}
