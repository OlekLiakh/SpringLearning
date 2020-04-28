package com.servingwebcontent.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(/*@RequestParam(name="name", required = false, defaultValue = "World")
                                       String name, */Model model){
        model.addAttribute("title", "Home"/*name*/);
        return "home";
    }

    @GetMapping("/about-us")
    public String aboutUs (Model model){
        model.addAttribute("title", "About Us");
    return "about-us";
    }

}

