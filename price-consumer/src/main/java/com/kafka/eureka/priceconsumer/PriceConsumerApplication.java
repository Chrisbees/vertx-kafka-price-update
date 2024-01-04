package com.kafka.eureka.priceconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.kafka.eureka.priceconsumer.*")
public class PriceConsumerApplication {

	public static void main(String[] args) {
		SpringApplication springApplication=new SpringApplication(PriceConsumerApplication.class);
		springApplication.setWebApplicationType(WebApplicationType.NONE);
		springApplication.setAdditionalProfiles("prod");
		springApplication.run(args);
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

}
