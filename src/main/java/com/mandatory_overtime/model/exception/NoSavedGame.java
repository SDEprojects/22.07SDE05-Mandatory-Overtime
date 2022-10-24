package com.mandatory_overtime.model.exception;

import java.io.FileNotFoundException;

public class NoSavedGame extends FileNotFoundException {

  public NoSavedGame() {
    System.out.println("You have no saved previous game. Starting new game.");

  }

  public NoSavedGame(String s) {
    super(s);

  }
}
