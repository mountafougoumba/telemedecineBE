package com.telemedecineBE.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.telemedecineBE.enumeration.UserType;
import lombok.*;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "admin-appointment-request")
    private List<Appointment> requestedAppointments;



    public Admin(String fname, String lname, String email, String cellphone, String userpassword){
        super(fname, lname, userpassword, UserType.ADMIN, email, cellphone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Appointment> getRequestedAppointments() {
        return requestedAppointments;
    }

    public Appointment getRequestedAppointment(Appointment appointment)
    {
        if(requestedAppointments.contains(appointment)){
            return appointment;
        } else {
            for (Appointment a: requestedAppointments) {
                if(appointment.getId() != null){
                    if(a.getId() == appointment.getId()){
                        return a;
                    }
                }
                if(a.getDoctor() == appointment.getDoctor() && a.getPatient() == appointment.getPatient() && a.getSchedule() == appointment.getSchedule()){
                    return a;
                }
            }
        }
        return null;
    }
    public void setRequestedAppointments(List<Appointment> requestedAppointments) {
        this.requestedAppointments = requestedAppointments;
    }

    public Appointment addRequestedAppointment(Appointment appointment){
        if(!this.requestedAppointments.contains(appointment)){
            this.requestedAppointments.add(appointment);
            return appointment;
        }
        return null;
    }

    @Override
    public String toString (){
        return "Admin [id= " + id + ", fname= " + getFname() + ", lname= " + getLname() +
                ", username= " + getUserName() + ", userType= " + getUserType().getType() +
                ", email= " + getEmail() + ", cellphone= " + getCellphone() + ", requestedAppointments= " +
                getRequestedAppointments() + "]";
    }
}

