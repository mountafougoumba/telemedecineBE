package com.telemedecineBE.web;

import com.telemedecineBE.dao.MedicalHistoryRepository;
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
    @GetMapping("/medical_history")
    List<MedicalHistory> getAllMedicalHistory(){
        System.out.println("getAllMedicalHistory");
        return medicalHistoryRepository.findAll();
    }

    //get medical history by name
    @GetMapping("/medical_history/name={name}")
    MedicalHistory getMedicalHistoryByName(@PathVariable(value="name")String name){
        Boolean exists = medicalHistoryRepository.existsByNom(name);
        if(!exists){
            throw new IllegalStateException("Medical history with the name " + name + " does not exist.");
        }
        System.out.println("getMedicalHistoryByName");
        MedicalHistory medication = medicalHistoryRepository.findByNom(name);
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
        Boolean exists = medicalHistoryRepository.existsByNom(medicalHistory.getNom());
        if(exists){
            throw new IllegalStateException("Medical history with name " + medicalHistory.getNom() + " already exists.");
        }
        medicalHistoryRepository.save(medicalHistory);
        System.out.println("newMedicalHistory");
        return medicalHistory;
    }

    //delete medical history by name
    @DeleteMapping("/medical_history/name={name}")
    void deleteMedicalHistoryByName(@PathVariable(value = "name") String name){
        Boolean exists = medicalHistoryRepository.existsByNom(name);
        if(!exists){
            throw new IllegalStateException("Medical history with name " + name + " does not exist.");
        }
        System.out.println("deleteMedicalHistoryByName");
        medicalHistoryRepository.deleteByNom(name);
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
            @RequestParam(required = false)String nom,
            @RequestParam(required = false)String medecinTraitant,
            @RequestParam(required = false)String dateSurvenance,
            @RequestParam(required = false)Integer state
    ){
        Boolean exists = medicalHistoryRepository.existsByNom(name);
        if(!exists){
            throw new IllegalStateException("Medical history with name " + name + " does not exist.");
        }
        MedicalHistory medicalHistory = medicalHistoryRepository.findByNom(name);

        if(nom != null && nom.length() > 0 && medicalHistory.getNom() != nom){
            medicalHistory.setNom(nom);
        }

        if(medecinTraitant != null && medecinTraitant.length() > 0 && medicalHistory.getMedecinTraitant() != medecinTraitant){
            medicalHistory.setMedecinTraitant(medecinTraitant);
        }

        if(dateSurvenance != null && dateSurvenance.length() > 0 && medicalHistory.getDateSurvenance() != dateSurvenance){
            medicalHistory.setDateSurvenance(dateSurvenance);
        }

        if(state != null && state > 0 && state != medicalHistory.getState()){
            medicalHistory.setState(state);
        }

        System.out.println("updateMedicalHistory\n" + medicalHistory);
        medicalHistoryRepository.save(medicalHistory);
        return medicalHistory;
    }

    //update medical history by id
    @PutMapping("/medical_history/id={id}")
    MedicalHistory updateMedicalHistoryById(
            @PathVariable(value = "id") Integer id,
            @RequestParam(required = false)String nom,
            @RequestParam(required = false)String medecinTraitant,
            @RequestParam(required = false)String dateSurvenance,
            @RequestParam(required = false)Integer state
    ){
        Boolean exists = medicalHistoryRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Medical History with id " + id + " does not exist.");
        }
        MedicalHistory medicalHistory = medicalHistoryRepository.findById(id);

        if(nom != null && nom.length() > 0 && medicalHistory.getNom() != nom){
            medicalHistory.setNom(nom);
        }

        if(medecinTraitant != null && medecinTraitant.length() > 0 && medicalHistory.getMedecinTraitant() != medecinTraitant){
            medicalHistory.setMedecinTraitant(medecinTraitant);
        }

        if(dateSurvenance != null && dateSurvenance.length() > 0 && medicalHistory.getDateSurvenance() != dateSurvenance){
            medicalHistory.setDateSurvenance(dateSurvenance);
        }

        if(state != null && state > 0 && state != medicalHistory.getState()){
            medicalHistory.setState(state);
        }

        System.out.println("updateMedicalHistory\n" + medicalHistory);
        medicalHistoryRepository.save(medicalHistory);
        return medicalHistory;
    }
}
