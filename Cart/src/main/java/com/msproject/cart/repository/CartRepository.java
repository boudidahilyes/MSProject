package com.msproject.cart.repository;

import com.msproject.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(String userId);
    Cart findByUserIdAndProductId(String userId, int productId);
    Cart findByProductIdAndUserId(int productId, String userId);
}
