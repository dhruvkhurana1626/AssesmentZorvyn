package com.example.AssesmentZorvyn.service;

import com.example.AssesmentZorvyn.dao.UserDao;
import com.example.AssesmentZorvyn.dto.request.UserRequest;
import com.example.AssesmentZorvyn.dto.response.UserResponse;
import com.example.AssesmentZorvyn.enums.Role;
import com.example.AssesmentZorvyn.models.User;
import com.example.AssesmentZorvyn.transformation.UserTransformer;
import com.example.AssesmentZorvyn.utility.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Validation validation;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    // Get logged-in user
    private User getLoggedInUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return validation.getUserByEmail(email);
    }

    //Creating New User - Only Admin can Access It
    public UserResponse createNewUser(UserRequest userRequest) {

        validation.checkifAdmin(getLoggedInUser().getEmail());

        validation.validateNewUser(userRequest);
        String password = userRequest.getPassword();

        User user = UserTransformer.userRequestToUser(userRequest);
        user.setPassword(passwordEncoder.encode(password));

        UserResponse userResponse = UserTransformer.userToUserResponse(userDao.save(user));

        return userResponse;
    }

    //Updating Existing User - Only Admin can Access It
    public String updateUserRole(Long userId, Role role) {

        validation.checkifAdmin(getLoggedInUser().getEmail());

        User user = validation.checkIfUserExist_byId_ReturnUser(userId);

        user.setRole(role);
        userDao.save(user);

        return "Role of " + user.getName() + " has been changed to " + role ;

    }

    //List of All the Users
    public List<UserResponse> getAllUsers() {

        List<User> userList = userDao.findAll();
        List<UserResponse> userResponsesList = new ArrayList<>();
        for(User user : userList){
            userResponsesList.add(UserTransformer.userToUserResponse(user));
        }
        return userResponsesList;

    }


    //Updating User status
    public String updateUserStatus(Long userId,Boolean active) {

        validation.checkifAdmin(getLoggedInUser().getEmail());

        User user = validation.checkIfUserExist_byId_ReturnUser(userId);

        if (user.getActive().equals(active)) {
            return active ? "User already active" : "User already inactive";
        }

        user.setActive(active);
        userDao.save(user);

        return active ? "User activated successfully" : "User deactivated successfully";
     }
}
