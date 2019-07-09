package com.pixogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PixogramGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PixogramGatewayServiceApplication.class, args);
	}

}
