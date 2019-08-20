package com.mschoeffel.mydms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        return "hello world";
    }

}
