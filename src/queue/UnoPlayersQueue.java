package queue;

import exceptions.InvalidInputException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class UnoPlayersQueue {
  private static UnoPlayersQueue queueInstance;
  private final Queue<UnoPlayer> queue;
  private UnoPlayersQueue(){
    queue=new LinkedList<>();
    initializeQueue();
  }
  public static UnoPlayersQueue getInstance(){
    if(queueInstance==null)
      queueInstance = new UnoPlayersQueue();
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
        UnoPlayer p = new UnoPlayer(player);
        queue.add(p);
      }
    }catch(InvalidInputException e){
      System.out.println(e.getMessage());
      initializeQueue();
    }
  }
  
  public Queue<UnoPlayer> getQueue(){
    return queue;
  }
  
  public void nextPlayer(){
    UnoPlayer currentUnoPlayer = queue.remove();
    queue.add(currentUnoPlayer);
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
