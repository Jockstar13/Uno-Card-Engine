package card.unoCards;

import card.abstractCard.AbstractActionCard;
import card.abstractCard.Color;
import queue.UnoPlayersQueue;

public class SkipCard extends AbstractActionCard {
  public SkipCard(Color color) {
    super(color);
  }
  @Override
  public String getCardName() {
    return "Skip";
  }

  @Override
  public void performAction() {
    UnoPlayersQueue playerQueue = UnoPlayersQueue.getInstance();
    playerQueue.nextPlayer();
    System.out.println(playerQueue.getQueue().peek().getName()+" has been skipped!");
    playerQueue.nextPlayer();
  }
  
}
