package card.abstractCard;

import card.unoCards.NumberedAbstractCard;
import exceptions.IllegalCardException;

public abstract class AbstractActionAbstractCard implements AbstractCard {
  private final Color color;

  protected AbstractActionAbstractCard(Color color){
    this.color=color;
  }
  
  public Color getColor() {
    return color;
  }
  
  @Override
  public boolean canBePlayed(AbstractCard topCard) {
    if(topCard instanceof NumberedAbstractCard card2){
      return (getColor() == card2.getColor());
    }
    if(topCard instanceof AbstractActionAbstractCard card2){
      return (getColor() == card2.getColor() || getCardName().equals(card2.getCardName()));
    }
    if(topCard instanceof AbstractWildCard card2){
      return getColor() == card2.getColor();
    }
    throw new IllegalCardException("Invalid card type: " + topCard);
  }
  
  @Override
  public int getCardScore() {
    return 20;
  }
  
  @Override
  public String toString() {
    return color.toString().toLowerCase() + " " + getCardName() + " card.";
  }
  
  public abstract String getCardName();
  public abstract void performAction();
}
