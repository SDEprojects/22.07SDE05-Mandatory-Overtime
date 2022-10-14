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

  public final BufferedReader inputParser = new BufferedReader(new InputStreamReader(System.in));
  public String[] userInputArray;

  UserView userView = new UserView();

  public void printGameIntroduction() {
    try {
      //      Instantiate UserView

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
//This method is parsing the user's input taking first word and assigning
// it to verb and the last word assigning to noun
  public void gamePlayParser() {
    String verb;
    String noun;
    try {
      do {
        userInputArray = inputParser.readLine().trim().split(" ");
        verb = userInputArray[0];
        noun = userInputArray[userInputArray.length -1];
//        System.out.println(Arrays.toString(userInputArray));
//        System.out.println(verb + noun);
        if (verb.equals("help")) {
          System.out.println(userView.showHelp());
        } else if (verb.equals("new")){
            newGame();
        }else {
          System.out.println(userView.incorrectInput() + userView.showHelp());
        }
      } while (true);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public void newGame(){
    GamePlay restart = new GamePlay();
    restart.printGameIntroduction();
    restart.gamePlayParser();
  }
}
