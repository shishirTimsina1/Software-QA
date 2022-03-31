Feature: play paper against rock

Background:
Given player 1 is a "paper" player
And player 2 is a "rock" player
Given sb is a scoreboard
Given an RPS game

Scenario: If the game is played 5 times, the scoreboard is "(5:0:0)".

When I play the game 5 times
Then Scoreboard is "(5:0:0)"

Scenario: If the game is played 3 times, the scoreboard is "(3:0:0)".

When I play the game 3 times
Then Scoreboard is "(3:0:0)"
