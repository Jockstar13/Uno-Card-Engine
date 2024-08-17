package exceptions;

public class IllegalCardException extends IllegalArgumentException {
  public IllegalCardException(String message){
    super(message);
  }
}
