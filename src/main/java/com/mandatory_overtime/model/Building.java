package com.mandatory_overtime.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


//This class is a culmination of the json structures.
public class Building {

  private final HashMap<String, Room> building;


//  FIELDS

  //  Creates game state
private GameState gameState = GameState.IN_PROGRESS;

//  Instantiations for the game to Building.
  Player player = new Player();


// CONSTRUCTOR

// Constructor to create structures
  public Building() throws IOException {
    Gson gson = new Gson();
    List<Room> rooms = load("RoomStructure.json", gson, new TypeToken<ArrayList<Room>>(){}.getType());

    building = (HashMap<String, Room>) rooms.stream().collect(
        Collectors.toMap(Room::getDisplayName, room -> room));
    player.setCurrentLocation("office");
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


}
  //  BUSINESS METHODS
  public void moveRooms(String noun){
    String currentLoc =  player.getCurrentLocation();
    String[] directions = building.get(currentLoc).getDirections();
        for (String direction : directions) {
          if (noun.equals(direction)) {
            player.setCurrentLocation(noun);
            System.out.println("You are now at the" + noun);
          }
        }
  }



}
