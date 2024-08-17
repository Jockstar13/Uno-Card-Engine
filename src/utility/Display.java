package utility;

import abstractCard.*;
import card.NumberedCard;
import queue.Player;

import java.util.ArrayList;
import java.util.List;

import static utility.Utility.*;

public class Display {

  public static void printTopDiscardedCard(Card card){
    System.out.println("Discard Pile");
    List<Card> cardList = new ArrayList<>();
    cardList.add(card);
    String discardPile = spaceParts(cardList) + namePart(cardList) + spaceParts(cardList);
    System.out.println(discardPile);
  }
  public static void printPlayerCards(Player player){
    System.out.println(player.getName()+"'s turn");
    List<Card> cardList = player.getCardList();
    String playerCards = spaceParts(cardList) + namePart(cardList) + spaceParts(cardList) + drawNumbers(cardList);
    System.out.println(playerCards);
  }
  
  public static void printColorCards(){ //when wild card is chosen
    List<Card> cardList = new ArrayList<>();
    iterator<Color> iter=Color.getIterator();
    while(iter.hasNext()){
      NumberedCard card = new NumberedCard(0,iter.next());
      cardList.add(card);
    }
    String colorCards = spaceParts(cardList) + spaceParts(cardList) + drawNumbers(cardList);
    System.out.println(colorCards);
  }
  
  private static String spaceParts(List<Card> cardList){
    StringBuilder c = new StringBuilder();
    for(int i=0;i<2;i++){ // height of card
      for (Card card : cardList) {
        String color = "";
        if(card instanceof WildCard wildCard){
          color = wildCard.getColor() == null ? WHITE : getColor(wildCard.getColor());
        }
        if(card instanceof ActionCard actionCard){
          color = getColor(actionCard.getColor());
        }
        if(card instanceof NumberedCard numberedCard){
          color = getColor(numberedCard.getColor());
        }
        String fill = color + space(cardWidth) + STOP + space(spaceBetweenCards);
        c.append(fill);
      }
      c.append("\n");
    }
    return c.toString();
  }
  
  private static String namePart(List<Card> cardList) {
    StringBuilder c = new StringBuilder();
    for (Card card : cardList) {
      String name = card.getCardName();
      String color = "";
      if(card instanceof WildCard wildCard){
        color = wildCard.getColor() == null ? WHITE : getColor(wildCard.getColor());
      }
      if(card instanceof ActionCard actionCard){
        color = getColor(actionCard.getColor());
      }
      if(card instanceof NumberedCard numberedCard){
        name=Integer.toString(numberedCard.getNumber());
        color = getColor(numberedCard.getColor());
      }
      int spaceCount = (cardWidth - name.length()) / 2;
      String fill = color + space(spaceCount) + BLACK_FONT + name + space(cardWidth - (name.length() + spaceCount)) + STOP + space(spaceBetweenCards);
      c.append(fill);
    }
    c.append("\n");
    return c.toString();
  }
  
  private static String drawNumbers(List<Card> cardList){
    StringBuilder c = new StringBuilder();
    c.append(space((cardWidth-1)/2));
    for(int i=1;i<=cardList.size();i++){
      c.append(i).append(space(cardWidth + (i < 10 ? 1 : 0)));
    }
    c.append("\n");
    return c.toString();
  }
  
}
