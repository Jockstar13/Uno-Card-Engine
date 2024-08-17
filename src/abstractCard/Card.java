package abstractCard;

public interface Card {
  String getCardName();
  boolean canBePlayed(Card card);
  int getCardScore();
}
