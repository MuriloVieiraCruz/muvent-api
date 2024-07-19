package com.muvent.api.mapper;

import com.muvent.api.domain.coupon.Coupon;
import com.muvent.api.domain.coupon.dto.CouponRequestDTO;
import com.muvent.api.domain.coupon.dto.CouponResponseDTO;
import com.muvent.api.domain.event.Event;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;

@Component
public class CouponMapper {

    public static Coupon toCoupon(CouponRequestDTO requestDTO) {
        return Coupon.builder()
                .code(requestDTO.code())
                .discount(requestDTO.discount())
                .valid(Instant.ofEpochMilli(requestDTO.valid()).atZone(ZoneId.systemDefault()).toLocalDate())
                .event(new Event())
                .build();
    }

    public static CouponResponseDTO toCouponResponse(Coupon coupon) {
        return new CouponResponseDTO(
                coupon.getId(),
                coupon.getCode(),
                coupon.getDiscount(),
                coupon.getValid()
        );
    }
}
