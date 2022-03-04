package com.telemedecineBE.web;

import com.telemedecineBE.dao.DoctorRepository;
import com.telemedecineBE.entities.Doctor;
import com.telemedecineBE.enumeration.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class DoctorController {

    private DoctorRepository doctorRepository;

    @Autowired
    DoctorController(DoctorRepository doctorRepository) {this.doctorRepository = doctorRepository;}

    @GetMapping("/doctor")
    List<Doctor> getAll(){
        return doctorRepository.findAll();
    }

    @GetMapping("/doctor/id={id}")
    Optional<Doctor> get(@PathVariable Integer id)
    {
        return doctorRepository.findById(id);
    }

    @DeleteMapping("/doctor/id={id}")
    void delete(@PathVariable Integer id)
    {
        Optional<Doctor> doc = doctorRepository.findById(id);
        doc.ifPresent(d -> doctorRepository.delete(d));
    }

    @PostMapping("/doctor")
    Doctor create(@RequestBody Doctor doc){
        doc.setUserType(UserType.DOCTOR);
        doc.setUserName(doc.getEmail());
        doc.setUserpassword(doc.getUserpassword());
        doctorRepository.save(doc);
        return doc;
    }

    @PutMapping("/doctor/id={id}")
    Doctor update(
            @PathVariable Integer id,
            @RequestParam(required = false) String fname,
            @RequestParam(required = false) String lname,
            @RequestParam(required = false) String office,
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone
    )
    {
        Optional<Doctor> fetchedDoctor = doctorRepository.findById(id);
        if(fetchedDoctor.isPresent())
        {
            Doctor updatedDoctor = fetchedDoctor.get();
            updatedDoctor.setFname(fname);
            updatedDoctor.setLname(lname);
            updatedDoctor.setOfficeName(office);
            updatedDoctor.setSpecialty(specialty);
            updatedDoctor.setUserpassword(password);
            updatedDoctor.setEmail(email);
            updatedDoctor.setCellphone(phone);
            return updatedDoctor;
        } else {
            throw new IllegalStateException("Doctor with id " + id + " does not exist.");
        }
    }

}
