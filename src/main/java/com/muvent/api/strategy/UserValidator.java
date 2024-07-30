package com.muvent.api.strategy;

import com.muvent.api.domain.user.User;
import com.muvent.api.strategy.userStrategies.ValidationStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    private final List<ValidationStrategy> strategies = new ArrayList<>();

    public void addStrategy(ValidationStrategy strategy) {
        strategies.add(strategy);
    }

    public boolean validate(User user) {
        for (ValidationStrategy strategy : strategies) {
            if (!strategy.validate(user)) {
                return false;
            }
        }
        return true;
    }
}
