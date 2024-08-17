package customGame;

import card.abstractCard.AbstractActionAbstractCard;
import card.abstractCard.AbstractCard;
import card.abstractCard.AbstractWildCard;
import card.unoCards.NumberedAbstractCard;

import game.GameRound;
import game.Options;
import queue.Player;
import queue.PlayersQueue;

import defaultGame.DefaultRound;

import java.util.Queue;

public class CustomRound extends GameRound {
  DefaultRound def;
  public CustomRound(Queue<Player> queue, Options o) {
    super(queue, o);
    def=new DefaultRound(queue,o);
  }
  
  @Override
  protected void setupRound(){
    int numOfPlayers = playerQueue.size();
    int numOfCardsPerPlayer = options.getNumOfCardsPerPlayer();
    if (numOfPlayers * numOfCardsPerPlayer > drawPile.getDrawPileSize()){
      throw new IllegalArgumentException("Not enough cards.");
    }
    for(int i=0;i<numOfPlayers;i++){
      Player player= playerQueue.remove();
      player.drawCard(numOfCardsPerPlayer);
      playerQueue.add(player);
    }
  }
  
  @Override
  protected void playCard(Player player, int cardNumber){
    AbstractCard card = player.getCardList().get(cardNumber);

    player.playCard(cardNumber);
    if (card instanceof NumberedAbstractCard) {
      PlayersQueue.getInstance().nextPlayer();
    } else if (card instanceof AbstractActionAbstractCard abstractActionCard){
      abstractActionCard.performAction();
    } else if (card instanceof AbstractWildCard wildCard) {
      wildCard.performAction();
    }
  }
  
  @Override
  protected int selectCard(Player player){
    return def.selectCard(player);
  }

  @Override
  protected void displayRoundWinner() {
    System.out.println(roundWinner.getName() + " won this round 🎉🎉🎉🎉🎉");
  }
  
  @Override
  protected void displayScores(){
    for (Player player : playerQueue) {
      System.out.println(player.getName() + "'s score: " + player.getScore());
    }
  }
  
  @Override
  protected void endRound(){
    for(Player player : playerQueue){
      player.clearCardList();
    }
    drawPile.initializeDrawPile();
    discardPile.initializeDiscardPile();
  }
}
