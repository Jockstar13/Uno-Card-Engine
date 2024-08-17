package piles;

import card.abstractCard.AbstractCard;
import card.unoCards.NumberedAbstractCard;

import java.util.Stack;

public class DiscardPile {
  private static DiscardPile discardPileInstance;
  private Stack<AbstractCard> cardStack;
  private DiscardPile(){
    initializeDiscardPile();
  }

  public static DiscardPile getInstance() {
    if(discardPileInstance==null){
      discardPileInstance = new DiscardPile();
    }
    return discardPileInstance;
  }

  public AbstractCard getTopCard(){
    return cardStack.peek();
  }

  public void initializeDiscardPile(){
    cardStack = new Stack<>();
    AbstractCard c = DrawPile.getInstance().drawCard();
    cardStack.push(c);
    if(!(c instanceof NumberedAbstractCard)){ // first card in discard pile has to be a number
      initializeDiscardPile();
    }
  }

  public void addCard(AbstractCard card){
    cardStack.push(card);
  }

  public Stack<AbstractCard> getCardStack() {
    return cardStack;
  }
}
