package com.msproject.product.product;

public record ProductDTO(
        int id,
        String name,
        String description,
        double price,
        int quantity,
        int categoryId,
        String categoryName,
        String categoryDescription
     ) {

}
