package customGame;

import card.abstractCard.AbstractActionCard;
import card.abstractCard.AbstractCard;
import card.abstractCard.AbstractWildCard;
import card.unoCards.NumberedCard;

import game.GameRound;
import game.Options;
import queue.UnoPlayer;
import queue.UnoPlayersQueue;

import defaultGame.DefaultRound;

import java.util.Queue;

public class CustomRound extends GameRound {
  DefaultRound def;
  public CustomRound(Queue<UnoPlayer> queue, Options o) {
    super(queue, o);
    def=new DefaultRound(queue,o);
  }
  
  @Override
  protected void setupRound(){
    int numOfPlayers = unoPlayerQueue.size();
    int numOfCardsPerPlayer = options.getNumOfCardsPerPlayer();
    if (numOfPlayers * numOfCardsPerPlayer > drawDeck.getDrawPileSize()){
      throw new IllegalArgumentException("Not enough cards.");
    }
    for(int i=0;i<numOfPlayers;i++){
      UnoPlayer unoPlayer = unoPlayerQueue.remove();
      unoPlayer.drawCard(numOfCardsPerPlayer);
      unoPlayerQueue.add(unoPlayer);
    }
  }
  
  @Override
  protected void playCard(UnoPlayer unoPlayer, int cardNumber){
    AbstractCard card = unoPlayer.getCardList().get(cardNumber);

    unoPlayer.playCard(cardNumber);
    if (card instanceof NumberedCard) {
      UnoPlayersQueue.getInstance().nextPlayer();
    } else if (card instanceof AbstractActionCard abstractActionCard){
      abstractActionCard.performAction();
    } else if (card instanceof AbstractWildCard wildCard) {
      wildCard.performAction();
    }
  }
  
  @Override
  protected int selectCard(UnoPlayer unoPlayer){
    return def.selectCard(unoPlayer);
  }

  @Override
  protected void displayRoundWinner() {
    System.out.println(roundWinner.getName() + " won this round 🎉🎉🎉🎉🎉");
  }
  
  @Override
  protected void displayScores(){
    for (UnoPlayer unoPlayer : unoPlayerQueue) {
      System.out.println(unoPlayer.getName() + "'s score: " + unoPlayer.getScore());
    }
  }
  
  @Override
  protected void endRound(){
    for(UnoPlayer unoPlayer : unoPlayerQueue){
      unoPlayer.clearCardList();
    }
    drawDeck.initializeDrawPile();
    discardDeck.initializeDiscardPile();
  }
}
