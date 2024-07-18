package com.muvent.api.domain.event;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Table(name = "event")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    private String imgUrl;

    private String eventUrl;

    private Boolean remote;

    private Date date;
}
