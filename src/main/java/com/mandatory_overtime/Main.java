package com.mandatory_overtime;


import com.mandatory_overtime.controller.GamePlay;
import com.mandatory_overtime.model.JSONProcessor;



public class Main {

  public static void main(String[] args) {
    JSONProcessor newobject = new JSONProcessor();
    newobject.jsonFileReader();

    GamePlay gamePlay = new GamePlay();
    gamePlay.newGame();

  }
}
