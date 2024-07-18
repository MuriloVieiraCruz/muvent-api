package com.muvent.api.domain.coupon.dto;

public record CouponRequestDTO(
        String code,
        Integer discount,
        Long valid
) {
}
