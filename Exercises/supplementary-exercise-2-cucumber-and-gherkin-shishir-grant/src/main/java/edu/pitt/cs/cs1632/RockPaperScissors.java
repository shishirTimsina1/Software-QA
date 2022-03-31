package edu.pitt.cs.cs1632;
public class RockPaperScissors {
	private Player p1;
	private Player p2;
	private ScoreBoard sb;

	public RockPaperScissors(Player p1, Player p2, ScoreBoard sb) {
		this.p1 = p1;
		this.p2 = p2;
		this.sb = sb;
	}

	private void round() {
		Hand p1Hand = p1.throwHand();
		Hand p2Hand = p2.throwHand();
		if (p1Hand == p2Hand) {
			sb.draw();
		} else if (p1Hand == Hand.ROCK && p2Hand == Hand.SCISSORS) {
			sb.p1Win();
		} else if (p1Hand == Hand.PAPER && p2Hand == Hand.ROCK) {
			sb.p1Win();
		} else if (p1Hand == Hand.SCISSORS && p2Hand == Hand.PAPER) {
			sb.p1Win();
		} else {
			sb.p2Win();
		}
	}
	
	public String play(int rounds) {
		for (int i = 0; i < rounds; i++) {
			round();
		}
		return sb.toString();
	}
}

