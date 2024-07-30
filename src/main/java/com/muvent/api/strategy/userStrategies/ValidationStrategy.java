package com.muvent.api.strategy.userStrategies;

import com.muvent.api.domain.user.User;

public interface ValidationStrategy {

    boolean validate(User user);
}
