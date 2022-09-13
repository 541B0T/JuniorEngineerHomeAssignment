package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MontyHallTests {

	//Do I have a better chance to win if I change my door
	@Test
	public void isBetterToChange(){
		Assertions.assertEquals(true, GameShowApp.Simulate());
	}

}
