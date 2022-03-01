package com.telemedecineBE.web;

import com.telemedecineBE.dao.MedicalHistoryRepository;
import com.telemedecineBE.entities.Address;
import com.telemedecineBE.entities.MedicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalHistoryController {

    private MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    MedicalHistoryController(MedicalHistoryRepository medicalHistoryRepository){
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    //get all medical history (conditions)
    @GetMapping("/medical_histories")
    List<MedicalHistory> getAllMedicalHistory(){
        System.out.println("getAllMedicalHistory");
        return medicalHistoryRepository.findAll();
    }

    //get medical history by name
    @GetMapping("/medical_history/name={name}")
    MedicalHistory getMedicalHistoryByName(@PathVariable(value="name")String name){
        Boolean exists = medicalHistoryRepository.existsByName(name);
        if(!exists){
            throw new IllegalStateException("Medical history with the name " + name + " does not exist.");
        }
        System.out.println("getMedicalHistoryByName");
        MedicalHistory medication = medicalHistoryRepository.findByName(name);
        return medication;
    }

    //get medical history by id
    @GetMapping("/medical_history/id={id}")
    MedicalHistory getMedicalHistoryById(@PathVariable(value="id")Integer id){
        Boolean exists = medicalHistoryRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Medical history with id " + id + " does not exist");
        }
        System.out.println("getMedicalHistoryById");
        MedicalHistory medication = medicalHistoryRepository.findById(id);
        return medication;
    }

    //add new medical history
    @PostMapping("/medical_history")
    MedicalHistory newMedicalHistory(@RequestBody MedicalHistory medicalHistory){
        Boolean exists = medicalHistoryRepository.existsByName(medicalHistory.getName());
        if(exists){
            throw new IllegalStateException("Medical history with name " + medicalHistory.getName() + " already exists.");
        }
        medicalHistoryRepository.save(medicalHistory);
        System.out.println("newMedicalHistory");
        return medicalHistory;
    }

    @PostMapping("/medical_histories")
    List<MedicalHistory> newMedicalHistories(@RequestBody List<MedicalHistory> medicalHistories){
        for (MedicalHistory mH:
                medicalHistories) {
            if(medicalHistoryRepository.existsByName(mH.getName())){
                throw new IllegalStateException("Medical History with  " + mH.getName() + " already exists.");
            }
        }
        System.out.println("newMedicalHistories");
        medicalHistoryRepository.saveAll(medicalHistories);
        return medicalHistories;
    }

    //delete medical history by name
    @DeleteMapping("/medical_history/name={name}")
    void deleteMedicalHistoryByName(@PathVariable(value = "name") String name){
        Boolean exists = medicalHistoryRepository.existsByName(name);
        if(!exists){
            throw new IllegalStateException("Medical history with name " + name + " does not exist.");
        }
        System.out.println("deleteMedicalHistoryByName");
        medicalHistoryRepository.deleteByName(name);
    }

    //delete medical history by id
    @DeleteMapping("/medical_history/id={id}")
    void deleteMedicalHistoryById(@PathVariable(value = "id") Integer id){
        Boolean exists = medicalHistoryRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Medical History with id " + id + " does not exist.");
        }
        System.out.println("deleteMedicalHistoryById");
        medicalHistoryRepository.deleteById(id);
    }

    //update medical history by name
    @PutMapping("/medical_history/name={name}")
    MedicalHistory updateMedicalHistoryByName(
            @PathVariable(value = "name") String name,
            @RequestParam(required = false)String newName,
            @RequestParam(required = false)String doctorDiagnosed,
            @RequestParam(required = false)String dateDiagnosed,
            @RequestParam(required = false)String description,
            @RequestParam(required = false)Integer state

    ){
        Boolean exists = medicalHistoryRepository.existsByName(name);
        if(!exists){
            throw new IllegalStateException("Medical history with name " + name + " does not exist.");
        }
        MedicalHistory medicalHistory = medicalHistoryRepository.findByName(name);

        if(newName != null && newName.length() > 0 && medicalHistory.getName() != newName){
            medicalHistory.setName(newName);
        }

        if(doctorDiagnosed != null && doctorDiagnosed.length() > 0 && medicalHistory.getDoctorDiagnosed() != doctorDiagnosed){
            medicalHistory.setDoctorDiagnosed(doctorDiagnosed);
        }

        if(dateDiagnosed != null && dateDiagnosed.length() > 0 && medicalHistory.getDateDiagnosed() != dateDiagnosed){
            medicalHistory.setDateDiagnosed(dateDiagnosed);
        }

        if(description != null && description.length() > 0 && medicalHistory.getDescription() != description){
            medicalHistory.setDescription(description);
        }

        if(state != null && state > 0 && state != medicalHistory.getState()){
            medicalHistory.setState(state);
        }

        System.out.println("updateMedicalHistoryByName\n" + medicalHistory);
        medicalHistoryRepository.save(medicalHistory);
        return medicalHistory;
    }

    //update medical history by id
    @PutMapping("/medical_history/id={id}")
    MedicalHistory updateMedicalHistoryById(
            @PathVariable(value = "id") Integer id,
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String doctorDiagnosed,
            @RequestParam(required = false)String dateDiagnosed,
            @RequestParam(required = false)String description,
            @RequestParam(required = false)Integer state
    ){
        Boolean exists = medicalHistoryRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Medical History with id " + id + " does not exist.");
        }
        MedicalHistory medicalHistory = medicalHistoryRepository.findById(id);

        if(name != null && name.length() > 0 && medicalHistory.getName() != name){
            medicalHistory.setName(name);
        }

        if(doctorDiagnosed != null && doctorDiagnosed.length() > 0 && medicalHistory.getDoctorDiagnosed() != doctorDiagnosed){
            medicalHistory.setDoctorDiagnosed(doctorDiagnosed);
        }

        if(dateDiagnosed != null && dateDiagnosed.length() > 0 && medicalHistory.getDateDiagnosed() != dateDiagnosed){
            medicalHistory.setDateDiagnosed(dateDiagnosed);
        }

        if(description != null && description.length() > 0 && medicalHistory.getDescription() != description){
            medicalHistory.setDescription(description);
        }

        if(state != null && state > 0 && state != medicalHistory.getState()){
            medicalHistory.setState(state);
        }

        System.out.println("updateMedicalHistoryById\n" + medicalHistory);
        medicalHistoryRepository.save(medicalHistory);
        return medicalHistory;
    }
}
