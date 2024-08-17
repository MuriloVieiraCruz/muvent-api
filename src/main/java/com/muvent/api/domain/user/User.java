package com.muvent.api.domain.user;

import com.muvent.api.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "tb_user")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String cpf;
    private LocalDate birthDate;
    private boolean active;
}
