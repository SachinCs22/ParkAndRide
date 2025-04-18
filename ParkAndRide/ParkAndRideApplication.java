package com.example.ParkAndRide.ParkAndRide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ParkAndRideApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkAndRideApplication.class, args);
	}

}
