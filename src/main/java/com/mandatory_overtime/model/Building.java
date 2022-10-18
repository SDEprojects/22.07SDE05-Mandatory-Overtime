package com.mandatory_overtime.model;

import java.util.ArrayList;


//This class is a culmination of the json structures.
public class Building {

//This is a list of the rooms in the building
  RoomJSONReader readRooms = new RoomJSONReader();
  ArrayList<RoomStructure> roomStructure = readRooms.jsonRoomFileReader();


}
