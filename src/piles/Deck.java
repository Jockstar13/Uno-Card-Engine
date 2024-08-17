package piles;

import java.lang.reflect.Method;
import java.util.HashMap;
public class Deck {
  private static Deck deckInstance;
  private final HashMap<String,Integer> deck;

  private Deck(){
    deck=new HashMap<>();
    DeckInfo defaultDeckOptions=new DeckInfo();
    initializeDeck(defaultDeckOptions);
  }

  public static Deck getInstance() {
    if(deckInstance==null)
      deckInstance = new Deck();
    return deckInstance;
  }

  public void setDeckOptions(DeckInfo deckOptions) {
    initializeDeck(deckOptions);
  }

  private void initializeDeck(DeckInfo deckOptions){ // calls all getters in DeckInfo and puts the card name and its count in the hashmap
    deck.clear();
    Class<?> deckOptionsClass=deckOptions.getClass();
    Method[] methods=deckOptionsClass.getDeclaredMethods();
    for(Method method:methods){
      if (method.getName().startsWith("get")){
        try {
          int result = (Integer) method.invoke(deckOptions);
          deck.put(method.getName().substring(3),result);
        }catch (Exception e){
          e.printStackTrace();
        }
      }
    }
  }

  public HashMap<String, Integer> getDeck() {
    return deck;
  }
}