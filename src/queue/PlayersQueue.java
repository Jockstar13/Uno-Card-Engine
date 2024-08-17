package queue;

import exceptions.InvalidInputException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class PlayersQueue {
  private static PlayersQueue queueInstance;
  private final Queue<Player> queue;
  private PlayersQueue(){
    queue=new LinkedList<>();
    initializeQueue();
  }
  public static PlayersQueue getInstance(){
    if(queueInstance==null)
      queueInstance = new PlayersQueue();
    return queueInstance;
  }
  
  private void initializeQueue(){
    System.out.println("Welcome to UNO!");
    try {
      System.out.println("Enter your names separated by spaces:");
      Scanner input=new Scanner(System.in);
      String players=input.nextLine();
      String[] playersArray = players.split(" ");
      if(playersArray.length < 2){
        throw new InvalidInputException("You need at least 2 players to play UNO. Try again.");
      }
      if(playersArray.length > 10){
        throw new InvalidInputException("Maximum number of players is 10. Try again.");
      }
      for (String player : playersArray) {
        Player p = new Player(player);
        queue.add(p);
      }
    }catch(InvalidInputException e){
      System.out.println(e.getMessage());
      initializeQueue();
    }
  }
  
  public Queue<Player> getQueue(){
    return queue;
  }
  
  public void nextPlayer(){
    Player currentPlayer = queue.remove();
    queue.add(currentPlayer);
  }

  public static <T> void reverseQueue(Queue<T> queue){
    Stack<T> stack = new Stack<>();
    while (!queue.isEmpty()) {
      stack.push(queue.remove());
    }
    while (!stack.isEmpty()) {
      queue.add(stack.pop());
    }
  }
}
