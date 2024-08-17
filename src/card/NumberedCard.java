package card;

import abstractCard.ActionCard;
import abstractCard.Card;
import abstractCard.Color;
import abstractCard.WildCard;
import exceptions.IllegalCardException;

public class NumberedCard implements Card {
  private final int number;
  private final Color color;
  
  public NumberedCard(int number, Color color) {
    this.number = number;
    this.color = color;
  }
  public int getNumber() {
    return number;
  }
  
  public Color getColor() {
    return color;
  }
  
  @Override
  public String getCardName() {
    return "Numbered";
  }
  
  @Override
  public boolean canBePlayed(Card topCard) {
    if(topCard instanceof ActionCard card2){
      return getColor() == card2.getColor();
    }
    if(topCard instanceof WildCard card2){
      return getColor() == card2.getColor();
    }
    if(topCard instanceof NumberedCard card2){
      return (getNumber() == card2.getNumber() || getColor() == card2.getColor());
    }
    throw new IllegalCardException("Invalid card type: " + topCard);
  }
  
  @Override
  public int getCardScore() {
    return number;
  }

  @Override
  public String toString() {
    return color.toString().toLowerCase() + " " + getNumber() + " card.";
  }
}
