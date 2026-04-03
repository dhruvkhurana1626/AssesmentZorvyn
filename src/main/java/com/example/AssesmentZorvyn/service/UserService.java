package com.example.AssesmentZorvyn.service;

import com.example.AssesmentZorvyn.dao.UserDao;
import com.example.AssesmentZorvyn.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public User createUser(User user) {
        user.setActive(true);
        return userDao.save(user);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());

        return userDao.save(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
