package com.example.AssesmentZorvyn.service;

import com.example.AssesmentZorvyn.dao.UserDao;
import com.example.AssesmentZorvyn.dto.request.UserRequest;
import com.example.AssesmentZorvyn.dto.response.UserResponse;
import com.example.AssesmentZorvyn.enums.Role;
import com.example.AssesmentZorvyn.models.User;
import com.example.AssesmentZorvyn.transformation.UserTransformer;
import com.example.AssesmentZorvyn.utility.Validation;
import lombok.RequiredArgsConstructor;
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

    //Creating New User - Only Admin can Access It
    public UserResponse createNewUser(UserRequest userRequest) {

        validation.validateNewUser(userRequest);
        String password = userRequest.getPassword();

        User user = UserTransformer.userRequestToUser(userRequest);
        user.setPassword(passwordEncoder.encode(password));

        UserResponse userResponse = UserTransformer.userToUserResponse(userDao.save(user));
        userResponse.setPassword(password);

        return userResponse;
    }

    //Updating Existing User - Only Admin can Access It
    public String updateUserRole(Long userId, Role role) {

        User user = validation.checkIfUserExist_byId_ReturnUser(userId);

        user.setRole(role);
        userDao.save(user);

        return "Role of " + user.getName() + " has been changed";

    }

    //List of All the Users
    public List<UserResponse> getAllUsers() {
        List<User> userList = userDao.findAllByActive();
        List<UserResponse> userResponsesList = new ArrayList<>();
        for(User user : userList){
            userResponsesList.add(UserTransformer.userToUserResponse(user));
        }
        return userResponsesList;
    }


    //Deleting user Data by Id
    public String updateUserStatus(Long userId,Boolean active) {
        User user = validation.checkIfUserExist_byId_ReturnUser(userId);

        if (user.getActive().equals(active)) {
            return active ? "User already active" : "User already inactive";
        }

        user.setActive(active);
        userDao.save(user);

        return active ? "User activated successfully" : "User deactivated successfully";
     }
}
