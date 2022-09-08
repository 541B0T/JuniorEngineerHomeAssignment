package com.example.demo;

import org.springframework.web.util.pattern.PathPattern;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private int numberOFWonRounds = 0;
    private int getNumberOFLostRounds = 0;
    private int selectedDoor;

    public Player(int numberOFWonRounds, int getNumberOFLostRounds, int selectedDoor) {
        this.numberOFWonRounds = numberOFWonRounds;
        this.getNumberOFLostRounds = getNumberOFLostRounds;
        this.selectedDoor = selectedDoor;
    }

    public int getNumberOFWonRounds() {
        return numberOFWonRounds;
    }

    public void setNumberOFWonRounds(int numberOFWonRounds) {
        this.numberOFWonRounds = numberOFWonRounds;
    }

    public int getGetNumberOFLostRounds() {
        return getNumberOFLostRounds;
    }

    public void setGetNumberOFLostRounds(int getNumberOFLostRounds) {
        this.getNumberOFLostRounds = getNumberOFLostRounds;
    }

    public int getSelectedDoor() {
        return selectedDoor;
    }

    public void setSelectedDoor(int selectedDoor) {
        this.selectedDoor = selectedDoor;
    }

    public void selectDoorRandomly(GameHost gameHost) {
        //update the ods loop-------REMOVE COMMENT
        gameHost.doors.get(3).setSelected(true);
        gameHost.doors.get(5).setOpen(true);

        //is a door selected -1
        int doorIsSelected=0;
        for (int i = 0; i < gameHost.doors.size(); i++) {
            if (gameHost.doors.get(i).isSelected()) {
                doorIsSelected = 1;
                System.out.print("door is selected  =");
            }
        }
        System.out.println(doorIsSelected);

        //Creates a list of doors that will inherent Probability
        List<Integer> doorsNotOpenedOrSelected = new ArrayList<>();
        for (int i = 0; i < gameHost.doors.size(); i++) {
            if (!gameHost.doors.get(i).isOpen()
                    && !gameHost.doors.get(i).isSelected()) {
                doorsNotOpenedOrSelected.add(i);
            }
        }

        BigDecimal newOds =BigDecimal.valueOf(gameHost.doors.size()-doorIsSelected).divide
               (BigDecimal.valueOf(doorsNotOpenedOrSelected.size()));

        //Prints to consol------REMOVE COMMENT
        System.out.println("newOds: "+newOds);
        System.out.println("gameHost.doors.size()-doorIsSelected: " + (gameHost.doors.size() - doorIsSelected));
        System.out.println("doorsNotOpenedOrSelected.size(): " + doorsNotOpenedOrSelected.size());




        //Finds the highest ods.
        double highestOds = 0;
        for (Door door : gameHost.doors) {
            if (door.getOds() > highestOds) {
                highestOds = door.getOds();
            }
        }
            /*makes a list of Door indexes that has the
            "highestOds" && "!isSelected" && "!isOpen"
            to select a new door from.
             */
        List<Integer> doorsIndexWithBestOds = new ArrayList<>();
        for (int j = 0; j < gameHost.doors.size(); j++) {
            if (gameHost.doors.get(j).getOds() == highestOds
                    && !gameHost.doors.get(j).isOpen()
                    && !gameHost.doors.get(j).isSelected()) {
                doorsIndexWithBestOds.add(j);
            }
        }
        //prints highest ods -------REMOVE COMMENT
        System.out.println(highestOds);
        //prints the number of doors with the same "highest" ods.-------REMOVE COMMENT
        System.out.println(doorsIndexWithBestOds.size());
        int random = doorsIndexWithBestOds.indexOf
                (ThreadLocalRandom.current().nextInt(doorsIndexWithBestOds.size()));
        System.out.println("random door selected " + random);

    }

}
