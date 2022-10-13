package com.mandatory_overtime;

import com.mandatory_overtime.view.UserView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {

  public static void main(String[] args) {

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ) {
//      Instantiate UserView
      UserView userView = new UserView();

//      print startup title info
      System.out.println(userView.startUpTitle());

//      print game info and user name
      System.out.println("What is your name");
      String name = reader.readLine().trim();
      System.out.println(userView.startUpInfo(name));

//    print help instructions
      do {
        if (reader.readLine().trim().equals("help")) {
          System.out.println(userView.showHelp());
        } else {
          break;
        }
      } while (true);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
