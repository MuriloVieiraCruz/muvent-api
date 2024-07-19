package com.muvent.api.controller;

import com.muvent.api.domain.event.dto.EventRequestDTO;
import com.muvent.api.domain.event.dto.EventResponseDTO;
import com.muvent.api.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<EventResponseDTO> create(@ModelAttribute EventRequestDTO eventRequestDTO

    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEvent(eventRequestDTO));
    }

    @GetMapping()
    public ResponseEntity<List<EventResponseDTO>> getUpcomingEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUpcomingEvents(page, size));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventResponseDTO>> getFilteredEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate

    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getFilteredEvents(
                page,
                size,
                title,
                city,
                uf,
                startDate,
                endDate
        ));
    }
}
