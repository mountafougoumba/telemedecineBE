package com.telemedecineBE.web;

import com.telemedecineBE.dao.HabilitationsRepository;
import com.telemedecineBE.entities.Habilitations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HabilitationController {

    @Autowired
	private final HabilitationsRepository habilitationsRepository;

    HabilitationController(HabilitationsRepository habilitationsRepository){
        this.habilitationsRepository = habilitationsRepository;
    }

    //get all habilitations
    @GetMapping("/habilitation")
    List<Habilitations> getAllHabilitations(){
        System.out.println("getAllHabilitations");
        return habilitationsRepository.findAll();
    }

    //get habilitation by id
    @GetMapping("/habilitation/id={id}")
    Habilitations getHabilitationById(@PathVariable(value="id") Integer id){
        Boolean exists = habilitationsRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Habilitation with id " + id + " does not exist.");
        }
        System.out.println("getHabilitationById");
        return habilitationsRepository.findById(id);
    }

    //get habilitation by name
    @GetMapping("/habilitation/name={name}")
    Habilitations getHabilitationByName(@PathVariable(value="name") String name){
        Boolean exists = habilitationsRepository.existsByNameHabilitation(name);
        if(!exists){
            throw new IllegalStateException("Habilitation with name " + name + " does not exist.");
        }
        System.out.println("getHabilitationByName");
        return habilitationsRepository.findByNameHabilitation(name);
    }

    //add new habilitation
    @PostMapping("/habilitation")
    Habilitations addHabilitation(@RequestBody Habilitations habilitations){
        Boolean exists = habilitationsRepository.existsByNameHabilitation(habilitations.getNameHabilitation());
        if(exists){
            throw new IllegalStateException("Habilitation with name " + habilitations.getNameHabilitation() + " already exists.");
        }
        System.out.println("addHabilitation");
        habilitationsRepository.save(habilitations);
        return habilitations;
    }

    @DeleteMapping("/habilitation/id={id}")
    void deleteHabilitationById(@PathVariable(value="id")Integer id){
        Boolean exists = habilitationsRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Habilitation with id " + id + " does not exist.");
        }
        System.out.println("deleteHabilitationById");
        habilitationsRepository.deleteById(id);
    }

    @DeleteMapping("/habilitation/name={name}")
    void deleteHabilitationByName(@PathVariable(value = "name")String name){
        Boolean exists = habilitationsRepository.existsByNameHabilitation(name);
        if(!exists){
            throw new IllegalStateException("Habilitation with name " + name + " does not exist.");
        }
        System.out.println("deleteHabilitionByName");
        habilitationsRepository.deleteByNameHabilitation(name);
    }

    @PutMapping("/habilitation/id={id}")
    Habilitations updateHabilitationById(@PathVariable(value="id")Integer id,
                                         @RequestParam(required = false)String nameHabilitation,
                                         @RequestParam(required = false)Integer state){
        Boolean exists = habilitationsRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Habilitation with id " + id + " does not exist.");
        }
        Habilitations habilitations = habilitationsRepository.findById(id);

        if(nameHabilitation != null && nameHabilitation.length() > 0 && habilitations.getNameHabilitation() != nameHabilitation){
            habilitations.setNameHabilitation(nameHabilitation);
        }

        if(state != null && state > 0 && habilitations.getState() != state){
            habilitations.setState(state);
        }
        System.out.println("updateHabilitionById\n" + habilitations);
        habilitationsRepository.save(habilitations);
        return habilitations;
    }

    @PutMapping("/habilitation/name={name}")
    Habilitations updateHabilitationByName(@PathVariable(value="name")String name,
                                         @RequestParam(required = false)String nameHabilitation,
                                         @RequestParam(required = false)Integer state){
        Boolean exists = habilitationsRepository.existsByNameHabilitation(name);
        if(!exists){
            throw new IllegalStateException("Habilitation with name " + name + " does not exist.");
        }
        Habilitations habilitations = habilitationsRepository.findByNameHabilitation(name);

        if(nameHabilitation != null && nameHabilitation.length() > 0 && habilitations.getNameHabilitation() != nameHabilitation){
            habilitations.setNameHabilitation(nameHabilitation);
        }

        if(state != null && state > 0 && habilitations.getState() != state){
            habilitations.setState(state);
        }
        System.out.println("updateHabilitionByName\n" + habilitations);
        habilitationsRepository.save(habilitations);
        return habilitations;
    }

}
