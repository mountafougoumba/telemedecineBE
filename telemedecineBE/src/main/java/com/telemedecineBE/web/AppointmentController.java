package com.telemedecineBE.web;

import com.telemedecineBE.dao.AppointmentRepository;
import com.telemedecineBE.entities.Appointment;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.swing.text.html.Option;
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
    Optional<Appointment> get(@PathVariable String id)
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
    @PostMapping("/insurance")
    Appointment create(@RequestBody Appointment app){
        appRep.save(app);
        return app;
    }

    // Update appointment
    @PutMapping("/insurance/id={id}")
    Optional<Appointment> update(@PathVariable String id)
    {
        Optional<Appointment> app = appRep.findById(id);
        if(app.isPresent())
        {
            // Save updated data
            return app;
        } else {
            throw new IllegalStateException("Appointment with id " + id + " does not exist.");
        }
    }
}
