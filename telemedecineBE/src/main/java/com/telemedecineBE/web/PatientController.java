package com.telemedecineBE.web;

import com.telemedecineBE.dao.PatientRepository;
import com.telemedecineBE.dao.UserDao;
import com.telemedecineBE.entities.*;
import com.telemedecineBE.enumeration.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

	private final PatientRepository patientRepository;
	private final UserDao userDao;

	@Autowired
	public PatientController(PatientRepository patientRepository, UserDao userDao) {
		this.patientRepository = patientRepository;
		this.userDao = userDao;
	}

	@GetMapping("/patients")
	List<Patient> getAllPatients(){
		System.out.println("getAllPatients");
		return patientRepository.findAll();
	}

	@GetMapping("patient/id={id}/appointments")
	List<Appointment> getAppointments(@PathVariable(value = "id")Integer id){
		System.out.println("getPatientAppointments");
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		return patient.getAppointments();
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
		Boolean exists = patientRepository.existsByCellphone(phone);
		if(!exists){
			throw new IllegalStateException("Patient with phone " + phone + " does not exist.");
		}
		System.out.println("getPatientByTel");
		return patientRepository.findByCellphone(phone);
	}

	@PostMapping("/patient")
	Patient newPatient(@RequestBody Patient patient){
		Boolean exists = patientRepository.existsByEmail(patient.getEmail());
		Boolean exists2 = patientRepository.existsByCellphone(patient.getCellphone());
		if(exists){
			throw new IllegalStateException("Patient with email " + patient.getEmail() + " already exists.");
		} else if(exists2){
			throw new IllegalStateException("Patient with phone " + patient.getCellphone() + " already exists.");
		}
		patient.setUserName(patient.getEmail());
		patient.setUserType(UserType.PATIENT);
		patient.setUserpassword(patient.getUserpassword());
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
			} else if(patientRepository.existsByCellphone(p.getCellphone())){
				throw new IllegalStateException("Patient with phone " + p.getCellphone() + " already exists.");
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
		Boolean exists = patientRepository.existsByCellphone(phone);
		if(!exists){
			throw new IllegalStateException("Patient with phone " + phone + " does not exist.");
		}
		System.out.println("deletePatientByPhone");
		patientRepository.deleteByCellphone(phone);
	}

	@PutMapping("/patient/id={id}")
	Patient updatePatientByIdWithRequestBody(@PathVariable(value = "id") Integer id,
											 @RequestBody Patient patient) {
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException(
					"Patient with id " + id + " does not exist."
			);
		}

		Patient currentPatient = patientRepository.findById(id);

		if(patient.getFname() != null && patient.getFname().length() > 0 && currentPatient.getFname() != patient.getFname()){
			currentPatient.setFname(patient.getFname());
		}

		if(patient.getLname() != null && patient.getLname().length() > 0 && currentPatient.getLname() != patient.getLname()){
			currentPatient.setLname(patient.getLname());
		}

		if(patient.getEmail() != null && patient.getEmail().length() > 0 && patient.getEmail() != currentPatient.getEmail() && !userDao.existsByEmail(patient.getEmail())){
			currentPatient.setEmail(patient.getEmail());
		}

		if(patient.getCellphone() != null && patient.getCellphone().length() > 0 && patient.getCellphone() != currentPatient.getCellphone() && !userDao.existsByCellphone(patient.getCellphone())){
			currentPatient.setCellphone(patient.getCellphone());
		}

		if(patient.getUserName() != null && patient.getUserName() .length() > 0 && patient.getUserName()  != currentPatient.getUserName() && !userDao.existsByUserName(patient.getUserName())){
			currentPatient.setUserName(patient.getUserName() );
		}

		if(patient.getUserpassword() != null && patient.getUserpassword().length() > 0 && patient.getUserpassword() != currentPatient.getUserpassword()){
			currentPatient.setUserpassword(patient.getUserpassword());
		}

		if(patient.getUserType() != null && patient.getUserType() != currentPatient.getUserType()){
			currentPatient.setUserType(patient.getUserType());
		}

		if(patient.getDob() != null && patient.getDob().length() > 0 && patient.getDob() != currentPatient.getDob()){
			currentPatient.setDob(patient.getDob());
		}

		if(patient.getState() != null && patient.getState() > 0 && patient.getState() != currentPatient.getState()){
			currentPatient.setState(patient.getState());
		}

		System.out.println(currentPatient);
		patientRepository.save(currentPatient);
		return currentPatient;
	}
/*
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

		if(fname != null && fname.length() > 0 && patient.getFname() != fname){
			patient.setFname(fname);
		}

		if(lname != null && lname.length() > 0 && patient.getLname() != lname){
			patient.setLname(lname);
		}

		if(dob != null && dob.length() > 0 && patient.getDob() != dob){
			patient.setDob(dob);
		}

		if(tel != null && tel.length() > 0 && patient.getCellphone() != tel){
			patient.setCellphone(tel);
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

 */

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

		if(tel != null && tel.length() > 0 && patient.getCellphone() != tel){
			patient.setCellphone(tel);
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
		Boolean exists = patientRepository.existsByCellphone(phone);
		if(!exists){
			throw new IllegalStateException(
					"Patient with tel " + phone + " does not exist."
			);
		}

		Patient patient = patientRepository.findByCellphone(phone);

		if(fname != null && fname.length() > 0 && patient.getFname()!=fname){
			patient.setFname(fname);
		}

		if(lname != null && lname.length() > 0 && patient.getLname() != lname){
			patient.setLname(lname);
		}

		if(dob != null && dob.length() > 0 && patient.getDob() != dob){
			patient.setDob(dob);
		}

		if(tel != null && tel.length() > 0 && patient.getCellphone() != tel){
			patient.setCellphone(tel);
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
