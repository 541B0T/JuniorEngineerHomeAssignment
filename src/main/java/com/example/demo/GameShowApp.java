package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GameShowApp {
	public static void main(String[] args) {
		SpringApplication.run(GameShowApp.class, args);

		Contestant tobias=new Contestant();

		GameShowHost montyHall=new GameShowHost();
		montyHall.CreateDoors();
		montyHall.CalculateOds();
		tobias.SelectDoorRandomly(montyHall);
		montyHall.CalculateOds();





	}
}
