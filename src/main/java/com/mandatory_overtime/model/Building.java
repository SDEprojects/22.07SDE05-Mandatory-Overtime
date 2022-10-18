package com.mandatory_overtime.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


//This class is a culmination of the json structures.
public class Building {

//  FIELDS

  //  Creates game state
private GameState gameState = GameState.IN_PROGRESS;

//  Instantiations for the game to Building.
  Player player = new Player();
 private List<Room> rooms;
 private List<Item> items;
 private List<Npc> npcs;


// CONSTRUCTOR

// Constructor to create structures
  public Building() throws IOException {
    Gson gson = new Gson();
    rooms = load("RoomStructure.json", gson, new TypeToken<ArrayList<Room>>(){}.getType());
    items = load("ItemStructure.json", gson, new TypeToken<ArrayList<Item>>(){}.getType());
    npcs = load("NPCStructure.json", gson, new TypeToken<ArrayList<Npc>>(){}.getType());

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
  try(Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(resourceFile))) {
    return gson.fromJson(reader, type);
  }

//  BUSINESS METHODS

}





}
