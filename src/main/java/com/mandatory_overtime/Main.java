package com.mandatory_overtime;

import com.mandatory_overtime.view.UserView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {

  public static void main(String[] args) {
   try {
     Reader input = new InputStreamReader(System.in);
     BufferedReader reader = new BufferedReader(input);
     UserView view = new UserView();
     System.out.println("What is your name");
     String name = reader.readLine().trim();
     System.out.println(view.startUpInfo(name));
   }catch (IOException e){
     throw new RuntimeException(e);
   }


  }

}
