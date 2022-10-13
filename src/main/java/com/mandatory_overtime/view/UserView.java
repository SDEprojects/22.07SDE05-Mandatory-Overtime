package com.mandatory_overtime.view;


public class UserView {

  public String startUpInfo(){
    return null;
  }

  public String startUpTitle(){
    return null;
  }

  public String showHelp(){

      System.out.println("\nGame Commands:\n"
          + "Go <location>  i.e. 'Go Elevator'\n"
          + "Get <item>     i.e. 'Get Phone'\n"
          + "Use <item>     i.e. 'Use Mask'\n"
          + "Interact <NPC> i.e. 'Interact Roy'\n"
          + "Inspect <item> i.e. 'Inspect Sweater'\n"
          + "'Look Around' (this will display the room description again)\n"
          + "'Quit' (to quit the game at any time)\n");
      return null;
    }



}
