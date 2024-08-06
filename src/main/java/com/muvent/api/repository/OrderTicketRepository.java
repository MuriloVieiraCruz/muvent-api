package com.muvent.api.repository;

import com.muvent.api.domain.orderTicket.OrderTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderTicketRepository extends JpaRepository<OrderTicket, UUID> {
}
