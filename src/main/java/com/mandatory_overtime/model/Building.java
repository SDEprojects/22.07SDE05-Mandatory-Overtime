package com.mandatory_overtime.model;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.mandatory_overtime.model.exception.IllegalMoveException;
import com.mandatory_overtime.model.exception.MissingRequirementException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Building class that creates gameboard to track and update state
 */
public class Building {

//  FIELDS

  Player player = new Player();
  public GameState gameState;

  private HashMap<String, Room> building;
  private HashMap<String, Item> items;
  private HashMap<String, Npc> npcs;
  private int npcCount = 0;

// CONSTRUCTOR

  // Constructor to create structures

  /**
   * Class Constructor. Responsible for creating the Java HashMaps from JSON classes. Utilizes
   * load() method to do this.
   *
   * @throws IOException
   */
  public Building() throws IOException {

  }

  public void createGameStructureFromNew() throws IOException {
    Gson gson = new Gson();
    setGameState(GameState.IN_PROGRESS);
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

    npcs = (HashMap<String, Npc>) npcsArray.stream()
        .collect(Collectors.toMap(Npc::getName, npc -> npc));
  }

  public void createGameStructureFromSave() throws IOException {
    Gson gson = new Gson();
    setGameState(GameState.IN_PROGRESS);
    List<Room> rooms = loadSave("RoomStructureSave.json", gson, new TypeToken<ArrayList<Room>>() {
    }.getType());

    List<Item> itemArray = loadSave("ItemStructureSave.json", gson, new TypeToken<ArrayList<Item>>() {
    }.getType());

    List<Npc> npcsArray = loadSave("NPCStructureSave.json", gson, new TypeToken<ArrayList<Npc>>() {
    }.getType());

    //load hashmaps
    building = (HashMap<String, Room>) rooms.stream().collect(
        Collectors.toMap(Room::getDisplayName, room -> room));

    items = (HashMap<String, Item>) itemArray.stream()
        .collect(Collectors.toMap(Item::getName, item -> item));

    npcs = (HashMap<String, Npc>) npcsArray.stream()
        .collect(Collectors.toMap(Npc::getName, npc -> npc));

    //load player
    try (Reader reader = new InputStreamReader(
        getClass().getClassLoader().getResourceAsStream("PlayerSave.json"))) {
      player = gson.fromJson(reader, Player.class);

    }
  }



  /**
   * GameSave method to convert current game structure back to JSON objects to load in next game.
   * (incomplete.) Will run quit() once save is complete.
   *
   * @throws IOException
   */
  public void gameSave() throws IOException {
    //save game

    //get hashmaps of structures//convert back to Lists<>
    List<Room> roomsSvArr = new ArrayList<Room>(building.values());
    List<Item> itemsSvArr = new ArrayList<>(items.values());
    List<Npc> npcsSvArr = new ArrayList<>(npcs.values());

    //convert structures to saved json files
    convertSaveDataToJson(roomsSvArr,"RoomStructureSave.json" );
    convertSaveDataToJson(itemsSvArr, "ItemStructureSave.json");
    convertSaveDataToJson(npcsSvArr, "NPCStructureSave.json");

    //Write Player obj to Json file
    convertSavedPlayerToJson(player, "PlayerSave.json");

    //move created files to resources
//    Files.move()
    System.out.println("Game saved!");
  //Will save game then run quit.
  quit();
  }

  public void convertSavedPlayerToJson(Player currentPlayer, String filename) throws IOException {
    try {
      String filePath = new File(filename).getAbsolutePath();

      Gson gson = new Gson();

      Writer writer = new FileWriter(filePath, false);
      gson.toJson(currentPlayer, writer);

      writer.close();


    } catch (IOException e) {
      e.printStackTrace();
    } catch (JsonIOException e) {
      throw new RuntimeException(e);
    }
  }
  public void convertSaveDataToJson(List<?> structureArr, String filename ){
    try {

      //Write obj to Json file ==================
      // create GSON instance
      Gson gson = new Gson();

      //Create writer that access correct file in jar
      String filePath = new File(filename).getAbsolutePath();
      Writer writer = new FileWriter(filePath, false);

      // convert list to JSON File
      gson.toJson(structureArr, writer);

      // close the writer
      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
//      throw new RuntimeException(e);
    }
  }

//  GETTERS/SETTERS

