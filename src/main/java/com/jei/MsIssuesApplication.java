package com.jei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsIssuesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsIssuesApplication.class, args);
	}

}
