package com.telemedecineBE.entities;


import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "CONSULTATION")
public class Consultation implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="patientID")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name="typeConsultationID")
    private TypeConsultation typeConsultation;
    private String dateConsultation;
    private String diagnostic;
    private Double totalApercevoir;
    private Double totalPercu;
    private Double totalRestant;
    private Double montantPrisEnChargeParAssurance;
    private Double montantRegleParPatient;
    private Integer state =1;
	public Consultation( String dateConsultation,  String diagnostic,  Double totalApercevoir,
			 Double totalPercu,  Double totalRestant,
			Double montantPrisEnChargeParAssurance, Double montantRegleParPatient) {
		super();
		this.dateConsultation = dateConsultation;
		this.diagnostic = diagnostic;
		this.totalApercevoir = totalApercevoir;
		this.totalPercu = totalPercu;
		this.totalRestant = totalRestant;
		this.montantPrisEnChargeParAssurance = montantPrisEnChargeParAssurance;
		this.montantRegleParPatient = montantRegleParPatient;
	}
	public Consultation() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public TypeConsultation getTypeConsultation() {
		return typeConsultation;
	}
	public void setTypeConsultation(TypeConsultation typeConsultation) {
		this.typeConsultation = typeConsultation;
	}
	public String getDateConsultation() {
		return dateConsultation;
	}
	public void setDateConsultation(String dateConsultation) {
		this.dateConsultation = dateConsultation;
	}
	public String getDiagnostic() {
		return diagnostic;
	}
	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}
	public Double getTotalApercevoir() {
		return totalApercevoir;
	}
	public void setTotalApercevoir(Double totalApercevoir) {
		this.totalApercevoir = totalApercevoir;
	}
	public Double getTotalPercu() {
		return totalPercu;
	}
	public void setTotalPercu(Double totalPercu) {
		this.totalPercu = totalPercu;
	}
	public Double getTotalRestant() {
		return totalRestant;
	}
	public void setTotalRestant(Double totalRestant) {
		this.totalRestant = totalRestant;
	}
	public Double getMontantPrisEnChargeParAssurance() {
		return montantPrisEnChargeParAssurance;
	}
	public void setMontantPrisEnChargeParAssurance(Double montantPrisEnChargeParAssurance) {
		this.montantPrisEnChargeParAssurance = montantPrisEnChargeParAssurance;
	}
	public Double getMontantRegleParPatient() {
		return montantRegleParPatient;
	}
	public void setMontantRegleParPatient(Double montantRegleParPatient) {
		this.montantRegleParPatient = montantRegleParPatient;
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
	@Override
	public String toString() {
		return "Consultation [id=" + id + ", patient=" + patient + ", typeConsultation=" + typeConsultation
				+ ", dateConsultation=" + dateConsultation + ", diagnostic=" + diagnostic + ", totalApercevoir="
				+ totalApercevoir + ", totalPercu=" + totalPercu + ", totalRestant=" + totalRestant
				+ ", montantPrisEnChargeParAssurance=" + montantPrisEnChargeParAssurance + ", montantRegleParPatient="
				+ montantRegleParPatient + ", state=" + state + "]";
	}
	
    
	
	

}
