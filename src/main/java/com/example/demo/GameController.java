package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GameController {
    @Autowired
    GameShowHost gameShowHost=new GameShowHost();
    Contestant contestant =new Contestant();

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
    public String FirstDisplayDoors(Model model){
        model.addAttribute("doors",gameShowHost.getAllDoors());
        return "firstSelection";
    }

    //sets the selected door "isSelected" true. and deselects the others.
    @GetMapping("/playAGame/firstSelection/{number}")
    public String FirstSelectDoor(@PathVariable int number) {
        contestant.SelectDoorNumber(number-1, gameShowHost);
        return "redirect:/playAGame/secondSelection";
    }

    /*
    page "secondSelection" presents the three doors to the player.
    And the host opens one of the empty doors.
     */
    @GetMapping("/playAGame/secondSelection")
    public String SecondDisplayDoors(Model model){
        model.addAttribute("doors",gameShowHost.getAllDoors());
        gameShowHost.openDoor();
        return "secondSelection";
    }

    //sets the selected door "isSelected" true. and deselects the others.
    @GetMapping("/playAGame/secondSelection/{number}")
    public String SecondSelectDoor(@PathVariable int number) {
        contestant.SelectDoorNumber(number-1, gameShowHost);
        return "redirect:/conclusion";
    }

    //Opens the remaining doors
    @GetMapping("/conclusion")
    public String Conclusion(Model model){
        model.addAttribute("doors",gameShowHost.getAllDoors());
        gameShowHost.Conclusion(contestant);
        return "concludeRound";
    }

    @GetMapping("/simulate")
    public String Simulate(){
        Contestant player=new Contestant();
        GameShowHost host=new GameShowHost();

        host.StartNewRound();
        host.CalculateOds();
        host.DoorListInfo();

        player.SelectDoorRandomly(host);
        host.openDoor();
        host.CalculateOds();
        host.DoorListInfo();

        player.SelectDoorRandomly(host);
        host.DoorListInfo();
        //montyHall.Conclusion(tobias);

        return "/simulate";
    }
}
