package card.unoCards;

import card.abstractCard.AbstractActionAbstractCard;
import card.abstractCard.Color;
import queue.PlayersQueue;

public class SkipAbstractCardAbstract extends AbstractActionAbstractCard {
  public SkipAbstractCardAbstract(Color color) {
    super(color);
  }
  @Override
  public String getCardName() {
    return "Skip";
  }

  @Override
  public void performAction() {
    PlayersQueue playerQueue = PlayersQueue.getInstance();
    playerQueue.nextPlayer();
    System.out.println(playerQueue.getQueue().peek().getName()+" has been skipped!");
    playerQueue.nextPlayer();
  }
  
}
