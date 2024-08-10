package com.muvent.api.strategy.userStrategies.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muvent.api.domain.user.User;
import com.muvent.api.mapper.UserMapper;
import com.muvent.api.strategy.userStrategies.EmailSenderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSenderProducerImpl implements EmailSenderProducer {

    //The @Qualifier annotation can be used for separate de bean for each queue

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    @Value("${rabbit.name.exchange}")
    private String exchangeEmail;

    @Value("${rabbit.name.routing_key}")
    private String routingKey;

    @Override
    public void send(User user) {
        amqpTemplate.convertAndSend(
                exchangeEmail,
                routingKey,
                UserMapper.toUserResponseDTO(user)
        );
    }

}
