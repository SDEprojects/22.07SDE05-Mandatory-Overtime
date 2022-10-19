package com.mandatory_overtime.controller;

import com.mandatory_overtime.model.Building;
import com.mandatory_overtime.view.UserView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GamePlay {

  public final BufferedReader inputParser = new BufferedReader(new InputStreamReader(System.in));
  public String[] userInputArray;

  Building building;
  UserView userView = new UserView();
  private String verb;
  private String noun;

  public GamePlay() throws IOException {
    building = new Building();
  }


  public void printGameIntroduction() {
    try {
      //      print startup title info
      System.out.println(userView.startUpTitle());
      System.out.println(userView.showHelp());
      //      print game info and username
      System.out.println("What is your name");
      String name = inputParser.readLine().trim();
      System.out.println(userView.startUpInfo(name));
      //run printRoomDescription() here to show current room.
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void gamePlayParser() {
//    this parses userInput into 1st word == verb and last word == noun
    try {
      userInputArray = inputParser.readLine().trim().split(" ", 1);
      verb = userInputArray[0];
      noun = userInputArray[userInputArray.length - 1];
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void newGame() throws IOException {
    GamePlay restart = new GamePlay();
    restart.printGameIntroduction();
    restart.gamePlayCommands();
  }

  public void quit() throws IOException {
//    this prompts user whether they want to quit continue or new game
    System.out.println(
        "Are you sure you would like to quit, start a new game or would you like to continue your game? \n(Enter: 'quit', 'new game', or 'continue game')");
    while (true) {
      gamePlayParser();
      if (verb.equals("quit")) {
        System.exit(0); //building.quit()
      } else if (verb.equals("new")) {
        newGame(); //building.newGme()
      } else if (verb.equals("continue")) {
        break; //samesy
      }
      System.out.println("Please type 'quit', 'new game' or 'continue game'");
    }
  }

  public void gamePlayCommands() {
//this parses commands to controller and view using conditionals
    do {

      try {
        gamePlayParser();
        if (verb.equals("help")) {
          System.out.println(userView.showHelp());
        } else if (verb.equals("quit")) {
          quit();
        } else if (verb.equals("go")) {
  //       add moveRooms method. Invoke method in building to move(noun)
        } else if (verb.equals("get")) {
  //     add get Item method
        } else if (verb.equals("interact")) {
  //        add npc interact method here
        } else {
          System.out.println(userView.incorrectInput() + userView.showHelp());
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }

    } while (true);//building is updating the state
    //State = state.loss
    //!building.getGameState()
  }
}