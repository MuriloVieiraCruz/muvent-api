package com.muvent.api.controller;

import com.muvent.api.domain.coupon.Coupon;
import com.muvent.api.domain.coupon.dto.CouponRequestDTO;
import com.muvent.api.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService service;
    
    @PostMapping("/event/{eventId}")
    public ResponseEntity<Coupon> create(@PathVariable UUID eventId, @RequestBody CouponRequestDTO couponRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCoupon(eventId, couponRequestDTO));
    }
}
