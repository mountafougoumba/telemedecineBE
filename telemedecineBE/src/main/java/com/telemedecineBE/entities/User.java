package com.telemedecineBE.entities;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.telemedecineBE.enumeration.UserType;
import lombok.*;


@Entity
@Table(name = "XUSER",
	uniqueConstraints = @UniqueConstraint(columnNames = {"EMAIL", "PHONE", "USERNAME"}))
@Getter
@Setter



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

	@Column(name="USER_TYPE")
	private UserType userType;
	@Column(name="EMAIL")
  
	private String userType;

	private String email;
	private String cellphone;
	private Integer state=1;


	public User(){}

	public User(Integer id, List<Habilitations> habilitations, String lname, String fname, String userName, String userpassword, UserType userType, String email, String cellphone, Integer state) {
		this.id = id;
		this.habilitations = habilitations;
		this.lname = lname;
		this.fname = fname;
		this.userName = email;
		this.userpassword = userpassword;
		this.userType = userType;

	public User(String nom,  String prenom, String nomUtilisateur,
				String motDePasse, String email, String cellphone) {
		super();
		this.lname = nom;
		this.fname = prenom;
		this.userName = nomUtilisateur;
		this.userpassword = motDePasse;

		this.email = email;
		this.cellphone = cellphone;
		this.state = state;
	}

	public User(String lname, String fname, String userName, String userpassword, UserType userType, String email, String cellphone) {
		this.lname = lname;
		this.fname = fname;
		this.userName = userName;
		this.userpassword = userpassword;
		this.userType = userType;
		this.email = email;
		this.cellphone = cellphone;
	}

	public User(String first, String last, String email, String phone, String password) {
		super();
		this.lname = last;
		this.fname = first;
		this.userName = email;
		this.userpassword = password;
		this.email = email;
		this.cellphone = phone;
	}

	public User() {
		super();
	}

	public User(String userName, String password) {
		this.userName = userName;
		this.userpassword = password;
	}

	public User(String first, String last,
				String password,  String userType,  String email,
				String cellphone) {
		super();
		this.fname = first;
		this.lname = last;
		this.userName = email;
		this.userpassword = password;
		this.userType = this.userType.findByName(userType);
		this.email = email;
		this.cellphone = cellphone;
		if(userType == null){
			throw new IllegalStateException("user type " + userType + "does not exist");
		}
	}

	public User(String first, String last,
				String password,  UserType userType,  String email,
				String cellphone) {
		super();
		this.fname = first;
		this.lname = last;
		this.userName = email;
		this.userpassword = password;
		this.userType = userType;
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

	@Override
	public String toString() {
		return "User [id= " + id + ", fname= " + fname + ", lname= " + lname + ", userName= " +
		email + ", userType= " + userType.getType() + ", email= " + email + ", phone= " + cellphone
		+ "]";
	}


}
