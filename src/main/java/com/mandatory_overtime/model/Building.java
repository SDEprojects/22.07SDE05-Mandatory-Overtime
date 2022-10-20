package com.mandatory_overtime.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mandatory_overtime.controller.GamePlay;
import com.mandatory_overtime.model.exception.IllegalMoveException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Building class that creates gameboard to track and update state
 */
public class Building {

//  FIELDS

  Player player = new Player();
  private boolean gameState;
  private HashMap<String, Room> building;
  private HashMap<String, Item> items;
  private HashMap<String, Npc> npcs;
  private int npcCount = 0;

// CONSTRUCTOR

  // Constructor to create structures
  public Building() throws IOException {
    Gson gson = new Gson();
    setGameState(GameState.IN_PROGRESS.isTerminal());
    List<Room> rooms = load("RoomStructure.json", gson, new TypeToken<ArrayList<Room>>() {
    }.getType());

    List<Item> itemArray = load("ItemStructure.json", gson, new TypeToken<ArrayList<Item>>() {
    }.getType());

    List<Npc> npcsArray = load("NPCStructure.json", gson, new TypeToken<ArrayList<Npc>>() {
    }.getType());

    building = (HashMap<String, Room>) rooms.stream().collect(
        Collectors.toMap(Room::getDisplayName, room -> room));

    items = (HashMap<String, Item>) itemArray.stream()
        .collect(Collectors.toMap(Item::getName, item -> item));
    System.out.println("Item map " + items.get("keyfob").getPreReq());

    npcs = (HashMap<String, Npc>) npcsArray.stream().collect(
        Collectors.toMap(Npc::getName, npc -> npc));

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
  public void quit() {
    System.exit(0); //Update to utilize state.
//    setGameState(GameState.LOSS.isTerminal());
//    System.out.println("Is quit() working?????");
  }


  public void moveRooms(String noun) throws IOException, IllegalMoveException {
    String currentLoc = player.getCurrentLocation();
    String[] directions = building.get(currentLoc).getDirections();
    int counter = 0;
    boolean loopStop = true;
    while(loopStop) {
      try {
        for (String direction : directions) {
          if (noun.equals(direction)) {
            player.setCurrentLocation(noun);
            getRoomDescriptionInfo();
            loopStop = false;
          } else {
            counter++;
          }
        }
        if (counter == directions.length) {
          loopStop = false;
          throw new IllegalMoveException(noun);
        }
      } catch (IllegalMoveException e) {
      }
    }
  }


  public void getItem(String item) throws IOException {
    getRoomDescriptionInfo();
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
          System.out.println(items.get(item).getPurpose());
          System.out.println(player.getInventory().toString());
        }
      } else {
        checkItemPreReqIsFulfilled(item);
      }
    } else {
      System.out.printf("You cannot get %s.\n>", item);
    }
