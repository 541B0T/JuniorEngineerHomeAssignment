package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GameController {
    @Autowired
    GameShowHost gameShowHost;

    //Redirect browser to "/gameMenu" page
    @GetMapping("/")
    public String redirectToGameMenu(){
        return "redirect:/gameMenu";
    }

    //Menu page
    @GetMapping("/gameMenu")
    public String GameMenu() {
        return "gameMenu";
    }

    //Button "Play a game" creates a new game
    @GetMapping("/playAGame")
    public String PlayAGame(Model model) {
        model.addAttribute("doors",gameShowHost.getAllDoors());
        gameShowHost.StartNewRound();
        return "redirect:/playAGame/firstSelection";
    }

    //page "firstSelection" presents the three doors to the player
    @GetMapping("/playAGame/firstSelection")
    public String displayGameState(Model model){
        model.addAttribute("doors",gameShowHost.getAllDoors());
        return "firstSelection";
    }

    //sets the selected door "isSelected" true. and deselects the others.
    @GetMapping("/playAGame/firstSelection/{number}")
    public String firstSelectedDoor(@PathVariable int number) {
        gameShowHost.SelectDoorNumber(number);
        return "redirect:/playAGame/secondSelection";
    }

    /*
    page "secondSelection" presents the three doors to the player.
    And the host opens one of the empty doors.
     */
    @GetMapping("/playAGame/secondSelection")
    public String switcher(Model model){
        model.addAttribute("doors",gameShowHost.getAllDoors());
        gameShowHost.openDoor();
        return "secondSelection";
    }

    //sets the selected door "isSelected" true. and deselects the others.
    @GetMapping("/playAGame/secondSelection/{number}")
    public String selectingDoorTwo(@PathVariable int number) {
        gameShowHost.SelectDoorNumber(number);
        return "redirect:/conclution";
    }

    //Opens the remaining doors
    @GetMapping("/conclution")
    public String conclution(Model model){
        model.addAttribute("doors",gameShowHost.getAllDoors());
        for (Door door : gameShowHost.doors) {
            door.setOpen(true);
        }
        return "concludeRound";
    }
}
