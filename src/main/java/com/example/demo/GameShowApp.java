package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameShowApp {
	public static void main(String[] args) {
		SpringApplication.run(GameShowApp.class, args);

		Simulate();
	}
	public static boolean Simulate(){
		Contestant changeDoorPlayer=new Contestant();
		GameShowHost host=new GameShowHost();

		for (int i=0;i<100;i++){
			host.StartNewRound();
			host.CalculateOds();

			changeDoorPlayer.SelectDoor(host);
			host.openDoor();
			host.CalculateOds();

			changeDoorPlayer.SelectDoor(host);
			host.Conclusion(changeDoorPlayer);
		}

		//if you don't change door
		Contestant noChangeDoorPlayer=new Contestant();
		GameShowHost host2=new GameShowHost();

		for (int i=0;i<100;i++) {
			host2.StartNewRound();
			host2.CalculateOds();

			noChangeDoorPlayer.SelectDoor(host2);
			host2.openDoor();
			host2.CalculateOds();

			host2.Conclusion(noChangeDoorPlayer);
		}
		boolean isChangeBetter=false;
		if (changeDoorPlayer.getNumberOFWonRounds()>noChangeDoorPlayer.getNumberOFWonRounds()){
			isChangeBetter=true;
		}
		return isChangeBetter;
	}
}
