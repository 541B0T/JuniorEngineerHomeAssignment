package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MontyHallApp {
	public static void main(String[] args) {
		SpringApplication.run(MontyHallApp.class, args);

		GameHost gameHost=new GameHost();
		Player player=new Player(0,0,0);

		gameHost.CreateGame(3,1);
		gameHost.printsDoors(3);
		player.selectDoorRandomly(gameHost);
		gameHost.printsDoors(3);

	}
}
