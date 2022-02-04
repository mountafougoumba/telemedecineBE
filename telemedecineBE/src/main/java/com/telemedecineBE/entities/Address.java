package com.telemedecineBE.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "ZIPECODE")
	private String zipcode;
	
	@Column(name = "LINE",length = 255)
	private String line;
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Address(Long id, String zipcode, String line) {
		super();
		this.id = id;
		this.zipcode = zipcode;
		this.line = line;
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getLine() {
		return line;
	}


	public void setLine(String line) {
		this.line = line;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", zipcode=" + zipcode + ", line=" + line + "]";
	}
	

}
