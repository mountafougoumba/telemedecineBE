package com.telemedecineBE.web;

import com.telemedecineBE.dao.PrescriptionRepository;
import com.telemedecineBE.entities.Priscriptions;
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

    @GetMapping("/prescription")
    List<Priscriptions> getAllPrescriptions(){
        System.out.println("getAllPrescriptions");
        return prescriptionRepository.findAll();
    }

    @GetMapping("/prescription/id={id}")
    Priscriptions getPrescriptionById(@PathVariable(value = "id") Integer id){
        Boolean exists = prescriptionRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("prescription with id " + id + " does not exist");
        }
        System.out.println("getPrescriptionById");
        return prescriptionRepository.findById(id);
    }

    @PostMapping("/prescription")
    Priscriptions newPrescription(@RequestBody Priscriptions priscriptions){
        Boolean exists = prescriptionRepository.existsById(priscriptions.getId());
        if(exists){
            throw new IllegalStateException("prescription with id " + priscriptions.getId() + " already exists");
        }
        System.out.println("newPrescription");
        prescriptionRepository.save(priscriptions);
        return priscriptions;
    }

    @DeleteMapping("/prescription/id={id}")
    void deletePrescriptionById(@PathVariable(value = "id") Integer id){
        Boolean exists = prescriptionRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("prescription with id " + id + " does not exist");
        }
        System.out.println("deletePrescriptionById");
        prescriptionRepository.deleteById(id);
    }

    @PutMapping("/prescription/id={id}")
    Priscriptions updatePrescriptionById(@PathVariable(value = "id")Integer id,
                                         @RequestParam(required = false)String dosages,
                                         @RequestParam(required = false)String description){
        Boolean exists = prescriptionRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("prescription with id " + id + " does not exist");
        }
        System.out.println("updatePrescriptionById");
        Priscriptions priscriptions = prescriptionRepository.findById(id);

        if(dosages != null && dosages.length() > 0 && priscriptions.getDosages() != dosages){
            priscriptions.setDosages(dosages);
        }

        if(description != null && description.length() > 0 && priscriptions.getDescription() != description){
            priscriptions.setDescription(description);
        }
        System.out.println(priscriptions);
        prescriptionRepository.save(priscriptions);
        return priscriptions;
    }
}
