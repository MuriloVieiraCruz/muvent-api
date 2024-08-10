package com.muvent.api.controller;

import com.muvent.api.domain.user.dto.UserRequestDTO;
import com.muvent.api.domain.user.dto.UserResponseDTO;
import com.muvent.api.domain.user.dto.UserUpdateDTO;
import com.muvent.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UserResponseDTO> findUserBy(@PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(cpf));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String cpf, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(cpf, userUpdateDTO));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteUser(@PathVariable String cpf) {
        userService.deleteUser(cpf);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
