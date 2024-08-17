package card.unoCards;

import card.abstractCard.AbstractActionAbstractCard;
import card.abstractCard.Color;
import queue.PlayersQueue;


public class DrawTwoAbstractCardAbstract extends AbstractActionAbstractCard {
  public DrawTwoAbstractCardAbstract(Color color) {
    super(color);
  }
  
  @Override
  public String getCardName() {
    return "DrawTwo";
  }
  
  @Override
  public void performAction(){
    PlayersQueue playerQueue = PlayersQueue.getInstance();
    playerQueue.nextPlayer();
    playerQueue.getQueue().peek().drawCard(2);
    System.out.println(playerQueue.getQueue().peek().getName()+" has drawn two cards!");
    playerQueue.nextPlayer();
  }
}
