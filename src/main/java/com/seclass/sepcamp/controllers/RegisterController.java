package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.UserRegister;
import com.seclass.sepcamp.models.UserRegisterResult;
import com.seclass.sepcamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "")
    UserRegisterResult registerRequestView(@RequestBody UserRegister registerInfo) {
        return userService.register(registerInfo);
    }

    @GetMapping(value = "verify")
    boolean registerVerificationView(String verificationToken) {
        return userService.registerVerify(verificationToken);
    }
}
