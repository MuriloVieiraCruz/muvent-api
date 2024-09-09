package com.muvent.api.domain.event;

import com.muvent.api.domain.BaseEntity;
import com.muvent.api.domain.address.Address;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "tb_event")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Event extends BaseEntity implements Serializable {

    private String title;
    private String description;
    private String imgUrl;
    private String eventUrl;
    private Boolean remote;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Address address;
}
