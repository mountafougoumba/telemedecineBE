package com.telemedecineBE.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "REPORT")
public class Report implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String description;
	private String doctor_upload;

	public Report() { super(); }

	public Report(Integer id, String description, String doctor_upload) {
		super();
		this.id = id;
		this.description = description;
		this.doctor_upload = doctor_upload;
		
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDoctor_upload() {
		return doctor_upload;
	}
	public void setDoctor_upload(String doctor_upload) {
		this.doctor_upload = doctor_upload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Report [id=" + id + ", description=" + description + ", doctor_upload=" + doctor_upload + ", getId()="
				+ getId() + ", getDescription()=" + getDescription() + ", getDoctor_upload()=" + getDoctor_upload()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	

}
