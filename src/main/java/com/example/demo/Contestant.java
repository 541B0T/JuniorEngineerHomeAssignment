package com.example.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Contestant {

    private int numberOFWonRounds;
    private int getNumberOFLostRounds;
    private int selectedDoor;

    public Contestant() {
        this.numberOFWonRounds = 0;
        this.getNumberOFLostRounds = 0;
        this.selectedDoor = 0;
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

    //Selects a door randomy among the doors with the higest ods
    public void SelectDoorRandomly(GameShowHost gameHost) {
        //Finds the highest ods.
        BigDecimal highestOds = new BigDecimal("0");
        for (Door door : gameHost.doors) {
            if (door.getOds().compareTo(highestOds)==1) {
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
        int random = doorsIndexWithBestOds.indexOf
                (ThreadLocalRandom.current().nextInt(doorsIndexWithBestOds.size()));
        gameHost.doors.get(random).setSelected(true);
    }
}
