package com.muvent.api.controller;

import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.event.dto.EventRequestDTO;
import com.muvent.api.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Event> create(@ModelAttribute EventRequestDTO eventRequestDTO

    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEvent(eventRequestDTO));
    }
}
