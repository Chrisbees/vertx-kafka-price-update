package com.kafka.eureka.priceconsumer.config;


import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    @Autowired
    public Router router(Vertx vertx) {
        return Router.router(vertx);
    }

}