  public GameState getGameState() {
    return gameState;
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

//  HELPER METHODS

  /**
   * Loads in JSON classes from original JSON files create Java structures
   * @param resourceFile
   * @param gson
   * @param type
   * @param <T>
   * @return
   * @throws IOException
   */
  private <T> T load(String resourceFile, Gson gson, Type type) throws IOException {
    //noinspection ConstantConditions
    try (Reader reader = new InputStreamReader(
        getClass().getClassLoader().getResourceAsStream(resourceFile))) {
      return gson.fromJson(reader, type);
    }
  }

  /**
   * Loads JSON classes from saved data to create Java structures
   * @param resourceFile
   * @param gson
   * @param type
   * @return
   * @param <T>
   * @throws IOException
   */
  private <T> T loadSave(String resourceFile, Gson gson, Type type) throws IOException {
    //noinspection ConstantConditions
    try (Reader reader = new InputStreamReader(
        getClass().getClassLoader().getResourceAsStream(resourceFile))) {
      return gson.fromJson(reader, type);
//Can only use getresourceasstream if data in resource file
      //Buffered reader can replace this.
    }

  }

//  BUSINESS METHODS

  //
  public void newGame() throws IOException {
//    GamePlay restart = new GamePlay();
//    restart.printGameIntroduction();
//    restart.gamePlayCommands();
  }

  /**
   * Ends the game completely
   */
  public void quit() {
    System.exit(0); //Update to utilize state.
  }


  public void moveRooms(String noun)
      throws IOException, IllegalMoveException, InterruptedException {
    String currentLoc = player.getCurrentLocation();
    String[] directions = building.get(currentLoc).getDirections();
    List<String> inventory = new ArrayList<>();
    inventory = player.getInventory();
    String nextRoomPreReq = building.get(noun).getPreReq();
    if (nextRoomPreReq == null || inventory.contains(nextRoomPreReq)) {
      int counter = 0;
      boolean loopStop = true;
      while (loopStop) {
        try {
          for (String direction : directions) {
            if (noun.equals(direction)) {
              player.setCurrentLocation(noun);
              GameMusic.playMoveSound(noun);
              GameMusic.playRoomSound(noun);
              getRoomDescriptionInfo();
              if (player.getInventory().contains(building.get(currentLoc).getPreReq())) {
                GameMusic.playAccessGrantedSound();
                GameMusic.playDoorOpenSound();
              }
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
    } else {
      try {
        winGameCheck(noun);
        GameMusic.playAccessDeniedSound(noun);
        throw new MissingRequirementException(noun);
      } catch (MissingRequirementException e) {
      }
    }
  }

  private void winGameCheck(String noun) {
    boolean wonGame = false;
    String preReqCondition = building.get(noun).getPreReq();
    ArrayList<String> currentItems = (ArrayList<String>) player.getInventory();
    Boolean roomFail = building.get(noun).getFailCondition();
    if (roomFail == true) {
      if (currentItems.contains(preReqCondition)) {
        setGameState(GameState.WIN);
      } else {
        setGameState(GameState.LOSS);
      }
    }
  }


  public void getItem(String item) throws IOException, InterruptedException {
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
          GameMusic.playItemSound();
          items.get(item).setAcquired(true);
          //remove item from list in rooms method
          System.out.println(items.get(item).getPurpose());
          System.out.println(player.getInventory().toString());
        }
      } else {
        checkItemPreReqIsFulfilled(item);
      }
    } else {
      System.out.printf("You cannot get %s.\n", item);
      System.out.print(">");
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

  /**
   * Cheat mode method that allows user to move through game without blocks.
   *
   * @param noun
   */
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
    if (noun.contains("move")) {
      String[] newNoun = noun.split(" ", 2);
      String loc = newNoun[newNoun.length - 1];
      player.setCurrentLocation(loc);
      getRoomDescriptionInfo();
    }

  }

  private void checkItemPreReqIsFulfilled(String item) throws InterruptedException {
    //If prereq in player inventory, add to inventory, remove prereq from inventory, set acquired to true.
    if (player.getInventory().contains(items.get(item).getPreReq())) {
      player.addToInventory(item);
      GameMusic.playDrawerSound();
      GameMusic.playVendingMachineSound();
      GameMusic.playItemSound();
      items.get(item).setAcquired(true);
      player.removeFromInventory(
          items.get(item).getPreReq()); //removes prereq from player inventory

      System.out.println("Added to inventory " + player.getInventory().toString());

    } else {
      System.out.println("You need " + items.get(item).getPreReq() + " to get this item.\n>");


    }
  }

  private void runItemChallenge(String item) throws IOException, InterruptedException {
    while (!player.getInventory().contains(item)) {
      //reads input
      BufferedReader inputParser = new BufferedReader(new InputStreamReader(System.in));
      //print prompt
      System.out.println(items.get(item).getChallengePrompt());
      GameMusic.playTypingSound();
      GameMusic.playPhoneTyping();
      String userAnswer = inputParser.readLine().toLowerCase().trim();
      //if user answer correct, add to inventory. Set item challenge to false. Set acquired to true.
      if (items.get(item).getChallengeAnswer().equals(userAnswer)) {
        player.addToInventory(item);
        items.get(item).setAcquired(true);
        items.get(item).setChallenge(false);
        GameMusic.playPhoneUnlockingSound();
        GameMusic.playItemSound();
        System.out.println(items.get(item).getPurpose());
        System.out.println(player.getInventory().toString());

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
    String npc = building.get(currentLocation).getNPC() == null ? "No one is around"
        : building.get(currentLocation).getNPC();
    String item = building.get(currentLocation).getItem() == null ? "There are no items"
        : building.get(currentLocation).getItem();
    String directions = Arrays.toString(building.get(currentLocation).getDirections());
    String description = building.get(currentLocation).getDescription() == null ? ""
        : building.get(currentLocation).getDescription();

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
    System.out.print(">");
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
        }
      }
    }
  }

  public void setName(String name) {
    player.setName(name);
  }

  public HashMap<String, Room> getBuilding() {
    return building;
  }

}











