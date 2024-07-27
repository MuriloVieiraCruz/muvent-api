package com.muvent.api.mapper;

import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.event.dto.DetailedEventResponseDTO;
import com.muvent.api.domain.event.dto.EventRequestDTO;
import com.muvent.api.domain.event.dto.EventResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Mapper
public interface IEventMapper {

    IEventMapper INSTANCE = Mappers.getMapper(IEventMapper.class);

    @Mapping(target = "imgUrl", expression = "java(eventDTO.image().getOriginalFilename())")
    @Mapping(target = "date", source = "date", qualifiedByName = "mapDate")
    Event toEvent(EventRequestDTO eventDTO);

    @Mapping(target = "city", expression = "java(event.getAddress() != null ? event.getAddress().getCity() : \"\")")
    @Mapping(target = "uf", expression = "java(event.getAddress() != null ? event.getAddress().getUf() : \"\")")
    EventResponseDTO toEventResponse(Event event);

    @Mapping(target = "city", expression = "java(event.getAddress() != null ? event.getAddress().getCity() : \"\")")
    @Mapping(target = "uf", expression = "java(event.getAddress() != null ? event.getAddress().getUf() : \"\")")
    @Mapping(target = "couponResponse", expression = "java(new ArrayList<>())")
    DetailedEventResponseDTO toDetailedEventResponse(Event event);

    default LocalDate mapDate(Long date) {
        return Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
