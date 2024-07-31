package com.muvent.api.strategy.userStrategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.muvent.api.domain.user.User;

public interface EmailSenderProducer {

    void send(User user);
}
