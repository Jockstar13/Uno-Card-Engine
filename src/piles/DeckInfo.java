package piles;

public class DeckInfo { // stores the count of each card type for each color
  private int zeroCards;
  private int numberedCards;
  private int skipCards;
  private int reverseCards;
  private int drawTwoCards;
  private int wildCards;
  private int wildDrawFourCards;

  public DeckInfo(){
    setZeroCards(1); // 1 zero card for each color, 4 total
    setNumberedCards(2);
    setReverseCards(2);
    setDrawTwoCards(2);
    setSkipCards(2);
    setWildCards(1); // 4 cards
    setWildDrawFourCards(1); // 4 cards
  }
  public int getZero() {
    return zeroCards;
  }

  public void setZeroCards(int zeroCards) {
    this.zeroCards = zeroCards;
  }

  public int getNumbered() {
    return numberedCards;
  }

  public void setNumberedCards(int numberedCards) {
    this.numberedCards = numberedCards;
  }

  public int getSkip() {
    return skipCards;
  }

  public void setSkipCards(int skipCards) {
    this.skipCards = skipCards;
  }

  public int getReverse() {
    return reverseCards;
  }

  public void setReverseCards(int reverseCards) {
    this.reverseCards = reverseCards;
  }

  public int getDrawTwo() {
    return drawTwoCards;
  }

  public void setDrawTwoCards(int drawTwoCards) {
    this.drawTwoCards = drawTwoCards;
  }

  public int getWild() {
    return wildCards;
  }

  public void setWildCards(int wildCards) {
    this.wildCards = wildCards;
  }

  public int getWildDrawFour() {
    return wildDrawFourCards;
  }

  public void setWildDrawFourCards(int wildDrawFourCards) {
    this.wildDrawFourCards = wildDrawFourCards;
  }
}
