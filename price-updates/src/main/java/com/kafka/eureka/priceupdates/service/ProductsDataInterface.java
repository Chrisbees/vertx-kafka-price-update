package com.kafka.eureka.priceupdates.service;

import com.kafka.eureka.priceupdates.model.Products;

import java.util.List;

public interface ProductsDataInterface {
    List<Products> getProductList();
}
