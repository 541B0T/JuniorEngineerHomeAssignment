package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MontyHallTests {

	@Test
	void contextLoads() {
	}

	/*Test if the there is at least two empty doors at all times.
	This is made curtain in the method.
	 */
	@Test
	public void createGameTest(){
		int numberOfWinningDoors=2;
		int numberOfDoors=4;
		GameHost gameHost=new GameHost();
		boolean result=gameHost.CreateGame(numberOfDoors,numberOfWinningDoors);
		Assertions.assertEquals(true, result);

	}

}
