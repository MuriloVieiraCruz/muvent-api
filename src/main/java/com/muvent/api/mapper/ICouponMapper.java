package com.muvent.api.mapper;

import com.muvent.api.domain.coupon.Coupon;
import com.muvent.api.domain.coupon.dto.CouponRequestDTO;
import com.muvent.api.domain.coupon.dto.CouponResponseDTO;
import com.muvent.api.domain.event.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface ICouponMapper {

    ICouponMapper INSTANCE = Mappers.getMapper(ICouponMapper.class);

    @Mapping(target = "valid", source = "valid", qualifiedByName = "mapValid")
    @Mapping(target = "event", source = "event", qualifiedByName = "mapEvent")
    Coupon toCoupon(CouponRequestDTO requestDTO);

    CouponResponseDTO toCouponResponse(Coupon coupon);

    default LocalDate mapValid(Long valid) {
        return Instant.ofEpochMilli(valid).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    default Event mapEvent(CouponRequestDTO requestDTO) {
        return new Event();
    }
}
