package com.telemedecineBE.web;

import com.telemedecineBE.dao.PatientRepository;
import com.telemedecineBE.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

	private final PatientRepository patientRepository;
	private final AddressController addressController;
	private final MedicalHistoryController medicalHistoryController;
	private final InsuranceController insuranceController;

	@Autowired
	public PatientController(PatientRepository patientRepository, AddressController addressController,
							 InsuranceController insuranceController, MedicalHistoryController medicalHistoryController) {
		this.patientRepository = patientRepository;
		this.addressController = addressController;
		this.medicalHistoryController = medicalHistoryController;
		this.insuranceController = insuranceController;
	}

	@GetMapping("/patient")
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
		Boolean exists = patientRepository.existsByTel(phone);
		if(!exists){
			throw new IllegalStateException("Patient with phone " + phone + " does not exist.");
		}
		System.out.println("getPatientByTel");
		return patientRepository.findByTel(phone);
	}

	@PostMapping("/patient")
	Patient newPatient(@RequestBody Patient patient){
		Boolean exists = patientRepository.existsByEmail(patient.getEmail());
		if(exists){
			throw new IllegalStateException("Patient with email " + patient.getEmail() + " already exists.");
		}
		patientRepository.save(patient);
		System.out.println("newPatient");
		return patient;
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
		Boolean exists = patientRepository.existsByTel(phone);
		if(!exists){
			throw new IllegalStateException("Patient with phone " + phone + " does not exist.");
		}
		System.out.println("deletePatientByPhone");
		patientRepository.deleteByTel(phone);
	}

	@PutMapping("/patient/id={id}")
	Patient updatePatientById(@PathVariable(value = "id")Integer id,
							  @RequestParam(required = false) String fname,
							  @RequestParam(required = false) String lname,
							  @RequestParam(required = false) String dateNaissance,
							  @RequestParam(required = false) String tel,
							  @RequestParam(required = false) String email,
							  @RequestParam(required = false) String BP,
							  @RequestParam(required = false) Boolean estAssure,
							  @RequestParam(required = false) String datePremiereConsultation,
							  @RequestParam(required = false) Integer state,
							  @RequestParam(required = false) @RequestBody MedicalHistory medicalHistory,
							  @RequestParam(required = false) @RequestBody Insurance insurance,
							  @RequestParam(required = false) @RequestBody Address address/*,
							  @RequestParam(required = false) @RequestBody Appointment appointment,
							  @RequestParam(required = false) @RequestBody User user */){
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

		if(dateNaissance != null && dateNaissance.length() > 0 && dateNaissance != patient.getDateNaissance()){
			patient.setDateNaissance(dateNaissance);
		}

		if(tel != null && tel.length() > 0 && patient.getTel() != tel){
			patient.setTel(tel);
		}

		if(email != null && email.length() > 0  && patient.getEmail() != email){
			patient.setEmail(email);
		}

		if(BP != null && BP.length() > 0 && patient.getBP() != BP){
			patient.setBP(BP);
		}

		if(estAssure != null && estAssure != patient.isEstAssure()){
			patient.setEstAssure(estAssure);
		}

		if(datePremiereConsultation != null && datePremiereConsultation.length() > 0 && patient.getDatePremiereConsultation() != datePremiereConsultation){
			patient.setDatePremiereConsultation(datePremiereConsultation);
		}

		if(state != null & patient.getState() != state){
			patient.setState(state);
		}

		if(medicalHistory != null && !patient.getAntecedantMedicaux().contains(medicalHistory)){
			try{
				medicalHistoryController.updateMedicalHistoryByName(medicalHistory.getNom(), medicalHistory.getNom(),
						medicalHistory.getMedecinTraitant(), medicalHistory.getDateSurvenance(), medicalHistory.getState());
			} catch(IllegalStateException e){
				System.out.println("Medical condition does not exist");
			}
		}

		if(insurance != null && !patient.getInsurance().equals(insurance)){
			try{
				insuranceController.updateInsuranceByName(insurance.getNom(), insurance.getNom(),
						insurance.getCouvreToutSoins(), insurance.getCouvreFraisConsultation(), insurance.getState(),
						insurance.getPercentageAssurance());
			} catch(IllegalStateException e){
				System.out.println("Insurance does not exist");
			}
		}

		if(address != null && !patient.getAddress().equals(address)){
			addressController.updateAddressByLine(address.getLine(), address.getZipcode(), address.getLine());
		}

		/*
		if(appointment != null && !patient.getAppointment().equals(appointment)){
			//add
		}

		if(user != null & !patient.getUser().equals(user)){
			//add
		}
		*/

		System.out.println(patient);
		patientRepository.save(patient);
		return patient;
	}

	@PutMapping("/patient/email={email}")
	Patient updatePatientByEmail(@PathVariable(value = "email")String emailOriginal,
							  @RequestParam(required = false) String fname,
							  @RequestParam(required = false) String lname,
							  @RequestParam(required = false) String dateNaissance,
							  @RequestParam(required = false) String tel,
							  @RequestParam(required = false) String email,
							  @RequestParam(required = false) String BP,
							  @RequestParam(required = false) Boolean estAssure,
							  @RequestParam(required = false) String datePremiereConsultation,
							  @RequestParam(required = false) Integer state,
							  @RequestParam(required = false) @RequestBody MedicalHistory medicalHistory,
							  @RequestParam(required = false) @RequestBody Insurance insurance,
							  @RequestParam(required = false) @RequestBody Address address/*,
							  @RequestParam(required = false) @RequestBody Appointment appointment,
							  @RequestParam(required = false) @RequestBody User user */){
		Boolean exists = patientRepository.existsByEmail(emailOriginal);
		if(!exists){
			throw new IllegalStateException(
					"Patient with email " + emailOriginal + " does not exist."
			);
		}

		Patient patient = patientRepository.findByEmail(emailOriginal);

		if(fname != null && fname.length() > 0 && patient.getFname() != fname){
			patient.setFname(fname);
		}

		if(lname != null && lname.length() > 0 && patient.getLname() != lname){
			patient.setLname(lname);
		}

		if(dateNaissance != null && dateNaissance.length() > 0 && dateNaissance != patient.getDateNaissance()){
			patient.setDateNaissance(dateNaissance);
		}

		if(tel != null && tel.length() > 0 && patient.getTel() != tel){
			patient.setTel(tel);
		}

		if(email != null && email.length() > 0  && patient.getEmail() != email){
			patient.setEmail(email);
		}

		if(BP != null && BP.length() > 0 && patient.getBP() != BP){
			patient.setBP(BP);
		}

		if(estAssure != null && estAssure != patient.isEstAssure()){
			patient.setEstAssure(estAssure);
		}

		if(datePremiereConsultation != null && datePremiereConsultation.length() > 0 && patient.getDatePremiereConsultation() != datePremiereConsultation){
			patient.setDatePremiereConsultation(datePremiereConsultation);
		}

		if(state != null & patient.getState() != state){
			patient.setState(state);
		}

		if(medicalHistory != null && !patient.getAntecedantMedicaux().contains(medicalHistory)){
			System.out.println("updatePatientMedicalHistory");
		}

		if(insurance != null && !patient.getInsurance().equals(insurance)){
			System.out.println("updatePatientInsurance");
		}

		if(address != null && !patient.getAddress().equals(address)){
			System.out.println("updatePatientAddress");
			patient.setAddress(address);
		}

		/*
		if(appointment != null && !patient.getAppointment().equals(appointment)){
			//add
		}

		if(user != null & !patient.getUser().equals(user)){
			//add
		}
		*/

		System.out.println(patient);
		patientRepository.save(patient);
		return patient;
	}

	@PutMapping("/patient/tel={tel}")
	Patient updatePatientByTel(@PathVariable(value = "tel")String phone,
								 @RequestParam(required = false) String fname,
								 @RequestParam(required = false) String lname,
								 @RequestParam(required = false) String dateNaissance,
								 @RequestParam(required = false) String tel,
								 @RequestParam(required = false) String email,
								 @RequestParam(required = false) String BP,
								 @RequestParam(required = false) Boolean estAssure,
								 @RequestParam(required = false) String datePremiereConsultation,
							     @RequestParam(required = false) Integer state,
								 @RequestParam(required = false) @RequestBody MedicalHistory medicalHistory,
								 @RequestParam(required = false) @RequestBody Insurance insurance,
								 @RequestParam(required = false) @RequestBody Address address/*,
							  @RequestParam(required = false) @RequestBody Appointment appointment,
							  @RequestParam(required = false) @RequestBody User user */){
		Boolean exists = patientRepository.existsByTel(phone);
		if(!exists){
			throw new IllegalStateException(
					"Patient with tel " + phone + " does not exist."
			);
		}

		Patient patient = patientRepository.findByTel(phone);

		if(fname != null && fname.length() > 0 && patient.getFname() != fname){
			patient.setFname(fname);
		}

		if(lname != null && lname.length() > 0 && patient.getLname() != lname){
			patient.setLname(lname);
		}

		if(dateNaissance != null && dateNaissance.length() > 0 && dateNaissance != patient.getDateNaissance()){
			patient.setDateNaissance(dateNaissance);
		}

		if(tel != null && tel.length() > 0 && patient.getTel() != tel){
			patient.setTel(tel);
		}

		if(email != null && email.length() > 0  && patient.getEmail() != email){
			patient.setEmail(email);
		}

		if(BP != null && BP.length() > 0 && patient.getBP() != BP){
			patient.setBP(BP);
		}

		if(estAssure != null && estAssure != patient.isEstAssure()){
			patient.setEstAssure(estAssure);
		}

		if(datePremiereConsultation != null && datePremiereConsultation.length() > 0 && patient.getDatePremiereConsultation() != datePremiereConsultation){
			patient.setDatePremiereConsultation(datePremiereConsultation);
		}

		if(state != null & patient.getState() != state){
			patient.setState(state);
		}

		if(medicalHistory != null && !patient.getAntecedantMedicaux().contains(medicalHistory)){
			try{
				medicalHistoryController.updateMedicalHistoryByName(medicalHistory.getNom(), medicalHistory.getNom(),
						medicalHistory.getMedecinTraitant(), medicalHistory.getDateSurvenance(), medicalHistory.getState());
			} catch(IllegalStateException e){
				System.out.println("Medical condition does not exist");
			}
		}

		if(insurance != null && !patient.getInsurance().equals(insurance)){
			try{
				insuranceController.updateInsuranceByName(insurance.getNom(), insurance.getNom(),
						insurance.getCouvreToutSoins(), insurance.getCouvreFraisConsultation(), insurance.getState(),
						insurance.getPercentageAssurance());
			} catch(IllegalStateException e){
				System.out.println("Insurance does not exist");
			}
		}

		if(address != null && !patient.getAddress().equals(address)){
			addressController.updateAddressByLine(address.getLine(), address.getZipcode(), address.getLine());
		}

		/*
		if(appointment != null && !patient.getAppointment().equals(appointment)){
			//add
		}

		if(user != null & !patient.getUser().equals(user)){
			//add
		}
		*/

		System.out.println(patient);
		patientRepository.save(patient);
		return patient;
	}
}
