package card.unoCards;

import card.abstractCard.AbstractWildCard;
import queue.UnoPlayersQueue;

public class ColorWildCard extends AbstractWildCard {
  @Override
  public String getCardName() {
    return "Wild";
  }

  @Override
  public void performAction() {
    chooseColor();
    UnoPlayersQueue.getInstance().nextPlayer();
  }
}
