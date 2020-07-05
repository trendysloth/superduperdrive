package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @GetMapping(value="/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @GetMapping(value="/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value="/home")
    public String getHomePage() {
        return "home";
    }


    @PostMapping(value="/signup")
    public String register(@ModelAttribute("SpringWeb") User user) {
        if (user == null) {
            return "redirect:signup";
        }
        try {
            userService.register(user);
        } catch (Exception e) {
            return "redirect:signup?error";
        }
        return "redirect:signup?success";
    }

    @PostMapping(value="/login")
    public String login(@ModelAttribute("SpringWeb") User user) {
//        if (user == null) {
//            return "redirect:login";
//        }
//        try {
//            userService.loadUserByUsername(user.getUsername());
//
//        } catch (Exception e) {
//            return "redirect:login?error";
//        }
        return "home";
    }
}
