package com.muvent.api.service;

import com.muvent.api.domain.coupon.Coupon;
import com.muvent.api.domain.coupon.dto.CouponRequestDTO;
import com.muvent.api.domain.event.Event;
import com.muvent.api.mapper.CouponMapper;
import com.muvent.api.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository repository;

    private final EventService eventService;

    public Coupon createCoupon(UUID eventId, CouponRequestDTO couponRequestDTO) {
        Event event = eventService.findEventById(eventId);
        Coupon coupon = CouponMapper.toCoupon(couponRequestDTO);
        coupon.setEvent(event);

        return repository.save(coupon);
    }
}
