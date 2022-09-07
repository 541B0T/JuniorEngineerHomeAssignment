package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MontyHallApp {

	public static void main(String[] args) {
		SpringApplication.run(MontyHallApp.class, args);

		GameHost gameHost=new GameHost();
		gameHost.CreateGame(10,1);

	}
}
