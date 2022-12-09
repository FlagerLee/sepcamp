package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.UserRegister;
import com.seclass.sepcamp.models.UserRegisterResult;
import com.seclass.sepcamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "register")
    UserRegisterResult registerRequestView(UserRegister registerInfo) {
        return userService.register(registerInfo);
    }
}
