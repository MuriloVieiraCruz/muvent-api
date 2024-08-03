package com.muvent.api.domain.orderTicket;

import com.muvent.api.domain.BaseEntity;
import com.muvent.api.domain.ticket.Ticket;
import com.muvent.api.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "tb_order_ticket")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderTicket extends BaseEntity {

    private Long totalAmount;
    private Long quantity;

    @OneToMany
    private User user;

    @OneToMany
    private Ticket ticket;
}
