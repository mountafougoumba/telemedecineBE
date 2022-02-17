package com.telemedecineBE.web;

import com.telemedecineBE.dao.AddressRepository;
import com.telemedecineBE.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    private AddressRepository addressRepository;

    @Autowired
    AddressController(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @GetMapping("/address")
    List<Address> getAllAddresses(){
        System.out.println("getAllAddresses");
        return addressRepository.findAll();
    }

    @GetMapping("/address/id={id}")
    Address getAddressById(@PathVariable(value = "id") Long id){
        Boolean exists = addressRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("address with id " + id + " does not exist");
        }
        System.out.println("getAddressById");
        return addressRepository.findById(id);
    }

    @GetMapping("/address/line={line}")
    Address getAddressByLine(@PathVariable(value = "line")String line){
        Boolean exists = addressRepository.existsByLine(line);
        if(!exists){
            throw new IllegalStateException("address with line " + line + " does not exist");
        }
        System.out.println("getAddressByLine");
        return addressRepository.findByLine(line);
    }

    @PostMapping("/address")
    Address newAddress(@RequestBody Address address){
        Boolean exists = addressRepository.existsById(address.getId());
        if(exists){
            throw new IllegalStateException("Address with id " + address.getId() + " already exists.");
        }
        addressRepository.save(address);
        System.out.println("newAddress");
        return address;
    }

    @DeleteMapping("/address/id={id}")
    void deleteAddressById(@PathVariable(value = "id")Long id){
        Boolean exists = addressRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Address with id " + id + " does not exist.");
        }
        System.out.println("deleteAddressById");
        addressRepository.deleteById(id);
    }

    @DeleteMapping("/address/line={line}")
    void deleteAddressByLine(@PathVariable(value = "line")String line){
        Boolean exists = addressRepository.existsByLine(line);
        if(!exists){
            throw new IllegalStateException("Address with line " + line + " does not exist.");
        }
        System.out.println("deleteAddressByLine");
        addressRepository.deleteByLine(line);
    }

    @PutMapping("/address/id={id}")
    Address updateAddressById(@PathVariable(value = "id") Long id,
                              @RequestParam(required = false)String zipcode,
                              @RequestParam(required = false)String line){
        Boolean exists = addressRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("address with id " + id + " does not exist");
        }
        Address address = addressRepository.findById(id);

        if(zipcode != null && zipcode.length() > 0 && address.getZipcode() != zipcode){
            address.setZipcode(zipcode);
        }

        if(line != null && line.length() > 0 && address.getLine() != line){
            address.setLine(line);
        }
        System.out.println("updateAddressById\n" + address);
        addressRepository.save(address);
        return address;
    }

    @PutMapping("/address/line={line}")
    Address updateAddressByLine(@PathVariable(value = "line") String searchLine,
                              @RequestParam(required = false)String zipcode,
                              @RequestParam(required = false)String line){
        Boolean exists = addressRepository.existsByLine(searchLine);
        if(!exists){
            throw new IllegalStateException("address with line " + searchLine + " does not exist");
        }
        Address address = addressRepository.findByLine(searchLine);

        if(zipcode != null && zipcode.length() > 0 && address.getZipcode() != zipcode){
            address.setZipcode(zipcode);
        }

        if(line != null && line.length() > 0 && address.getLine() != line){
            address.setLine(line);
        }
        System.out.println("updateAddressByLine\n" + address);
        addressRepository.save(address);
        return address;
    }

}
