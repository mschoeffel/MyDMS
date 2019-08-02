package com.mschoeffel.mydms.controller;

import com.mschoeffel.mydms.model.User;
import com.mschoeffel.mydms.service.TypeService;
import com.mschoeffel.mydms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
public class WebController {

    private UserService userService;
    private TypeService typeService;


    /*----------------------------------------------------------------------------------------------------------------*/
    /* Home */
    /*----------------------------------------------------------------------------------------------------------------*/

    public WebController(UserService userService, TypeService typeService){
        this.userService = userService;
        this.typeService = typeService;
    }

    @GetMapping("/home")
    public String showHome(){
        return "list.html";
    }


    /*----------------------------------------------------------------------------------------------------------------*/
    /* User */
    /*----------------------------------------------------------------------------------------------------------------*/
    @GetMapping("/users")
    public String showUserList(Model model){
        model.addAttribute("users", userService.findAll());
        return "users.html";
    }

    @GetMapping("/user/{username}")
    public String showUser(Model model, @PathVariable String username){
        model.addAttribute("user", userService.findById(username));
        return "editUser.html";
    }

    @GetMapping("/user")
    public String showNewUser(Model model){
        model.addAttribute("user", new User());
        return "editUser.html";
    }

    @PostMapping("/user/update/{username}")
    public String updateUser(Model model, @ModelAttribute("user") User user, @PathVariable String username){
        try {
            userService.save(user);
            model.addAttribute("message", "Save Successful");
        } catch(Exception e){
            model.addAttribute("error", "Error occurred: " + e.getLocalizedMessage());
        }

        String id;
        if(username != null && !username.isEmpty() && !username.equals("null")){
            id = username;
        } else{
            id = user.getUsername();
        }
        model.addAttribute("user", userService.findById(id));
        return "editUser.html";
    }

    @PostMapping("/user/delete/{username}")
    public String deleteUser(Model model, @PathVariable String username){
        try {
            userService.deleteById(username);
            model.addAttribute("message", "Delete Successful");
        } catch(RuntimeException e){
            model.addAttribute("error", e.getLocalizedMessage());
        }
        model.addAttribute("users", userService.findAll());
        return "users.html";
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* User */
    /*----------------------------------------------------------------------------------------------------------------*/
    @GetMapping("/types")
    public String showTypeList(Model model){
        model.addAttribute("types", typeService.findAll());
        return "types.html";
    }

    /*
    @GetMapping("/user/{username}")
    public String showUser(Model model, @PathVariable String username){
        model.addAttribute("user", userService.findById(username));
        return "editUser.html";
    }

    @GetMapping("/user")
    public String showNewUser(Model model){
        model.addAttribute("user", new User());
        return "editUser.html";
    }

    @PostMapping("/user/update/{username}")
    public String updateUser(Model model, @ModelAttribute("user") User user, @PathVariable String username){
        try {
            userService.save(user);
            model.addAttribute("message", "Save Successful");
        } catch(Exception e){
            model.addAttribute("error", "Error occurred: " + e.getLocalizedMessage());
        }

        String id;
        if(username != null && !username.isEmpty() && !username.equals("null")){
            id = username;
        } else{
            id = user.getUsername();
        }
        model.addAttribute("user", userService.findById(id));
        return "editUser.html";
    }

    @PostMapping("/user/delete/{username}")
    public String deleteUser(Model model, @PathVariable String username){
        try {
            userService.deleteById(username);
            model.addAttribute("message", "Delete Successful");
        } catch(RuntimeException e){
            model.addAttribute("error", e.getLocalizedMessage());
        }
        model.addAttribute("users", userService.findAll());
        return "users.html";
    }*/

}
