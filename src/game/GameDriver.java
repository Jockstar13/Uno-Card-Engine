package game;

import customGame.CustomGame;
import defaultGame.DefaultGame;

public class GameDriver {
  public static void main(String[] args){
    //Game game = new CustomGame();
    Game game = new DefaultGame();
    game.play();
  }
}
