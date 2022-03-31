package edu.pitt.cs.cs1632;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class RPSStepDefinitions{

	private PaperPlayer p1;
	private RockPlayer p2;
	private ScoreBoard sb;
	private RockPaperScissors rps;
	private String result;

	@Given("player 1 is a {string} player")
	public void setPaperPlayer(String typ){
		p1 = new PaperPlayer();
	}
	@Given("player 2 is a {string} player")
	public void setRockPlayer(String typ){
		p2 = new RockPlayer();
	}
	@Given("sb is a scoreboard")
	public void setScoreboard(){
		sb = new ScoreBoard();
	}
	@Given("an RPS game")
	public void aRPSgame(){
		rps = new RockPaperScissors(p1,p2,sb);
	}
	@When("I play the game 5 times")
	public void playGame5Times(){
		result = rps.play(5);
	}
	@When("I play the game 3 times")
	public void playGame3Times(){
		result = rps.play(3);
	}
	@Then("Scoreboard is (5:0:0)")
	public void get5Scoreboard(){
		assertEquals(result, "(5:0:0)");
	}
	@Then("Scoreboard is (3:0:0)")
	public void get3Scoreboard(){
		assertEquals(result, "(3:0:0)");
	}

}


