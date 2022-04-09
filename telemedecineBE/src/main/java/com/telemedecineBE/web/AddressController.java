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

    @GetMapping("/addresses")
    List<Address> getAllAddresses(){
        System.out.println("getAllAddresses");
        return addressRepository.findAll();
    }

    @GetMapping("/address/id={id}")
    Address getAddressById(@PathVariable(value = "id") Integer id){
        Boolean exists = addressRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("address with id " + id + " does not exist");
        }
        System.out.println("getAddressById");
        return addressRepository.findById(id);
    }

    @GetMapping("/address/streetAddress={streetAddress}")
    Address getAddressByStreetAddress(@PathVariable(value = "streetAddress")String streetAddress){
        Boolean exists = addressRepository.existsByStreetAddress(streetAddress);
        if(!exists){
            throw new IllegalStateException("address with street address " + streetAddress + " does not exist");
        }
        System.out.println("getAddressByStreetAddress");
        return addressRepository.findByStreetAddress(streetAddress);
    }

    @GetMapping("/addresses/city={city}")
    List<Address> getAddressesByCity(@PathVariable(value = "city") String city){
        Boolean exists = addressRepository.existsByCity(city);
        if(!exists){
            throw new IllegalStateException("No address in city " + city + " exists.");
        }
        System.out.println("getAddressesByCity");
        return addressRepository.findAddressesByCity(city);
    }

    @GetMapping("/addresses/zipcode={zipcode}")
    List<Address> getAddressesByZipcode(@PathVariable(value = "zipcode") String zipcode){
        Boolean exists = addressRepository.existsByZipcode(zipcode);
        if(!exists){
            throw new IllegalStateException("No address in zipcode " + zipcode + " exists.");
        }
        System.out.println("getAddressesByZipcode");
        return addressRepository.findAddressesByZipcode(zipcode);
    }

    @GetMapping("/addresses/usState={usState}")
    List<Address> getAddressesByUsState(@PathVariable(value = "usState") String usState){
        Boolean exists = addressRepository.existsByUsState(usState);
        if(!exists){
            throw new IllegalStateException("No address in US state " + usState + " exists.");
        }
        System.out.println("getAddressesByUSState");
        return addressRepository.findAddressesByUsState(usState);
    }

    @PostMapping("/address")
    Address newAddress(@RequestBody Address address){
        addressRepository.save(address);
        System.out.println("newAddress");
        return address;
    }
    
    @PostMapping("/addresses")
    List<Address> multipleNewAddresses(@RequestBody List<Address> addresses){
        for (Address a:
             addresses) {
            if(addressRepository.existsByStreetAddress(a.getStreetAddress())){
                throw new IllegalStateException("Address with street address " + a.getStreetAddress() + " already exists.");
            }
        }
        System.out.println("multipleNewAddresses");
        addressRepository.saveAll(addresses);
        return addresses;
    }

    @DeleteMapping("/address/id={id}")
    void deleteAddressById(@PathVariable(value = "id")Integer id){
        Boolean exists = addressRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Address with id " + id + " does not exist.");
        }
        System.out.println("deleteAddressById");
        addressRepository.deleteById(id);
    }

    @DeleteMapping("/address/streetAddress={streetAddress}")
    void deleteAddressByStreetAddress(@PathVariable(value = "streetAddress")String streetAddress){
        Boolean exists = addressRepository.existsByStreetAddress(streetAddress);
        if(!exists){
            throw new IllegalStateException("Address with street address " + streetAddress + " does not exist.");
        }
        System.out.println("deleteAddressByStreetAddress");
        addressRepository.deleteByStreetAddress(streetAddress);
    }

    @PutMapping("/address/id={id}")
    Address updateAddressById(@PathVariable(value = "id") Integer id,
                              @RequestBody Address address){
        Boolean exists = addressRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("address with id " + id + " does not exist");
        }
        Address currentAddress = addressRepository.findById(id);

        if(address.getZipcode() != null && address.getZipcode().length() > 0 && currentAddress.getZipcode() != address.getZipcode()){
            currentAddress.setZipcode(address.getZipcode());
        }

        if(address.getStreetAddress() != null && address.getStreetAddress().length() > 0 && currentAddress.getStreetAddress() != address.getStreetAddress()){
            currentAddress.setStreetAddress(address.getStreetAddress());
        }

        if(address.getCity() != null && address.getCity().length() > 0 && currentAddress.getCity() != address.getCity()){
            currentAddress.setCity(address.getCity());
        }

        if(address.getUsState() != null && address.getUsState().length() > 0 && currentAddress.getUsState() != address.getUsState()){
            currentAddress.setUsState(address.getUsState());
        }

        System.out.println("updateAddressById\n" + currentAddress);
        addressRepository.save(currentAddress);
        return currentAddress;
    }

    /*
    @PutMapping("/address/id={id}")
    Address updateAddressById(@PathVariable(value = "id") Long id,
                              @RequestParam(required = false)String zipcode,
                              @RequestParam(required = false)String streetAddress,
                              @RequestParam(required = false)String city,
                              @RequestParam(required = false)String usState){
        Boolean exists = addressRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("address with id " + id + " does not exist");
        }
        Address address = addressRepository.findById(id);

        if(zipcode != null && zipcode.length() > 0 && address.getZipcode() != zipcode){
            address.setZipcode(zipcode);
        }

        if(streetAddress != null && streetAddress.length() > 0 && address.getStreetAddress() != streetAddress){
            address.setStreetAddress(streetAddress);
        }

        if(city != null && city.length() > 0 && address.getCity() != city){
            address.setCity(city);
        }

        if(usState != null && usState.length() > 0 && address.getUsState() != usState){
            address.setUsState(usState);
        }

        System.out.println("updateAddressById\n" + address);
        addressRepository.save(address);
        return address;
    }

     */

    @PutMapping("/address/streetAddress={streetAddress}")
    Address updateAddressByStreetAddress(@PathVariable(value = "streetAddress") String searchStreetAddress,
                                         @RequestParam(required = false)String zipcode,
                                         @RequestParam(required = false)String streetAddress,
                                         @RequestParam(required = false)String city,
                                         @RequestParam(required = false)String usState){
        Boolean exists = addressRepository.existsByStreetAddress(searchStreetAddress);
        if(!exists){
            throw new IllegalStateException("address with street address " + searchStreetAddress + " does not exist");
        }
        Address address = addressRepository.findByStreetAddress(searchStreetAddress);

        if(zipcode != null && zipcode.length() > 0 && address.getZipcode() != zipcode){
            address.setZipcode(zipcode);
        }

        if(streetAddress != null && streetAddress.length() > 0 && address.getStreetAddress() != streetAddress){
            address.setStreetAddress(streetAddress);
        }

        if(city != null && city.length() > 0 && address.getCity() != city){
            address.setCity(city);
        }

        if(usState != null && usState.length() > 0 && address.getUsState() != usState) {
            address.setUsState(usState);
        }

        System.out.println("updateAddressByStreetAddress\n" + address);
        addressRepository.save(address);
        return address;
    }

}
