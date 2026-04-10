package com.example.AssesmentZorvyn.controller;

import com.example.AssesmentZorvyn.dto.request.UserRequest;
import com.example.AssesmentZorvyn.dto.response.UserResponse;
import com.example.AssesmentZorvyn.enums.Role;
import com.example.AssesmentZorvyn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/user")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    //create new user
    @PostMapping
    public ResponseEntity createNewUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.createNewUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    //update user Role
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserRole(@PathVariable Long userId,
                                            @RequestParam Role role){
        String updateMsg = userService.updateUserRole(userId,role);
        return ResponseEntity.ok(updateMsg);
    }

    //get all user
    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    //update user status - active or not active
    @PutMapping("/status/{userId}")
    public String updateUserStatus(@PathVariable Long userId,
                                   @RequestParam Boolean active){
        return userService.updateUserStatus(userId,active);
    }
}
