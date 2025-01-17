package com.practice.doctor_appointment_booking;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.core.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootApplication
public class DoctorAppointmentBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorAppointmentBookingApplication.class, args);
	}


	@PostConstruct
	public void testModularMonolith()
	{
		ApplicationModules applicationModules = ApplicationModules.of(DoctorAppointmentBookingApplication.class);
		// Print Modules Detected.
		applicationModules.forEach(System.out::println);
		// Verify Dependency Violation.
		applicationModules.verify();
		// Draw Dependencies Between Modules.
		new Documenter(applicationModules)
				.writeDocumentation()
				.writeIndividualModulesAsPlantUml();
	}
}
