package com.muvent.api.domain.event.dto;

import com.muvent.api.domain.coupon.dto.CouponResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record DetailedEventResponseDTO(
        String title,
        String description,
        LocalDate date,
        String image,
        String eventUrl,
        Boolean remote,
        String city,
        String uf,
        List<CouponResponseDTO> couponResponse
) {
}
