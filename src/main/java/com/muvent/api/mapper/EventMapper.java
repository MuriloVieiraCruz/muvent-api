package com.muvent.api.mapper;

import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.event.dto.DetailedEventResponseDTO;
import com.muvent.api.domain.event.dto.EventRequestDTO;
import com.muvent.api.domain.event.dto.EventResponseDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;
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
                .date(Instant.ofEpochMilli(eventDTO.date()).atZone(ZoneId.systemDefault()).toLocalDate())
                .remote(eventDTO.remote())
                .build();
    }

    public static EventResponseDTO toEventResponse(Event event) {

        return new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
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
                event.getDate(),
                event.getImgUrl(),
                event.getEventUrl(),
                event.getRemote(),
                event.getAddress() != null ? event.getAddress().getCity() : "",
                event.getAddress() != null ? event.getAddress().getUf() : "",
                new ArrayList<>()
        );
    }
}
