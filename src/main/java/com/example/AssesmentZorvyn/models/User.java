package com.example.AssesmentZorvyn.models;

import com.example.AssesmentZorvyn.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private Boolean active;
}
