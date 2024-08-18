package defaultGame;

import exceptions.InvalidInputException;
import game.Game;
import game.Options;
import queue.UnoPlayer;
import queue.UnoPlayersQueue;

import java.util.Scanner;

public class DefaultGame extends Game {
  public DefaultGame(){
    options = new Options.Builder().build(); // default options
    playersQueue = UnoPlayersQueue.getInstance().getQueue();
  }
  
  public void play(){
    while (!isGameOver()) {
      gameRound = new DefaultRound(playersQueue, options);
      gameRound.playRound();
      if(isGameOver() || promptPlayAgain().equals("n")){
        break;
      }
    }
    displayWinner();
  }
  
  @Override
  protected boolean isGameOver(){
    int maxScore = 0;
    for (UnoPlayer unoPlayer : playersQueue){
      if(unoPlayer.getScore() >= maxScore){
        maxScore = unoPlayer.getScore();
        gameWinner = unoPlayer;
      }
    }
    return gameWinner.getScore() >= options.getScoreToWin();
  }
  
  @Override
  protected String promptPlayAgain(){
    String playMore = "";
    boolean validInput = false;
    while (!validInput){
      try {
        System.out.println("Play another round? (y/n)");
        Scanner input = new Scanner(System.in);
        playMore = input.next();
        if (!(playMore.equalsIgnoreCase("y") || playMore.equalsIgnoreCase("n"))){
          throw new InvalidInputException("You must enter y or n.");
        }
        validInput = true;
      } catch (InvalidInputException e) {
        System.out.println(e.getMessage());
      }
    }
    return playMore.toLowerCase();
  }
  
  @Override
  protected void displayWinner(){
    System.out.println();
    System.out.println("🎉🎉🎉🎉🎉");
    System.out.println(gameWinner.getName().toUpperCase() + " HAS WON WITH A SCORE OF " + gameWinner.getScore() + "!!!!!");
  }
}
