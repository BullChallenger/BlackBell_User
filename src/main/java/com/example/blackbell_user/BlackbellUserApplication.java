package com.example.blackbell_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlackbellUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackbellUserApplication.class, args);
	}

}
