package com.telemedecineBE.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "HABILITATIONS")
public class Habilitations implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String nameHabilitation;
    private Integer state = 1;
    @ManyToMany(mappedBy = "habilitations")
    private List<User> users = new ArrayList<>();
    
	@Override
	public String toString() {
		return  "ID="+id + "Name=" + nameHabilitation + " State=" + state;
	}
	public Habilitations() {
		super();
	}
	public Habilitations( String nomHabilitation) {
		super();
		this.nameHabilitation = nomHabilitation;
	}
	public String getNameHabilitation() {
		return nameHabilitation;
	}
	public void setNameHabilitation(String nomHabilitation) {
		this.nameHabilitation = nomHabilitation;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Habilitations( String nomHabilitation, Integer state) {
		super();
		this.nameHabilitation = nomHabilitation;
		this.state = state;
	}
	
	
    
	
    
}


