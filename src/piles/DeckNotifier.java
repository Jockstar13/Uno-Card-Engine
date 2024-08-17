package piles;

import abstractCard.Card;

public class DeckNotifier implements CardObserver {
    private final Deck deck;

    public DeckNotifier(Deck deck) {
        this.deck = deck;
    }
    @Override
    public void cardAdded(Card card) {
            String cardType = card.getCardName();  // Assuming Card class has a method to get the card type
            int currentCount = deck.getDeck().getOrDefault(cardType, 0);
            deck.getDeck().put(cardType, currentCount + 1);
            System.out.println(card+" added");
    }
    @Override
    public void cardRemoved(Card card) {
            String cardType = card.getCardName();
            int currentCount = deck.getDeck().getOrDefault(cardType, 0);
            if (currentCount > 0) {
                deck.getDeck().put(cardType, currentCount - 1);
                System.out.println(card+" removed");
        }
    }
}
