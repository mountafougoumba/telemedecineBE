package com.telemedecineBE.web;

import com.telemedecineBE.dao.AppointmentRepository;
import com.telemedecineBE.entities.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AppointmentController {
    private AppointmentRepository appRep;

    @Autowired
    AppointmentController(AppointmentRepository ar){
        this.appRep = ar;
    }

    @GetMapping("/appointment")
    List<Appointment> getAllAppointments(){
        return appRep.findAll();
    }

    @GetMapping("/appointment/id={id}")
    Optional<Appointment> get(@PathVariable Integer id)
    {
        return appRep.findById(id);
    }

    @DeleteMapping("/appointment/id={id}")
    void delete(@PathVariable String id)
    {
        Optional<Appointment> app = appRep.findById(id);
        app.ifPresent(appointment -> appRep.delete(appointment));
    }

    // Create appointment
    @PostMapping("/appointment")
    Appointment create(@RequestBody Appointment app){
        appRep.save(app);
        return app;
    }

    // Update appointment
    @PutMapping("/appointment/id={id}")
    Appointment update(
            @PathVariable String id,
            @RequestParam(required = false) Date dateRDV,
            @RequestParam(required = false)String heureRDV,
            @RequestParam(required = false)String objectRDV
    )
    {
        Optional<Appointment> fetchedAppointment = appRep.findById(id);
        if(fetchedAppointment.isPresent())
        {
            Appointment updatedAppointment = fetchedAppointment.get();
            updatedAppointment.setDateRDV(dateRDV);
            updatedAppointment.setHeureRDV(heureRDV);
            updatedAppointment.setObjectRDV(objectRDV);
            return updatedAppointment;
        } else {
            throw new IllegalStateException("Appointment with id " + id + " does not exist.");
        }
    }
}
