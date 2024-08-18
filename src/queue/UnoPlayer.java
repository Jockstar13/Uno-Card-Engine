package queue;

import card.abstractCard.AbstractCard;
import piles.DiscardPile;
import piles.DrawPile;

import java.util.ArrayList;
import java.util.List;

public class UnoPlayer {
  private final String name;
  private List<AbstractCard> cardList;
  private int score;
  
  public UnoPlayer(String name){
    this.name = name;
    this.cardList = new ArrayList<>();
    this.score = 0;
  }
  
  public AbstractCard drawCard(){
    AbstractCard card = DrawPile.getInstance().drawCard();
    cardList.add(card);
    return card;
  }
  
  public void drawCard(int num){
    for (int i=0;i<num;i++){
      drawCard();
    }
  }
  
  public void playCard(int num){
    AbstractCard playedCard = cardList.remove(num);
    DiscardPile.getInstance().addCard(playedCard);
  }
  
  public int getScore() {
    return score;
  }
  
  public void incrementScore(int score) {
    this.score += score;
  }
  
  public String getName() {
    return name;
  }
  
  public List<AbstractCard> getCardList() {
    return cardList;
  }
  
  public void clearCardList(){
    cardList = new ArrayList<>();
  }
  
}
