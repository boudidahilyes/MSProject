package com.msproject.cart.service;

import com.msproject.cart.dto.request.CartRequest;
import com.msproject.cart.dto.response.ProductResponse;
import com.msproject.cart.entity.Cart;
import com.msproject.cart.product.Product;
import com.msproject.cart.product.ProductClient;
import com.msproject.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductClient productClient;
    public Cart addCart(Cart cart) {
        Cart cart1 = cartRepository.findByProductIdAndUserId(cart.getProductId(),cart.getUserId());
        if (cart1 != null) {
            cart1.setQuantity(cart1.getQuantity() + cart.getQuantity());
            cart = cart1;
        }
        return cartRepository.save(cart);
    }

    public List<ProductResponse> getCartOfAUser(String userId) {
        List<Cart> carts = cartRepository.findAllByUserId(userId);
        List<ProductResponse> products = new ArrayList<>();
        for (Cart cart : carts) {
            Product product=productClient.getProductById(cart.getProductId());
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setQuantity(cart.getQuantity());
            products.add(productResponse);
        }
        return products;
    }

    public Cart updateCart(int productId, String userId , int quantity) {
        Cart cart = cartRepository.findByUserIdAndProductId(userId,productId);
        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }

    public void deleteCart(int productId , String userId) {
        Cart cart = cartRepository.findByUserIdAndProductId(userId,productId);
        cartRepository.delete(cart);
    }
}
