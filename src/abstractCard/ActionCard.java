package abstractCard;

import card.NumberedCard;
import exceptions.IllegalCardException;

public abstract class ActionCard implements Card{
  private final Color color;

  protected ActionCard(Color color){
    this.color=color;
  }
  
  public Color getColor() {
    return color;
  }
  
  @Override
  public boolean canBePlayed(Card topCard) {
    if(topCard instanceof NumberedCard card2){
      return (getColor() == card2.getColor());
    }
    if(topCard instanceof ActionCard card2){
      return (getColor() == card2.getColor() || getCardName().equals(card2.getCardName()));
    }
    if(topCard instanceof WildCard card2){
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
