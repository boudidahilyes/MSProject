package com.msproject.discount.controller;

import com.msproject.discount.dto.request.DiscountRequest;
import com.msproject.discount.dto.response.DiscountResponse;
import com.msproject.discount.entity.Discount;
import com.msproject.discount.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("discount")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping("/add")
    public ResponseEntity<Discount> addDiscount(@RequestBody Discount discount) {
        return ResponseEntity.ok(discountService.createDiscount(discount));
    }

    @GetMapping("/apply")
    public ResponseEntity<?> getDiscountPercentage(@RequestBody DiscountRequest discountRequest) {
        if (discountService.applyDiscount(discountRequest) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(discountService.applyDiscount(discountRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Discount>> getAllDiscount() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscount(@PathVariable int id) {
        discountService.deleteDiscount(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change/{id}")
    public ResponseEntity<?> changeDiscount(@PathVariable int id) {
        return ResponseEntity.ok(discountService.changeActiveDiscount(id));
    }
}
