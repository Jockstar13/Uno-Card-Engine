package piles;

import card.abstractCard.AbstractCard;

public class DeckNotifier implements CardObserver {
    private final Deck deck;

    public DeckNotifier(Deck deck) {
        this.deck = deck;
    }
    @Override
    public void cardAdded(AbstractCard card) {
            String cardType = card.getCardName();
        // in case the  Card class has a method to get the card type
            int currentCount = deck.getDeck().getOrDefault(cardType, 0);
            deck.getDeck().put(cardType, currentCount + 1);
            System.out.println(card+" added");
    }
    @Override
    public void cardRemoved(AbstractCard card) {
            String cardType = card.getCardName();
            int currentCount = deck.getDeck().getOrDefault(cardType, 0);
            if (currentCount > 0) {
                deck.getDeck().put(cardType, currentCount - 1);
                System.out.println(card+" removed");
        }
    }
}
