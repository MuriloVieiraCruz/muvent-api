package com.muvent.api.service;

import com.muvent.api.domain.user.User;
import com.muvent.api.domain.user.dto.UserRequestDTO;
import com.muvent.api.domain.user.dto.UserResponseDTO;
import com.muvent.api.mapper.UserMapper;
import com.muvent.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserRequestDTO userRequestDTO) {
        userRepository.save(UserMapper.toUser(userRequestDTO));
    }

    public UserResponseDTO findUser(String userCpf) {
        return UserMapper.toUserResponseDTO(findByCpf(userCpf));
    }

    public UserResponseDTO updateUser(String userCpf, UserRequestDTO userRequestDTO) {
        User user = findByCpf(userCpf);
        User user2 = UserMapper.toUser(userRequestDTO);
        user2.setId(user.getId());
        userRepository.save(user2);
        return UserMapper.toUserResponseDTO(user2);
    }

    private User findByCpf(String userCpf) {
        return userRepository.findByCpf(userCpf).orElseThrow(() -> new RuntimeException("Test Error"));
    }

    public void deleteUser(String userCpf) {
        User user = findByCpf(userCpf);
        user.setActive(false);
        userRepository.save(user);
    }
}
