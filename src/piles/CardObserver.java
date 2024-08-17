package piles;

import card.abstractCard.AbstractCard;

public interface CardObserver {
    void cardAdded(AbstractCard cards);
    void cardRemoved(AbstractCard cards);
}
