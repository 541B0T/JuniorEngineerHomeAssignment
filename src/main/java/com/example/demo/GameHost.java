package com.example.demo;

import com.example.demo.Door;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameHost {
    public List<Door> doors=new ArrayList<>();

    public boolean CreateGame(int numberOfDoors, int numberOfWinningDoors){
        /*
        Checks if there is at least two doors that has no price, and if there
        is a minimum of 3doors. If this is not the case, it will change it to
        a standard Monty Hall game "1 door with a price and 2 empty doors"
         */
        if (numberOfDoors<3){
            numberOfDoors=3;
        }
        if (numberOfWinningDoors>numberOfDoors-2){
            numberOfWinningDoors=numberOfDoors-2;
        }

        //Creates the "numberOFDoors" retested.
        for (int i=0;i<numberOfDoors;i++){
            doors.add(new Door(false,false,false,1.0));
        }
        //Randomly selects the winning doors.
        for (int i=0;i<numberOfWinningDoors;i++){
            boolean hasPrice=true;
            while (hasPrice){
                int randomDoor= ThreadLocalRandom.current().nextInt(numberOfDoors);
                if (!doors.get(randomDoor).hasPrise()){
                    doors.get(randomDoor).setHasPrise(true);
                    hasPrice=false;
                }
            }
        }
        /*Tests that the numbers of doors with and without prices ar correct for a
        game of Monty Hall.
         */
        int hasNoPrice=0;
        if (numberOfDoors<11){
            //-------REMOVE COMMENT
            System.out.println(".::Door INFO::.");
        }
        for (int i=0;i<doors.size();i++){
            /*
            Prints the door.hasPrice to the console if the game has less than 11 doors.
            Only to make it easy for the developer to follow the game during gameplay test.
             */
            //Don't need this when its finished-------REMOVE COMMENT
            if (numberOfDoors<11){
                System.out.println("door index: "+(i+1)+" hasPrise=" +doors.get(i).hasPrise());
            }

            if (!doors.get(i).hasPrise()){
                hasNoPrice+=1;
            }
        }
        return hasNoPrice>=2;
    }
}
