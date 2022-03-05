package com.telemedecineBE;

import com.telemedecineBE.dao.PatientRepository;
import com.telemedecineBE.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TelemedecineBeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TelemedecineBeApplication.class, args);
	}

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void run(String...args) throws Exception {
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
