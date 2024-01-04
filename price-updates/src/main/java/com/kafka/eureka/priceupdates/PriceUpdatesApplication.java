package com.kafka.eureka.priceupdates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PriceUpdatesApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(PriceUpdatesApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.setAdditionalProfiles("prod");
        springApplication.run(args);
    }

}
