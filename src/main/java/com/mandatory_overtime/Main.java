package com.mandatory_overtime;


import com.mandatory_overtime.model.Room;
import java.util.HashMap;


public class Main {

  public static void main(String[] args) {
//
//    GamePlay gamePlay = new GamePlay();
//    gamePlay.newGame();
    Room objects = new Room();
    objects.jsonFileReader();
    HashMap<String, String[]> directions = objects.getLocationsDirections();

    HashMap<String, String> items = objects.getLocationItems();

    HashMap<String, String> NPC = objects.getLocationNPCs();

    HashMap<String, String> display = objects.getLocationDisplayName();

    String[] elevatorDirections = directions.get("elevator");

    System.out.println(elevatorDirections[3]);
    System.out.println(items.get("desk"));
    System.out.println(NPC);
    System.out.println(display.get("5"));

  }
}
