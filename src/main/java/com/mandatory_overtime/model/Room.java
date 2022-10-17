package com.mandatory_overtime.model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Room {

  private HashMap<String, String[]> locationsDirections = new HashMap<>();
  private HashMap<String, String> locationItems = new HashMap<>();
  private HashMap<String, String> locationNPCs = new HashMap<>();
  private HashMap<String, String> locationDisplayName = new HashMap<>();
  private HashMap<String, String> locationPreReqs = new HashMap<>();

  private HashMap<String, Boolean> locationFail = new HashMap<>();

  public void jsonFileReader(){

    JSONParser jsonParser = new JSONParser();

    try (FileReader reader = new FileReader("src/main/resources/RoomStructure.json"))
    {
      //Read JSON file
      Object obj = jsonParser.parse(reader);
      //Makes JSON File into JSON object
      JSONObject jsonObject =(JSONObject) obj;
      //Takes the array of "Rooms" out of JSON object
      JSONArray jsonDataArray = (JSONArray) jsonObject.get("Rooms");

      //calls method that parses json RoomsArray
      parseJsonObjectIntoRooms(jsonDataArray);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  //This method grabs each "location" and direction from the json
  //then makes a key value pair in a hashmap called "locationS"
    private void parseJsonObjectIntoRooms(JSONArray results){

      String locationSpecificName;
      //iterates over each object in the array
      for(int i=0 ; i < results.size(); i++){
//      Collection<String> directions = new ArrayList<String>();

        JSONObject location = (JSONObject) results.get(i);

//      Lines 65-69 get the key (location)
        //returns key SET of object
        Set locationName = location.keySet();
        Object[] locationSpecific = locationName.toArray();
        //gives the String name of the indexed object ie. "elevator" ,"desk", "conference room", etc
        locationSpecificName = (String) locationSpecific[0]; // this should be our key for locations

//     THE following lines get the value (directions)
        //gives location info of a specific object
        JSONObject locationInfo = (JSONObject) location.get(locationSpecificName);
        //get directions,items,NPCS, and roomName from each location
        JSONArray directionsArray = (JSONArray) locationInfo.get("directions");
        String locationItem = (String) locationInfo.get("items");
        String locationNPC = (String) locationInfo.get("NPC");
        String locationDisplay = (String) locationInfo.get("RoomName");
        String locationReqs = (String) locationInfo.get("preReqs");
        Boolean locationFailConditions = (Boolean) locationInfo.get("fail");


        //turns into object[] that can be indexed
        Object[] objDirections = directionsArray.toArray();
        String[] directions = Arrays.asList(objDirections).toArray(new String[objDirections.length]);

        //puts each locations information into seperate hashmaps so we can easily
        locationsDirections.putIfAbsent(locationSpecificName, directions);
        locationItems.putIfAbsent(locationSpecificName,locationItem);
        locationNPCs.putIfAbsent(locationSpecificName,locationNPC);
        locationDisplayName.putIfAbsent(locationSpecificName,locationDisplay);
        locationPreReqs.putIfAbsent(locationSpecificName,locationReqs);
        locationFail.putIfAbsent(locationSpecificName,locationFailConditions);

        //Prints out the first direction and location
//        System.out.println(locationSpecificName);
//        System.out.println(directions[0]);
//        System.out.println(Arrays.toString(new boolean[]{Arrays.stream(directions).anyMatch("5"::equals)}));
        //puts name and directions in locations hashmap

      }
    }
  public HashMap<String, String[]> getLocationsDirections() {
    return locationsDirections;
  }
  public HashMap<String, String> getLocationItems() {
    return locationItems;
  }

  public HashMap<String, String> getLocationNPCs() {
    return locationNPCs;
  }

  public HashMap<String, String> getLocationDisplayName() {
    return locationDisplayName;
  }
  public HashMap<String, String> getLocationPreReqs() {
    return locationPreReqs;
  }

  public HashMap<String, Boolean> getLocationFail() {
    return locationFail;
  }

  // go <direction>

  //method go(String userInput){
//  if matches directionsStream (userInput) from line 79 in JSON processor{
//    currentRoom = locations[userInput];
//  else
//    sorry dog cant go that way
//  return null;

  }