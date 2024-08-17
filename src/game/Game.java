package game;

import queue.Player;

import java.util.Queue;

public abstract class Game {
  protected Options options;
  protected Queue<Player> playersQueue;
  protected Player gameWinner;
  protected GameRound gameRound;
  
  public abstract void play();
  protected abstract boolean isGameOver();
  protected abstract String playMore();
  protected abstract void displayWinner();
  
}
