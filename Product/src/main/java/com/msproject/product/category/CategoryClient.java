package com.msproject.product.category;

import msproject.category.category.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "category")
public interface CategoryClient {
    @GetMapping("/api/v1/category/{id}")
    Category getCategoryById(@PathVariable("id") int id);
}
