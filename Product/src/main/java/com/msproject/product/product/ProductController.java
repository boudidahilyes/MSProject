package com.msproject.product.product;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @PathParam("minPrice") Double minPrice,
            @PathParam("maxPrice") Double maxPrice,
            @PathParam("category") Integer category,
            @PathParam("name") String name
    ) {
        List<ProductDTO> products = productService.getAllProducts();
        if(minPrice != null){
            products = products.stream().filter(p -> p.price() >= minPrice).collect(Collectors.toList());
        }
        if(maxPrice != null){
            products = products.stream().filter(p -> p.price() <= maxPrice).collect(Collectors.toList());
        }
        if(category != null){
            products = products.stream().filter(p -> p.categoryId() == category).collect(Collectors.toList());
        }
        if(name != null){
            products = products.stream().filter(p -> p.name().contains(name)).collect(Collectors.toList());
        }

        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }
    @PutMapping()
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateQuantity(@PathVariable int id, @PathParam("quantity") int quantity) {
        if(productService.hasEnoughProducts(id, quantity)) {
            productService.modifyQuantity(id, quantity);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>("Not Enough Products", HttpStatus.BAD_REQUEST);
    }
}
