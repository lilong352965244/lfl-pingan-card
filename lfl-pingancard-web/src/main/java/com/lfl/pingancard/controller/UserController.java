package com.lfl.pingancard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: lifalong
 * @create: 2019-11-05 15:27
 * @description:
 **/
@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("/login")
    public String login(){
        return "hello";
    }
}
