package card;

import abstractCard.ActionCard;
import abstractCard.Color;
import queue.PlayersQueue;

public class SkipCard extends ActionCard {
  public SkipCard(Color color) {
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
