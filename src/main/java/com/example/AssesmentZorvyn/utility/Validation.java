package com.example.AssesmentZorvyn.utility;

import com.example.AssesmentZorvyn.dao.UserDao;
import com.example.AssesmentZorvyn.dto.request.UserRequest;
import com.example.AssesmentZorvyn.exception.BusinessException;
import com.example.AssesmentZorvyn.exception.InvalidRequestException;
import com.example.AssesmentZorvyn.exception.ResourceNotFoundException;
import com.example.AssesmentZorvyn.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Validation {

    private final UserDao userDao;

    public static void checkIfActive(User user) {
        if(!user.getActive())
            throw new BusinessException("User not active cant perform any function on it");
    }

    public void validateNewUser(UserRequest userRequest){
        if(userDao.existsByEmail(userRequest.getEmail()))
            throw new InvalidRequestException("Email Already Registered");
    }

    public User checkIfUserExist_byId_ReturnUser(Long userId) {
        return userDao.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with id :- " + userId + " dont exist"));
    }
}
