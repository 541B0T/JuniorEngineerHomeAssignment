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
    public String ridirectToGameMenu(){
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
        gameShowHost.doors.clear();
        model.addAttribute("doors",gameShowHost.getAllDoors());
        gameShowHost.CreateDoors();
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
        if (!gameShowHost.doors.get(number-1).isOpen()){
            for (int i=0;i<3;i++){
                gameShowHost.doors.get(i).setSelected(i == number - 1);
            }
        }
        gameShowHost.DoorListInfo();
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
    @GetMapping("/playAGame/secondSelection/{number}")
    public String selectingDoorTwo(@PathVariable int number) {
        if (!gameShowHost.doors.get(number-1).isOpen()){
            for (int i=0;i<3;i++){
                gameShowHost.doors.get(i).setSelected(i == number - 1);
            }
        }
        gameShowHost.DoorListInfo();
        return "redirect:/conclution";
    }
    //sets the selected door "isSelected" true. and deselects the others.
    @GetMapping("/conclution")
    public String conclution(Model model){
        model.addAttribute("doors",gameShowHost.getAllDoors());
        for (Door door : gameShowHost.doors) {
            door.setOpen(true);
        }
        return "concludeRound";
    }
}
