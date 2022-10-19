package com.mandatory_overtime.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mandatory_overtime.controller.GamePlay;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Building class that creates gameboard to track and update state
 */
public class Building {

//  FIELDS

  //  Instantiations for the game to Building.
  Player player = new Player();
  //  Creates game state
  private boolean gameState;
  private List<Npc> npcs;
  private HashMap<String, Room> building;
  private HashMap<String, Item> items;

// CONSTRUCTOR

  // Constructor to create structures
  public Building() throws IOException {
    Gson gson = new Gson();
    setGameState(GameState.IN_PROGRESS.isTerminal());
    List<Room> rooms = load("RoomStructure.json", gson, new TypeToken<ArrayList<Room>>() {
    }.getType());

    List<Item> itemArray = load("ItemStructure.json", gson, new TypeToken<ArrayList<Item>>() {
    }.getType());

    building = (HashMap<String, Room>) rooms.stream().collect(
        Collectors.toMap(Room::getDisplayName, room -> room));

    items = (HashMap<String, Item>) itemArray.stream()
        .collect(Collectors.toMap(Item::getName, item -> item));
    System.out.println("Item map " + items.get("keyfob").getPreReq());

  }

//  GETTERS/SETTERS

  public boolean getGameState() {
    return gameState;
  }

  public void setGameState(boolean gameState) {
    this.gameState = gameState;
  }

//  HELPER METHODS

  //Helper methods for JSON readers
  private <T> T load(String resourceFile, Gson gson, Type type) throws IOException {
    //noinspection ConstantConditions
    try (Reader reader = new InputStreamReader(
        getClass().getClassLoader().getResourceAsStream(resourceFile))) {
      return gson.fromJson(reader, type);
    }

  }

//  BUSINESS METHODS

  //
  public void newGame() throws IOException {
    GamePlay restart = new GamePlay();
    restart.printGameIntroduction();
    restart.gamePlayCommands();
  }
  //LOSS State ends game
  public void quit(){
    setGameState(GameState.LOSS.isTerminal());
  }


  public void moveRooms(String noun) {
    String currentLoc = player.getCurrentLocation();
    String[] directions = building.get(currentLoc).getDirections();
    for (String direction : directions) {
      if (noun.equals(direction)) {
        player.setCurrentLocation(noun);
        System.out.println("You are now at the" + noun);
      }
    }
  }


  public void getItem(String item) throws IOException {
    String playerCurrentLocation = player.getCurrentLocation();

    //conditional to check if item is in array //check if location correct // check if npc doesn't have it
    if (items.containsKey(item) && items.get(item).getAcquired() == false && items.get(item)
        .getLocation().equals(playerCurrentLocation)
        && items.get(item).isNpc() == false) {

      //conditionals to check it item has prerequisite
      if (items.get(item).getPreReq() == null) {

        //conditional to check for challenge
        if (items.get(item).getChallenge() == true) {
          runItemChallenge(item);
        } else {
          player.addToInventory(item);
          items.get(item).setAcquired(true);
          //remove item from list in rooms method
          System.out.println(player.getInventory().toString());
        }
      } else {
        checkItemPreReqIsFulfilled(item);
      }
    } else {
      System.out.println("Dun cant touch this!");
    }

  }

  private void checkItemPreReqIsFulfilled(String item) {
    if (player.getInventory().contains(items.get(item).getPreReq())) {
      player.addToInventory(item);
      items.get(item).setAcquired(true);
      player.removeFromInventory(
          items.get(item).getPreReq()); //removes prereq from player inventory
      //remove item from list in rooms method.

      System.out.println("Prereq inventory method " + player.getInventory().toString());

    } else {
      System.out.println("You need " + items.get(item).getPreReq() + " to get this item.");

    }
  }

  private void runItemChallenge(String item) throws IOException {

    //reads input
    BufferedReader inputParser = new BufferedReader(new InputStreamReader(System.in));

    //print prompt
    System.out.println(items.get(item).getChallengePrompt());
    String userAnswer = inputParser.readLine().toLowerCase().trim();

    //if user answer correct, add to inventory. Set item challenge to false.
    if (items.get(item).getChallengeAnswer().equals(userAnswer)) {
      player.addToInventory(item);
      items.get(item).setAcquired(true);
      items.get(item).setChallenge(false);
      System.out.println("challenge won inv " + player.getInventory().toString());
    } else {
      System.out.println("Would you like to try again? Enter 'yes' or 'no'.");
      String userAnswer1 = inputParser.readLine().toLowerCase().trim();

      if (userAnswer1.equals("yes")) {
        runItemChallenge(item);

      } else if (userAnswer1.equals("no")) {
        return;
      }
    }

  }


}
