package com.telemedecineBE.entities;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "XUSER")
public class User implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@ManyToMany(cascade = {
			CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "userHabilitation",
			joinColumns = @JoinColumn(name = "userID"),
			inverseJoinColumns = @JoinColumn(name = "habilitationID")
	)
	private List<Habilitations> habilitations = new ArrayList<>();
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
	@JsonBackReference
	private Patient patient;

	private String lname;
	private String fname;
	@Column(unique = true)
	private String userName;
	private String userpassword;
	private String userType;
	private String email;
	private String cellphone;
	private Integer state=1;
	public User(String nom,  String prenom, String nomUtilisateur,
				String motDePasse, String email, String cellphone) {
		super();
		this.lname = nom;
		this.fname = prenom;
		this.userName = nomUtilisateur;
		this.userpassword = motDePasse;
		this.email = email;
		this.cellphone = cellphone;
	}

	public User() {
		super();
	}

	public User(String userName, String password) {
		this.userName = userName;
		this.userpassword = password;
	}

	public User(String nom, String prenom,  String nomUtilisateur,
				String motDePasse,  String typeUtilisateur,  String email,
				String cellphone) {
		super();
		this.lname = nom;
		this.fname = prenom;
		this.userName = nomUtilisateur;
		this.userpassword = motDePasse;
		this.userType = typeUtilisateur;
		this.email = email;
		this.cellphone = cellphone;
	}

	public User( String nom, String prenom,  String nomUtilisateur,
				 String motDePasse,  String typeUtilisateur, String email,
				 String cellphone,Integer state) {
		super();
		this.lname = nom;
		this.fname = prenom;
		this.userName = nomUtilisateur;
		this.userpassword = motDePasse;
		this.userType = typeUtilisateur;
		this.email = email;
		this.cellphone = cellphone;
		this.state = state;
	}



	@Override
	public String toString() {
		return "nom=" + lname + " prenom=" + fname + " nomUtilisateur=" + userName
				+ " motDePasse=" + userpassword + " typeUtilisateur=" + userType + " email=" + email
				+ "  cellphone=" + cellphone + " ";
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

	public List<Habilitations> getHabilitations() {
		return habilitations;
	}

	public void setHabilitations(List<Habilitations> habilitations) {
		this.habilitations = habilitations;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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
