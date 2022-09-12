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

    public void StartNewRound() {
      doors.clear();
      CreateDoors();
    }
    public void OpenDoorRandomly() {
        //Finds the highest ods.
        BigDecimal highestOds = new BigDecimal("0");
        for (Door door : doors) {
            if (door.getOds().compareTo(highestOds)==1) {
                highestOds = door.getOds();
            }
        }
            /*makes a list of Door indexes that has the
            "highestOds" && "!isSelected" && "!isOpen"
            to select a new door from.
             */
        List<Integer> doorsIndexWithBestOds = new ArrayList<>();
        for (int j = 0; j < doors.size(); j++) {
            if (doors.get(j).getOds() == highestOds
                    && !doors.get(j).isOpen()
                    && !doors.get(j).isSelected()) {
                doorsIndexWithBestOds.add(j);
            }
        }
        int random = doorsIndexWithBestOds.indexOf
                (ThreadLocalRandom.current().nextInt(doorsIndexWithBestOds.size()));
        doors.get(random).setOpen(true);
    }


    public void CreateDoors() {
        //Adds the number of doors to the door list "doors".
        for (int i = 0; i < numberOfDoors; i++) {
            doors.add(new Door(i+1));
        }
        //Generates the number of winning doors randomly.
        for (int i = 0; i < getNumberOfWinningDoors(); i++) {
            int randomDoor = ThreadLocalRandom.current().nextInt(getNumberOfDoors());
            while (!doors.get(randomDoor).hasPrice()) {
                doors.get(randomDoor).setHasPrice(true);
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
            System.out.println("hasPrice: " + door.hasPrice()
                    + " isSelected: " + door.isSelected()
                    + " isOpen: " + door.isOpen() + " ods: " + door.getOds());
        }
    }
    public List<Door> getAllDoors(){
        return doors;
    }
    public void openDoor(){
        for (Door door : doors) {
            if (!door.isSelected() && !door.hasPrice()) {
                door.setOpen(true);
                break;
            }
        }
    }
    public void SelectDoorNumber(int number){
        if (!doors.get(number-1).isOpen()){
            for (int i=0;i<3;i++){
                doors.get(i).setSelected(i == number - 1);
            }
        }
    }
    public void Conclution(){
        for (Door door : doors) {
            door.setOpen(true);
        }

    }
}