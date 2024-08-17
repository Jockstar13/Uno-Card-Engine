package card.unoCards;

import card.abstractCard.AbstractActionAbstractCard;
import card.abstractCard.Color;
import queue.Player;
import queue.PlayersQueue;

import java.util.Queue;

import static queue.PlayersQueue.reverseQueue;

public class ReverseAbstractCardAbstract extends AbstractActionAbstractCard {
  public ReverseAbstractCardAbstract(Color color) {
    super(color);
  }
  @Override
  public String getCardName() {
    return "Reverse";
  }

  @Override
  public void performAction(){
    Queue<Player> playerQueue = PlayersQueue.getInstance().getQueue();
    if(playerQueue.size()!=2) { // the queue shouldn't be reversed if there's only two players, it acts as a skip instead
      reverseQueue(playerQueue);
    }
  }
}
