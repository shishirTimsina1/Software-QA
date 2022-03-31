package edu.pitt.cs.cs1632;
import java.util.Random;

public class Player {
	private Random random = new Random();
	public Hand throwHand() {
		// Returns rock, paper, scissors randomly
		return Hand.values()[random.nextInt(3)];
	}
}