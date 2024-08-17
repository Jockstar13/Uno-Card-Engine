package card.unoCards;

import card.abstractCard.AbstractCard;
import card.abstractCard.Color;
import exceptions.IllegalCardException;

public class CardFactory { // the factory will decide which card type to create based on the provided arguments
  public static AbstractCard createCard(int number, Color color){
    return new NumberedAbstractCard(number,color);
  }
  public static AbstractCard createCard(String cardType, Color color){
    return switch (cardType) {
      case "Skip" -> new SkipAbstractCardAbstract(color);
      case "Reverse" -> new ReverseAbstractCardAbstract(color);
      case "DrawTwo" -> new DrawTwoAbstractCardAbstract(color);
      default -> throw new IllegalCardException("Invalid card type: " + cardType);
    };
  }
  public static AbstractCard createCard(String cardType){
    return switch (cardType) {
      case "Wild" -> new ColorAbstractWildCard();
      case "WildDrawFour" -> new DrawFourAbstractWildCard();
      default -> throw new IllegalCardException("Invalid card type: " + cardType);
    };
  }
}
