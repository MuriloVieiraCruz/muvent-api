package com.muvent.api.domain.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.muvent.api.domain.BaseEntity;
import com.muvent.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;

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

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(columnDefinition = "Geometry")
    private Point location;
}
