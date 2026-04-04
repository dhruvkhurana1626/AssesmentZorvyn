package com.example.AssesmentZorvyn.utility;

import com.example.AssesmentZorvyn.dao.FinancialRecordsDao;
import com.example.AssesmentZorvyn.dao.UserDao;
import com.example.AssesmentZorvyn.dto.request.UserRequest;
import com.example.AssesmentZorvyn.enums.Role;
import com.example.AssesmentZorvyn.exception.BusinessException;
import com.example.AssesmentZorvyn.exception.InvalidRequestException;
import com.example.AssesmentZorvyn.exception.ResourceNotFoundException;
import com.example.AssesmentZorvyn.models.FinancialRecord;
import com.example.AssesmentZorvyn.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Validation {

    private final UserDao userDao;
    private final FinancialRecordsDao financialRecordsDao;

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

    public void checkifUserActive(Boolean active) {
        if(!active) throw new InvalidRequestException("User is not Active - Dont assist , and Report it to the Admin First");
    }

    public void checkifAdmin(String email) {
        if(userDao.findByEmail(email).get().getRole()!= Role.ADMIN)
            throw new BusinessException("You dont have the authority to perform this action");
    }

    public User getUserByEmail(String email) {
        return userDao.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User not found by Email Id"));
    }

    public FinancialRecord getFinancialRecordById(Long id) {
        return financialRecordsDao.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(" Record with id :- " + id + " dont exist"));
    }
}
