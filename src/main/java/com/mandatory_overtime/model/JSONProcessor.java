package com.mandatory_overtime.model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONProcessor {

  private HashMap<Set, String[]> locations;
  private String[] items;
  private String[] NPCs;
  private String[] directions;

  public void jsonFileReader(){

    JSONParser jsonParser = new JSONParser();

    try (FileReader reader = new FileReader("src/main/resources/GameDataStructure.json"))
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

    //iterates over each object in the array
    for(int i=0 ; i < results.size(); i++){
        JSONObject location = (JSONObject) results.get(i);
        //returns key SET of object
        Set locationName = location.keySet();
        String[] locationSpecific = (String[]) locationName.toArray();

        System.out.println(locationSpecific[0]);


//        JSONObject locationInfo = (JSONObject) location.get(locationName);
//        System.out.println(locationInfo);
//        System.out.println(key.get("directions"));
      }

    }





}
