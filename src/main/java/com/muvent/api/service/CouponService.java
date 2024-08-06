package com.muvent.api.service;

import com.muvent.api.domain.coupon.Coupon;
import com.muvent.api.domain.coupon.dto.CouponRequestDTO;
import com.muvent.api.domain.coupon.dto.CouponResponseDTO;
import com.muvent.api.domain.event.Event;
import com.muvent.api.mapper.CouponMapper;
import com.muvent.api.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public Coupon createCoupon(Event event, CouponRequestDTO couponRequestDTO) {
        Coupon coupon = CouponMapper.toCoupon(couponRequestDTO);
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }

    public List<CouponResponseDTO> consultCoupons(UUID eventId, LocalDate now) {
        return couponRepository.findByEventIdAndValidAfter(eventId, now).stream()
                .map(CouponMapper::toCouponResponse)
                .toList();
    }

    public Long getCouponDiscount(UUID couponId, LocalDate now) {
        return couponRepository.findByIdAndValidAfter(couponId, now).orElseThrow(() -> new RuntimeException("Test Coupon"));
    }
}
