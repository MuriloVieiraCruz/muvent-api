package com.muvent.api.mapper;

import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.event.dto.EventRequestDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EventMapper {

    public static Event toEvent(EventRequestDTO eventDTO) {
        return Event.builder()
                .title(eventDTO.title())
                .description(eventDTO.description())
                .imgUrl("")
                .eventUrl(eventDTO.eventUrl())
                .date(new Date(eventDTO.date()))
                .remote(eventDTO.remote())
                .build();
    }
}
