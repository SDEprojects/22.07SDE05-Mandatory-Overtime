package com.mandatory_overtime.model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RoomJSONReader {
  ArrayList<RoomStructure> roomStructureArray = new ArrayList<>();
  public ArrayList<RoomStructure> jsonRoomFileReader(){
    Gson gson = new Gson();
    try (Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("RoomStructure.json")))
    {
      Type roomListType = new TypeToken<ArrayList<RoomStructure>>(){}.getType();
      roomStructureArray = new Gson().fromJson(reader, roomListType);

      return roomStructureArray;

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
   }
    return roomStructureArray;
  }

}