package card.unoCards;

import card.abstractCard.AbstractActionCard;
import card.abstractCard.Color;
import queue.UnoPlayersQueue;


public class DrawTwoCard extends AbstractActionCard {
  public DrawTwoCard(Color color) {
    super(color);
  }
  
  @Override
  public String getCardName() {
    return "DrawTwo";
  }
  
  @Override
  public void performAction(){
    UnoPlayersQueue playerQueue = UnoPlayersQueue.getInstance();
    playerQueue.nextPlayer();
    playerQueue.getQueue().peek().drawCard(2);
    System.out.println(playerQueue.getQueue().peek().getName()+" has drawn two cards!");
    playerQueue.nextPlayer();
  }
}
