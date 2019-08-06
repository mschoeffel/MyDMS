package com.mschoeffel.mydms.controller;

import com.mschoeffel.mydms.model.Type;
import com.mschoeffel.mydms.model.User;
import com.mschoeffel.mydms.service.TypeService;
import com.mschoeffel.mydms.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
        User user = new User();
        user.setDate(LocalDate.now());
        model.addAttribute("user", user);
        return "editUser.html";
    }

    @PostMapping("/user/update/{username}")
    public String updateUser(Model model, @ModelAttribute("user") User user, @PathVariable String username){
        String id;
        if(userService.existsId(user.getUsername())){
            //Already exists
            User userold = userService.findById(user.getUsername());
            user.setDate(userold.getDate());
            user.setPassword(userold.getPassword());
            user.setUsername(userold.getUsername());
        } else{
            //Need to create new
            user.setDate(LocalDate.now());
        }
        if(username != null && !username.isEmpty() && !username.equals("null")){
            id = username;
        } else{
            id = user.getUsername();
        }

        try {
            userService.save(user);
            model.addAttribute("message", "Save Successful");
        } catch(Exception e){
            model.addAttribute("error", "Error occurred: " + e.getLocalizedMessage());
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
    /* Types */
    /*----------------------------------------------------------------------------------------------------------------*/
    @GetMapping("/types")
    public String showTypeList(Model model){
        model.addAttribute("types", typeService.findAll());
        return "types.html";
    }


    @GetMapping("/type/{typeshort}")
    public String showType(Model model, @PathVariable String typeshort){
        model.addAttribute("type", typeService.findById(typeshort));
        return "editType.html";
    }

    @GetMapping("/type")
    public String showNewType(Model model){
        Type type = new Type();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        type.setUser(userService.findById(authentication.getName()));
        type.setDate(LocalDate.now());
        model.addAttribute("type", type);
        return "editType.html";
    }

    @PostMapping("/type/update/{typeshort}")
    public String updateType(Model model, @ModelAttribute("type") Type type, @PathVariable String typeshort){
        String id;
        if(typeService.existsId(type.getShort_name())){
            //Already exists
            Type typeold = typeService.findById(type.getShort_name());
            type.setUser(typeold.getUser());
            type.setShort_name(typeold.getShort_name());
            type.setDate(typeold.getDate());
            id = typeshort;
        } else{
            //Need to create new
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            type.setUser(userService.findById(authentication.getName()));
            type.setDate(LocalDate.now());
            id = type.getShort_name();
        }

        try {
            typeService.save(type);
            model.addAttribute("message", "Save Successful");
        } catch(Exception e){
            model.addAttribute("error", "Error occurred: " + e.getLocalizedMessage());
        }


        model.addAttribute("type", typeService.findById(id));
        return "editType.html";
    }



    @PostMapping("/type/delete/{typeshort}")
    public String deleteType(Model model, @PathVariable String typeshort){
        try {
            typeService.deleteById(typeshort);
            model.addAttribute("message", "Delete Successful");
        } catch(RuntimeException e){
            model.addAttribute("error", e.getLocalizedMessage());
        }
        model.addAttribute("types", typeService.findAll());
        return "types.html";
    }

}
