package com.muvent.api.controller;

import com.muvent.api.controller.documentation.EventControllerDocumentation;
import com.muvent.api.domain.coupon.dto.CouponRequestDTO;
import com.muvent.api.domain.coupon.dto.CouponResponseDTO;
import com.muvent.api.domain.event.dto.*;
import com.muvent.api.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController implements EventControllerDocumentation {

    private final EventService service;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<EventResponseDTO> create(
             @ModelAttribute @Valid EventRequestDTO eventRequestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEvent(eventRequestDTO));
    }

    @GetMapping("/detail/{eventId}")
    public ResponseEntity<DetailedEventResponseDTO> getDetailedEvents(@PathVariable UUID eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEventDetails(eventId));
    }

    @GetMapping()
    public ResponseEntity<List<EventResponseDTO>> getUpcomingEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUpcomingEvents(page, size));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventResponseDTO>> getFilteredEvents(@ModelAttribute EventFilterDTO eventFilterDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getFilteredEvents(eventFilterDTO));
    }

    @GetMapping("/filter/events-nearby")
    public ResponseEntity<List<EventResponseDTO>> getNearbyEvents(@RequestBody @Valid LocationDTO locationDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getNearbyEvents(locationDTO));
    }

    @PostMapping("/coupon/{eventId}")
    public ResponseEntity<CouponResponseDTO> createCoupon(@PathVariable UUID eventId, @RequestBody CouponRequestDTO couponRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCouponByEventId(eventId, couponRequestDTO));
    }

}
