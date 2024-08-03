package com.muvent.api.domain.ticket;

import com.muvent.api.domain.BaseEntity;
import com.muvent.api.domain.event.Event;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "tb_ticket")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Ticket extends BaseEntity {

    private Long price;
    private Long quantity;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
