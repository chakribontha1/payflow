package com.example.PayFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PayFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayFlowApplication.class, args);
	}

}
