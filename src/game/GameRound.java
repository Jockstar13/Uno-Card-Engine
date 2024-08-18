package game;

import card.abstractCard.AbstractCard;
import piles.DiscardPile;
import piles.DrawPile;
import queue.UnoPlayer;
import queue.UnoPlayersQueue;

import java.util.*;

import static utility.Display.printPlayerCards;
import static utility.Display.printTopDiscardedCard;

public abstract class GameRound {
  protected final DrawPile drawDeck;
  protected final DiscardPile discardDeck;
  protected final Queue<UnoPlayer> unoPlayerQueue;
  protected final Options options;
  protected UnoPlayer roundWinner;
  
  public GameRound(Queue<UnoPlayer> queue, Options o){
    unoPlayerQueue = queue;
    options = o;
    drawDeck = DrawPile.getInstance();
    discardDeck = DiscardPile.getInstance();
  }
  
  public void playRound(){
    setupRound();
    while (!isRoundOver()) {
      UnoPlayer currentUnoPlayer = unoPlayerQueue.peek();
      printPlayerCards(currentUnoPlayer);
      AbstractCard topDiscardedCard = discardDeck.getTopCard();
      printTopDiscardedCard(topDiscardedCard);
      if(!hasPlayableCard(currentUnoPlayer)){
        System.out.println("You don't have a card to play, " + currentUnoPlayer.getName() + "! Drawing a card.");
        AbstractCard drawnCard = currentUnoPlayer.drawCard();
        System.out.println("You drew a " + drawnCard.toString());
        if (canBePlayed(drawnCard)){ // play the drawn card if valid
          System.out.println("You can play this card.");
          playCard(currentUnoPlayer, currentUnoPlayer.getCardList().size()-1);
        } else if (options.getDrawOnlyOneCardIfCantPlay()){ // if only one card move to the next player
          UnoPlayersQueue.getInstance().nextPlayer();
        }
      } else {
        int cardNumber = selectCard(currentUnoPlayer);
        playCard(currentUnoPlayer, cardNumber);
      }
      System.out.println("-------------------------------------------------");
    }
    displayRoundWinner();
    calculateScore();
    displayScores();
    endRound();
  }
  
  private boolean isRoundOver(){
    for (UnoPlayer unoPlayer : unoPlayerQueue){
      if(unoPlayer.getCardList().isEmpty()){
        roundWinner = unoPlayer;
        return true;
      }
    }
    return false;
  }
  
  private Boolean hasPlayableCard(UnoPlayer unoPlayer){
    List<AbstractCard> cardList = unoPlayer.getCardList();
    for(AbstractCard card : cardList){
      if (canBePlayed(card)){
        return true;
      }
    }
    return false;
  }

  protected boolean canBePlayed(AbstractCard playerCard){
    AbstractCard topCard = discardDeck.getTopCard();

    return playerCard.isPlayable(topCard);
  }
  
  private void calculateScore(){
    for (UnoPlayer unoPlayer : unoPlayerQueue){
      for (AbstractCard card : unoPlayer.getCardList()){
        roundWinner.incrementScore(card.getCardScore());
      }
    }
  }
  protected abstract void setupRound();
  protected abstract void playCard(UnoPlayer unoPlayer, int cardNumber);
  protected abstract int selectCard(UnoPlayer unoPlayer);
  protected abstract void displayRoundWinner();
  protected abstract void displayScores();
  protected abstract void endRound();
  
}
