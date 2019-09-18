package com.trilogyed.eurekaserviceregistrygame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceRegistryGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceRegistryGameApplication.class, args);
	}

}
