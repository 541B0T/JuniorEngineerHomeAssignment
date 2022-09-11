package com.example.demo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class GameShowHost {

    private int numberOfDoors;
    private int numberOfWinningDoors;
    protected List<Door> doors = new ArrayList<>();

    public GameShowHost() {
        this.numberOfDoors = 3;
        this.numberOfWinningDoors = 1;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfWinningDoors() {
        return numberOfWinningDoors;
    }

    public void setNumberOfWinningDoors(int numberOfWinningDoors) {
        this.numberOfWinningDoors = numberOfWinningDoors;
    }

    public void CreateGame() {
        CreateDoors();
        CalculateOds();
        DoorListInfo();
        //Above is great.

        //Simulate Player.SelectDoor. Make it so that a Player2 can stay with the first door.
        doors.get(1).setSelected(true);
        CalculateOds();
        DoorListInfo();

        //Simulate montyHall.OpenDoor
        for (Door door : doors) {
            if (!door.isSelected() && !door.hasPrise()) {
                door.setOpen(true);
                break;
            }
        }
        CalculateOds();
        DoorListInfo();

        //Simulate Player.ChangeDoor

        //Simulate montyHall.ResolvesTheRound

        /*
        ____INFO____________
        Calculate how many doors needs
        to be opened for only one door to remain.
        Or is there a better way!?

        Make a test for all the open door combinations.
         */
    }

    public void CreateDoors() {
        //Adds the number of doors to the door list "doors".
        for (int i = 0; i < numberOfDoors; i++) {
            doors.add(new Door(i+1));
        }
        //Generates the number of winning doors randomly.
        for (int i = 0; i < getNumberOfWinningDoors(); i++) {
            int randomDoor = ThreadLocalRandom.current().nextInt(getNumberOfDoors());
            while (!doors.get(randomDoor).hasPrise()) {
                doors.get(randomDoor).setHasPrise(true);
            }
        }
    }

    public void CalculateOds() {
        BigDecimal newOds = new BigDecimal(0);
        BigDecimal doorIsSelected = BigDecimal.valueOf(getNumberOfDoors());
        BigDecimal numberOfOpenDoors = new BigDecimal(0);
        for (Door door : doors) {
            if (door.isSelected()) {
                doorIsSelected = doorIsSelected.subtract(new BigDecimal(1));
            }
            if (door.isOpen()) {
                numberOfOpenDoors = numberOfOpenDoors.add(new BigDecimal(1));
            }
            newOds = doorIsSelected.divide(doorIsSelected.subtract(numberOfOpenDoors), 4, RoundingMode.HALF_DOWN);
        }
        for (Door door : doors) {
            if (!door.isOpen() && !door.isSelected()) {
                door.setOds(newOds);
            } else if (door.isOpen()) {
                door.setOds(new BigDecimal(0));
            }
        }
    }

    public void DoorListInfo() {
        System.out.println("_____________________");
        for (Door door : doors) {
            System.out.println("hasPrice: " + door.hasPrise()
                    + " isSelected: " + door.isSelected()
                    + " isOpen: " + door.isOpen() + " ods: " + door.getOds());
        }
    }
    public List<Door> getAllDoors(){

        return doors;
    }
}