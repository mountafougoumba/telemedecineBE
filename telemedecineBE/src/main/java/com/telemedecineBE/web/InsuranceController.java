package com.telemedecineBE.web;

import com.telemedecineBE.dao.InsuranceRepository;
import com.telemedecineBE.entities.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class InsuranceController {

    private InsuranceRepository insuranceRepository;

    @Autowired
    InsuranceController(InsuranceRepository insuranceRepository){
        this.insuranceRepository = insuranceRepository;
    }

    //get all insurance
    @GetMapping("/insurance")
    List<Insurance> getAllInsurance(){
        System.out.println("getAllInsurance");
        return insuranceRepository.findAll();
    }

    //get insurance by name
    @GetMapping("/insurance/name={name}")
    Insurance getInsuranceByName(@PathVariable(value = "name") String name){
        Boolean exists = insuranceRepository.existsByName(name);
        if(!exists){
            throw new IllegalStateException("insurance with name " + name + "does not exist.");
        }
        Insurance insurance = insuranceRepository.findByName(name);
        System.out.println("getInsuranceByName");
        return insurance;
    }

    //get insurance by id
    @GetMapping("/insurance/id={id}")
    Insurance getInsuranceById(@PathVariable(value = "id") Integer id){
        Boolean exists = insuranceRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("insurance with id " + id + "does not exist.");
        }
        Insurance insurance = insuranceRepository.findById(id);
        System.out.println("getInsuranceById");
        return insurance;
    }

    //add new Insurance object to repository
    @PostMapping("/insurance")
    Insurance newInsurance(@RequestBody Insurance insurance){
        Boolean exists = insuranceRepository.existsByName(insurance.getName());
        if(exists){
            throw new IllegalStateException("Insurance with name " + insurance.getName() + " already exists");
        }
        insuranceRepository.save(insurance);
        System.out.println("newInsurance");
        return insurance;
    }

    //delete insurance by name
    @DeleteMapping("/insurance/name={name}")
    void deleteInsuranceByName(@PathVariable(value = "name") String name) {
        Boolean exists = insuranceRepository.existsByName(name);
        if (!exists) {
            throw new IllegalStateException("Insurance with name " + name + " does not exist.");
        }
        System.out.println("deleteInsuranceByName");
        insuranceRepository.deleteByName(name);
    }

    //delete insurance by id
    @DeleteMapping("/insurance/id={id}")
    void deleteInsuranceById(@PathVariable(value = "id") Integer id) {
        Boolean exists = insuranceRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Insurance with id " + id + " does not exist.");
        }
        System.out.println("deleteInsuranceById");
        insuranceRepository.deleteById(id);
    }

    //update by name
    @PutMapping("/insurance/name={name}")
    Insurance updateInsuranceByName(
            @PathVariable(value = "name") String name,
            @RequestParam(required = false)String nom,
            @RequestParam(required = false)Boolean couvreToutSoins,
            @RequestParam(required = false)Boolean couvreFraisConsultation,
            @RequestParam(required = false)Integer state,
            @RequestParam(required = false)Double percentageAssurance){
        System.out.println("updateInsurance");
        Boolean exists = insuranceRepository.existsByName(name);
        if(!exists){
            throw new IllegalStateException(
                    "Insurance with name " + nom + "does not exist."
            );
        }
        Insurance insurance = insuranceRepository.findByName(name);

        if(nom != null && nom.length() > 0 &&
        insurance.getName() != name){
            insurance.setName(nom);
        }

        if(insurance.getAllCareCoverage() != couvreToutSoins && couvreToutSoins != null){
            insurance.setAllCareCoverage(couvreToutSoins);
        }

        if(insurance.getConsultingFeesCovered() != couvreFraisConsultation && couvreFraisConsultation != null){
            insurance.setConsultingFeesCovered(couvreFraisConsultation);
        }

        if(state != null && state > 0 && insurance.getState() != state){
            insurance.setState(state);
        }

        if(percentageAssurance != null && percentageAssurance >= 0
            && insurance.getPercentInsured() != percentageAssurance){
            insurance.setPercentInsured(percentageAssurance);
        }
        System.out.println(insurance);
        insuranceRepository.save(insurance);
        return insurance;
    }

    //update by id
    @PutMapping("/insurance/id={id}")
    Insurance updateInsuranceById(
            @PathVariable(value = "id") Integer id,
            @RequestParam(required = false)String nom,
            @RequestParam(required = false)Boolean couvreToutSoins,
            @RequestParam(required = false)Boolean couvreFraisConsultation,
            @RequestParam(required = false)Integer state,
            @RequestParam(required = false)Double percentageAssurance){
        System.out.println("updateInsurance");
        Boolean exists = insuranceRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "Insurance with id " + id + "does not exist."
            );
        }
        Insurance insurance = insuranceRepository.findById(id);

        if(nom != null && nom.length() > 0 && insurance.getName() != nom){
            insurance.setName(nom);
        }

        if(insurance.getAllCareCoverage() != couvreToutSoins && couvreToutSoins != null){
            insurance.setAllCareCoverage(couvreToutSoins);
        }

        if(insurance.getConsultingFeesCovered() != couvreFraisConsultation && couvreFraisConsultation != null){
            insurance.setConsultingFeesCovered(couvreFraisConsultation);
        }

        if(state != null && state > 0 && insurance.getState() != state){
            insurance.setState(state);
        }

        if(percentageAssurance != null && percentageAssurance >= 0
                && insurance.getPercentInsured() != percentageAssurance){
            insurance.setPercentInsured(percentageAssurance);
        }
        System.out.println(insurance);
        insuranceRepository.save(insurance);
        return insurance;
    }

}
