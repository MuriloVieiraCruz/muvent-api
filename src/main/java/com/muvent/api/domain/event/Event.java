package com.muvent.api.domain.event;

import com.muvent.api.domain.address.Address;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate date;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Address address;
}
