package com.mandatory_overtime.model.exception;

public class IllegalMoveException extends IllegalArgumentException{

  public IllegalMoveException(){}
  public IllegalMoveException(String noun){
    System.out.println("Sorry, you can't go to the " + noun + " from your current location.");
  }
}

// * Represents the exceptional condition resulting from an attempt to make an invalid move in an
// * ongoing game.
// * <p>All constructors in this class correspond directly to the constructors with the same parameter
// * types in {@link IllegalArgumentException}.</p>
// *
// * @see com.nickbenn.onehundred.model.Game#play(int)
// */
//@SuppressWarnings({"unused"})
//public class IllegalMoveException extends IllegalArgumentException {
//  public IllegalMoveException() {
//  }
//  public IllegalMoveException(String message) {
//    super(message);
//  }
//  public IllegalMoveException(Throwable cause) {
//    super(cause);
//  }
//  public IllegalMoveException(String message, Throwable cause) {
//    super(message, cause);
//  }
//}