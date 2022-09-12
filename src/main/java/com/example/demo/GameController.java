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
    public String startGame(){
        return "redirect:/game";
    }

    @GetMapping("/game")
    public String startGame(Model model) {
        gameShowHost.doors.clear();
        model.addAttribute("doors",gameShowHost.getAllDoors());
        System.out.println("____WebGame____");
        gameShowHost.CreateDoors();
        gameShowHost.CalculateOds();

        gameShowHost.doors.get(2).setOpen(true);
        return "game";
    }
    @GetMapping("/round")
    public String displayGameState(Model model){
        model.addAttribute("doors",gameShowHost.getAllDoors());
        return "round";
    }
    @GetMapping("/{number}")
    public String selectingDoorTwo(@PathVariable int number) {
        if (!gameShowHost.doors.get(number-1).isOpen()){
            for (int i=0;i<3;i++){
                gameShowHost.doors.get(i).setSelected(i == number - 1);
            }
        }
        gameShowHost.DoorListInfo();
        return "redirect:/round";
    }
}
