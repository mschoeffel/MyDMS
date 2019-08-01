package com.mschoeffel.mydms.controller;

import com.mschoeffel.mydms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

    private UserService userService;

    public WebController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/home")
    public String showHome(){
        return "list.html";
    }

    @GetMapping("/user")
    public String showUserList(Model model){
        model.addAttribute("users", userService.findAll());
        return "user.html";
    }

    @GetMapping("/user/{username}")
    public String showUser(Model model, @PathVariable String username){
        model.addAttribute("user", userService.findById(username));
        return "editUser.html";
    }
}
