package com.mschoeffel.mydms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/accessDenied")
    public String showAccessDenied(Model model){
        model.addAttribute("accessError", true);
        return "login";
    }

    @GetMapping("/logout-success")
    public String showLogout(Model model){
        model.addAttribute("logoutSuccess", true);
        return "login";
    }

}
