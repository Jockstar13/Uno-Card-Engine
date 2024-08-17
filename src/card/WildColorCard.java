package card;

import abstractCard.WildCard;
import queue.PlayersQueue;

public class WildColorCard extends WildCard {
  @Override
  public String getCardName() {
    return "Wild";
  }

  @Override
  public void performAction() {
    chooseColor();
    PlayersQueue.getInstance().nextPlayer();
  }
}
