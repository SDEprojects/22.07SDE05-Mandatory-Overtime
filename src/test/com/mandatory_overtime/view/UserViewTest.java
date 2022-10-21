package com.mandatory_overtime.view;

import static org.junit.Assert.*;;
import org.junit.Assert;
import org.junit.Test;

public class UserViewTest {

//  @Test
//  public void startUpInfo_nameDisplaysWithInfo() {
//    UserView view = new UserView();
//    String name = "carl";
//
//
//    String expected = "Hello carl, \n"
//        + "You are a developer for Amazon and it's Halloween night\n"
//        + "You have had a long week at the office, but you are diving head first into the weekend\n"
//        + "because there's a rooftop Halloween Party tonight!\n\n"
//        + "You wake up from slobbering on your desk. You wipe off the cheetos dust from your cheek.\n"
//        + "While you're looking at the same error in your code from the last 2 weeks,\n"
//        + "your coworker Pete tells you that he has been hearing rumors of Mandatory Overtime.\n\n"
//        + "You know that it's your turn to be On-Call this weekend.\n"
//        + "Finish your work and escape the building with your things\n"
//        + "before your manager, Scott, finds you and you miss the party tonight.\n";
//
//    String actual = view.startUpInfo(name);
//
//    assertEquals(expected, actual);
//
//  }

//  @Test
//  public void startUpTitle() {
//    UserView showTitle = new UserView();
//    String actual = showTitle.startUpTitle();
//    String pumpkin = "\t\t\t\t\t\t\t\t\t\t\t────────────────████\n\t\t\t\t\t\t\t\t\t\t\t───────────────█░░███\n\t\t\t\t\t\t\t\t\t\t\t───────────────█░░████\n\t\t\t\t\t\t\t\t\t\t\t────────────────███▒██─────████████\n\t\t\t\t\t\t\t\t\t\t\t──────████████─────█▒█──████▒▒▒▒▒▒████\n\t\t\t\t\t\t\t\t\t\t\t────███▒▒▒▒▒▒████████████░░████▒▒▒▒▒███\n\t\t\t\t\t\t\t\t\t\t\t──██▒▒▒▒░▒▒████░░██░░░░██░░░░░█▒▒▒▒▒▒▒███\n\t\t\t\t\t\t\t\t\t\t\t─██▒▒░░░░▒██░░░░░█▒░░░░░██▒░░░░░░░▒▒▒▒▒▒█\n\t\t\t\t\t\t\t\t\t\t\t██▒░░░░░▒░░░░░░░░░▒░░░░░░░▒▒░░░░░░░▒▒▒▒▒██\n\t\t\t\t\t\t\t\t\t\t\t█░░░░░░▒░░░██░░░░░░░░░░░░░██░░░░░░░░▒▒▒▒▒█\n\t\t\t\t\t\t\t\t\t\t\t█░░░░░░░░█▒▒███░░░░░░░░░█▒▒███░░░░░░░▒▒▒▒█\n\t\t\t\t\t\t\t\t\t\t\t█░░░░░░░████████░░░░░░░████████░░░░░░▒▒▒▒█\n\t\t\t\t\t\t\t\t\t\t\t█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒█\n\t\t\t\t\t\t\t\t\t\t\t─█░░░░██░█░░░░░░░░░░░░░░░░░░░░░░░███▒▒▒▒▒█\n\t\t\t\t\t\t\t\t\t\t\t─█▒▒░░░░█████░░░█░░░░██░░░██░░████░▒▒▒▒▒▒█\n\t\t\t\t\t\t\t\t\t\t\t─██▒▒░░░░░█████████████████████░░░▒▒▒▒▒▒██\n\t\t\t\t\t\t\t\t\t\t\t──██▒▒▒▒░░░░░██░░░███░░░██░░░█░░░▒▒▒▒▒▒██\n\t\t\t\t\t\t\t\t\t\t\t───███▒▒▒░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒█████\n\t\t\t\t\t\t\t\t\t\t\t─────███▒▒▒▒▒▒░░░░░░░░░░░░░▒▒▒▒▒▒████\n\t\t\t\t\t\t\t\t\t\t\t────────██████████████████████████\n";
//    String title = "\n▒█▀▄▀█ █▀▀█ █▀▀▄ █▀▀▄ █▀▀█ ▀▀█▀▀ █▀▀█ █▀▀█ █░░█ 　 ▒█▀▀▀█ ▀█░█▀ █▀▀ █▀▀█ ▀▀█▀▀ ░▀░ █▀▄▀█ █▀▀\n▒█▒█▒█ █▄▄█ █░░█ █░░█ █▄▄█ ░░█░░ █░░█ █▄▄▀ █▄▄█ 　 ▒█░░▒█ ░█▄█░ █▀▀ █▄▄▀ ░░█░░ ▀█▀ █░▀░█ █▀▀\n▒█░░▒█ ▀░░▀ ▀░░▀ ▀▀▀░ ▀░░▀ ░░▀░░ ▀▀▀▀ ▀░▀▀ ▄▄▄█ 　 ▒█▄▄▄█ ░░▀░░ ▀▀▀ ▀░▀▀ ░░▀░░ ▀▀▀ ▀░░░▀ ▀▀▀\n--------------------------------------------------------------------------------------------";
//    String summary = "\n\nIt's Halloween night in the office and the halls echo with rumours of mandatory overtime.\nEscape before your manager finds you to make it to your Halloween Party!";
//    String expected = pumpkin + title + summary;
//    Assert.assertEquals(expected, actual);
//
//  }
//
//  @Test
//  public void showHelp() {
//    UserView help = new UserView();
//    String expected =
//        "\nGame Commands:\n"
//            + "Go <location>  i.e. 'Go Elevator'\n"
//            + "Get <item>     i.e. 'Get Phone'\n"
//            + "Use <item>     i.e. 'Use Mask'\n"
//            + "Interact <NPC> i.e. 'Interact Roy'\n"
//            + "Inspect <item> i.e. 'Inspect Sweater'\n"
//            + "'Look Around' (this will display the room description again)\n"
//            + "'Quit' (to quit the game at any time)\n";
//    String actual = help.showHelp();
//    assertEquals(expected, actual);
//  }
}

