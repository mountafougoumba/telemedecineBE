package com.telemedecineBE.entities;


import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "TYPECONSULTATION")
public class TypeConsultation implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
   
    private String nomTypeConsultation;
    
    private double montantTypeConsultation;
    
    private Integer state;
	public TypeConsultation(String nomTypeConsultation,double montantTypeConsultation) {
		super();
		this.nomTypeConsultation = nomTypeConsultation;
		this.montantTypeConsultation = montantTypeConsultation;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomTypeConsultation() {
		return nomTypeConsultation;
	}
	public void setNomTypeConsultation(String nomTypeConsultation) {
		this.nomTypeConsultation = nomTypeConsultation;
	}
	public double getMontantTypeConsultation() {
		return montantTypeConsultation;
	}
	public void setMontantTypeConsultation(double montantTypeConsultation) {
		this.montantTypeConsultation = montantTypeConsultation;
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
