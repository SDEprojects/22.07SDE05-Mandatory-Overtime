package com.mandatory_overtime.controller;

import com.mandatory_overtime.view.UserView;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;


import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.stream.Stream;

public class GamePlay {

  private String verb;
  private String noun;

  public final BufferedReader inputParser = new BufferedReader(new InputStreamReader(System.in));
  public String[] userInputArray;

  UserView userView = new UserView();

  public void printGameIntroduction() {
    try {
      //      print startup title info
      System.out.println(userView.startUpTitle());
      System.out.println(userView.showHelp());

      //      print game info and username
      System.out.println("What is your name");
      String name = inputParser.readLine().trim();
      System.out.println(userView.startUpInfo(name));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void gamePlayParser() {
//    this parses userInput into 1st word == verb and last word == noun
    try {
        userInputArray = inputParser.readLine().trim().split(" ");
        verb = userInputArray[0];
        noun = userInputArray[userInputArray.length - 1];
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public void newGame() {
    GamePlay restart = new GamePlay();
    restart.printGameIntroduction();
    restart.gamePlayCommands();
  }

public void quit() {
//    this prompts user whether they want to quit continue or new game
  System.out.println(
      "Are you sure you would like to quit, start a new game or would you like to continue your game? \n(Enter: 'quit', 'new game', or 'continue game')");
  while(true) {
    gamePlayParser();
    if (verb.equals("quit")) {
      System.exit(0);
    } else if (verb.equals("new")) {
      newGame();
    } else if (verb.equals("continue")) {
      break;
    }
    System.out.println("Please type 'quit', 'new game' or 'continue game'");
  }
}
  public void gamePlayCommands() {
//this parses commands to controller and view using conditionals
    do {
      gamePlayParser();
      if (verb.equals("help")) {
        System.out.println(userView.showHelp());
      } else if (verb.equals("quit")) {
        quit();
      } else {
        System.out.println(userView.incorrectInput() + userView.showHelp());
      }
    } while (true);

  }
}


