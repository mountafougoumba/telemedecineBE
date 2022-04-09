package com.telemedecineBE.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "INSURANCE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Insurance implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
	//all care coverage
    private Boolean allCareCoverage;
	//cover consulting fees
    private Boolean consultingFeesCovered;
    private Integer state =1;
    private Double percentInsured;

	@ManyToOne
	@JoinColumn(name="patientID")
	@JsonBackReference(value = "patient-insurance")
	private Patient patient;

	public Insurance(String name, Boolean allCareCoverage, Boolean consultingFeesCovered,
					 Integer state, Double percentInsured) {
		super();
		this.name = name;
		this.allCareCoverage = allCareCoverage;
		this.consultingFeesCovered = consultingFeesCovered;
		this.state = state;
		this.percentInsured = percentInsured;
	}
	public Insurance(String name, Boolean allCareCoverage, Boolean consultingFeesCovered,
					 Double percentInsured) {
		super();
		this.name = name;
		this.allCareCoverage = allCareCoverage;
		this.consultingFeesCovered = consultingFeesCovered;
		this.percentInsured = percentInsured;
	}

	public Insurance(String name, Boolean allCareCoverage, Boolean consultingFeesCovered,
					 Double percentInsured, Patient patient) {
		super();
		this.name = name;
		this.allCareCoverage = allCareCoverage;
		this.consultingFeesCovered = consultingFeesCovered;
		this.percentInsured = percentInsured;
		this.patient = patient;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Insurance{" +
				"id=" + id +
				", name='" + name + '\'' +
				", allCareCoverage=" + allCareCoverage +
				", consultingFeesCovered=" + consultingFeesCovered +
				", state=" + state +
				", percentInsured=" + percentInsured +
				'}';
	}
}
