package piles;

import abstractCard.Card;

public interface CardObserver {
    void cardAdded(Card cards);
    void cardRemoved(Card cards);
}
