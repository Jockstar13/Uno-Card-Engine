package piles;

import card.abstractCard.AbstractCard;
import card.abstractCard.Color;
import card.abstractCard.iterator;
import card.unoCards.CardFactory;
import exceptions.IllegalCardException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class DrawPile {
  private static DrawPile drawPileInstance;
  private Stack<AbstractCard> cardStack;
  private DrawPile(){
    initializeDrawPile();
  }

  public static DrawPile getInstance() {
    if(drawPileInstance==null){
      drawPileInstance = new DrawPile();
    }
    return drawPileInstance;
  }

  public AbstractCard drawCard(){
    if (cardStack.empty()){
      handleEmptyDrawPile();
    }
    return cardStack.pop();
  }

  public void initializeDrawPile(){
    cardStack=new Stack<>();
    HashMap<String,Integer> deck = Deck.getInstance().getDeck();
    for (String key : deck.keySet()) {
      createCardType(key, deck);
    }
    Collections.shuffle(cardStack);
  }

  private void handleEmptyDrawPile(){
    // excluding the top card, Transfers all cards from the discard pile to the draw pile
    Stack<AbstractCard> discardPile = DiscardPile.getInstance().getCardStack();
    AbstractCard topCard = discardPile.pop();
    while (!discardPile.empty()){
      cardStack.push(discardPile.pop());
    }
    Collections.shuffle(cardStack);
    discardPile.push(topCard);
  }

  public int getDrawPileSize(){
    return cardStack.size();
  }

  private void createCardType(String cardType, HashMap<String,Integer> deck){
    switch (cardType){
      case "Numbered" -> createNumberedCards(deck.get("Zero"), deck.get("Numbered"));
      case "Skip", "Reverse", "DrawTwo" -> createActionCards(cardType,deck.get(cardType));
      case "Wild", "WildDrawFour" -> createWildCards(cardType, deck.get(cardType));
      case "Zero" -> {}
      default -> throw new IllegalCardException("Invalid card type: " + cardType);
    }
  }

  private void createNumberedCards(int numZeroCards, int numOtherCards){
    for(int i=0;i<=9;i++){
      int count=i==0?numZeroCards:numOtherCards;
      for(int j=0;j<count;j++){
        iterator<Color>iter=Color.getIterator();
        while(iter.hasNext()){
          cardStack.push(CardFactory.createCard(i, iter.next()));
        }
      }
    }
  }

  private void createActionCards(String cardType, int numCards){
    for(int i=0;i< numCards;i++){
      iterator<Color>iter=Color.getIterator();
      while (iter.hasNext()) {
        cardStack.push(CardFactory.createCard(cardType, iter.next()));
      }
    }
  }

  private void createWildCards(String cardType, int numCards){
    int numColors= Color.values().length;
    for(int i=0;i< numCards;i++){
      for(int j=0;j<numColors;j++) {
        cardStack.push(CardFactory.createCard(cardType));
      }
    }
  }

}
