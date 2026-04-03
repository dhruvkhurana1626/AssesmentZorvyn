package com.example.AssesmentZorvyn.controller;

import com.example.AssesmentZorvyn.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDao userDao;


}
