package com.mandatory_overtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.mandatory_overtime.view.UserView;
public class Main {

  public static void main(String[] args) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ) {

      do {
        if (reader.readLine().trim().equals("help")) {
          UserView help = new UserView();
          help.showHelp();
        } else {
          break;
        }

      } while (true);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
