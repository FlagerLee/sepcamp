package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.Response;
import com.seclass.sepcamp.models.User;
import com.seclass.sepcamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("userinfo")
public class UserController {
    @Autowired
    private UserService userService;

    static class IdList {
        List<Integer> ids;

        public List<Integer> getIds() {
            return ids;
        }
    }

    @PostMapping("/getById")
    public List<User> getUsersByIds(@RequestBody IdList userId) {
        return userService.getUserByIds(userId.getIds());
    }
    //public void test(HttpEntity<String> httpEntity) {
    //    System.out.println(httpEntity.getBody());
    //}

    @PostMapping("/getUserInformation")
    public User getUserInformation(@RequestBody User user) {
        System.out.println(user.getUser_id());
        return userService.getUserInformation(user.getUser_id());
    }

    @PostMapping("/changeUserInformation")
    public Response changeUserInformation(@RequestBody User user) {
        System.out.println(user.getInterests());
        return userService.updateUserInformation(user);
    }


}
