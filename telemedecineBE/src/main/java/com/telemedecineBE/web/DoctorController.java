package com.telemedecineBE.web;

import com.telemedecineBE.dao.DoctorRepository;
import com.telemedecineBE.dao.UserDao;
import com.telemedecineBE.entities.*;
import com.telemedecineBE.enumeration.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class DoctorController {

    private DoctorRepository doctorRepository;
    private UserDao userDao;

    @Autowired
    DoctorController(DoctorRepository doctorRepository, UserDao userDao) {
        this.doctorRepository = doctorRepository;
        this.userDao = userDao;
    }

    @GetMapping("/doctors")
    List<Doctor> getAll(){
        return doctorRepository.findAll();
    }

    @GetMapping("/doctor/id={id}")
    Doctor get(@PathVariable Integer id)
    {
        return doctorRepository.findById(id).get();
    }

    @DeleteMapping("/doctor/id={id}")
    void delete(@PathVariable Integer id)
    {
        Optional<Doctor> doc = doctorRepository.findById(id);
        doc.ifPresent(d -> doctorRepository.delete(d));
    }

    @GetMapping("doctor/id={id}/appointments")
    List<Appointment> getAppointments(@PathVariable(value = "id")Integer id){
        System.out.println("getDoctorAppointments");
        Boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Doctor with id " + id + " does not exist.");
        }
        Doctor doctor = doctorRepository.findById(id).get();
        return doctor.getAppointments();
    }

    @GetMapping("doctor/id={id}/address")
    Address getAddress(@PathVariable(value = "id")Integer id){
        System.out.println("getPatientAddress");
        Boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Patient with id " + id + " does not exist.");
        }
        Doctor doctor = doctorRepository.findById(id).get();
        return doctor.getOfficeAddress();
    }

    @GetMapping("doctor/id={id}/prescribed-prescriptions")
    List<Prescriptions> getPrescriptions(@PathVariable(value = "id")Integer id){
        System.out.println("getDoctorPrescribedPrescriptions");
        Boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Doctor with id " + id + " does not exist.");
        }
        Doctor doctor = doctorRepository.findById(id).get();
        return doctor.getPrescribedPrescriptions();
    }

    @GetMapping("doctor/id={id}/requests")
    List<Requests> getRequests(@PathVariable(value = "id")Integer id){
        System.out.println("getDoctorRequestedPrescriptions");
        Boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Doctor with id " + id + " does not exist.");
        }
        Doctor doctor = doctorRepository.findById(id).get();
        return doctor.getRequests();
    }

    @GetMapping("doctor/id={id}/patients")
    List<Patient> getPatients(@PathVariable(value = "id")Integer id){
        System.out.println("getDoctorPatients");
        Boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Doctor with id " + id + " does not exist.");
        }
        Doctor doctor = doctorRepository.findById(id).get();
        return doctor.getPatients();
    }

    @PutMapping("doctor/id={id}/patients")
    Patient addPatient(@PathVariable(value = "id")Integer id, @RequestBody Patient patient){
        System.out.println("getDoctorPatients");
        Boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Doctor with id " + id + " does not exist.");
        }
        Doctor doctor = doctorRepository.findById(id).get();
        doctor.addPatient(patient);
        doctorRepository.save(doctor);
        return patient;
    }

    @PutMapping("doctor/id={id}/patients-remove")
    Patient removePatient(@PathVariable(value = "id")Integer id, @RequestBody Patient patient){
        System.out.println("removeDoctorPatients");
        Boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Doctor with id " + id + " does not exist.");
        }
        Doctor doctor = doctorRepository.findById(id).get();
        doctor.removePatient(patient);
        doctorRepository.save(doctor);
        return patient;
    }

    @PutMapping("/doctor/id={id}/add-address")
    Address addOfficeAddress(@PathVariable Integer id,
                              @RequestBody Address address){
        System.out.println(address);
        Boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Patient with id " + id + " does not exist.");
        }
        Doctor doctor = doctorRepository.findById(id).get();
        doctor.setOfficeAddress(address);
        doctorRepository.save(doctor);
        return address;
    }

    @PostMapping("/doctor")
    Doctor create(@RequestBody Doctor doc){
        doc.setUserType(UserType.DOCTOR);
        doc.setUserName(doc.getEmail());
        doc.setUserpassword(doc.getUserpassword());
        doctorRepository.save(doc);
        return doc;
    }

    /*
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
    */
    @PutMapping("/doctor/id={id}")
    Doctor updateDoctorById(@PathVariable(value = "id")Integer id,
                        @RequestBody Doctor doctor){
        Boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Doctor with id " + id + " does not exist.");
        }
        Doctor currentDoctor = doctorRepository.findById(id).get();

        if(doctor.getLname() != null && doctor.getLname().length() > 0 && doctor.getLname() != currentDoctor.getLname()){
            currentDoctor.setLname(doctor.getLname());
        }

        if(doctor.getFname() != null && doctor.getFname().length() > 0 && doctor.getFname() != currentDoctor.getFname()){
            currentDoctor.setFname(doctor.getFname());
        }

        if(doctor.getUserName() != null && doctor.getUserName() .length() > 0 && doctor.getUserName()  != currentDoctor.getUserName() && !userDao.existsByUserName(doctor.getUserName())){
            currentDoctor.setUserName(doctor.getUserName() );
        }

        if(doctor.getUserpassword() != null && doctor.getUserpassword().length() > 0 && doctor.getUserpassword() != currentDoctor.getUserpassword()){
            currentDoctor.setUserpassword(doctor.getUserpassword());
        }

        if(doctor.getUserType() != null && doctor.getUserType() != currentDoctor.getUserType()){
            currentDoctor.setUserType(doctor.getUserType());
        }

        if(doctor.getEmail() != null && doctor.getEmail().length() > 0 && doctor.getEmail() != currentDoctor.getEmail() && !userDao.existsByEmail(doctor.getEmail())){
            currentDoctor.setEmail(doctor.getEmail());
        }

        if(doctor.getCellphone() != null && doctor.getCellphone().length() > 0 && doctor.getCellphone() != currentDoctor.getCellphone() && !userDao.existsByCellphone(doctor.getCellphone())){
            currentDoctor.setCellphone(doctor.getCellphone());
        }

        if(doctor.getOfficeName() != null && doctor.getOfficeName().length() > 0  && doctor.getOfficeName() != currentDoctor.getOfficeName()){
            currentDoctor.setOfficeName(doctor.getOfficeName());
        }

        if(doctor.getSpecialty() != null && doctor.getSpecialty().length() > 0 && doctor.getSpecialty() != currentDoctor.getSpecialty()){
            currentDoctor.setSpecialty(doctor.getSpecialty());
        }

        if(doctor.getState() != null && doctor.getState() > 0 && doctor.getState() != currentDoctor.getState()){
            currentDoctor.setState(doctor.getState());
        }

        System.out.println("updateDoctorById");
        doctorRepository.save(currentDoctor);
        return currentDoctor;
    }

}
