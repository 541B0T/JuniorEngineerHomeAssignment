package com.example.demo;

import java.math.BigDecimal;
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

    public void selectDoorRandomly(GameShowHost gameHost) {
        //is a door selected -1
        int doorIsSelected=0;
        for (int i = 0; i < gameHost.doors.size(); i++) {
            if (gameHost.doors.get(i).isSelected()) {
                doorIsSelected = 1;
            }
        }
        /*
        Creates a index list of doors that will inherent Probability of the empty ones,
        that the host opened.
         */
        List<Integer> doorsNotOpenedOrSelected = new ArrayList<>();
        for (int i = 0; i < gameHost.doors.size(); i++) {
            if (!gameHost.doors.get(i).isOpen()
                    && !gameHost.doors.get(i).isSelected()) {
                doorsNotOpenedOrSelected.add(i);
            }
        }
        //Calculates the "newOds" that these doors will inhere.
        BigDecimal newOds =BigDecimal.valueOf(gameHost.doors.size()-doorIsSelected).divide
               (BigDecimal.valueOf(doorsNotOpenedOrSelected.size()));

        for (int i = 0; i < gameHost.doors.size(); i++) {
            if (!gameHost.doors.get(i).isOpen()
                    && !gameHost.doors.get(i).isSelected()) {
                gameHost.doors.get(i).setOds(newOds);
            }
            else if (gameHost.doors.get(i).isOpen()){
                gameHost.doors.get(i).setOds(new BigDecimal(0));
            }
        }

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
        for (Integer doorsIndexWithBestOd : doorsIndexWithBestOds) {
            System.out.println(doorsIndexWithBestOd);
        }
        int random = doorsIndexWithBestOds.indexOf
                (ThreadLocalRandom.current().nextInt(doorsIndexWithBestOds.size()));
        gameHost.doors.get(random).setSelected(true);
    }
}
