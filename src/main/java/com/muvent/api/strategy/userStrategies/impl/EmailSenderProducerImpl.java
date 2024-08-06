package com.muvent.api.strategy.userStrategies.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muvent.api.domain.user.User;
import com.muvent.api.mapper.UserMapper;
import com.muvent.api.strategy.userStrategies.EmailSenderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSenderProducerImpl implements EmailSenderProducer {

    //The @Qualifier annotation can be used for separate de bean for each queue
    //To send the message, you can use the method convertAndSend(Queue name, Object value);

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    private static final String EXCHANGE_EMAIL = "send-email-exchange";
    private static final String ROUT_KEY = "email-send-rout-key";

    @Override
    public void send(User user) {
        try {
            amqpTemplate.convertAndSend(
                    EXCHANGE_EMAIL,
                    ROUT_KEY,
                    objectMapper.writeValueAsString(UserMapper.toUserResponseDTO(user))
            );
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Test Error");
        }
    }

}
