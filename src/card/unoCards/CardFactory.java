package card.unoCards;

import card.abstractCard.AbstractCard;
import card.abstractCard.Color;
import exceptions.IllegalCardException;

public class CardFactory {
  // based on the argument the factory will create the chosen card type
  public static AbstractCard createCard(int number, Color color){
    return new NumberedCard(number,color);
  }
  public static AbstractCard createCard(String cardType, Color color){
    return switch (cardType) {
      case "Skip" -> new SkipCard(color);
      case "Reverse" -> new ReverseCard(color);
      case "DrawTwo" -> new DrawTwoCard(color);
      default -> throw new IllegalCardException("Invalid card type: " + cardType);
    };
  }
  public static AbstractCard createCard(String cardType){
    return switch (cardType) {
      case "Wild" -> new ColorWildCard();
      case "WildDrawFour" -> new DrawFourWildCard();
      default -> throw new IllegalCardException("Invalid card type: " + cardType);
    };
  }
}
