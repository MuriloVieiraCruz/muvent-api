package com.muvent.api.domain.orderTicket;

import com.muvent.api.domain.BaseEntity;
import com.muvent.api.domain.ticket.Ticket;
import com.muvent.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_order_ticket")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderTicket extends BaseEntity {

    private Long totalAmount;
    private Long quantity;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
