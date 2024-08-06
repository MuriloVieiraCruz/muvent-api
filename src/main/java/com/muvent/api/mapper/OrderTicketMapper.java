package com.muvent.api.mapper;

import com.muvent.api.domain.orderTicket.OrderTicket;
import com.muvent.api.domain.orderTicket.dto.OrderTicketResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderTicketMapper {

    public static OrderTicketResponseDTO toOrderTicketResponse(OrderTicket orderTicket) {
        return new OrderTicketResponseDTO(
                orderTicket.getId(),
                orderTicket.getTotalAmount(),
                orderTicket.getQuantity(),
                UserMapper.toUserResponseDTO(orderTicket.getUser()),
                TicketMapper.toTicketResponse(orderTicket.getTicket())
        );
    }
}
