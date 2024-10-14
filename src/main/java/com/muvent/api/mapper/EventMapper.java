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
                .initialDate(Instant.ofEpochMilli(eventDTO.initialDateTime()).atZone(ZoneId.systemDefault()).toLocalDateTime())
                .finalDate(Instant.ofEpochMilli(eventDTO.finalDateTime()).atZone(ZoneId.systemDefault()).toLocalDateTime())
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
                event.getAddress(),
                event.getGeoLocalization().getLatitude(),
                event.getGeoLocalization().getLongitude()
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
                event.getAddress() != null ? event.getAddress() : "",
                new ArrayList<>()
        );
    }
}