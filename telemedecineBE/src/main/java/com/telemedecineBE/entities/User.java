package com.telemedecineBE.entities;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.telemedecineBE.Security.AES;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.telemedecineBE.enumeration.UserType;
import lombok.*;


@Entity
@Table(name = "XUSER",
		uniqueConstraints = @UniqueConstraint(columnNames = {"EMAIL", "PHONE", "USERNAME"}))
@Getter
@Setter
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

	@Column(name="LAST_NAME")
	private String lname;
	@Column(name="FIRST_NAME")
	private String fname;
	@Column(name="USERNAME")
	private String userName;
	@Column(name="PASSWORD")
	private String userpassword;
	@Column(name="USER_TYPE")
	private UserType userType;
	@Column(name="EMAIL")
	private String email;
	@Column(name="PHONE")
	private String cellphone;
	@Column(name="STATE")
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
