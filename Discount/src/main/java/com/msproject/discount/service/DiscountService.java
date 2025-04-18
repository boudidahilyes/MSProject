package com.msproject.discount.service;

import com.msproject.discount.dto.request.DiscountRequest;
import com.msproject.discount.dto.response.DiscountResponse;
import com.msproject.discount.entity.Discount;
import com.msproject.discount.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;

    public Discount createDiscount(Discount discount) {
        discount.setActive(true);
        return discountRepository.save(discount);
    }

    public boolean isDiscountValid(Discount discount) {
        if (discountRepository.findByCode(discount.getCode()) == null) {
            return false;
        }
        if (!discount.isActive()) {
            return false;
        }
        return true;
    }

    public DiscountResponse applyDiscount(DiscountRequest discountRequest) {
        Discount discount = discountRepository.findByCode(discountRequest.getCode());
        if (discount != null) {
            if (isDiscountValid(discount)) {
                return new DiscountResponse(discount.getPercentage());
            }
        }
        return null;
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public void deleteDiscount(int id) {
        Discount discount = discountRepository.findById(id).orElse(null);
        discountRepository.delete(discount);
    }

    public Discount changeActiveDiscount(int id) {
        Discount discount = discountRepository.findById(id).orElse(null);
        if (discount.isActive()) discount.setActive(false);
        else discount.setActive(true);
        return discountRepository.save(discount);
    }
}
