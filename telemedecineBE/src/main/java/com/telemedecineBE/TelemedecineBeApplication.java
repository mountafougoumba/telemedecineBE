package com.telemedecineBE;

import com.telemedecineBE.dao.*;
import com.telemedecineBE.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

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
	private InsuranceRepository insuranceRepository;
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	@Autowired
	private MedicalHistoryRepository medicalHistoryRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public void run(String...args) throws Exception {
        //Add patient entities
		this.patientRepository.save(new Patient("Denise",
							"Smith",
							"dSmith@gmail.com",
							"123-456-7890",
							"password"));
		this.patientRepository.save(new Patient("Daniel",
						"Lewis",
						"dLewis@gmail.com",
						"098-765-4321",
						"password123"));
		this.patientRepository.save(new Patient("Stanley",
						"Goodman",
						"stanleyG@gmail.com",
						"345-453-2345",
						"password"));

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

		//add insurance entities
		this.insuranceRepository.save(new Insurance(
						"State Farm",
						true,
						false,
						3.42
		));
		this.insuranceRepository.save(new Insurance(
						"Aetna",
						false,
						true,
						5.42
		));
		this.insuranceRepository.save(new Insurance(
						"Medicare",
						true,
						true,
						95.50
		));

		//add prescription entities
		this.prescriptionRepository.save(new Prescriptions(
						"Nyquill",
						"10mg",
						"Cold/Sleep Medication"
		));
		this.prescriptionRepository.save(new Prescriptions(
				"Advil",
				"25mg",
				"Pain Medication"
		));
		this.prescriptionRepository.save(new Prescriptions(
				"Claratin",
				"15mg",
				"Allergy Medication"
		));

		//add medicalHistory entities to database
		this.medicalHistoryRepository.save(new MedicalHistory(
				"Diabetes",
				"Dr.Bill",
				"2020-01-23"
		));
		this.medicalHistoryRepository.save(new MedicalHistory(
				"Insomnia",
				"Dr.Steve",
				"1995-06-30",
				"Averages 4 hours of sleep/day. Takes unisom for sleep."
		));

		this.doctorRepository.save(new Doctor(
				"Benjamin",
				"Button",
				"Future Doctors Inc.",
				"Pediatrics",
				"password",
				"benB@gmail.com",
				"678-892-3234"
		));
		this.doctorRepository.save(new Doctor(
				"Daisy",
				"Hun",
				"Baily's Dentistry",
				"Dentist",
				"password123",
				"daisssyH@gmail.com",
				"634-234-2143"
		));
		this.doctorRepository.save(new Doctor(
				"Jackson",
				"Cod",
				"All Seeing Eye LTD.",
				"Optomitrist",
				"password123",
				"jCod@gmail.com",
				"453-234-4356"
		));

		this.appointmentRepository.save(new Appointment(
				LocalDateTime.of(2022, 5, 25, 10, 30),
				"Check-up/Physical",
				patientRepository.findByEmail("dSmith@gmail.com"),
				doctorRepository.findByEmail("benB@gmail.com")
		));

		this.appointmentRepository.save(new Appointment(
				LocalDateTime.of(2022, 8, 18, 12, 45),
				"Cleaning",
				patientRepository.findByEmail("stanleyG@gmail.com"),
				doctorRepository.findByEmail("daisssyH@gmail.com")
		));
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
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
