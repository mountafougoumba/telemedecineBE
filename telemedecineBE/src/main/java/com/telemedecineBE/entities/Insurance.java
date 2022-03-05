package com.telemedecineBE.entities;

import lombok.*;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INSURANCE")
@ToString
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
