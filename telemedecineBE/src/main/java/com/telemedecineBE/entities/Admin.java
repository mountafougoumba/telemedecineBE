package com.telemedecineBE.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.telemedecineBE.enumeration.UserType;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "ADMIN",
        uniqueConstraints = @UniqueConstraint(columnNames={"EMAIL", "PHONE"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ADMIN_ID")
    private Integer id;

    @OneToMany(mappedBy="admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("admin-request")
    private List<Requests> requestedAppointments;

    public Admin(String fname, String lname, String email, String cellphone, String userpassword){
        super(fname, lname, userpassword, UserType.ADMIN, email, cellphone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}

