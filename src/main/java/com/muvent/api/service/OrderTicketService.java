package com.muvent.api.service;

import com.muvent.api.domain.orderTicket.OrderTicket;
import com.muvent.api.domain.orderTicket.dto.OrderTicketRequestDTO;
import com.muvent.api.domain.orderTicket.dto.OrderTicketResponseDTO;
import com.muvent.api.domain.ticket.Ticket;
import com.muvent.api.domain.user.User;
import com.muvent.api.mapper.OrderTicketMapper;
import com.muvent.api.repository.OrderTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderTicketService {

    private final OrderTicketRepository orderTicketRepository;
    private final CouponService couponService;

    public OrderTicketResponseDTO createTicketOrder(Ticket ticketFound, User userFound, OrderTicketRequestDTO orderTicketRequestDTO) {
        long totalAmount = ticketFound.getPrice() * orderTicketRequestDTO.quantity();

        if (orderTicketRequestDTO.couponId() != null) {
            Long couponDiscount = couponService.getCouponDiscount(orderTicketRequestDTO.couponId(), orderTicketRequestDTO.purchaseDate());

            totalAmount = totalAmount - couponDiscount;
        }

        OrderTicket orderTicket = OrderTicket.builder()
                .quantity(orderTicketRequestDTO.quantity())
                .totalAmount(totalAmount)
                .user(userFound)
                .ticket(ticketFound)
                .build();

        return OrderTicketMapper.toOrderTicketResponse(orderTicketRepository.save(orderTicket));
    }

    public OrderTicketResponseDTO findOrderTicket(UUID orderId) {
        return OrderTicketMapper.toOrderTicketResponse(getOrderTicket(orderId));
    }

    private OrderTicket getOrderTicket(UUID orderId) {
        return orderTicketRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Test OrderTicket"));
    }

    public void deleteOrderTicket(UUID orderId) {
        OrderTicket orderTicket = getOrderTicket(orderId);
        orderTicket.setActive(false);
        orderTicketRepository.save(orderTicket);
    }
}
