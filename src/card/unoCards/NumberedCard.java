package card.unoCards;

import card.abstractCard.AbstractActionCard;
import card.abstractCard.AbstractCard;
import card.abstractCard.Color;
import card.abstractCard.AbstractWildCard;
import exceptions.IllegalCardException;

public class NumberedCard implements AbstractCard {
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
  public boolean isPlayable(AbstractCard topCard) {
    if(topCard instanceof AbstractActionCard card2){
      return getColor() == card2.getColor();
    }
    if(topCard instanceof AbstractWildCard card2){
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
