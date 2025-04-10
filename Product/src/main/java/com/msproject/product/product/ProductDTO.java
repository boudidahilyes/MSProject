package com.msproject.product.product;

public record ProductDTO(
        int id,
        String name,
        String description,
        int price,
        int quantity,
        int categoryId,
        String categoryName,
        String categoryDescription
     ) {

}
