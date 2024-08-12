package com.muvent.api.mapper;

import com.muvent.api.domain.user.User;
import com.muvent.api.domain.user.dto.UserRequestDTO;
import com.muvent.api.domain.user.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User toUser(UserRequestDTO userRequestDTO) {
        return User.builder()
                .firstName(userRequestDTO.firstName())
                .lastName(userRequestDTO.lastName())
                .password(userRequestDTO.password())
                .email(userRequestDTO.email())
                .cpf(userRequestDTO.cpf())
                .birthDate(userRequestDTO.birthDate())
                .active(true)
                .build();
    }

    public static UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCpf(),
                user.getBirthDate()
        );
    }
}
