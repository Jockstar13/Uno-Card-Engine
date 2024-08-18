package card.abstractCard;

public interface AbstractCard {
  String getCardName();
  boolean isPlayable(AbstractCard card);
  int getCardScore();
}
