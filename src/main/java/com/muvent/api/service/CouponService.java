package com.muvent.api.service;

import com.muvent.api.domain.coupon.Coupon;
import com.muvent.api.domain.coupon.dto.CouponRequestDTO;
import com.muvent.api.domain.coupon.dto.CouponResponseDTO;
import com.muvent.api.domain.event.Event;
import com.muvent.api.mapper.CouponMapper;
import com.muvent.api.mapper.ICouponMapper;
import com.muvent.api.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository repository;

    public Coupon createCoupon(Event event, CouponRequestDTO couponRequestDTO) {
        Coupon coupon = ICouponMapper.INSTANCE.toCoupon(couponRequestDTO);
        coupon.setEvent(event);

        return repository.save(coupon);
    }

    public List<CouponResponseDTO> consultCoupons(UUID eventId, LocalDate now) {
        return repository.findByEventIdAndValidAfter(eventId, now).stream()
                .map(ICouponMapper.INSTANCE::toCouponResponse)
                .toList();
    }
}
