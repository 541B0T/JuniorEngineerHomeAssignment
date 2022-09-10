package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @Autowired
    GameShowHost gameShowHost;

    @GetMapping("/")
    public String start(Model model) {
        model.addAttribute("doors",gameShowHost.getAllDoors());
        return "gameUI";
    }
}
