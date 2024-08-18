package card.unoCards;

import card.abstractCard.AbstractWildCard;
import queue.UnoPlayersQueue;

public class DrawFourWildCard extends AbstractWildCard {
  @Override
  public String getCardName() {
    return "Draw Four";
  }

  @Override
  public void performAction() {
    chooseColor();
    UnoPlayersQueue playerQueue = UnoPlayersQueue.getInstance();
    playerQueue.nextPlayer();
    playerQueue.getQueue().peek().drawCard(4);
    System.out.println(playerQueue.getQueue().peek().getName()+" has drawn four cards!");
    playerQueue.nextPlayer();
  }
}
