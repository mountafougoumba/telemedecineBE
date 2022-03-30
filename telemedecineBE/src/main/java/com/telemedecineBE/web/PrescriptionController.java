package com.telemedecineBE.web;

import com.telemedecineBE.dao.PrescriptionRepository;
import com.telemedecineBE.entities.Doctor;
import com.telemedecineBE.entities.Patient;
import com.telemedecineBE.entities.Prescriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrescriptionController {
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    PrescriptionController(PrescriptionRepository prescriptionRepository){
        this.prescriptionRepository = prescriptionRepository;
    }

    @GetMapping("/prescriptions")
    List<Prescriptions> getAllPrescriptions(){
        System.out.println("getAllPrescriptions");
        return prescriptionRepository.findAll();
    }

    @GetMapping("/prescription/id={id}")
    Prescriptions getPrescriptionById(@PathVariable(value = "id") Integer id){
        Boolean exists = prescriptionRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("prescription with id " + id + " does not exist");
        }
        System.out.println("getPrescriptionById");
        return prescriptionRepository.findById(id);
    }

    @GetMapping("/prescription/id={id}/doctor")
    Doctor getPrescriptionDoctor(@PathVariable(value = "id") Integer id){
        Boolean exists = prescriptionRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("prescription with id " + id + " does not exist");
        }
        System.out.println("getPrescriptionById");
        Prescriptions ps = prescriptionRepository.findById(id);
        System.out.println(ps.getDoctorPrescribed());
        return ps.getDoctorPrescribed();
    }

    @GetMapping("/prescription/id={id}/patient")
    Patient getPrescriptionPatient(@PathVariable(value = "id") Integer id){
        Boolean exists = prescriptionRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("prescription with id " + id + " does not exist");
        }
        System.out.println("getPrescriptionById");
        Prescriptions ps = prescriptionRepository.findById(id);
        return ps.getPatient();
    }

    @PostMapping("/prescription")
    Prescriptions newPrescription(@RequestBody Prescriptions prescriptions){
        Boolean exists = prescriptionRepository.existsById(prescriptions.getId());
        if(exists){
            throw new IllegalStateException("prescription with id " + prescriptions.getId() + " already exists");
        }
        System.out.println("newPrescription");
        prescriptionRepository.save(prescriptions);
        return prescriptions;
    }

    @DeleteMapping("/prescription/id={id}")
    void deletePrescriptionById(@PathVariable(value = "id") Integer id){
        Boolean exists = prescriptionRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("prescription with id " + id + " does not exist");
        }
        Prescriptions p = prescriptionRepository.findById(id);
        System.out.println("deletePrescriptionById");
        prescriptionRepository.delete(p);
    }

    @PutMapping("/prescription/id={id}")
    Prescriptions updatePrescriptionById(@PathVariable(value = "id")Integer id,
                                         @RequestBody Prescriptions prescriptions){
        Boolean exists = prescriptionRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("prescription with id " + id + " does not exist");
        }
        System.out.println("updatePrescriptionById");
        Prescriptions currentPrescription = prescriptionRepository.findById(id);

        if(prescriptions.getName() != null && prescriptions.getName().length() > 0 && currentPrescription.getName() != prescriptions.getName()){
            currentPrescription.setName(prescriptions.getName());
        }

        if(prescriptions.getDosages() != null && prescriptions.getDosages().length() > 0 && currentPrescription.getDosages() != prescriptions.getDosages()){
            currentPrescription.setDosages(prescriptions.getDosages());
        }

        if(prescriptions.getDescription() != null && prescriptions.getDescription().length() > 0 && currentPrescription.getDescription() != prescriptions.getDescription()){
            currentPrescription.setDescription(prescriptions.getDescription());
        }

        System.out.println(currentPrescription);
        prescriptionRepository.save(currentPrescription);
        return currentPrescription;
    }
}
