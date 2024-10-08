package customGame;

import exceptions.InvalidInputException;
import game.Game;
import game.Options;
import piles.DeckInfo;
import queue.UnoPlayer;
import queue.UnoPlayersQueue;

import java.util.Scanner;

public class CustomGame extends Game {
  public CustomGame(){
    DeckInfo deckOptions = new DeckInfo();
    deckOptions.setWildDrawFourCards(2);
    deckOptions.setWildCards(2);
    options = new Options.Builder().deckOptions(deckOptions).drawOnlyOneCard(false).sayUno(false).numOfCardsPerPlayer(5).scoreToWin(100).build(); // customized options
    playersQueue = UnoPlayersQueue.getInstance().getQueue();
  }
  
  public void play(){
    while (!isGameOver()) {
      gameRound = new CustomRound(playersQueue, options);
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
        System.out.println("Play another round? (yes / no)");
        Scanner input = new Scanner(System.in);
        playMore = input.next();
        if (!(playMore.equalsIgnoreCase("yes") || playMore.equalsIgnoreCase("no"))){
          throw new InvalidInputException("You must enter yes or no.");
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
    System.out.println(gameWinner.getName() + " is the winner");
  }
}
