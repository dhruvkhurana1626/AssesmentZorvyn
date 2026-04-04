package com.example.AssesmentZorvyn.utility;

import com.example.AssesmentZorvyn.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;

@RequiredArgsConstructor
public class Email {

    private final JavaMailSender javaMailSender;
    public static void sendEmail_ForNewUser(User user){

    }
}
