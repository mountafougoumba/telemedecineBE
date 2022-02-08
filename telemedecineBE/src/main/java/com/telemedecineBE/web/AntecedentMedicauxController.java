package com.telemedecineBE.web;

import com.telemedecineBE.dao.AntecedentMedicauxRepository;
import com.telemedecineBE.entities.AntecedentMedicaux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AntecedentMedicauxController {

    private AntecedentMedicauxRepository antecedentMedicauxRepository;

    @Autowired
    AntecedentMedicauxController(AntecedentMedicauxRepository antecedentMedicauxRepository){
        this.antecedentMedicauxRepository = antecedentMedicauxRepository;
    }

    //get all medications
    @GetMapping("/medication")
    List<AntecedentMedicaux> getAllMedications(){
        System.out.println("getAllMedications");
        return antecedentMedicauxRepository.findAll();
    }

    //get medication by name
    @GetMapping("/medication/name={name}")
    AntecedentMedicaux getMedicationByName(@PathVariable(value="name")String name){
        Boolean exists = antecedentMedicauxRepository.existsByNom(name);
        if(!exists){
            throw new IllegalStateException("Medication with the name " + name + " does not exist.");
        }
        System.out.println("getMedicationByName");
        AntecedentMedicaux medication = antecedentMedicauxRepository.findByNom(name);
        return medication;
    }

    //get medication by id
    @GetMapping("/medication/id={id}")
    AntecedentMedicaux getMedicationById(@PathVariable(value="id")Integer id){
        Boolean exists = antecedentMedicauxRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Medication with id " + id + " does not exist");
        }
        System.out.println("getMedicationById");
        AntecedentMedicaux medication = antecedentMedicauxRepository.findById(id);
        return medication;
    }

    //add new medication
    @PostMapping("/medication")
    AntecedentMedicaux newMedication(@RequestBody AntecedentMedicaux antecedentMedicaux){
        Boolean exists = antecedentMedicauxRepository.existsByNom(antecedentMedicaux.getNom());
        if(exists){
            throw new IllegalStateException("Medication with name " + antecedentMedicaux.getNom() + " already exists.");
        }
        antecedentMedicauxRepository.save(antecedentMedicaux);
        System.out.println("newMedication");
        return antecedentMedicaux;
    }

    //delete medication by name
    @DeleteMapping("/medication/name={name}")
    void deleteMedicationByName(@PathVariable(value = "name") String name){
        Boolean exists = antecedentMedicauxRepository.existsByNom(name);
        if(!exists){
            throw new IllegalStateException("Medication with name " + name + " does not exist.");
        }
        System.out.println("deleteMedicationByName");
        antecedentMedicauxRepository.deleteByNom(name);
    }

    //delete medicaiton by id
    @DeleteMapping("/medication/id={id}")
    void deleteMedicationById(@PathVariable(value = "id") Integer id){
        Boolean exists = antecedentMedicauxRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Medication with id " + id + "does not exist.");
        }
        System.out.println("deleteMedicationById");
        antecedentMedicauxRepository.deleteById(id);
    }

    //update medication by name
    @PutMapping("/medication/name={name}")
    AntecedentMedicaux updateMedicationByName(
            @PathVariable(value = "name") String name,
            @RequestParam(required = false)String nom,
            @RequestParam(required = false)String medecinTraitant,
            @RequestParam(required = false)String dateSurvenance
    ){
        Boolean exists = antecedentMedicauxRepository.existsByNom(name);
        if(!exists){
            throw new IllegalStateException("Medication with name " + name + " does not exist.");
        }
        AntecedentMedicaux antecedentMedicaux = antecedentMedicauxRepository.findByNom(name);

        if(nom != null && nom.length() > 0 && antecedentMedicaux.getNom() != nom){
            antecedentMedicaux.setNom(nom);
        }

        if(medecinTraitant != null && medecinTraitant.length() > 0 && antecedentMedicaux.getMedecinTraitant() != medecinTraitant){
            antecedentMedicaux.setMedecinTraitant(medecinTraitant);
        }

        if(dateSurvenance != null && dateSurvenance.length() > 0 && antecedentMedicaux.getDateSurvenance() != dateSurvenance){
            antecedentMedicaux.setDateSurvenance(dateSurvenance);
        }
        System.out.println("updateMedication\n" + antecedentMedicaux);
        antecedentMedicauxRepository.save(antecedentMedicaux);
        return antecedentMedicaux;
    }

    //update medication by id
    @PutMapping("/medication/id={id}")
    AntecedentMedicaux updateMedicationById(
            @PathVariable(value = "id") Integer id,
            @RequestParam(required = false)String nom,
            @RequestParam(required = false)String medecinTraitant,
            @RequestParam(required = false)String dateSurvenance
    ){
        Boolean exists = antecedentMedicauxRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Medication with id " + id + " does not exist.");
        }
        AntecedentMedicaux antecedentMedicaux = antecedentMedicauxRepository.findById(id);

        if(nom != null && nom.length() > 0 && antecedentMedicaux.getNom() != nom){
            antecedentMedicaux.setNom(nom);
        }

        if(medecinTraitant != null && medecinTraitant.length() > 0 && antecedentMedicaux.getMedecinTraitant() != medecinTraitant){
            antecedentMedicaux.setMedecinTraitant(medecinTraitant);
        }

        if(dateSurvenance != null && dateSurvenance.length() > 0 && antecedentMedicaux.getDateSurvenance() != dateSurvenance){
            antecedentMedicaux.setDateSurvenance(dateSurvenance);
        }
        System.out.println("updateMedication\n" + antecedentMedicaux);
        antecedentMedicauxRepository.save(antecedentMedicaux);
        return antecedentMedicaux;
    }
}
