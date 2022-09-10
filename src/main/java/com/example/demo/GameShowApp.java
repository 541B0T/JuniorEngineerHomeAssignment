package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameShowApp {
	public static void main(String[] args) {
		SpringApplication.run(GameShowApp.class, args);

		GameShowHost montyHall=new GameShowHost();
		montyHall.CreateGame();


	}
}
