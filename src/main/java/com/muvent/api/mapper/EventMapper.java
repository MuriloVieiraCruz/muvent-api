package com.muvent.api.mapper;

import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.event.dto.DetailedEventResponseDTO;
import com.muvent.api.domain.event.dto.EventRequestDTO;
import com.muvent.api.domain.event.dto.EventResponseDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

@Component
public class EventMapper {

    public static Event toEvent(EventRequestDTO eventDTO) {

        return Event.builder()
                .title(eventDTO.title())
                .description(eventDTO.description())
                .imgUrl("")
                .eventUrl(eventDTO.eventUrl())
                .initialDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(eventDTO.initialDateTime()), ZoneId.systemDefault()))
                .finalDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(eventDTO.finalDateTime()), ZoneId.systemDefault()))
                .remote(eventDTO.remote())
                .build();
    }

    public static EventResponseDTO toEventResponse(Event event) {

        return new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getInitialDate(),
                event.getFinalDate(),
                event.getImgUrl(),
                event.getEventUrl(),
                event.getRemote(),
                event.getAddress() != null ? event.getAddress().getCity() : "",
                event.getAddress() != null ? event.getAddress().getUf() : ""
        );
    }

    public static DetailedEventResponseDTO toDetailedEventResponse(Event event) {

        return new DetailedEventResponseDTO(
                event.getTitle(),
                event.getDescription(),
                event.getInitialDate(),
                event.getFinalDate(),
                event.getImgUrl(),
                event.getEventUrl(),
                event.getRemote(),
                event.getAddress() != null ? event.getAddress().getCity() : "",
                event.getAddress() != null ? event.getAddress().getUf() : "",
                new ArrayList<>()
        );
    }
}