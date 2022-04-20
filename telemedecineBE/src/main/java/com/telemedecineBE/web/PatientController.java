package com.telemedecineBE.web;

import com.telemedecineBE.Security.AES;
import com.telemedecineBE.TelemedecineBeApplication;
import com.telemedecineBE.dao.InsuranceRepository;
import com.telemedecineBE.dao.PatientRepository;
import com.telemedecineBE.dao.UserDao;
import com.telemedecineBE.entities.*;
import com.telemedecineBE.enumeration.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PatientController {

	private final PatientRepository patientRepository;
	private final UserDao userDao;
    private final InsuranceRepository insuranceRepository;

	@Autowired
	public PatientController(PatientRepository patientRepository, UserDao userDao, InsuranceRepository insuranceRepository) {
		this.patientRepository = patientRepository;
		this.userDao = userDao;
        this.insuranceRepository = insuranceRepository;
	}

	@GetMapping("/patients")
	List<Patient> getAllPatients(){
		System.out.println("getAllPatients");
		return patientRepository.findAll();
	}

	@GetMapping("patient/id={id}/appointments")
	List<Appointment> getAppointments(@PathVariable(value = "id")Integer id) {
		System.out.println("getPatientAppointments");
		Boolean exists = patientRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		return patient.getAppointments();
	}

	@GetMapping("patient/id={id}/prescriptions")
	List<Prescriptions> getPrescriptions(@PathVariable(value = "id")Integer id){
		System.out.println("getPatientPrescriptions");
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		return patient.getPrescriptions();
	}

	@GetMapping("patient/id={id}/requests")
	List<Requests> getRequestedPrescriptions(@PathVariable(value = "id")Integer id){
		System.out.println("getPatientPrescriptions");
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		return patient.getRequests();
	}

	@GetMapping("/patient/id={id}/reports")
	List<Integer> getReportIds(@PathVariable Integer id){
		System.out.println("getPatientReportIds");
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		return patient.getReportIds();
	}

	@GetMapping("/patient/id={id}")
	Patient getPatientById(@PathVariable(value = "id")Integer id){
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		System.out.println("getPatientById");
		Patient p = patientRepository.findById(id);
		p.decryptUserData();
		return p;
	}

	@GetMapping("/patient/email={email}")
	Patient getPatientByEmail(@PathVariable(value = "email")String email){
		Boolean exists = patientRepository.existsByEmail(email);
		if(!exists){
			throw new IllegalStateException("Patient with email " + email + " does not exist.");
		}
		Patient p = patientRepository.findByEmail(email);
		p.decryptUserData();
		return p;
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

		// Encode password
		String encodedPassword = BCrypt.hashpw(patient.getUserpassword(), BCrypt.gensalt(TelemedecineBeApplication.strength));
		patient.setUserpassword(encodedPassword);

		patient.setUserpassword(patient.getUserpassword());

		//Boolean exists2 = patientRepository.existsByPhone(patient.getPhone());
		if(exists){
			throw new IllegalStateException("Patient with email " + patient.getEmail() + " already exists.");
		} //else if(exists2){
			//throw new IllegalStateException("Patient with phone " + patient.getPhone() + " already exists.");
		//}

		patient.encryptUserData();
		patientRepository.save(patient);
		patient.decryptUserData();
		return patient;
	}

	@GetMapping("patient/id={id}/address")
	Address getAddress(@PathVariable(value = "id")Integer id){
		System.out.println("getPatientAddress");
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		return patient.getAddress();
	}

	@GetMapping("patient/id={id}/insurance")
	List<Insurance> getInsurance(@PathVariable(value = "id")Integer id){
		System.out.println("getPatientInsurance");
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		return patient.getInsurance();
	}

	@GetMapping("patient/id={id}/medical-history")
	List<MedicalHistory> getMedicalHistory(@PathVariable(value = "id")Integer id){
		System.out.println("getPatientInsurance");
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		return patient.getMedicalHistory();
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

		if(patient.getUsername() != null && patient.getUsername() .length() > 0 && patient.getUsername()  != currentPatient.getUsername() && !userDao.existsByUserName(patient.getUsername())){
			currentPatient.setUserName(patient.getUsername() );
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

		currentPatient.encryptUserData();
		patientRepository.save(currentPatient);
		currentPatient.decryptUserData();
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

	@PutMapping("/patient/id={id}/add-medical-history")
	MedicalHistory addPatientMedicalHistory(@PathVariable Integer id,
								  @RequestBody MedicalHistory medicalHistory){
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		List<MedicalHistory> mHList= patient.getMedicalHistory();
		mHList.add(medicalHistory);
		patient.setMedicalHistory(mHList);
		patientRepository.save(patient);
		return medicalHistory;
	}

	@PutMapping("/patient/id={id}/add-insurance")
	Insurance addPatientInsurance(@PathVariable Integer id,
								  @RequestBody Insurance insurance){
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		List<Insurance> insuranceList= patient.getInsurance();
		insuranceList.add(insurance);
		patient.setInsurance(insuranceList);
		patientRepository.save(patient);
		return insurance;
	}

	@PutMapping("/patient/id={id}/add-address")
	Address addPatientAddress(@PathVariable Integer id,
								  @RequestBody Address address){
		System.out.println(address);
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		patient.setAddress(address);
		patientRepository.save(patient);
		return address;
	}

	@PutMapping("/patient/id={id}/address")
	Patient updatePatientAddress(@PathVariable Integer id,
								 @RequestBody Address address){
		Boolean exists = patientRepository.existsById(id);
		if(!exists){
			throw new IllegalStateException("Patient with id " + id + " does not exist.");
		}
		Patient patient = patientRepository.findById(id);
		Address currAddress = patient.getAddress();

		if(address.getZipcode() != null && address.getZipcode().length() > 0 && address.getZipcode() != currAddress.getZipcode()){
			currAddress.setZipcode(address.getZipcode());
		}

		if(address.getStreetAddress() != null && address.getStreetAddress().length() > 0 && address.getStreetAddress() != currAddress.getStreetAddress()){
			currAddress.setStreetAddress(address.getStreetAddress());
		}

		if(address.getCity() != null && address.getCity().length() > 0 && address.getCity() != currAddress.getCity()){
			currAddress.setCity(address.getCity());
		}

		if(address.getUsState() != null && address.getUsState().length() > 0 && address.getUsState() != currAddress.getUsState()) {
			currAddress.setUsState(address.getUsState());
		}

		patient.setAddress(currAddress);
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
