package com.example.AssesmentZorvyn.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String role;
    private String password;

}
