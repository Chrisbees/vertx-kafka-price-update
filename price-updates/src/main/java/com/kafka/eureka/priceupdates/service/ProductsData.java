package com.kafka.eureka.priceupdates.service;

import com.kafka.eureka.priceupdates.model.Products;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsData implements ProductsDataInterface {

    private final List<Products> productList = new ArrayList<>();

    public ProductsData() {
        build();
    }

    private void build() {
        addProdToList(Products.builder()
                .name("Gionee")
                .price(0.00)
                .build());

        addProdToList(Products.builder()
                .name("Apple")
                .price(0.00)
                .build());

        addProdToList(Products.builder()
                .name("Infinix")
                .price(0.00)
                .build());
    }

    private void addProdToList(Products products) {
        productList.add(products);
    }

    @Override
    public List<Products> getProductList() {
        return productList;
    }

}


