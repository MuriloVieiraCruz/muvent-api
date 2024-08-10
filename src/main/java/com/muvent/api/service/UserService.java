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

    public UserResponseDTO findUser(String userCpf) {
        return UserMapper.toUserResponseDTO(findByCpf(userCpf));
    }

    @Transactional
    public UserResponseDTO updateUser(String userCpf, UserUpdateDTO userUpdateDTO) {
        User user = findByCpf(userCpf);
        userUpdateValidation.validate(user, userUpdateDTO);

        return UserMapper.toUserResponseDTO(userRepository.save(user));
    }

    public User findUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Test Error"));
    }

    @Transactional
    public void deleteUser(String userCpf) {
        User user = findByCpf(userCpf);
        user.setActive(false);
        userRepository.save(user);
    }

    private User findByCpf(String userCpf) {
        return userRepository.findByCpf(userCpf).orElseThrow(() -> new RuntimeException("Test Error"));
    }
}
