package card.unoCards;

import card.abstractCard.AbstractActionAbstractCard;
import card.abstractCard.AbstractCard;
import card.abstractCard.Color;
import card.abstractCard.AbstractWildCard;
import exceptions.IllegalCardException;

public class NumberedAbstractCard implements AbstractCard {
  private final int number;
  private final Color color;
  
  public NumberedAbstractCard(int number, Color color) {
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
  public boolean canBePlayed(AbstractCard topCard) {
    if(topCard instanceof AbstractActionAbstractCard card2){
      return getColor() == card2.getColor();
    }
    if(topCard instanceof AbstractWildCard card2){
      return getColor() == card2.getColor();
    }
    if(topCard instanceof NumberedAbstractCard card2){
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
