package com.mandatory_overtime.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mandatory_overtime.controller.GamePlay;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


//This class is a culmination of the json structures.
public class Building {

//  FIELDS

  //  Creates game state
  private GameState gameState = GameState.IN_PROGRESS;

  //  Instantiations for the game to Building.
  Player player = new Player();
  private List<Room> rooms;
  private List<Item> items;

  private HashMap<String, Npc> npcs;
  private HashMap<String, Room> building;
  private int npcCount = 0;
  GamePlay gamePlay;

// CONSTRUCTOR

  // Constructor to create structures
  public Building() throws IOException {
    Gson gson = new Gson();
    rooms = load("RoomStructure.json", gson, new TypeToken<ArrayList<Room>>() {
    }.getType());
    items = load("ItemStructure.json", gson, new TypeToken<ArrayList<Item>>() {
    }.getType());
    List<Npc> npcsArray = load("NPCStructure.json", gson, new TypeToken<ArrayList<Npc>>() {
    }.getType());
    npcs = (HashMap<String, Npc>) npcsArray.stream().collect(
        Collectors.toMap(Npc::getName, npc -> npc));
    System.out.println(npcs.get("roy").getDialogueNoItem());

  }


  public void interactWithNpc(String noun) {
    for (String npc : npcs.keySet()) {
      if (npc.equals(noun)) {
        if (!player.getInventory().contains(npcs.get(npc).getPrereq())
            && npcs.get(npc).getNpcCount() == 0) {
          System.out.println(npcs.get(npc).getInitialDialogue());
          npcs.get(npc).getNpcCount();
        } else if (player.getInventory().contains((npcs.get(npc).getPrereq()))) {
          System.out.println((npcs.get(npc).getDialogueWithItem()));
          player.removeFromInventory((npcs.get(npc).getPrereq()));
          player.addToInventory((npcs.get(npc).getItems()));
          npcs.get(npc).setItems(null);
        } else if ((npcs.get(npc).getItems()) == null) {
          System.out.println("Go away youngin... I'm busy and have no more business with you!");
        } else if (!player.getInventory().contains((npcs.get(npc).getPrereq()))
            && npcs.get(npc).getNpcCount() >= 1) {
          System.out.println(npcs.get(npc).getDialogueNoItem());
          player.addToInventory(npcs.get(npc).getPrereq());
        }
      }
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

  //Helper methods for JSON readers
  private <T> T load(String resourceFile, Gson gson, Type type) throws IOException {
    //noinspection ConstantConditions
    try (Reader reader = new InputStreamReader(
        getClass().getClassLoader().getResourceAsStream(resourceFile))) {
      return gson.fromJson(reader, type);
    }

//  BUSINESS METHODS

  }


}
