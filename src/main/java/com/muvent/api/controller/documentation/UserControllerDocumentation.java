package com.muvent.api.controller.documentation;

import com.muvent.api.domain.user.dto.UserRequestDTO;
import com.muvent.api.domain.user.dto.UserResponseDTO;
import com.muvent.api.domain.user.dto.UserUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "User Management", description = "Operations related to managing users such as creation, updates, search and deletion")
public interface UserControllerDocumentation {

    @Operation(
            description = "Register user",
            summary = "Responsible for registering an user",
            responses = {
                    @ApiResponse(description = "Registration Successfully completed", responseCode = "201"),
                    @ApiResponse(
                            description = "Bad Request - Missing required field or invalid input",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "{\"error\": \"Missing required field: 'name'\"}"
                                    )
                            )
                    )
            }
    )
    ResponseEntity<Void> createUser(UserRequestDTO userRequestDTO);

    @Operation(
            description = "Search user detailed",
            summary = "Responsible for searching an user and return the specific details",
            responses = {
                    @ApiResponse(description = "Request Successfully completed", responseCode = "200"),
                    @ApiResponse(
                            description = "Bad Request - Missing required field or invalid input",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "{\"error\": \"Missing required field: 'name'\"}"
                                    )
                            )
                    )
            }
    )
    ResponseEntity<UserResponseDTO> findUserBy(UUID userId);

    @Operation(
            description = "Update user",
            summary = "Responsible for update an user and return the user updated",
            responses = {
                    @ApiResponse(description = "Update Successfully completed", responseCode = "200"),
                    @ApiResponse(
                            description = "Bad Request - Missing required field or invalid input",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "{\"error\": \"Missing required field: 'name'\"}"
                                    )
                            )
                    )
            }
    )
    ResponseEntity<UserResponseDTO> updateUser(UUID userId, UserUpdateDTO userUpdateDTO);

    @Operation(
            description = "Remove user",
            summary = "Change the client status to false and removes from consultations permanently",
            responses = {
                    @ApiResponse(description = "Remove Successfully completed", responseCode = "200"),
                    @ApiResponse(
                            description = "Bad Request - Missing required field or invalid input",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "{\"error\": \"Missing required field: 'name'\"}"
                                    )
                            )
                    )
            }
    )
    ResponseEntity<Void> deleteUser(UUID userId);
}
