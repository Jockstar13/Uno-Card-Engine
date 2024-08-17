package card;

import abstractCard.ActionCard;
import abstractCard.Color;
import queue.PlayersQueue;


public class DrawTwoCard extends ActionCard {
  public DrawTwoCard(Color color) {
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
