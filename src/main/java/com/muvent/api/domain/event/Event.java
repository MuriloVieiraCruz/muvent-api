package com.muvent.api.domain.event;

import com.muvent.api.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

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
    private String address;

    @Column(columnDefinition = "Geometry")
    private Point location;
}
