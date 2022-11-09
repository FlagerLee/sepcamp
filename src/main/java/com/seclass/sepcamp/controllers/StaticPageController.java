package com.seclass.sepcamp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StaticPageController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String indexView(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginView(HttpServletRequest request) {
        return "login";
    }
}
