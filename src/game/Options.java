package game;

import piles.Deck;
import piles.DeckInfo;

public class Options {
  private final DeckInfo deckOptions;
  private final int numOfCardsPerPlayer;
  private final boolean sayUno;
  private final int scoreToWin;
  private final boolean drawOnlyOneCardIfCantPlay;

  public static class Builder {
    private DeckInfo deckOptions = new DeckInfo();
    private int numOfCardsPerPlayer = 7;
    private boolean sayUno = true;
    private int scoreToWin = 500;
    private boolean drawOnlyOneCard = true;
    public Builder deckOptions(DeckInfo deckOptions){
      this.deckOptions = deckOptions;
      Deck.getInstance().setDeckOptions(deckOptions);
      return this;
    }
    public Builder numOfCardsPerPlayer(int numOfCardsPerPlayer){
      this.numOfCardsPerPlayer = numOfCardsPerPlayer;
      return this;
    }
    public Builder sayUno(boolean sayUno){
      this.sayUno = sayUno;
      return this;
    }
    public Builder scoreToWin(int scoreToWin){
      this.scoreToWin = scoreToWin;
      return this;
    }
    public Builder drawOnlyOneCard(boolean drawOnlyOneCard){
      this.drawOnlyOneCard = drawOnlyOneCard;
      return this;
    }
    public Options build(){
      return new Options(this);
    }
  }

  public Options(Builder builder){
    deckOptions = builder.deckOptions;
    numOfCardsPerPlayer = builder.numOfCardsPerPlayer;
    sayUno = builder.sayUno;
    scoreToWin = builder.scoreToWin;
    drawOnlyOneCardIfCantPlay = builder.drawOnlyOneCard;
  }

  public DeckInfo getDeckOptions() {
    return deckOptions;
  }

  public int getNumOfCardsPerPlayer() {
    return numOfCardsPerPlayer;
  }

  public boolean hasToSayUno() {
    return sayUno;
  }

  public int getScoreToWin() {
    return scoreToWin;
  }

  public boolean getDrawOnlyOneCardIfCantPlay() {
    return drawOnlyOneCardIfCantPlay;
  }
}