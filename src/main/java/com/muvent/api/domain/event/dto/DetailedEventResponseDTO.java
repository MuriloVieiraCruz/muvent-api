package com.muvent.api.domain.event.dto;

import com.muvent.api.domain.coupon.dto.CouponResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DetailedEventResponseDTO(
        String title,
        String description,
        LocalDateTime initialDate,
        LocalDateTime finalDate,
        String image,
        String eventUrl,
        Boolean remote,
        String address,
        List<CouponResponseDTO> couponResponse
) {
}
