package com.pixogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pixogram.property.FileStorageProperties;

@SpringBootApplication
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableConfigurationProperties({
    FileStorageProperties.class
})
@EnableDiscoveryClient
public class PixoGramApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(PixoGramApplication.class, args);
	}

}
