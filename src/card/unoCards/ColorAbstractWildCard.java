package card.unoCards;

import card.abstractCard.AbstractWildCard;
import queue.PlayersQueue;

public class ColorAbstractWildCard extends AbstractWildCard {
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
