package com.muvent.api.domain.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muvent.api.domain.BaseEntity;
import com.muvent.api.domain.event.Event;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name = "tb_address")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity implements Serializable {

    private String neighborhood;
    private String city;
    private String uf;
    private String patio;
    private String zipCode;
    private String number;
    private String complement;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;
}
