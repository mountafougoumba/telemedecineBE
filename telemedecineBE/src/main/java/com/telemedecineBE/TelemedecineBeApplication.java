package com.telemedecineBE;

import com.telemedecineBE.dao.*;
import com.telemedecineBE.entities.*;
import com.telemedecineBE.enumeration.AppointmentType;
import com.telemedecineBE.enumeration.RequestStatus;
import com.telemedecineBE.enumeration.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TelemedecineBeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TelemedecineBeApplication.class, args);
	}

	@Autowired
	private PatientRepository patientRepository;
    @Autowired
    private AddressRepository addressRepository;
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private RequestRepository requestRepository;

	public static Integer strength = 10;


	@Override
	public void run(String...args) throws Exception {
        //Add patient entities
		this.patientRepository.save(new Patient("Denise",
							"Smith",
							"dSmith@gmail.com",
							"123-456-7890",
				"1997-11-22",
				BCrypt.hashpw("password", BCrypt.gensalt(TelemedecineBeApplication.strength))));
		this.patientRepository.save(new Patient("Daniel",
						"Lewis",
						"dLewis@gmail.com",
						"098-765-4321",
				"1996-10-21",
				BCrypt.hashpw("password123", BCrypt.gensalt(TelemedecineBeApplication.strength))));
		this.patientRepository.save(new Patient("Stanley",
						"Goodman",
						"stanleyG@gmail.com",
						"345-453-2345",
				"1995-09-20",
				BCrypt.hashpw("password", BCrypt.gensalt(TelemedecineBeApplication.strength))));

        //Add address entities
        this.addressRepository.save(new Address(
                        "34212",
                        "123 Developer Way",
                        "Kennesaw",
                        "GA"));
        this.addressRepository.save(new Address(
                        "23423",
                        "456 Project Lane",
                        "Marietta",
                        "TX"));

		this.doctorRepository.save(new Doctor(
				"Benjamin",
				"Button",
				"Future Doctors Inc.",
				"Pediatrics",
				BCrypt.hashpw("password", BCrypt.gensalt(TelemedecineBeApplication.strength)),
				"benB@gmail.com",
				"678-892-3234"
		));
		this.doctorRepository.save(new Doctor(
				"Daisy",
				"Hun",
				"Baily's Dentistry",
				"Dentist",
				BCrypt.hashpw("password123", BCrypt.gensalt(TelemedecineBeApplication.strength)),
				"daisssyH@gmail.com",
				"634-234-2143"
		));
		this.doctorRepository.save(new Doctor(
				"Jackson",
				"Cod",
				"All Seeing Eye LTD.",
				"Optomitrist",
				BCrypt.hashpw("password", BCrypt.gensalt(TelemedecineBeApplication.strength)),
				"jCod@gmail.com",
				"453-234-4356"
		));

		this.adminRepository.save(new Admin(
				"Faith",
				"Swetnam",
				"fswetnam@gmail.com",
				"567-823-1244",
				BCrypt.hashpw("password", BCrypt.gensalt(TelemedecineBeApplication.strength))
		));

		this.adminRepository.save(new Admin(
				"Aubrey",
				"Lewis",
				"aLewis@gmail.com",
				"435-123-1245",
				BCrypt.hashpw("password", BCrypt.gensalt(TelemedecineBeApplication.strength))
		));

		this.adminRepository.save(new Admin(
				"Brandon",
				"Lewis",
				"bLewis@gmail.com",
				"231-154-5346",
				BCrypt.hashpw("password", BCrypt.gensalt(TelemedecineBeApplication.strength))
		));

		this.adminRepository.save(new Admin(
				"Barry",
				"Mamadou Mountagha",
				"bMM@gmail.com",
				"346-235-1351",
				BCrypt.hashpw("password", BCrypt.gensalt(TelemedecineBeApplication.strength))
		));


		//add prescription entities
		this.prescriptionRepository.save(new Prescriptions(
				"Nyquill",
				"10mg",
				"Cold/Sleep Medication",
				patientRepository.findByEmail("dSmith@gmail.com"),
				doctorRepository.findByEmail("benB@gmail.com")
		));
		this.prescriptionRepository.save(new Prescriptions(
				"Advil",
				"25mg",
				"Pain Medication",
				patientRepository.findByEmail("stanleyG@gmail.com"),
				doctorRepository.findByEmail("daisssyH@gmail.com")
		));
		this.prescriptionRepository.save(new Prescriptions(
				"Claratin",
				"15mg",
				"Allergy Medication",
				patientRepository.findByEmail("dLewis@gmail.com"),
				doctorRepository.findByEmail("jCod@gmail.com")
		));
/*
		this.requestRepository.save(new Requests(
				this.prescriptionRepository.findById(20),
				RequestType.PRESCRIPTION_REQUEST,
				RequestStatus.WAITING,
				patientRepository.findByEmail("dSmith@gmail.com"),
				doctorRepository.findByEmail("benB@gmail.com")
		));

		this.requestRepository.save(new Requests(
				this.prescriptionRepository.findById(21),
				RequestType.PRESCRIPTION_REQUEST,
				RequestStatus.DENIED,
				patientRepository.findByEmail("stanleyG@gmail.com"),
				doctorRepository.findByEmail("daisssyH@gmail.com")
		));

		this.requestRepository.save(new Requests(
				this.prescriptionRepository.findById(22),
				RequestType.PRESCRIPTION_REQUEST,
				RequestStatus.CONFIRMED,
				patientRepository.findByEmail("dLewis@gmail.com"),
				doctorRepository.findByEmail("jCod@gmail.com")
		));


        this.requestRepository.save(new Requests(
                new Appointment(
                        LocalDateTime.of(2022, 4, 5, 10, 30),
                        "Check-up/Physical",
                        patientRepository.findByEmail("d@Lewis@gmail.com"),
                        doctorRepository.findByEmail("jCod@gmail.com"),
						AppointmentType.IN_PERSON
                ),
                RequestType.APPOINTMENT_REQUEST,
                RequestStatus.CONFIRMED,
                AppointmentType.IN_PERSON,
                patientRepository.findByEmail("dLewis@gmail.com"),
                doctorRepository.findByEmail("jCod@gmail.com"),
                adminRepository.findByEmail("fswetnam@gmail.com")
        ));


 */

        this.requestRepository.save(new Requests(
                new Appointment(
                        LocalDateTime.of(2022, 4, 2, 12, 45),
                        "Cleaning",
                        patientRepository.findByEmail("stanleyG@gmail.com"),
                        doctorRepository.findByEmail("daisssyH@gmail.com"),
						AppointmentType.IN_PERSON
                ),
                RequestType.APPOINTMENT_REQUEST,
                RequestStatus.DENIED,
                AppointmentType.IN_PERSON,
                patientRepository.findByEmail("stanleyG@gmail.com"),
                doctorRepository.findByEmail("daisssyH@gmail.com"),
                adminRepository.findByEmail("fswetnam@gmail.com")
        ));

        this.requestRepository.save(new Requests(
                new Appointment(
                        LocalDateTime.of(2022, 4, 7, 10, 30),
                        "Check-up/Physical",
                        patientRepository.findByEmail("dSmith@gmail.com"),
                        doctorRepository.findByEmail("benB@gmail.com"),
						AppointmentType.ONLINE
                ),
                RequestType.APPOINTMENT_REQUEST,
                RequestStatus.CONFIRMED,
                AppointmentType.ONLINE,
                patientRepository.findByEmail("dSmith@gmail.com"),
                doctorRepository.findByEmail("benB@gmail.com"),
                adminRepository.findByEmail("fswetnam@gmail.com")
        ));

		this.requestRepository.save(new Requests(
				new Appointment(
						LocalDateTime.of(2022, 4, 6, 12, 0),
						"Check-up/Physical",
						patientRepository.findByEmail("dSmith@gmail.com"),
						doctorRepository.findByEmail("benB@gmail.com"),
						AppointmentType.ONLINE
				),
				RequestType.APPOINTMENT_REQUEST,
				RequestStatus.WAITING,
				AppointmentType.ONLINE,
				patientRepository.findByEmail("dSmith@gmail.com"),
				doctorRepository.findByEmail("benB@gmail.com"),
				adminRepository.findByEmail("fswetnam@gmail.com")
		));
/*
		this.requestRepository.save(new Requests(
				new Appointment(
						LocalDateTime.of(2022, 4, 6, 14, 30),
						"Check-up/Physical",
						patientRepository.findByEmail("stanleyG@gmail.com"),
						doctorRepository.findByEmail("benB@gmail.com"),
						AppointmentType.ONLINE
				),
				RequestType.APPOINTMENT_REQUEST,
				RequestStatus.CONFIRMED,
				AppointmentType.ONLINE,
				patientRepository.findByEmail("stanleyG@gmail.com"),
				doctorRepository.findByEmail("benB@gmail.com"),
				adminRepository.findByEmail("fswetnam@gmail.com")
		));

		this.requestRepository.save(new Requests(
				new Appointment(
						LocalDateTime.of(2022, 4, 6, 9, 30),
						"Blood Work",
						patientRepository.findByEmail("dLewis@gmail.com"),
						doctorRepository.findByEmail("benB@gmail.com"),
						AppointmentType.IN_PERSON
				),
				RequestType.APPOINTMENT_REQUEST,
				RequestStatus.WAITING,
				AppointmentType.ONLINE,
				patientRepository.findByEmail("dLewis@gmail.com"),
				doctorRepository.findByEmail("benB@gmail.com"),
				adminRepository.findByEmail("fswetnam@gmail.com")
		));

 */

	}


	//Had to add to get Angular, Spring Boot, and Chrome working
	@Bean
	public CorsFilter corsFilter(){
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Requests-Method", "Access-Control-Requests-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
