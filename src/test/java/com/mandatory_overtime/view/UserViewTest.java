package com.mandatory_overtime.view;

import static org.junit.Assert.*;
import com.mandatory_overtime.view.UserView;
import org.junit.Test;

public class UserViewTest {

  @Test
  public void startUpInfo() {
  }

  @Test
  public void startUpTitle() {
  }


  @Test
  public void showHelp() {
    UserView help = new UserView();
    String expected =
        "\nGame Commands:\n"
            + "Go <location>  i.e. 'Go Elevator'\n"
            + "Get <item>     i.e. 'Get Phone'\n"
            + "Use <item>     i.e. 'Use Mask'\n"
            + "Interact <NPC> i.e. 'Interact Roy'\n"
            + "Inspect <item> i.e. 'Inspect Sweater'\n"
            + "'Look Around' (this will display the room description again)\n"
            + "'Quit' (to quit the game at any time)\n";
    String actual = help.showHelp();
    assertEquals(expected, actual);
  }

}
