package com.muvent.api.repository;

import com.muvent.api.domain.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {

    List<Coupon> findByEventIdAndValidAfter(UUID eventId, LocalDate now);

    @Query("SELECT c.discount FROM Coupon c WHERE c.id = :couponId AND c.valid >= :now")
    Optional<Long> findByIdAndValidAfter(UUID couponId, LocalDate now);
}
