package com.mandatory_overtime.model;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TEST CLASS FOR SAVE AND LOAD GAME
 */
public class GameSave {


  private <T> T load(String resourceFile, Gson gson, Type type) throws IOException {
    //noinspection ConstantConditions
    try (Reader reader = new InputStreamReader(
        getClass().getClassLoader().getResourceAsStream(resourceFile))) {
      return gson.fromJson(reader, type);
    }


  }

  Item item = new Item();
//  Writer writer = Files.?/

}
