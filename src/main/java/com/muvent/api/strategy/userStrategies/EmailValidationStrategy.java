package com.muvent.api.strategy.userStrategies;

import com.muvent.api.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class EmailValidationStrategy implements ValidationStrategy {

    @Override
    public boolean validate(User user) {
        return false;
    }
}
