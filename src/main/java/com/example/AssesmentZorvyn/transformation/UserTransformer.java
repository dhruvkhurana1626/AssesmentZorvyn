package com.example.AssesmentZorvyn.transformation;

import com.example.AssesmentZorvyn.dto.request.UserRequest;
import com.example.AssesmentZorvyn.dto.response.UserResponse;
import com.example.AssesmentZorvyn.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public class UserTransformer {

    public PasswordEncoder passwordEncoder;

    public static User userRequestToUser(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .createdAt(LocalDateTime.now())
                .active(true)
                .role(userRequest.getRole())
                .build();

        return user;
    }

    public static UserResponse userToUserResponse(User user){
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();

        return userResponse;
    }
}
