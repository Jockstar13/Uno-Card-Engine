package piles;

import abstractCard.Card;
import card.NumberedCard;

import java.util.Stack;

public class DiscardPile {
  private static DiscardPile discardPileInstance;
  private Stack<Card> cardStack;
  private DiscardPile(){
    initializeDiscardPile();
  }

  public static DiscardPile getInstance() {
    if(discardPileInstance==null){
      discardPileInstance = new DiscardPile();
    }
    return discardPileInstance;
  }

  public Card getTopCard(){
    return cardStack.peek();
  }

  public void initializeDiscardPile(){
    cardStack = new Stack<>();
    Card c = DrawPile.getInstance().drawCard();
    cardStack.push(c);
    if(!(c instanceof NumberedCard)){ // first card in discard pile has to be a number
      initializeDiscardPile();
    }
  }

  public void addCard(Card card){
    cardStack.push(card);
  }

  public Stack<Card> getCardStack() {
    return cardStack;
  }
}
