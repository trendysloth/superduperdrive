package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/login", "/home"})
    public String login(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
//        if (session.getAttribute("user") == null) {
//            return "login";
//        }
//        User savedUser = userService.loadUserByUsername(user.getUserName());
//
//        model.addAttribute("files", fileService.getAllFilesByUserId(savedUser.getUserid()));
//        model.addAttribute("credentials", credentialService.getAllByUserid(savedUser.getUserid()));
//        model.addAttribute("notes", notesService.getAllByUserid(savedUser.getUserid()));
        return "home";
    }
}