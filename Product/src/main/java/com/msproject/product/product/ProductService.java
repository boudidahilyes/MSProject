package com.msproject.product.product;

import com.msproject.product.category.Category;
import com.msproject.product.category.CategoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    private final CategoryClient categoryClient;


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public ProductDTO getProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        Category category = categoryClient.getCategoryById(product.getIdCategory());
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getIdCategory(),
                category.getName(),
                category.getDescription()
        );
    }
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            Category category = categoryClient.getCategoryById(product.getIdCategory());
            ProductDTO productDTO = new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getIdCategory(),
                    category.getName(),
                    category.getDescription()
            );
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
    public Product modifyQuantity(int id, int quantity) {
        Product product = productRepository.findById(id).get();
        product.setQuantity(product.getQuantity() + quantity);
        return productRepository.save(product);
    }
    public boolean hasEnoughProducts(int id, int quantity) {
        Product product = productRepository.findById(id).get();
        return product.getQuantity() + quantity >= 0;
    }

}
