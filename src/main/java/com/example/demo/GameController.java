package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    @Autowired
    GameShowHost gameShowHost;

    @GetMapping("/")
    public String startGame(Model model) {
        model.addAttribute("doors",gameShowHost.getAllDoors());
        System.out.println("____WebGame____");
        gameShowHost.CreateDoors();
        gameShowHost.CalculateOds();
        return "gameUI";
    }
    @GetMapping("/gameUI")
    public String displayGameState(Model model){
        model.addAttribute("doors",gameShowHost.getAllDoors());
        return "gameUI";
    }
    @GetMapping("/{number}")
    public String selectingDoorTwo(@PathVariable int number) {

        for (int i=0;i<3;i++){
            if (i!=number-1){
                gameShowHost.doors.get(i).setSelected(false);
            }
            else {
                gameShowHost.doors.get(i).setSelected(true);
            }
        }
        return "redirect:/gameUI";
    }
}
