package com.telemedecineBE.web;

import com.telemedecineBE.dao.ConsultationRepository;
import com.telemedecineBE.entities.Consultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ConsultationController {

    private ConsultationRepository conRep;

    @Autowired
    ConsultationController(ConsultationRepository cr)
    {
        this.conRep = cr;
    }

    @GetMapping("/consultation/id={id}")
    Optional<Consultation> get(@PathVariable Integer id) {
        return conRep.findById(id);
    }

    @DeleteMapping("/consultation/id={id}")
    void delete(@PathVariable Integer id) {
        Optional<Consultation> consultation = conRep.findById(id);
        consultation.ifPresentOrElse(
                con -> conRep.delete(con),
                () -> {
                    throw new IllegalStateException("Consultation with id, " + id + " does not exist.");
                }
        );
    }

    @PostMapping("/consultation")
    Consultation post(
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String upload
    ) {
        Consultation consultation = new Consultation();
        /**
         * @todo Add consultation creation logic
         */
        conRep.save(consultation);
        return consultation;
    }
}
