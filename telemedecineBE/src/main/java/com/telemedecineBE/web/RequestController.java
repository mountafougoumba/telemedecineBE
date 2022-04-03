package com.telemedecineBE.web;

import com.telemedecineBE.dao.AppointmentRepository;
import com.telemedecineBE.dao.DoctorRepository;
import com.telemedecineBE.dao.PatientRepository;
import com.telemedecineBE.dao.RequestRepository;
import com.telemedecineBE.entities.Appointment;
import com.telemedecineBE.entities.Doctor;
import com.telemedecineBE.entities.Patient;
import com.telemedecineBE.entities.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController {
    private final RequestRepository requestRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    @Autowired
    RequestController(RequestRepository requestRepository,
                      PatientRepository patientRepository,
                      DoctorRepository doctorRepository,
                      AppointmentRepository appointmentRepository){
        this.requestRepository = requestRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/requests")
    List<Requests> getAllRequests(){
        return requestRepository.findAll();
    }

    @GetMapping("/request/id={id}")
    Requests getRequestByID(@PathVariable Integer id)
    {
        Boolean exists = requestRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Requests with id " + id + " does not exist.");
        }
        System.out.println("getRequestById");
        return requestRepository.findById(id);
    }

    @GetMapping("/request/id={id}/doctor")
    Doctor getRequestDoctor(@PathVariable(value="id")Integer id){
        Boolean exists = requestRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Requests with id " + id + " does not exist.");
        }
        Requests requests = requestRepository.findById(id);
        return requests.getDoctor();
    }

    @PutMapping("/request/appointment")
    Requests getRequestByAppointment(@RequestBody Appointment appointment){
        Boolean exists = appointmentRepository.existsById(appointment.getId());
        if(!exists){
            throw new IllegalStateException("Appointment with id " + appointment.getId() + " does not exist.");
        }
        return requestRepository.findByAppointmentRequest(appointment);
    }

    @GetMapping("/request/id={id}/patient")
    Patient getRequestPatient(@PathVariable(value="id")Integer id){
        Boolean exists = requestRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Requests with id " + id + " does not exist.");
        }
        Requests requests = requestRepository.findById(id);
        return requests.getRequestingPatient();
    }

    @PutMapping("/request/patient")
    List<Requests> getRequestsByPatient(@RequestBody Patient patient){
        Boolean exists = patientRepository.existsById(patient.getId());
        if(!exists){
            throw new IllegalStateException("Patient with id " + patient.getId() + " does not exist.");
        }
        return requestRepository.findByRequestingPatient(patient);
    }

    @PostMapping("/request")
    Requests addRequest(@RequestBody Requests requests){
        System.out.println("addRequest");
        if(requests.getPID() != null && requests.getRequestingPatient() == null){
            Boolean exists = patientRepository.existsById(requests.getPID());
            if(!exists){
                throw new IllegalStateException("Patient with id " + requests.getPID() + " does not exist.");
            }
            Patient patient = patientRepository.findById(requests.getPID());
            requests.setRequestingPatient(patient);
        }
        if(requests.getDID() != null && requests.getDoctor() == null){
            Boolean exists = doctorRepository.existsById(requests.getDID());
            if(!exists){
                throw new IllegalStateException("Doctor with id " + requests.getDID() + " does not exist.");
            }
            Doctor doctor = doctorRepository.findById(requests.getDID()).get();
            requests.setDoctor(doctor);
        }
        requestRepository.save(requests);
        return requests;
    }

    @DeleteMapping("/request/id={id}")
    void deleteById(@PathVariable(value = "id") Integer id){
        Boolean exists = requestRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Requests with id " + id + " does not exist.");
        }
        System.out.println("deleteRequestById");
        requestRepository.deleteById(id);
    }

    @PutMapping("/request/id={id}")
    Requests updateRequest(@PathVariable(value = "id")Integer id,
                           @RequestBody Requests requests){
        Boolean exists = requestRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Requests with id " + id + " does not exist.");
        }
        System.out.println("updateRequest");

        Requests currentRequests = requestRepository.findById(id);

        if(requests.getRequestType() != null && !currentRequests.getRequestType().equals(requests.getRequestType())){
            currentRequests.setRequestType(requests.getRequestType());
        }

        if(requests.getRequestStatus() != null && !currentRequests.getRequestStatus().equals(requests.getRequestStatus())){
            currentRequests.setRequestStatus(requests.getRequestStatus());
        }

        if(requests.getRequestingPatient() != null && !currentRequests.getRequestingPatient().equals(requests.getRequestingPatient())){
            currentRequests.setRequestingPatient(requests.getRequestingPatient());
        }

        if(requests.getDoctor() != null && !currentRequests.getDoctor().equals(requests.getDoctor())){
            currentRequests.setDoctor(requests.getDoctor());
        }

        if(requests.getPrescriptionRequest() != null && !currentRequests.getPrescriptionRequest().equals(requests.getPrescriptionRequest())){
            currentRequests.setPrescriptionRequest(requests.getPrescriptionRequest());
        }

        System.out.println(currentRequests);
        requestRepository.save(currentRequests);
        return currentRequests;
    }
}
