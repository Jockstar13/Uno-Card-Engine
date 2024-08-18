package game;

import queue.UnoPlayer;

import java.util.Queue;

public abstract class Game {
  protected Options options;
  protected Queue<UnoPlayer> playersQueue;
  protected UnoPlayer gameWinner;
  protected GameRound gameRound;
  
  public abstract void play();
  protected abstract boolean isGameOver();
  protected abstract String promptPlayAgain();
  protected abstract void displayWinner();
  
}
