package card.unoCards;

import card.abstractCard.AbstractActionCard;
import card.abstractCard.Color;
import queue.UnoPlayer;
import queue.UnoPlayersQueue;

import java.util.Queue;

import static queue.UnoPlayersQueue.reverseQueue;

public class ReverseCard extends AbstractActionCard {
  public ReverseCard(Color color) {
    super(color);
  }
  @Override
  public String getCardName() {
    return "Reverse";
  }

  @Override
  public void performAction(){
    Queue<UnoPlayer> unoPlayerQueue = UnoPlayersQueue.getInstance().getQueue();
    if(unoPlayerQueue.size()!=2) { // the queue shouldn't be reversed if there's only two players, it acts as a skip instead
      reverseQueue(unoPlayerQueue);
    }
  }
}
