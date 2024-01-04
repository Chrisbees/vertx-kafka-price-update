package com.kafka.eureka.priceupdates.model;

import lombok.*;

import java.util.Random;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Products {

    private String name;
    private Double price;

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}