//
//    if (!items.containsKey((item)) && !items.get(item).getLocation().equals(playerCurrentLocation)
//        && !items.get(item).isNpc() == false) {
////      throw new InvalidItemException(String.format("You can't get %s ", item));
//      String.format("You can't get %s ", item);
//    }
//    if (items.get(item).getAcquired() == true) {
////      throw new ItemAcquiredException(String.format("You've already acquired %s ", item));
//      String.format("You've already acquired %s ", item);
//    }
//    if (items.get(item).getPreReq() == null) {
//
//      //conditional to check for challenge
//      if (items.get(item).getChallenge() == true) {
//        runItemChallenge(item);
//      } else {
//        player.addToInventory(item);
//        items.get(item).setAcquired(true);
//        //remove item from list in rooms method
//        System.out.println(player.getInventory().toString());
//      }
//    } else {
//      checkItemPreReqIsFulfilled(item);
//    }

  }

  public void getAllItems(String noun) {
    if (noun.equals("mode")) {
      for (String itemN : items.keySet()) {
        player.addToInventory(itemN);
        items.get(itemN).setAcquired(true);
        items.get(itemN).setChallenge(false);
        items.get(itemN).setNpc(false);
        items.get(itemN).setPreReq(null);
      }
    }

  }

  private void checkItemPreReqIsFulfilled(String item) {
    //If prereq in player inventory, add to inventory, remove prereq from inventory, set acquired to true.
    if (player.getInventory().contains(items.get(item).getPreReq())) {
      player.addToInventory(item);
      items.get(item).setAcquired(true);
      player.removeFromInventory(
          items.get(item).getPreReq()); //removes prereq from player inventory

      System.out.println("Added to inventory " + player.getInventory().toString());

    } else {
      System.out.println("You need " + items.get(item).getPreReq() + " to get this item.\n>");

    }
  }

  private void runItemChallenge(String item) throws IOException {
    while (!player.getInventory().contains(item)) {
      //reads input
      BufferedReader inputParser = new BufferedReader(new InputStreamReader(System.in));
      //print prompt
      System.out.println(items.get(item).getChallengePrompt());
      String userAnswer = inputParser.readLine().toLowerCase().trim();
      //if user answer correct, add to inventory. Set item challenge to false. Set acquired to true.
      if (items.get(item).getChallengeAnswer().equals(userAnswer)) {
        player.addToInventory(item);
        items.get(item).setAcquired(true);
        items.get(item).setChallenge(false);
        System.out.println("challenge won inv " + player.getInventory().toString());
      } else {
        System.out.println("Would you like to try again? Enter 'yes' or 'no'.");
        String userAnswer1 = inputParser.readLine().toLowerCase().trim();
        while (true) {
          if (userAnswer1.equals("yes")) {

            runItemChallenge(item);
            break;
          } else if (userAnswer1.equals("no")) {
            return;
          } else {
            System.out.println("Please enter 'Yes' or 'No'");
            userAnswer1 = inputParser.readLine().toLowerCase().trim();
          }
        }
      }
    }
  }


  public void inspectItem(String item) {
    //checks if item exists, if location is correct, if item is held by NPC
    if (items.containsKey(item) && items.get(item).getLocation().equals(player.getCurrentLocation())
        && items.get(item).isNpc() == false) {
      System.out.println(items.get(item).getPurpose());
    }
  }


  public void getRoomDescriptionInfo() {

    String inventory = player.getInventory().toString();
    String currentLocation = player.getCurrentLocation();
    String npc = building.get(currentLocation).getNPC() == null ? "No one is around" : building.get(currentLocation).getNPC();
    String item = building.get(currentLocation).getItem() == null ? "There are no items" : building.get(currentLocation).getItem();
    String directions = Arrays.toString(building.get(currentLocation).getDirections());
    String description = building.get(currentLocation).getDescription();

    System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
            + "========================================================================================================\n"
            + "Inventory: %1$s \t\t\t\t\t\t\tCurrent Room: %2$S\n"
            + "Items in Room: %3$s\n"
            + "========================================================================================================\n"
            + "%4$s\n"
            + "Coworkers in the room: %5$s\n"
            + "You can go the directions %6$s\n"
            + "You see the item: %3$s\n"
            + "========================================================================================================\n",
        inventory, currentLocation, item, description, npc, directions);

  }


  public void startingRoomDescription() {
    String currentLocation = player.getCurrentLocation();
    String directions = Arrays.toString(building.get(currentLocation).getDirections());
    String item = building.get(currentLocation).getItem();
    System.out.printf(
        "========================================================================================================\n"
            + "You are currently at the %1$S\n"
            + "You see the item, %3$s\n"
            + "You can go the to %2$s\n"
            + "========================================================================================================\n",
        currentLocation, directions, item);
  }

  public void interactWithNpc(String noun) {
    for (String npc : npcs.keySet()) {
      if (npc.equals(noun) && player.getCurrentLocation().equals(npcs.get(npc).getLocation())) {
        if (!player.getInventory().contains(npcs.get(npc).getPrereq())
            && npcs.get(npc).getNpcCount() == 0) {
          System.out.printf(npcs.get(npc).getInitialDialogue(), player.getName());
          npcs.get(npc).getNpcCount();
        } else if (player.getInventory().contains((npcs.get(npc).getPrereq()))) {
          System.out.printf((npcs.get(npc).getDialogueWithItem()), player.getName());
          player.removeFromInventory((npcs.get(npc).getPrereq()));
          player.addToInventory((npcs.get(npc).getItems()));
          npcs.get(npc).setItems(null);
        } else if ((npcs.get(npc).getItems()) == null) {
          System.out.printf((npcs.get(npc).getDialogueQuestDone()), player.getName());
        } else if (!player.getInventory().contains((npcs.get(npc).getPrereq()))
            && npcs.get(npc).getNpcCount() >= 1) {
          System.out.printf(npcs.get(npc).getDialogueNoItem(), player.getName());
//          player.addToInventory(npcs.get(npc).getPrereq());
        }
      }
    }
  }

  public void setName(String name) {
    player.setName(name);
  }


}
