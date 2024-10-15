package com.muvent.api.controller.documentation;

import com.muvent.api.domain.coupon.dto.CouponRequestDTO;
import com.muvent.api.domain.coupon.dto.CouponResponseDTO;
import com.muvent.api.domain.event.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(name = "Event Management", description = "Operations related to managing events such as creation, updates, searches and deletion")
public interface EventControllerDocumentation {

    @Operation(
            description = "Register event",
            summary = "Responsible for registering an event, it can be face-to-face or remote, if it is not remote you will need the address information",
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
    ResponseEntity<EventResponseDTO> create(EventRequestDTO eventRequestDTO);

    @Operation(
            description = "Search event detailed",
            summary = "Responsible for searching an event and return the specific details and coupons (if any)",
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
    ResponseEntity<DetailedEventResponseDTO> getDetailedEvents(UUID eventId);

    @Operation(
            description = "Search upcoming events",
            summary = "Responsible for searching events that will going to happen after the current day",
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
    ResponseEntity<List<EventResponseDTO>> getUpcomingEvents(int page, int size);

    @Operation(
            description = "Search events with filter",
            summary = "Responsible for searching events being able to inform filters referring to date, title and city",
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
    ResponseEntity<List<EventResponseDTO>> getFilteredEvents(EventFilterDTO eventFilterDTO);

    @Operation(
            description = "Search events close to the client",
            summary = "Responsible for searching events in an specified area by the client, thus returning the events within it",
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
    ResponseEntity<List<EventResponseDTO>> getNearbyEvents(LocationDTO locationDTO);

    @Operation(
            description = "Register an event coupon",
            summary = "Responsible for registering an event coupon, it can be face-to-face or remote, if it is not remote you will need the address information",
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
    ResponseEntity<CouponResponseDTO> createCoupon(UUID eventId, CouponRequestDTO couponRequestDTO);
}
