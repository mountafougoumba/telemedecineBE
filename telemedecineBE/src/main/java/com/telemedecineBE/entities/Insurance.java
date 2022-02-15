package com.telemedecineBE.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INSURANCE")

public class Insurance implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nom;
	//all care coverage
    private Boolean couvreToutSoins;
	//cover consulting fees
    private Boolean couvreFraisConsultation;
    private Integer state =1;
    private Double percentageAssurance;

	public Insurance(String nom,Boolean couvreToutSoins,Boolean couvreFraisConsultation,
			Integer state,Double percentageAssurance) {
		super();
		this.nom = nom;
		this.couvreToutSoins = couvreToutSoins;
		this.couvreFraisConsultation = couvreFraisConsultation;
		this.state = state;
		this.percentageAssurance = percentageAssurance;
	}
	public Insurance(String nom, Boolean couvreToutSoins, Boolean couvreFraisConsultation,
			Double percentageAssurance) {
		super();
		this.nom = nom;
		this.couvreToutSoins = couvreToutSoins;
		this.couvreFraisConsultation = couvreFraisConsultation;
		this.percentageAssurance = percentageAssurance;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Boolean getCouvreToutSoins() {
		return couvreToutSoins;
	}
	public void setCouvreToutSoins(Boolean couvreToutSoins) {
		this.couvreToutSoins = couvreToutSoins;
	}
	public Boolean getCouvreFraisConsultation() {
		return couvreFraisConsultation;
	}
	public void setCouvreFraisConsultation(Boolean couvreFraisConsultation) {
		this.couvreFraisConsultation = couvreFraisConsultation;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Double getPercentageAssurance() {
		return percentageAssurance;
	}
	public void setPercentageAssurance(Double percentageAssurance) {
		this.percentageAssurance = percentageAssurance;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Insurance() {
		super();
	}
	@Override
	public String toString() {
		return "Assurance id=" + id + " nom=" + nom + " couvreToutSoins=" + couvreToutSoins
				+ " couvreFraisConsultation=" + couvreFraisConsultation + " state=" + state + " percentageAssurance="
				+ percentageAssurance;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Insurance)) return false;
		Insurance insurance = (Insurance) o;
		return getNom().equals(insurance.getNom()) && getCouvreToutSoins().equals(insurance.getCouvreToutSoins()) && getCouvreFraisConsultation().equals(insurance.getCouvreFraisConsultation()) && getPercentageAssurance().equals(insurance.getPercentageAssurance());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNom(), getCouvreToutSoins(), getCouvreFraisConsultation(), getPercentageAssurance());
	}
}
