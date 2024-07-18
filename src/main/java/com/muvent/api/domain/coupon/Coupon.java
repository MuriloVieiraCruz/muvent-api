package com.muvent.api.domain.coupon;

import com.muvent.api.domain.event.Event;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Table(name = "coupon")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue
    private UUID id;

    private String code;

    private Integer discount;

    private Date valid;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
