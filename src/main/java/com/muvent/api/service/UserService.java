package com.muvent.api.service;

import com.muvent.api.domain.user.User;
import com.muvent.api.domain.user.dto.UserRequestDTO;
import com.muvent.api.domain.user.dto.UserResponseDTO;
import com.muvent.api.domain.user.dto.UserUpdateDTO;
import com.muvent.api.mapper.UserMapper;
import com.muvent.api.repository.UserRepository;
import com.muvent.api.strategy.userStrategies.EmailSenderProducer;
import com.muvent.api.strategy.userStrategies.UserUpdateValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EmailSenderProducer emailSenderProducer;
    private final UserRepository userRepository;
    private final UserUpdateValidation userUpdateValidation;

    @Transactional
    public void createUser(UserRequestDTO userRequestDTO) {
        User user = userRepository.save(UserMapper.toUser(userRequestDTO));
        emailSenderProducer.send(user);
    }

    @Cacheable(value = "users", key = "#userId")
    public UserResponseDTO findUser(UUID userId) {
        return UserMapper.toUserResponseDTO(findUserById(userId));
    }

    @Transactional
    @CachePut(value = "users", key = "#userId")
    public UserResponseDTO updateUser(UUID userId, UserUpdateDTO userUpdateDTO) {
        User user = findUserById(userId);
        userUpdateValidation.validate(user, userUpdateDTO);

        return UserMapper.toUserResponseDTO(userRepository.save(user));
    }

    public User findUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Test Error"));
    }

    @Transactional
    @CacheEvict(value = "users", key = "#userId", beforeInvocation = true)
    public void deleteUser(UUID userId) {
        User user = findUserById(userId);
        user.setActive(false);
        userRepository.save(user);
    }

}
