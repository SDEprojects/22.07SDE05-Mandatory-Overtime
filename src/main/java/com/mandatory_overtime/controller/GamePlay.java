package com.mandatory_overtime.controller;

import com.mandatory_overtime.model.Building;
import com.mandatory_overtime.model.GameState;
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

  /**
   * Constructor for Gameplay that starts newGame();
   * @throws IOException
   */
  public GamePlay() throws IOException {
    newGame();
  }

  /**
   * THIS METHOD STARTS A NEWGAME FROM INTRODUCTION
   * Starts gameIntro and then startmenu() start from new or start from save.
   * @throws IOException
   */
  public void newGame() throws IOException {
    printGameIntroduction();
    startMenu();
  }

  /**
   * Prints game intro Ascii art and game commands.
   */
  public void printGameIntroduction() {
  //      print startup title info
  System.out.println(userView.startUpTitle());
  System.out.println(userView.showHelp());
}

  /**
   * Asks if user wants to start game from new or game from previous game
   * @throws IOException
   */
  public void startMenu() throws IOException {
    System.out.println("\n-------------------------------------Game Menu---------------------------------------------\n"
        + "Would you like to start a 'new game' or 'previous game'?\n"
        + "(Enter: 'new' or 'previous')\n");
    System.out.print(">");
    do{
      gamePlayParser();
      if(verb.equals("new")){
        startGameFromNew();
      } else if (verb.equals("previous")) {
        startGameFromSaved();
      }else{
        System.out.println("Please enter 'new game' or a 'previous game'.");
      }
    }while(!verb.equals("new") && !verb.equals("previous"));

  }

  /**
   * Starts game from New and asks name
   * @throws IOException
   */
  public void startGameFromNew() throws IOException {
    building = new Building();
    try {
      //Ask if user wants to start new game, continue from previous game

      //Asks name and formats
      System.out.println("What is your name");
      String name = inputParser.readLine().trim();
      String nameTemp;
      while (name.length() < 1) {
        nameTemp = inputParser.readLine().trim();
        name = nameTemp;
      }

      //Updates Player instance name
      building.setName(name);

      //prints startup Info
      System.out.println(userView.startUpInfo(name));

      //run printRoomDescription() here to show current room.
      building.startingRoomDescription();

      //start game loop
      gamePlayCommands();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Starts game from saved (not complete)
   * @throws IOException
   */
  public void startGameFromSaved() throws IOException {
    System.out.println("method hasn't been created yet.");
    startGameFromNew();
  }

  /**
   * Parses user input for game
   */
  public void gamePlayParser() {
//    this parses userInput into 1st word == verb and last word == noun
    try {
      userInputArray = inputParser.readLine().trim().toLowerCase().split(" ", 2);
      verb = userInputArray[0];
      noun = userInputArray[userInputArray.length-1];
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Quit menu parser that gives different options and sends methods.
   * @throws IOException
   */
  public void quitMenu() throws IOException {
//    this prompts user whether they want to quit continue or new game
    System.out.println(
        "Are you sure you would like to quit, save & quit, start a new game or continue your game? \n(Enter: 'quit', 'save quit', 'new game', or 'continue game')");
    while (true) {
      gamePlayParser();
      if (verb.equals("quit")) {
        building.quit();
      } else if (verb.equals("new")) {
        newGame(); //building.newGame();
      } else if(verb.equals("save")){
        building.gameSave();
      }else if (verb.equals("continue")) {
        break;
      }
      System.out.println("Please type 'quit', 'new game' or 'continue game'");
    }
  }

  /**
   * Method for menu to show after player loses game.
   * @throws IOException
   */
  public void gameLoseMenu() throws IOException {
    do {
      System.out.println(
          "Would you like to start a new game or quit? \n"
              + "(Enter: 'new game', 'quit')");
      gamePlayParser();
      if (verb.equals("quit")) {
        building.quit();
      } else if (verb.equals("new")) {
        newGame(); //building.newGame();
      }
    } while (true);
  }

  /**
   * Takes user input to run game
   */
  public void gamePlayCommands() throws IOException {
//this parses commands to controller and view using conditionals
    do {

      try {
        gamePlayParser();
        if (verb.equals("help")) {
          System.out.println(userView.showHelp());
        } else if (verb.equals("quit")) {
          quitMenu();
        } else if (verb.equals("go")) {
          building.moveRooms(noun);
        } else if (verb.equals("get")) {
          building.getItem(noun);
        } else if (verb.equals("interact")) {
          building.interactWithNpc(noun);
        } else if (verb.equals("inspect")){
          building.inspectItem(noun);
        }else if(verb.equals("god")){
          //Type "god mode" will add all items to inventory.
          building.getAllItems(noun);
        }else if(verb.equals("look")){
          building.getRoomDescriptionInfo();
        }else {
          System.out.println(userView.incorrectInput());
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } while (building.gameState == GameState.IN_PROGRESS) ;//building is updating the state

    if (building.gameState == GameState.WIN) {
        System.out.println(userView.showWin());
      } else {
        System.out.println(userView.showLoss());
        gameLoseMenu();
      }

    //State = state.loss
  }
}