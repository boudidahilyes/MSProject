package com.msproject.cart.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product")
public interface ProductClient {
    @GetMapping("/api/v1/product/{id}")
    Product getProductById(@PathVariable("id") int id);
}
