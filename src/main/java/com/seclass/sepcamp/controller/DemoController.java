package com.seclass.sepcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "demo")
public class DemoController {
    @RequestMapping(value = "")
    public String demoView(HttpServletRequest request) {
        return "demo";
    }
}
