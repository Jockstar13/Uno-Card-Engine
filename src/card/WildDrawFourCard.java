package card;

import abstractCard.WildCard;
import queue.PlayersQueue;

public class WildDrawFourCard extends WildCard {
  @Override
  public String getCardName() {
    return "Draw Four";
  }

  @Override
  public void performAction() {
    chooseColor();
    PlayersQueue playerQueue = PlayersQueue.getInstance();
    playerQueue.nextPlayer();
    playerQueue.getQueue().peek().drawCard(4);
    System.out.println(playerQueue.getQueue().peek().getName()+" has drawn four cards!");
    playerQueue.nextPlayer();
  }
}
