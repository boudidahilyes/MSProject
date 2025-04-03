package com.msproject.cart.service;

import com.msproject.cart.dto.request.CartRequest;
import com.msproject.cart.entity.Cart;
import com.msproject.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Cart addCart(Cart cart) {
        Cart cart1 = cartRepository.findByProductIdAndUserId(cart.getProductId(),cart.getUserId());
        if (cart1 != null) {
            cart1.setQuantity(cart1.getQuantity() + cart.getQuantity());
            cart = cart1;
        }
        return cartRepository.save(cart);
    }

    public List<Cart> getCartOfAUser(String userId) {
        return cartRepository.findAllByUserId(userId);
    }

    public Cart updateCart(Long id, CartRequest cartRequest) {
        Cart cart = cartRepository.findById(id).orElse(null);
        cart.setQuantity(cartRequest.getQuantity());
        return cartRepository.save(cart);
    }

    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        cartRepository.delete(cart);
    }
}
