package game;

import abstractCard.Card;
import piles.DiscardPile;
import piles.DrawPile;
import queue.Player;
import queue.PlayersQueue;

import java.util.*;

import static utility.Display.printPlayerCards;
import static utility.Display.printTopDiscardedCard;

public abstract class GameRound {
  protected final DrawPile drawPile;
  protected final DiscardPile discardPile;
  protected final Queue<Player> playerQueue;
  protected final Options options;
  protected Player roundWinner;
  
  public GameRound(Queue<Player> queue, Options o){
    playerQueue = queue;
    options = o;
    drawPile = DrawPile.getInstance();
    discardPile = DiscardPile.getInstance();
  }
  
  public void playRound(){
    initializeRound();
    while (!isRoundOver()) {
      Player currentPlayer = playerQueue.peek();
      printPlayerCards(currentPlayer);
      Card topDiscardedCard = discardPile.getTopCard();
      printTopDiscardedCard(topDiscardedCard);
      if(!hasPlayableCard(currentPlayer)){
        System.out.println("You don't have a card to play, " + currentPlayer.getName() + "! Drawing a card.");
        Card drawnCard = currentPlayer.drawCard();
        System.out.println("You drew a " + drawnCard.toString());
        if (canBePlayed(drawnCard)){ // play the drawn card if valid
          System.out.println("You can play this card.");
          playCard(currentPlayer, currentPlayer.getCardList().size()-1);
        } else if (options.getDrawOnlyOneCardIfCantPlay()){ // if only one card move to the next player
          PlayersQueue.getInstance().nextPlayer();
        }
      } else {
        int cardNumber = chooseCard(currentPlayer);
        playCard(currentPlayer, cardNumber);
      }
      System.out.println("-------------------------------------------------");
    }
    displayRoundWinner();
    calculateScore();
    displayScores();
    endRound();
  }
  
  private boolean isRoundOver(){
    for (Player player : playerQueue){
      if(player.getCardList().isEmpty()){
        roundWinner = player;
        return true;
      }
    }
    return false;
  }
  
  private Boolean hasPlayableCard(Player player){
    List<Card> cardList = player.getCardList();
    for(Card card : cardList){
      if (canBePlayed(card)){
        return true;
      }
    }
    return false;
  }

  protected boolean canBePlayed(Card playerCard){
    Card topCard = discardPile.getTopCard();

    return playerCard.canBePlayed(topCard);
  }
  
  private void calculateScore(){
    for (Player player : playerQueue){
      for (Card card : player.getCardList()){
        roundWinner.incrementScore(card.getCardScore());
      }
    }
  }
  protected abstract void initializeRound();
  protected abstract void playCard(Player player, int cardNumber);
  protected abstract int chooseCard(Player player);
  protected abstract void displayRoundWinner();
  protected abstract void displayScores();
  protected abstract void endRound();
  
}
