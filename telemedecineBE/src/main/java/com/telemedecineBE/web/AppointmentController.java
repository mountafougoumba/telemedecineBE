package com.telemedecineBE.web;

import com.telemedecineBE.dao.AppointmentRepository;
import com.telemedecineBE.entities.Appointment;
import com.telemedecineBE.entities.Doctor;
import com.telemedecineBE.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Optional;

@RestController
public class AppointmentController {
    private AppointmentRepository appRep;

    @Autowired
    AppointmentController(AppointmentRepository ar){
        this.appRep = ar;
    }

    @GetMapping("/appointments")
    List<Appointment> getAllAppointments(){
        return appRep.findAll();
    }

    @GetMapping("/appointment/id={id}")
    Optional<Appointment> get(@PathVariable Integer id)
    {
        return appRep.findById(id);
    }

    @GetMapping("/appointment/patient/id={id}")
    Patient getAppointmentPatient(@PathVariable Integer id)
    {
        Appointment app = appRep.findById(id).get();
        return app.getPatient();
    }

    @GetMapping("/appointment/doctor/id={id}")
    Doctor getAppointmentDoctor(@PathVariable Integer id)
    {
        Appointment app = appRep.findById(id).get();
        return app.getDoctor();
    }

    @DeleteMapping("/appointment/id={id}")
    void delete(@PathVariable Integer id)
    {
        Optional<Appointment> app = appRep.findById(id);
        app.ifPresent(appointment -> appRep.delete(appointment));
    }

    /* Create appointment
    @PostMapping("/appointment")
    Appointment create(
            @RequestParam(required = false) LocalDateTime dateRDV,
            @RequestParam(required = false)String objectRDV
    )
    {
        Appointment appointment = new Appointment();

        if (dateRDV != null) {
            appointment.setDateScheduled(dateRDV);
        }
        if(objectRDV != null && !objectRDV.isEmpty())
        {
            appointment.setPurpose(objectRDV);
        }
        appRep.save(appointment);
        return appointment;
    }
     */
    @PostMapping("/appointment")
    Appointment addAppointment(@RequestBody Appointment appointment){
        Boolean exists = appRep.existsByDateScheduledAndPatient(appointment.getDateScheduled(), appointment.getPatient());
        if(exists){
            throw new IllegalStateException("appointment for patient " + appointment.getPatient().getEmail() + " at " + appointment.getDateScheduled().toString() + " already exists" );
        }
        appRep.save(appointment);
        return appointment;
    }


    // Update appointment
    @PutMapping("/appointment/id={id}")
    Appointment update(
            @PathVariable Integer id,
            @RequestBody Appointment appointment
    )
    {
        Optional<Appointment> fetchedAppointment = appRep.findById(id);
        if(fetchedAppointment.isPresent())
        {
            Appointment updatedAppointment = fetchedAppointment.get();
            if (appointment.getDateScheduled() != null) {
                updatedAppointment.setDateScheduled(appointment.getDateScheduled());
            }
            if(appointment.getPurpose() != null && appointment.getPurpose().length() > 0 && appointment.getPurpose() != updatedAppointment.getPurpose() )
            {
                updatedAppointment.setPurpose(appointment.getPurpose());
            }
            if(appointment.getPatient() != null){
                updatedAppointment.setPatient(appointment.getPatient());
            }
            if(appointment.getDoctor() != null){
                updatedAppointment.setDoctor(appointment.getDoctor());
            }
            appRep.save(updatedAppointment);
            System.out.println(updatedAppointment);
            return updatedAppointment;
        } else {
            throw new IllegalStateException("Appointment with id " + id + " does not exist.");
        }
    }
}