package com.msproject.cart.controller;

import com.msproject.cart.dto.request.CartRequest;
import com.msproject.cart.entity.Cart;
import com.msproject.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.addCart(cart));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getCartOfAUser(userId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteCart(@PathVariable int productId,@RequestParam String userId) {
        cartService.deleteCart(productId,userId);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @PutMapping("/{productId}/{userId}")
    public ResponseEntity<?> updateCart(@PathVariable int productId, @PathVariable String userId , @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateCart(productId, userId,quantity));
    }
}
