package edu.pitt.cs.cs1632;
public class RoundRobinPlayer extends Player {
	private int i = 0;
	public Hand throwHand() {
		// Returns rock, paper, scissors in round robin order
		Hand ret = Hand.values()[i];
		i = ++i % 3;
		return ret;
	}
}