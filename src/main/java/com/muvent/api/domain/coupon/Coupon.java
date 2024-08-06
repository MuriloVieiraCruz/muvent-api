package com.muvent.api.domain.coupon;

import com.muvent.api.domain.BaseEntity;
import com.muvent.api.domain.event.Event;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "tb_coupon")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Coupon extends BaseEntity {

    private String code;
    private Long discount;
    private LocalDate valid;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
