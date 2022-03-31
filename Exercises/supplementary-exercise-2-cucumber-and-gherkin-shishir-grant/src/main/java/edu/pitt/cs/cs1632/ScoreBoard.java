package edu.pitt.cs.cs1632;

public class ScoreBoard {
	private int p1Wins = 0;
	private int p2Wins = 0;
	private int draws = 0;
	
	public void p1Win() { p1Wins++; }
	public void p2Win() { p2Wins++; }
	public void draw() { draws++; }
	public String toString() {
		return "(" + p1Wins + ":" + p2Wins + ":" + draws + ")";
	}
}