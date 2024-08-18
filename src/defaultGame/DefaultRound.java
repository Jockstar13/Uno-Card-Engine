package defaultGame;

import card.abstractCard.AbstractActionCard;
import card.abstractCard.AbstractCard;
import card.abstractCard.AbstractWildCard;
import card.unoCards.NumberedCard;
import exceptions.IllegalMoveException;
import exceptions.InvalidInputException;
import game.GameRound;
import game.Options;
import piles.Deck;
import piles.DeckNotifier;
import queue.UnoPlayer;
import queue.UnoPlayersQueue;

import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Scanner;

public class DefaultRound extends GameRound {
  DeckNotifier deckNotifier;

  public DefaultRound(Queue<UnoPlayer> queue, Options o) {
    super(queue, o);
    deckNotifier = new DeckNotifier(Deck.getInstance());
  }

  @Override
  public void setupRound(){
    int numOfPlayers = unoPlayerQueue.size();
    int numOfCardsPerPlayer = options.getNumOfCardsPerPlayer();
    if (numOfPlayers * numOfCardsPerPlayer > drawDeck.getDrawPileSize() - 10){
      throw new IllegalArgumentException("There has to be at least 10 cards in the draw pile to start.");
    }
    for(int i=0;i<numOfPlayers;i++){
      UnoPlayer unoPlayer = unoPlayerQueue.remove();
      unoPlayer.drawCard(numOfCardsPerPlayer);
      unoPlayerQueue.add(unoPlayer);
    }
  }
  
  @Override
  public void playCard(UnoPlayer unoPlayer, int cardNumber){
    AbstractCard card = unoPlayer.getCardList().get(cardNumber);
    deckNotifier.cardRemoved(card);
    unoPlayer.playCard(cardNumber);
    if (card instanceof NumberedCard) {
      UnoPlayersQueue.getInstance().nextPlayer();
    } else if (card instanceof AbstractActionCard abstractActionCard){
      abstractActionCard.performAction();
    } else if (card instanceof AbstractWildCard wildCard) {
      wildCard.performAction();
    }
  }
  
  @Override
  public int selectCard(UnoPlayer unoPlayer){
    int cardNumber = 0;
    boolean validMove = false;
    while(!validMove) {
      try {
        cardNumber = handleCardNumberInput(unoPlayer);
        validateCardNumber(unoPlayer, cardNumber);
        cardNumber--;
        validatePlayableCard(unoPlayer, cardNumber);
        validMove = true;
      } catch (InvalidInputException e) {
        System.out.println(e.getMessage() + " Choose a valid card number:");
      } catch (InputMismatchException e){
        System.out.println("You need to enter a number. Enter a valid number:");
      } catch (IllegalMoveException e){
        System.out.println(e.getMessage() + " Choose a valid card:");
      }
    }
    return cardNumber;
  }
  
  public int handleCardNumberInput(UnoPlayer unoPlayer){
    System.out.println("Choose a card, " + unoPlayer.getName());
    if (options.hasToSayUno()) {
      String sayUno = sayUno(unoPlayer);
      if (unoPlayer.getCardList().size() == 2 && sayUno.equalsIgnoreCase("Uno")) {
        System.out.println("Good job! You remembered to say Uno. Choose a card:");
        return chooseCardNumber();
      }
      return Integer.parseInt(sayUno);
    }
    return chooseCardNumber();
  }
  
  public String sayUno(UnoPlayer unoPlayer){
    Scanner input = new Scanner(System.in);
    String uno = input.next();
    if (unoPlayer.getCardList().size() == 2 && !uno.equalsIgnoreCase("Uno")){
      System.out.println("You forgot to say Uno! You have to draw two cards.");
      unoPlayer.drawCard(2);
    }
    return uno;
  }
  
  public int chooseCardNumber() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }
  
  public void validateCardNumber(UnoPlayer unoPlayer, int cardNumber) throws InvalidInputException {
    if (cardNumber <= 0 || cardNumber > unoPlayer.getCardList().size()) {
      throw new InvalidInputException("You chose an invalid card number.");
    }
  }
  
  public void validatePlayableCard(UnoPlayer unoPlayer, int cardNumber) throws IllegalMoveException {
    AbstractCard chosenCard = unoPlayer.getCardList().get(cardNumber);
    if (!canBePlayed(chosenCard)) {
      throw new IllegalMoveException("You can't play this card.");
    }
  }
  
  @Override
  public void displayRoundWinner() {
    System.out.println("Congrats " + roundWinner.getName() + "!!! You won this round 🎉");
  }
  
  @Override
  public void displayScores(){
    for (UnoPlayer unoPlayer : unoPlayerQueue) {
      System.out.println(unoPlayer.getName() + "'s score: " + unoPlayer.getScore());
    }
  }
  
  @Override
  public void endRound(){
    for(UnoPlayer unoPlayer : unoPlayerQueue){
      for(AbstractCard card: unoPlayer.getCardList())
        deckNotifier.cardAdded(card);
      unoPlayer.clearCardList();
    }
    drawDeck.initializeDrawPile();
    discardDeck.initializeDiscardPile();
  }

}
