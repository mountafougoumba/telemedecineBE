package com.telemedecineBE.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.telemedecineBE.enumeration.RequestStatus;
import com.telemedecineBE.enumeration.RequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "REQUESTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Requests implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private RequestType requestType;

    private RequestStatus requestStatus;

    @ManyToOne
    @JoinColumn(name="patientID")
    @JsonBackReference("patient-request")
    private Patient requestingPatient;

    private Integer pID;

    @ManyToOne
    @JoinColumn(name="doctorID")
    @JsonBackReference("doctor-request")
    private Doctor doctor;

    private Integer dID;

    private LocalDateTime timeRequested;

    //T can be either an appointment or prescription
    @OneToOne
    @JoinColumn(name = "prescription_request_id")
    private Prescriptions prescriptionRequest;

    public Requests(Prescriptions request, String requestType, Patient patient, Doctor doctor){
        this.prescriptionRequest = request;
        this.requestType =  RequestType.findByTypeName(requestType);
        this.requestStatus =  RequestStatus.WAITING;
        this.requestingPatient = patient;
        this.doctor = doctor;
        this.pID = this.requestingPatient.getId();
        this.dID = this.doctor.getId();
    }

    public Requests(Prescriptions request, String requestType, String requestStatus, Patient patient, Doctor doctor){
        this.prescriptionRequest = request;
        this.requestType =  RequestType.findByTypeName(requestType);
        this.requestStatus =  RequestStatus.findByStatusName(requestStatus);
        this.requestingPatient = patient;
        this.doctor = doctor;
        this.pID = this.requestingPatient.getId();
        this.dID = this.doctor.getId();
    }

    public Requests(Prescriptions request, RequestType requestType, RequestStatus requestStatus, Patient patient, Doctor doctor){
        this.prescriptionRequest = request;
        this.requestType =  requestType;
        this.requestStatus =  requestStatus;
        this.requestingPatient = patient;
        this.doctor = doctor;
        this.pID = this.requestingPatient.getId();
        this.dID = this.doctor.getId();
    }

    public Requests(Prescriptions request, RequestType requestType, RequestStatus requestStatus, Integer patient, Integer doctor){
        this.prescriptionRequest = request;
        this.requestType =  requestType;
        this.requestStatus =  requestStatus;
        this.pID = patient;
        this.dID = doctor;
        this.pID = this.requestingPatient.getId();
        this.dID = this.doctor.getId();
    }


    @Override
    public String toString() {
        return "Requests{" +
                "id=" + id +
                ", requestType=" + requestType +
                ", requestStatus=" + requestStatus +
                ", prescriptionRequest=" + prescriptionRequest +
                '}';
    }

}
