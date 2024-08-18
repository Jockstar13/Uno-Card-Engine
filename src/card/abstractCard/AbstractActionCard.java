package card.abstractCard;

import card.unoCards.NumberedCard;
import exceptions.IllegalCardException;

public abstract class AbstractActionCard implements AbstractCard {
  private final Color color;

  protected AbstractActionCard(Color color){
    this.color=color;
  }
  
  public Color getColor() {
    return color;
  }
  
  @Override
  public boolean isPlayable(AbstractCard topCard) {
    if(topCard instanceof NumberedCard card2){
      return (getColor() == card2.getColor());
    }
    if(topCard instanceof AbstractActionCard card2){
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
