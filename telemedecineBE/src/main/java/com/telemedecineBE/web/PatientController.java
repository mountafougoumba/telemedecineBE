package com.telemedecineBE.web;

import com.telemedecineBE.dao.PatientRepository;
import com.telemedecineBE.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

	private final PatientRepository patientRepository;

	@Autowired
	public PatientController(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@GetMapping("/patients")
	List<Patient> getAllPatients(){
		System.out.println("getAllPatients");
		return patientRepository.findAll();
	}

	@GetMapping("/patient/id={id}")
	Patient getPatientById(@PathVariable(value = "id")Integer id){
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		System.out.println("getPatientById");
		return patientRepository.findById(id);
	}

	@GetMapping("/patient/email={email}")
	Patient getPatientByEmail(@PathVariable(value = "email")String email){
		Boolean exists = patientRepository.existsByEmail(email);
		if(!exists){
			throw new IllegalStateException("Patient with email " + email + " does not exist.");
		}
		System.out.println("getPatientByEmail");
		return patientRepository.findByEmail(email);
	}

	@GetMapping("/patient/tel={tel}")
	Patient getPatientByTel(@PathVariable(value = "tel")String phone){
		Boolean exists = patientRepository.existsByPhone(phone);
		if(!exists){
			throw new IllegalStateException("Patient with phone " + phone + " does not exist.");
		}
		System.out.println("getPatientByTel");
		return patientRepository.findByPhone(phone);
	}

	@PostMapping("/patient")
	Patient newPatient(@RequestBody Patient patient){
		Boolean exists = patientRepository.existsByEmail(patient.getEmail());
		Boolean exists2 = patientRepository.existsByPhone(patient.getPhone());
		if(exists){
			throw new IllegalStateException("Patient with email " + patient.getEmail() + " already exists.");
		} else if(exists2){
			throw new IllegalStateException("Patient with phone " + patient.getPhone() + " already exists.");
		}
		patientRepository.save(patient);
		System.out.println("newPatient");
		return patient;
	}

	@PostMapping("/patients")
	List<Patient> newPatients(@RequestBody List<Patient> patients){
		for (Patient p:
				patients) {
			if(patientRepository.existsByEmail(p.getEmail())){
				throw new IllegalStateException("Patient with email " + p.getEmail() + " already exists.");
			} else if(patientRepository.existsByPhone(p.getPhone())){
				throw new IllegalStateException("Patient with phone " + p.getPhone() + " already exists.");
			}
		}
		System.out.println("newPatients");
		patientRepository.saveAll(patients);
		return patients;
	}

	@DeleteMapping("/patient/id={id}")
	void deletePatientById(@PathVariable(value = "id")Integer id){
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		System.out.println("deletePatientById");
		patientRepository.deleteById(id);
	}

	@DeleteMapping("/patient/email={email}")
	void deletePatientByEmail(@PathVariable(value = "email")String email){
		Boolean exists = patientRepository.existsByEmail(email);
		if(!exists){
			throw new IllegalStateException("Patient with email " + email + " does not exist.");
		}
		System.out.println("deletePatientByEmail");
		patientRepository.deleteByEmail(email);
	}

	@DeleteMapping("/patient/tel={tel}")
	void deletePatientByTel(@PathVariable(value = "tel")String phone){
		Boolean exists = patientRepository.existsByPhone(phone);
		if(!exists){
			throw new IllegalStateException("Patient with phone " + phone + " does not exist.");
		}
		System.out.println("deletePatientByPhone");
		patientRepository.deleteByPhone(phone);
	}

	@PutMapping("/patient/id={id}")
	Patient updatePatientById(@PathVariable(value = "id")Integer id,
							  @RequestParam(required = false) String fname,
							  @RequestParam(required = false) String lname,
							  @RequestParam(required = false) String dob,
							  @RequestParam(required = false) String tel,
							  @RequestParam(required = false) String email,
							  @RequestParam(required = false) Boolean isInsured,
							  @RequestParam(required = false) Integer state){
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException(
					"Patient with id " + id + " does not exist."
			);
		}

		Patient patient = patientRepository.findById(id);

		if(fname != null && fname.length() > 0 && !patient.getFname().equals(fname)){
			patient.setFname(fname);
		}

		if(lname != null && lname.length() > 0 && !patient.getLname().equals(lname)){
			patient.setLname(lname);
		}

		if(dob != null && dob.length() > 0 && !patient.getDob().equals(dob)){
			patient.setDob(dob);
		}

		if(tel != null && tel.length() > 0 && !patient.getPhone().equals(tel)){
			patient.setPhone(tel);
		}

		if(email != null && email.length() > 0  && !patient.getEmail().equals(email)){
			patient.setEmail(email);
		}

		if(isInsured != null && isInsured != patient.getIsInsured()){
			patient.setIsInsured(isInsured);
		}

		if(state != null & !patient.getState().equals(state)){
			patient.setState(state);
		}

		System.out.println(patient);
		patientRepository.save(patient);
		return patient;
	}

	@PutMapping("/patient/email={email}")
	Patient updatePatientByEmail(@PathVariable(value = "email")String emailOriginal,
							  @RequestParam(required = false) String fname,
							  @RequestParam(required = false) String lname,
							  @RequestParam(required = false) String dob,
							  @RequestParam(required = false) String tel,
							  @RequestParam(required = false) String email,
							  @RequestParam(required = false) Boolean isInsured,
							  @RequestParam(required = false) Integer state){
		Boolean exists = patientRepository.existsByEmail(emailOriginal);
		if(!exists){
			throw new IllegalStateException(
					"Patient with email " + emailOriginal + " does not exist."
			);
		}

		Patient patient = patientRepository.findByEmail(emailOriginal);


		if(fname != null && fname.length() > 0 && patient.getFname()!=fname){
			patient.setFname(fname);
		}

		if(lname != null && lname.length() > 0 && patient.getLname() != lname){
			patient.setLname(lname);
		}

		if(dob != null && dob.length() > 0 && patient.getDob() != dob){
			patient.setDob(dob);
		}

		if(tel != null && tel.length() > 0 && patient.getPhone() != tel){
			patient.setPhone(tel);
		}

		if(email != null && email.length() > 0  && patient.getEmail() != email){
			patient.setEmail(email);
		}

		if(isInsured != null && isInsured != patient.getIsInsured()){
			patient.setIsInsured(isInsured);
		}

		if(state != null & patient.getState() != state){
			patient.setState(state);
		}

		System.out.println(patient);
		patientRepository.save(patient);
		return patient;
	}

	@PutMapping("/patient/tel={tel}")
	Patient updatePatientByTel(@PathVariable(value = "tel")String phone,
								 @RequestParam(required = false) String fname,
								 @RequestParam(required = false) String lname,
								 @RequestParam(required = false) String dob,
								 @RequestParam(required = false) String tel,
								 @RequestParam(required = false) String email,
								 @RequestParam(required = false) Boolean isInsured,
							     @RequestParam(required = false) Integer state){
		Boolean exists = patientRepository.existsByPhone(phone);
		if(!exists){
			throw new IllegalStateException(
					"Patient with tel " + phone + " does not exist."
			);
		}

		Patient patient = patientRepository.findByPhone(phone);

		if(fname != null && fname.length() > 0 && patient.getFname()!=fname){
			patient.setFname(fname);
		}

		if(lname != null && lname.length() > 0 && patient.getLname() != lname){
			patient.setLname(lname);
		}

		if(dob != null && dob.length() > 0 && patient.getDob() != dob){
			patient.setDob(dob);
		}

		if(tel != null && tel.length() > 0 && patient.getPhone() != tel){
			patient.setPhone(tel);
		}

		if(email != null && email.length() > 0  && patient.getEmail() != email){
			patient.setEmail(email);
		}


		if(isInsured != null && isInsured != patient.getIsInsured()){
			patient.setIsInsured(isInsured);
		}

		if(state != null & patient.getState() != state){
			patient.setState(state);
		}

		System.out.println(patient);
		patientRepository.save(patient);
		return patient;
	}



}
