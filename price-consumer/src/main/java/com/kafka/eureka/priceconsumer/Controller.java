package com.kafka.eureka.priceconsumer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class Controller {


    @GetMapping("/updates")
    public String kafkaPriceUpdates(){
        return "updates.html";
    }
}
