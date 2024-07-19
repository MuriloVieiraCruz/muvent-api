package com.muvent.api.domain.coupon.dto;

import java.time.LocalDate;
import java.util.UUID;

public record CouponResponseDTO(
        UUID id,
        String code,
        Integer discount,
        LocalDate valid

) {
}
