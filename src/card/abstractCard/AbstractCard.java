package card.abstractCard;

public interface AbstractCard {
  String getCardName();
  boolean canBePlayed(AbstractCard card);
  int getCardScore();
}
