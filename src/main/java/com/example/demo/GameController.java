package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    @Autowired
    GameShowHost gameShowHost;

    @GetMapping("/")
    public String start(Model model) {
        model.addAttribute("doors",gameShowHost.getAllDoors());
        System.out.println("____WebGame____");
        gameShowHost.CreateDoors();
        gameShowHost.CalculateOds();
        return "gameUI";
    }
    @GetMapping("/test")
    public String test (){
        return "test";
    }
    @PostMapping("/doorOne")
    public String selectingDoorOne() {
        gameShowHost.doors.get(0).setSelected(true);
        gameShowHost.DoorListInfo();

        return "gameUI";
    }
    @PostMapping("/doorTwo")
    public String selectingDoorTwo() {
        gameShowHost.doors.get(1).setSelected(true);
        gameShowHost.DoorListInfo();

        return "gameUI";
    }
}
