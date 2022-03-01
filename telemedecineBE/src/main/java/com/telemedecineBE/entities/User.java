package com.telemedecineBE.entities;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;


@Entity
@Table(name = "XUSER",
	uniqueConstraints = @UniqueConstraint(columnNames = {"EMAIL", "PHONE", "USERNAME"}))
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
	//@JsonBackReference
	private Patient patient;

	@Column(name="LAST_NAME")
	private String lname;
	@Column(name="FIRST_NAME")
	private String fname;
	@Column(name="USERNAME")
	private String userName;
	@Column(name="PASSWORD")
	private String userpassword;
	@Column(name="USER_TYPE")
	private String userType;
	@Column(name="EMAIL")
	private String email;
	@Column(name="PHONE")
	private String cellphone;
	@Column(name="STATE")
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}
